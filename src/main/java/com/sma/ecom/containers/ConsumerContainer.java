package com.sma.ecom.containers;

import jade.core.AID;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;

public class ConsumerContainer {

    public static void main(String[] args) {
        try {
            Runtime runtime = Runtime.instance();
            Profile profile = new ProfileImpl(false);
            profile.setParameter(Profile.MAIN_HOST, "localhost");
            AgentContainer agentContainer = runtime.createAgentContainer(profile);
            AgentController agentController = agentContainer.createNewAgent("Consumer", "com.sma.ecom.agents.ConsumerAgent", new Object[]{});
            agentController.start();
        } catch (ControllerException e) {
            e.printStackTrace();
        }
    }
}
