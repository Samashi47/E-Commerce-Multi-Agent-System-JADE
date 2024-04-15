package com.sma.ecom.containers;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;

public class IntermediateContainer {
    public static void main(String[] args) {
        try {
            Runtime runtime = Runtime.instance();
            Profile profile = new ProfileImpl(false);
            profile.setParameter(Profile.MAIN_HOST, "localhost");
            AgentContainer agentContainer = runtime.createAgentContainer(profile);
            AgentController agentController = agentContainer.
                    createNewAgent("Intermediate", "com.sma.ecom.agents.IntermediateAgent", new Object[]{});
            agentController.start();
        } catch (ControllerException e) {
            e.printStackTrace();
        }

    }
}
