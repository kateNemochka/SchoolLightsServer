package com.katenemochka.schoollights.controller.rest;

import com.katenemochka.schoollights.component.mqtt.MqttPublisher;
import com.katenemochka.schoollights.component.mqtt.MqttSpringClient;
import com.katenemochka.schoollights.service.PeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mqtt")
public class RestMqttController {

    @Autowired
    private MqttSpringClient mqttSpringClient;
    @Autowired
    private MqttPublisher mqttPublisher;
    @Autowired
    private PeriodService periodService;

    @RequestMapping("/setPeriod/{period}")
    public void setPeriod(@PathVariable("period") String periodName) {
        mqttPublisher.publishPeriod(periodService.getPeriodByName(periodName));
    }

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
