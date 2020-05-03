package com.prakash.jms.jmssenderreceiver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;


/*
* JMS configuration
* */
@Configuration
public class JmsConfig {

    /*
     * All Queue name are hardcoded here.Once the ActiveMq broker is up and running.
     * Spring boot will automatically create these queues in the broker
     * */
    public final static String MY_QUEUE = "asyncRattingQueue";
    public static final String MY_SEND_RCV_QUEUE = "replybacktome";

    /*
    * messageConverter helps to convert the incoming string data into properJSONObject.
    * So that Json can convert to java Object
    * */
    @Bean
    public MessageConverter messageConverter (){
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

}
