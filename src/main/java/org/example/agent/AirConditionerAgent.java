package org.example.agent;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.Random;

public class AirConditionerAgent extends Agent {
    private Random random = new Random();

    protected void setup() {
        System.out.println("AirConditionerAgent " + getAID().getLocalName() + " is ready.");

        addBehaviour(new TickerBehaviour(this, 5000) { // every 5 seconds
            protected void onTick() {
                int sensorValue = random.nextInt(100); // Random sensor value between 0 and 99
                System.out.println("Sensor value: " + sensorValue);

                if (sensorValue < 20 || sensorValue > 80) { // Assuming abnormal values are below 20 or above 80
                    System.out.println("Abnormal sensor value detected: " + sensorValue);
                    ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                    msg.addReceiver(getAID("DFAgent"));
                    msg.setContent("AirConditionerFailure");
                    send(msg);
                }
            }
        });
    }
}
