package org.example;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
import jade.wrapper.AgentController;

public class Main {
    public static void main(String[] args) {
        // Initialize the JADE runtime
        jade.core.Runtime runtime = jade.core.Runtime.instance();

        // Create the main container
        Profile profile = new ProfileImpl();
        profile.setParameter(Profile.GUI, "true");
        ContainerController mainContainer = runtime.createMainContainer(profile);

        try {
            // Launch the agents
            AgentController acAgent = mainContainer.createNewAgent("AirConditionerAgent", "org.example.agent.AirConditionerAgent", null);
            acAgent.start();
            AgentController mAgent = mainContainer.createNewAgent("MachineAgent", "org.example.agent.MachineAgent", null);
            mAgent.start();
            AgentController dfAgent = mainContainer.createNewAgent("DFAgent", "org.example.agent.DFAgent", null);
            dfAgent.start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }
}
