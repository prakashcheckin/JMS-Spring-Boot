package com.prakash.jms.jmssenderreceiver.sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prakash.jms.jmssenderreceiver.config.JmsConfig;
import com.prakash.jms.jmssenderreceiver.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.UUID;

/**
 * Only sends the message to respective queue.
 * Send and forget pattern
 */
@RequiredArgsConstructor
@Component
public class HelloSender {

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

    //@Scheduled(fixedRate = 2000)
    public void sendMessage(){

        HelloWorldMessage message = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("Hello World!")
                .build();

        jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE, message);

    }

    /**
     * It Will send the message and wait for the response message.
     * Send and receive pattern
     */
    @Scheduled(fixedRate = 2000)
    public void sendandReceiveMessage() throws JMSException {

        HelloWorldMessage message = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("Hello, How are you Prakash !!!")
                .build();

        Message receviedMsg = jmsTemplate.sendAndReceive(JmsConfig.MY_SEND_RCV_QUEUE, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message helloMessage = null;

                try {
                    helloMessage = session.createTextMessage(objectMapper.writeValueAsString(message));
                    helloMessage.setStringProperty("_type", "com.prakash.jms.jmssenderreceiver.model.HelloWorldMessage");

                    System.out.println(">> Sending message >>");

                    return helloMessage;

                } catch (JsonProcessingException e) {
                   throw new JMSException("boom");
                }
            }
        });

        System.out.println("<< Acknowledge received << " + receviedMsg.getBody(String.class));

    }

}
