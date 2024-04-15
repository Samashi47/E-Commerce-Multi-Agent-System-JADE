package com.sma.ecom.agents;

import com.sma.ecom.entities.Product;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import jade.wrapper.ControllerException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsumerAgent extends Agent{

    List<AID> services;

    public List<AID> chercherServices(Agent agent,String type){
        List<AID> services=new ArrayList<>();
        DFAgentDescription agentDescription=new DFAgentDescription();
        ServiceDescription serviceDescription=new ServiceDescription();
        serviceDescription.setType(type);
        agentDescription.addServices(serviceDescription);
        try {
            DFAgentDescription[] descriptions=DFService.search(agent, agentDescription);
            for(DFAgentDescription dfad:descriptions){
                services.add(dfad.getName());
            }
        } catch (FIPAException e) {
            e.printStackTrace();
        }
        return services;
    }
    public void sendRequest(String produit){
        ACLMessage aclMessage = new ACLMessage(ACLMessage.REQUEST);
        for(AID aid:services){
            aclMessage.addReceiver(aid);
        }
        aclMessage.setContent(produit);
        aclMessage.setOntology("Consumer");
        send(aclMessage);
    }
    public void sendConfirmation(String id_produit){
        ACLMessage aclMessage1 = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
        aclMessage1.addReceiver(new AID("Vendor",AID.ISLOCALNAME));
        aclMessage1.setContent("acheter");
        aclMessage1.setOntology("Consumer");
        aclMessage1.addUserDefinedParameter("id_produit",id_produit);
        send(aclMessage1);
    }
    @Override
    protected void setup() {
        System.out.println("Création et l'initialisation de l'agent "+this.getAID().getName());
        ConsumerAgent agent = this;
        ParallelBehaviour parallelBehaviour = new ParallelBehaviour();
        addBehaviour(parallelBehaviour);
        parallelBehaviour.addSubBehaviour(new CyclicBehaviour(){
            @Override
            public void action() {
                MessageTemplate messageTemplate = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.PROPOSE), MessageTemplate.MatchOntology("Consumer"));
                ACLMessage aclMessage = receive(messageTemplate);
                MessageTemplate messageTemplate1 = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.CONFIRM), MessageTemplate.MatchOntology("Consumer"))
                        .or(MessageTemplate.MatchPerformative(ACLMessage.FAILURE), MessageTemplate.MatchOntology("Consumer"));
                ACLMessage aclMessage1 = receive(messageTemplate1);
                MessageTemplate mt = MessageTemplate.MatchConversationId("search-setup");
                ACLMessage msg = myAgent.receive(mt);
                MessageTemplate mt1 = MessageTemplate.MatchConversationId("buy-product");
                ACLMessage msg2 = myAgent.receive(mt1);
                if (msg2 != null && msg2.getPerformative() == ACLMessage.INFORM){
                    String productID = msg2.getContent();
                    if(!productID.equals("No products"))
                        sendConfirmation(productID);
                    else
                        sendConfirmation(productID);
                } else block();
                if (msg != null) {
                    if (msg.getPerformative() == ACLMessage.REQUEST) {
                        String product = msg.getContent();
                        System.out.println("Agent " + myAgent.getAID().getLocalName() + " Content = " + product);
                        sendRequest(product);
                    }
                } else {
                    block();
                }
                if(aclMessage!=null){
                    try {
                        ArrayList<Product> products = (ArrayList<Product>)aclMessage.getContentObject();
                    } catch (UnreadableException ex) {
                        Logger.getLogger(ConsumerAgent.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else if(aclMessage1!=null){
                    System.out.println("Purchase complete: " +(aclMessage1.getPerformative() == ACLMessage.CONFIRM));
                }
                else{
                    block();
                }
            }
        });
        parallelBehaviour.addSubBehaviour(new TickerBehaviour(this,2000) {
            @Override
            protected void onTick() {
                services = chercherServices(agent, "ecommerce-vendeur");                    }
        });
    }

    @Override
    protected void beforeMove() {
        try {
            System.out.println("Avant migration de l'agent "+this.getAID().getName());
            System.out.println("de "+this.getContainerController().getContainerName());
        } catch (ControllerException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void afterMove() {
        try {
            System.out.println("Apres migration de l'agent "+this.getAID().getName());
            System.out.println("vers "+this.getContainerController().getContainerName());
        } catch (ControllerException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void takeDown() {
        System.out.println("L'agent "+this.getAID().getName()+" va mourir");
    }
}

