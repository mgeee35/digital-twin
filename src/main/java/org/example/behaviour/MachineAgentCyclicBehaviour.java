package org.example.behaviour;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class MachineAgentCyclicBehaviour extends CyclicBehaviour {
    private Agent agent;

    public MachineAgentCyclicBehaviour(Agent agent) {
        this.agent = agent;
    }

    @Override
    public void action() {
        MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
        ACLMessage msg = agent.receive(mt);
        if (msg != null) {
            String content = msg.getContent();
            if (content.equals("StopMachine")) {
                System.out.println("Received stop request. Stopping the machine.");
                // Implement machine stop logic here
            }
            if (content.equals("StartMachine")) {
                System.out.println("Received start request. Starting the machine. It is online again!");
                // Implement machine start logic here
            }
        } else {
            block();
        }
    }
}
