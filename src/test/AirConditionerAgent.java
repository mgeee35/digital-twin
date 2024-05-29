package test;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

import java.util.Random;

public class AirConditionerAgent extends Agent {
    protected void setup() {
        // Servis tanımı oluştur
        ServiceDescription sd = new ServiceDescription();
        sd.setType("Air-Conditioner");
        sd.setName(getLocalName() + "-Air-Conditioner");
        
        // DF'e kayıt
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        dfd.addServices(sd);
        try {
            DFService.register(this, dfd);
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }
        
        // Davranış tanımı
        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                // Sensör verisi al
                double temperature = generateRandomValue(15.0, 30.0);
                double pressure = generateRandomValue(900.0, 1100.0);

                // Anormallik kontrolü
                if (temperature > 25.0) {
                    // DF'den makine agentlerini bul
                    DFAgentDescription template = new DFAgentDescription();
                    ServiceDescription sd = new ServiceDescription();
                    sd.setType("Machine");
                    template.addServices(sd);
                    try {
                        DFAgentDescription[] result = DFService.search(myAgent, template);
                        if (result.length > 0) {
                            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                            msg.setContent("Air conditioner anomaly detected. Stop working.");
                            for (int i = 0; i < result.length; ++i) {
                                msg.addReceiver(result[i].getName());
                            }
                            send(msg);
                        }
                    } catch (FIPAException fe) {
                        fe.printStackTrace();
                    }
                }
            }
        });
    }

    protected void takeDown() {
        // DF'den kayıt sil
        try {
            DFService.deregister(this);
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }
    }

    private double generateRandomValue(double min, double max) {
        Random rand = new Random();
        return min + (max - min) * rand.nextDouble();
    }
}