package org.example.agent;

import jade.core.Agent;
import org.example.behaviour.DFAgentCyclicBehaviour;

public class DFAgent extends Agent {
    protected void setup() {
        System.out.println("DFAgent " + getAID().getLocalName() + " is ready.");
        addBehaviour(new DFAgentCyclicBehaviour(this));
    }
}