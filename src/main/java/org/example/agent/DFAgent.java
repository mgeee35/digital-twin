package org.example.agent;

import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class DFAgent extends Agent {
    protected void setup() {
        System.out.println("DFAgent " + getAID().getLocalName() + " is ready.");

        addBehaviour(new jade.core.behaviours.CyclicBehaviour() {
            public void action() {
                MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
                ACLMessage msg = receive(mt);
                if (msg != null) {
                    String content = msg.getContent();
                    if (content.equals("AirConditionerFailure")) {
                        System.out.println("Received air conditioner failure alert.");
                        ACLMessage stopMsg = new ACLMessage(ACLMessage.REQUEST);
                        stopMsg.addReceiver(getAID("MachineAgent"));
                        stopMsg.setContent("StopMachine");
                        send(stopMsg);
                    }
                } else {
                    block();
                }
            }
        });
    }
}
