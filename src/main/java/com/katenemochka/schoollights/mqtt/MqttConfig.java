package com.katenemochka.schoollights.mqtt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * @Classname MqttConfig
 * @Description mqtt Related configuration information
 * @Date 2020/3/5 11:00
 * @Created by bam
 */
@Component
@Setter
@Getter
public class MqttConfig {
    @Autowired
    private MqttSpringClient mqttSpringClient;

    @Value("${mqtt.username}")
    private String username;

    @Value("${mqtt.password}")
    private String password;

    @Value("${mqtt.host-url}")
    private String hostUrl;

    @Value("${mqtt.clientID}")
    private String clientID;

    @Value("${mqtt.default-topic}")
    private String defaultTopic;

    @Value("${mqtt.timeout}")
    private int timeout;

    @Value("${mqtt.keepalive}")
    private int keepalive;

    @Bean
    public MqttSpringClient getMqttSpringClient() {
        System.out.println("hostUrl: "+ hostUrl);
        System.out.println("clientID: "+ clientID);
        System.out.println("username: "+ username);
        System.out.println("password: "+ password);
        System.out.println("timeout: "+timeout);
        System.out.println("keepalive: "+ keepalive);
        mqttSpringClient.connect(hostUrl, clientID, username, password, timeout, keepalive);
        // End with / / to subscribe to all topics starting with test
        mqttSpringClient.subscribe(defaultTopic, 0);
        return mqttSpringClient;
    }
}
