package com.katenemochka.schoollights.controller.rest;

import com.katenemochka.schoollights.component.mqtt.MqttSpringClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 *
 * @author bam
 * 2020 March 5th 2013
 * TestController.java
 *
 */
@RestController
@RequestMapping("/")
public class TestController {

    @Autowired
    private MqttSpringClient mqttSpringClient;

    @GetMapping(value = "/publishTopic")
    public String publishTopic() {
        String topicString = "school/test";
        mqttSpringClient.publish(0, false, topicString, "Test posting");
        return "ok";
    }
    // Send custom message content (using default theme)
    @RequestMapping("/publishTopic/{data}")
    public void test1(@PathVariable("data") String data) {
        String topicString = "test";
        mqttSpringClient.publish(0,false, topicString, data);
        //return "ok";
    }

    // Send custom message content and specify subject
    @RequestMapping("/publishTopic/{topic}/{data}")
    public void test2(@PathVariable("topic") String topic, @PathVariable("data") String data) {
        mqttSpringClient.publish(0,false, topic, data);
        //return "ok";
    }
}
