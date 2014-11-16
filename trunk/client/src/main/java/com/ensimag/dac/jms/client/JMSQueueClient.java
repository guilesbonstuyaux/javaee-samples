package com.ensimag.dac.jms.client;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;

import com.ensimag.dac.message.JMSMessage;

public class JMSQueueClient {
    public static void main(String[] args) throws Exception {
        JMSQueueClient client = new JMSQueueClient();

        // init initial context
		Properties props = new Properties();
		props.put("org.omg.CORBA.ORBInitialHost", "localhost");
		props.put("org.omg.CORBA.ORBInitialPort", "3700");

        Context initialContext = new InitialContext(props);

        JMSMessage myMessage = new JMSMessage("Guillaume", "Renault");
        client.publish(myMessage, initialContext);
        System.out.println("Wainting 1s ...");
        Thread.sleep(1000);
        client.consume(initialContext);
    }

    public void publish(JMSMessage p_Message, Context p_Ctx) throws Exception {
        System.out.println("start publishing ...");
        ConnectionFactory cnxFactory = (ConnectionFactory) p_Ctx.lookup("java:comp/DefaultJMSConnectionFactory");
        Connection cnx = cnxFactory.createConnection();
        Session queueSession = cnx.createSession(false,
                Session.AUTO_ACKNOWLEDGE);

        cnx.start();

        Destination dest = (Queue) p_Ctx.lookup("DAC_JMSSampleQueue");

        MessageProducer producer = queueSession.createProducer(dest);

        Message myMessage = queueSession.createObjectMessage(p_Message);

        producer.send(myMessage);
        producer.close();

        cnx.close();
    }

    public void consume(Context p_Ctx) throws Exception {
        System.out.println("start consumming ...");
        ConnectionFactory cnxFactory = (ConnectionFactory) p_Ctx.lookup("java:comp/DefaultJMSConnectionFactory");
        Connection cnx = cnxFactory.createConnection();
        Session queueSession = cnx.createSession(false,
                Session.AUTO_ACKNOWLEDGE);

        cnx.start();

        Destination dest = (Queue) p_Ctx.lookup("DAC_JMSSampleQueue");

        MessageConsumer consummer = queueSession.createConsumer(dest);
        Message receivedMessage = consummer.receive();

        JMSMessage myMessage = (JMSMessage) ((ObjectMessage) receivedMessage)
                .getObject();

        System.out.println("message received : " + myMessage.firstName + "  "
                + myMessage.lastName);

        cnx.close();
    }

}
