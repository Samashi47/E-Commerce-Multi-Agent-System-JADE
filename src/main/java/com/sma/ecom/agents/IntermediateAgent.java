package com.sma.ecom.agents;

import com.sma.ecom.entities.Product;
import com.sma.ecom.helper.FactoryProvider;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.wrapper.ControllerException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.sma.ecom.entities.ProductDAO;

public class IntermediateAgent extends Agent {
    @Override
    protected void setup() {
        System.out.println("Création et l'initialisation de l'agent " + this.getAID().getName());
        ParallelBehaviour parallelBehaviour = new ParallelBehaviour();
        addBehaviour(parallelBehaviour);
        parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                MessageTemplate messageTemplate = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.REQUEST), MessageTemplate.MatchOntology("Consumer"));
                ACLMessage aclMessage = receive(messageTemplate);
                if (aclMessage != null) {
                    //traitement
                    Product produit = null;
                    ArrayList<Product> liste = null;
                    String nom_produit = aclMessage.getContent();
                    try {
                        ProductDAO produitDAO = new ProductDAO(FactoryProvider.getFactory());
                        System.out.println(nom_produit);
                        liste = produitDAO.FindProductByName(nom_produit);// recuperer le produit depuit la base de donnee
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //send message
                    ACLMessage reponse = aclMessage.createReply();
                    reponse.setPerformative(ACLMessage.PROPOSE);
//					reponse.addReceiver(new AID("client1",AID.ISLOCALNAME));
                    try {
                        reponse.setContentObject(liste);
                        reponse.setOntology("Consumer");
                        send(reponse);
                    } catch (IOException ex) {
                        Logger.getLogger(IntermediateAgent.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    block();
                }
            }
        });

        Agent myagent = this;
        parallelBehaviour.addSubBehaviour(new OneShotBehaviour(this) {
            @Override
            public void action() {
                System.out.println("Publication du service dans Directory Facilitator...");
                DFAgentDescription agentDescription = new DFAgentDescription();
                agentDescription.setName(myagent.getAID());
                ServiceDescription serviceDescription = new ServiceDescription();
                serviceDescription.setType("ecommerce-vendeur");
                serviceDescription.setName("Vendor");
                agentDescription.addServices(serviceDescription);
                try {
                    DFService.register(myagent, agentDescription);
                } catch (FIPAException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void beforeMove() {
        try {
            System.out.println("Avant migration de l'agent " + this.getAID().getName());
            System.out.println("de " + this.getContainerController().getContainerName());
        } catch (ControllerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected void afterMove() {
        try {
            System.out.println("Apres migration de l'agent " + this.getAID().getName());
            System.out.println("vers " + this.getContainerController().getContainerName());
        } catch (ControllerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected void takeDown() {
        System.out.println("L'agent " + this.getAID().getName() + " va mourir");
    }
}
