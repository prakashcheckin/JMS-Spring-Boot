package com.project2.jms.project2.sender;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Created by jt on 2019-07-17.
 */

@Component
public class HelloSender {

    @Scheduled(fixedRate = 2000)
    public void sendMessage() throws NamingException {

        InitialContext initialContext = new InitialContext();
        Queue queue = (Queue) initialContext.lookup("queue/myQueue");

        try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
            JMSContext context = cf.createContext())
        {
            context.createProducer().send( queue, "Arise Awake and stop until the goal is reached");
            System.out.println("Message sent>>");
        }

    }

}
