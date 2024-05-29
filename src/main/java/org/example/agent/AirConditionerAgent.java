package org.example.agent;

import jade.core.Agent;
import org.example.behaviour.AirConditionerTickerBehaviour;

public class AirConditionerAgent extends Agent {

    protected void setup() {
        System.out.println("AirConditionerAgent " + getAID().getLocalName() + " is ready.");
        addBehaviour(new AirConditionerTickerBehaviour(this, 1000));
    }
}