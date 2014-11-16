package com.ensimag.dac.ejb.mdb;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ensimag.dac.message.JMSMessage;

@MessageDriven(mappedName = "DAC_JMSSampleMDBQueue", activationConfig = {@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")})
public class MyMDBExample implements MessageListener {

    private static final Logger s_LOGGER = LoggerFactory.getLogger(MyMDBExample.class);

    @Resource
    private MessageDrivenContext mdc;

    public void onMessage(final Message p_InMessage) {
        try {
            if (p_InMessage instanceof ObjectMessage) {
                JMSMessage myMessage = (JMSMessage) ((ObjectMessage) p_InMessage)
                        .getObject();
                MyMDBExample.s_LOGGER
                        .info(
                                "Received message from Queue. The message was : {} {}",
                                myMessage.firstName, myMessage.lastName);
            } else {
                MyMDBExample.s_LOGGER
                        .info(
                                "Received an unknown message. MDB context was {}\nwith the destination {}",
                                this.mdc.toString(), p_InMessage
                                .getJMSDestination());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
