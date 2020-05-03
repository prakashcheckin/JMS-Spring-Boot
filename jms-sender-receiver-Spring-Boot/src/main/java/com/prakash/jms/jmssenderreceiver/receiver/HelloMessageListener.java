package com.prakash.jms.jmssenderreceiver.receiver;

import com.prakash.jms.jmssenderreceiver.config.JmsConfig;
import com.prakash.jms.jmssenderreceiver.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.UUID;

/**
 * Created by jt on 2019-07-17.
 */
@RequiredArgsConstructor
@Component
public class HelloMessageListener {

    private final JmsTemplate jmsTemplate;

    /*
    * This listener will only get the data
    */
    @JmsListener(destination = JmsConfig.MY_QUEUE)
    public void listen(@Payload HelloWorldMessage helloWorldMessage,
                       @Headers MessageHeaders headers, Message message){

        System.out.println("I Got a Message!!!!!");
        /*This runtime exception to check, if listener throw any exception, sender will resend the same message continuously to make that delivery success */
       // throw new RuntimeException("foo");
    }


    /*
    * This listener will receive the data and give response back in the same queue - message.getJMSReplyTo()
    **/
    @JmsListener(destination = JmsConfig.MY_SEND_RCV_QUEUE)
    public void listenForHello(@Payload HelloWorldMessage helloWorldMessage,
                               @Headers MessageHeaders headers, Message message) throws JMSException {

        System.out.println("<< Message Received <<  " + helloWorldMessage);

        HelloWorldMessage payloadMsg = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("Message Received")
                .build();

        System.out.println(">> Sending Received Acknowledgement >>");
        jmsTemplate.convertAndSend(message.getJMSReplyTo(), payloadMsg);

    }

}
