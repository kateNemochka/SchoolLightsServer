package com.katenemochka.schoollights.component.mqtt;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.katenemochka.schoollights.domain.Room;
import com.katenemochka.schoollights.domain.Zone;
import com.katenemochka.schoollights.domain.types.Period;
import com.katenemochka.schoollights.domain.types.ZoneType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;

@Component
public class MqttPublisher {

    @Autowired
    MqttSpringClient mqttSpringClient;
    JSONObject payload;

    public MqttPublisher(MqttSpringClient mqttSpringClient) {
        this.mqttSpringClient = mqttSpringClient;
    }

    /*MQTT_TOPIC_PERIOD = "school/period"*/
    public void publishPeriod(Period period) {
        String topic = "school/period";
        payload = new JSONObject();
        payload.put("period", period.getName().toLowerCase());
        payload.put("date", LocalDateTime.now().toLocalDate().toString());

        System.out.println("Publishing MQTT message to topic " + topic + ": " + payload.toString());
        mqttSpringClient.publish(0, true, topic, payload.toString());
    }

    /*MQTT_TOPIC_MODE = "school/floor-2/room-207/mode"*/
    public void publishZoneMode(Zone zone) {
        int floor = zone.getRoom().getFloor();
        String room = zone.getRoom().getName();
        String topic = "school/floor-" + floor + "/room-" + room + "/mode";

        payload = new JSONObject();
        payload.put("mode", zone.getMode().getName().toLowerCase());
        payload.put("date", LocalDateTime.now().toLocalDate().toString());
        payload.put("cust_dim", zone.getDimmerValue());
        payload.put("mode_time", zone.getModeTimeout());

        mqttSpringClient.publish(1, true, topic, payload.toString());
    }

    /*MQTT_TOPIC_CONFIG = "school/floor-2/room-207/config"*/
    public void publishRoomConfig(Room room) {
        int floor = room.getFloor();
        String roomName = room.getName();
        String topic = "school/floor-" + floor + "/room-" + roomName + "/config";

        Zone zone = room.getZones().get(0);
        ZoneType zoneType = zone.getZoneType();

        payload = new JSONObject();
        payload.put("room", roomName);
        payload.put("period", room.getPeriod().getName().toLowerCase());
        payload.put("mode", zone.getMode().getName());
        payload.put("zone", zone.getId());
        payload.put("row_number", zone.getRowList().get(0).getRowNumberFromWindow());
        payload.put("room_type", room.getPurpose());
        payload.put("sens_upd", 60);
        payload.put("stat_upd", 180);
        payload.put("cust_dim", zone.getDimmerValue());
        payload.put("min_lux", zoneType.getLightMinimum());
        payload.put("delta_lux", zoneType.getLightMaximum() - zoneType.getLightMinimum());

        payload.put("detect_int", new JSONArray(Collections.singletonList(
                zoneType.getDetectionIntervalsSorted())));
        payload.put("timeout", new JSONArray(Collections.singletonList(
                zoneType.getLightTimeoutsSorted())));
        payload.put("date", LocalDateTime.now().toLocalDate().toString());

        mqttSpringClient.publish(1, true, topic, payload.toString());
    }

    /*MQTT_TOPIC_ROOMTYPE = "school/"; //+roomType*/
    public void publishZoneTypeUpdate(ZoneType zoneType) {
        //TO-DO
    }
}
