package com.ensimag.dac.ejb.mdb;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;

import com.ensimag.dac.message.JMSMessage;

@MessageDriven(mappedName = "sampleQueue", activationConfig = {@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")})
public class MyMDBExample implements MessageListener {

    private static final Log s_LOGGER = LogFactory.getLog(MyMDBExample.class);

    @Resource
    private MessageDrivenContext mdc;

    public void onMessage(final Message p_InMessage) {
        try {
            if (p_InMessage instanceof ObjectMessage) {
                JMSMessage myMessage = (JMSMessage) ((ObjectMessage) p_InMessage)
                        .getObject();
                MyMDBExample.s_LOGGER
                        .info(
                                "Received message from Queue. The message was : {0} {1}",
                                myMessage.firstName, myMessage.lastName);
            } else {
                MyMDBExample.s_LOGGER
                        .info(
                                "Received an unknown message. MDB context was {0}\nwith the destination {1}",
                                this.mdc.toString(), p_InMessage
                                .getJMSDestination());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
