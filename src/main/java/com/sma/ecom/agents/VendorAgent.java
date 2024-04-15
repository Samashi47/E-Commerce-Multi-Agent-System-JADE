package com.sma.ecom.agents;

import com.sma.ecom.entities.Product;
import com.sma.ecom.helper.FactoryProvider;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.wrapper.ControllerException;
import com.sma.ecom.entities.ProductDAO;

public class VendorAgent extends Agent {
    @Override
    protected void setup() {
        System.out.println("Création et l'initialisation de l'agent "+this.getAID().getName());

        ParallelBehaviour parallelBehaviour = new ParallelBehaviour();
        addBehaviour(parallelBehaviour);
        parallelBehaviour.addSubBehaviour(new CyclicBehaviour(){

            @Override
            public void action() {
                MessageTemplate messageTemplate = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL), MessageTemplate.MatchOntology("Consumer"));
                ACLMessage aclMessage = receive(messageTemplate);
                if(aclMessage!=null){
                    //traitement
                    Product produit=null;
                    String id_produit = aclMessage.getUserDefinedParameter("id_produit");
                    try {
                        ProductDAO productDAO = new ProductDAO(FactoryProvider.getFactory());
                        produit=productDAO.GetProductByID(Integer.parseInt(id_produit));
                        System.out.println(id_produit);

                    } catch (Exception e) {e.printStackTrace();}

                    //send message
                    ACLMessage reponse = aclMessage.createReply();
                    try {
                        reponse.addUserDefinedParameter("verifier", produit.getName());
                        reponse.setContent("transaction réussie");
                        reponse.setPerformative(ACLMessage.CONFIRM);
                    } catch (Exception e) {
                        reponse.setContent("cette article n'existe pas désormais");
                        reponse.setPerformative(ACLMessage.FAILURE);
                    }
                    reponse.setOntology("Consumer");
                    send(reponse);
                }else{
                    block();
                }
            }

        });
    }

    @Override
    protected void beforeMove() {
        try {
            System.out.println("Avant migration de l'agent "+this.getAID().getName());
            System.out.println("de "+this.getContainerController().getContainerName());
        } catch (ControllerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected void afterMove() {
        try {
            System.out.println("Apres migration de l'agent "+this.getAID().getName());
            System.out.println("vers "+this.getContainerController().getContainerName());
        } catch (ControllerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected void takeDown() {
        System.out.println("L'agent "+this.getAID().getName()+" va mourir");
        try {
            DFService.deregister(this);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
    }

}
