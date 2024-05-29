package org.example.behaviour;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.Random;

public class AirConditionerTickerBehaviour extends TickerBehaviour {

    private Random random = new Random();
    private Agent agent;
    private int temp = 0;
    private boolean working = true;

    public AirConditionerTickerBehaviour(Agent a, long period) {
        super(a, period);
        agent = a;
    }

    @Override
    protected void onTick() {
        if (working) {
            temp += random.nextInt(-1, 4);
        } else {
            temp += random.nextInt(-4, 1);
        }

        System.out.println("Temperature value: " + temp);

        if (working && temp > 20) {
            working = false;
            System.out.println("Abnormal temperature value detected: " + temp);
            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            msg.addReceiver(agent.getAID("DFAgent"));
            msg.setContent("AirConditionerFailure");
            agent.send(msg);
        } else if (!working && temp < 20) {
            working = true;
            System.out.println("Normal temperature value is back: " + temp);
            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            msg.addReceiver(agent.getAID("DFAgent"));
            msg.setContent("AirConditionerOnlineAgain");
            agent.send(msg);
        }
    }
}
