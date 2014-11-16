package com.ensimag.dac.jms.client;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.InitialContext;

import com.ensimag.dac.message.JMSMessage;

public class JMSTopicClient {
    public static void main(String[] args) throws Exception {
        JMSTopicClient client = new JMSTopicClient();

        // init initial context
        Properties props = new Properties();
		props.put("org.omg.CORBA.ORBInitialHost", "localhost");
		props.put("org.omg.CORBA.ORBInitialPort", "3700");

        Context initialContext = new InitialContext(props);

        JMSMessage myMessage = new JMSMessage("Guillaume", "Renault");

        client.subscribe(initialContext);

        System.out.println("Wainting 1s ...");
        Thread.sleep(1000);

        client.publish(myMessage, initialContext);

        System.out.println("Waiting 5s before closing subcriber's connection ...");
        Thread.sleep(5000);

        System.exit(0);

    }

    public void publish(JMSMessage p_Message, Context p_Ctx) throws Exception {
        System.out.println("start publishing ...");
        ConnectionFactory cnxFactory = (ConnectionFactory) p_Ctx.lookup("java:comp/DefaultJMSConnectionFactory");
        Connection cnx = cnxFactory.createConnection();
        Session topicSession = cnx.createSession(false,
                Session.AUTO_ACKNOWLEDGE);

        cnx.start();

        Destination dest = (Topic) p_Ctx.lookup("DAC_JMSSampleTopic");

        MessageProducer producer = topicSession.createProducer(dest);

        Message myMessage = topicSession.createObjectMessage(p_Message);

        producer.send(myMessage);
        producer.close();

        cnx.close();
    }

    public void subscribe(Context p_Ctx) throws Exception {
        System.out.println("start subscriber ...");
        ConnectionFactory cnxFactory = (ConnectionFactory) p_Ctx.lookup("java:comp/DefaultJMSConnectionFactory");
        Connection cnx = cnxFactory.createConnection();
        Session topicSession = cnx.createSession(false,
                Session.AUTO_ACKNOWLEDGE);

        cnx.start();

        Destination dest = (Topic) p_Ctx.lookup("DAC_JMSSampleTopic");

        MessageConsumer consumer = topicSession.createConsumer(dest);
        consumer.setMessageListener(new MyTopicListener());


//		cnx.close();
    }

    /**
     * JMS Listener.
     *
     * @author Guillaume Renault
     */
    class MyTopicListener implements MessageListener {


        public void onMessage(Message p_Message) {

            try {
                JMSMessage myMessage = (JMSMessage) ((ObjectMessage) p_Message)
                        .getObject();
                System.out.println("message received : " + myMessage.firstName
                        + "  " + myMessage.lastName);
            } catch (JMSException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

}
