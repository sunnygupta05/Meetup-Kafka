package com.meetup;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * Created by Sunny on 12-04-2016.
 */
public class OrderSerializer implements Serializer,Deserializer {

    @Override
    public Object deserialize(String topic, byte[] data) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(data, Order.class);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("problem with object deserialization ");
        }
        return new Object();
    }

    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, Object data) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(data).getBytes();
        } catch (JsonProcessingException e) {
            System.out.println("problem with object serialization ");
        }
        return "".getBytes();
    }

    @Override
    public void close() {

    }
}
