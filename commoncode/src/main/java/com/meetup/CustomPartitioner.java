package com.meetup;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Created by sunny on 4/16/16.
 */
public class CustomPartitioner implements Partitioner {
    @Override
    public int partition(String s, Object o, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {
        String city = new String(bytes);
        if("Mumbai".equalsIgnoreCase(city)){
            return 0;
        }else if("Pune".equalsIgnoreCase(city)){
            return 1;
        }
        return 0;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
