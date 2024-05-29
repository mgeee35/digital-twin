package org.example.agent;

import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class MachineAgent extends Agent {
    protected void setup() {
        System.out.println("MachineAgent " + getAID().getLocalName() + " is ready.");

        addBehaviour(new jade.core.behaviours.CyclicBehaviour() {
            public void action() {
                MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
                ACLMessage msg = receive(mt);
                if (msg != null) {
                    String content = msg.getContent();
                    if (content.equals("StopMachine")) {
                        System.out.println("Received stop request. Stopping the machine.");
                        // Implement machine stop logic here
                    }
                } else {
                    block();
                }
            }
        });
    }
}
