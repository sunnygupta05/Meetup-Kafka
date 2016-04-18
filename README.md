# Meetup-Kafka
Application code for demo at Clairvoyant Pune Kafka Meetup

Overview of Demo :-

1) Producer is producing Order in NewOrder Topic.
2) Two consumer Groups "AccountGroup" and "ShipmentGroup", will consumer from NewOrder Topic.
3) AccountGroup is having only one Consumer where as ShipmentGroup is having 2 Consumers.

Please note, To run the application, Please provide path of commonconfig for all the application.

Run.txt file contains few of the commands needed to create and admin the kafka topic.
