package com.meetup;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.Future;

/**
 * Created by Sunny on 09-04-2016.
 */
@Component
public class Producer {

    //This can be used to get a random number to publish a message to particular partition
    private static Integer partitionNumber = 6;
    private static Random random= new Random();

    @Value("${bootstrap.servers}")
    private String bootstrapServer;

    @Value("${topic.name}")
    private String topicName;

    @Async
    public void produce(){
        int value = 0;
        int price = 100;
        Order order= null;

        //create the properties for kafka consumer
        Properties properties = new Properties();
        //A list of host/port pairs to use for establishing the initial connection to the Kafka cluster
        properties.put("bootstrap.servers", bootstrapServer);
        //Serializer class for key that implements the Serializer interface
        properties.put("key.serializer", StringSerializer.class.getCanonicalName());
        //Serializer class for key that implements the Serializer interface
        properties.put("value.serializer", OrderSerializer.class.getCanonicalName());
        //The number of acknowledgments the producer requires the leader to have received before considering a request complete.
        //properties.put("acks", 1);
        //The producer will attempt to batch records together into fewer requests whenever multiple records are being sent to the same partition.
        //properties.put("batch.size", 12345);

        properties.put("partitioner.class",CustomPartitioner.class);
        KafkaProducer producer = new KafkaProducer(properties);

        while(true) {
            try {
                //lets create new order randomly assign city randomly
                int randomNumber=random.nextInt(partitionNumber);
                if(randomNumber%2 == 0){
                    order = new Order("ItemName" + value,price,"Pune");
                }else{
                    order = new Order("ItemName" + value,price,"Mumbai");
                }
                // Below are there different ways for producing records
                //ProducerRecord producerRecord = new ProducerRecord<>("NewOrder", partition , "key", order);
                //ProducerRecord producerRecord = new ProducerRecord<>("NewOrder", order);
                ProducerRecord producerRecord = new ProducerRecord<>(topicName , order.getCity(), order);

                Future<RecordMetadata> recordMetadata = producer.send(producerRecord);
                //Below statement will hold up until it gets response for record send
                RecordMetadata r = recordMetadata.get();
                System.out.println(" Generated order for city : "+order.getCity());
                //Increment the name and value for next record
                value = value + 1;
                price=price+1;
                //put a wait before producing the new records, just for to see the behavior
                Thread.currentThread().sleep(2000);
            } catch (Exception exception) {
                System.out.println("Exception.. Retrying to publish to the queue directly for topic ");
            }
        }
    }
}
