package test;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class MachineAgent extends Agent {
    protected void setup() {
        // Servis tanımı oluştur
        ServiceDescription sd = new ServiceDescription();
        sd.setType("Machine");
        sd.setName(getLocalName() + "-Machine");
        
        // DF'e kayıt
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        dfd.addServices(sd);
        try {
            DFService.register(this, dfd);
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }
    }

    protected void takeDown() {
        // DF'den kayıt sil
        try {
            DFService.deregister(this);
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }
    }
}
