package org.example.behaviour;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class DFAgentCyclicBehaviour extends CyclicBehaviour {
    private Agent agent;

    public DFAgentCyclicBehaviour(Agent agent) {
        this.agent = agent;
    }

    @Override
    public void action() {
        MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
        ACLMessage msg = agent.receive(mt);
        if (msg != null) {
            String content = msg.getContent();
            if (content.equals("AirConditionerFailure")) {
                System.out.println("Received air conditioner failure alert.");
                ACLMessage stopMsg = new ACLMessage(ACLMessage.REQUEST);
                stopMsg.addReceiver(agent.getAID("MachineAgent"));
                stopMsg.setContent("StopMachine");
                agent.send(stopMsg);
            }
            if (content.equals("AirConditionerOnlineAgain")) {
                System.out.println("Received air conditioner online back message.");
                ACLMessage stopMsg = new ACLMessage(ACLMessage.REQUEST);
                stopMsg.addReceiver(agent.getAID("MachineAgent"));
                stopMsg.setContent("StartMachine");
                agent.send(stopMsg);
            }
        } else {
            block();
        }
    }
}
