package com.ensimag.dac.jms.client;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.ow2.carol.jndi.spi.MultiOrbInitialContextFactory;

import com.ensimag.dac.message.JMSMessage;

public class JMSMDBClient {
    public static void main(String[] args) throws Exception {
        JMSMDBClient client = new JMSMDBClient();

        // init initial context
        Properties props = new Properties();
        props.put(Context.PROVIDER_URL, "rmi://localhost:1099");
        props.put(Context.INITIAL_CONTEXT_FACTORY,
                MultiOrbInitialContextFactory.class.getName());

        Context initialContext = new InitialContext(props);

        JMSMessage myMessage = new JMSMessage("Guillaume", "Renault");
        client.publish(myMessage, initialContext);

    }

    public void publish(JMSMessage p_Message, Context p_Ctx) throws Exception {
        System.out.println("start publishing ...");
        ConnectionFactory cnxFactory = (ConnectionFactory) p_Ctx.lookup("JQCF");
        Connection cnx = cnxFactory.createConnection();
        Session queueSession = cnx.createSession(false,
                Session.AUTO_ACKNOWLEDGE);

        cnx.start();

        Destination dest = (Queue) p_Ctx.lookup("sampleQueue");

        MessageProducer producer = queueSession.createProducer(dest);

        Message myMessage = queueSession.createObjectMessage(p_Message);

        producer.send(myMessage);
        producer.close();

        cnx.close();
    }


}
