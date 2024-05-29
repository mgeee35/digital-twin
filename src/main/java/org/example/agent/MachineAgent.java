package org.example.agent;

import jade.core.Agent;
import org.example.behaviour.MachineAgentCyclicBehaviour;

public class MachineAgent extends Agent {
    protected void setup() {
        System.out.println("MachineAgent " + getAID().getLocalName() + " is ready.");
        addBehaviour(new MachineAgentCyclicBehaviour(this));
    }
}
