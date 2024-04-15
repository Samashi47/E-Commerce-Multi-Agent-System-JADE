package com.sma.ecom.servlets;

import com.sma.ecom.entities.Product;
import com.sma.ecom.entities.ProductDAO;
import com.sma.ecom.helper.FactoryProvider;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.wrapper.gateway.JadeGateway;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class SearchServlet extends HttpServlet {
    public SearchServlet() {
        super();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String product = request.getParameter("product");
        ProductDAO productDAO = new ProductDAO(FactoryProvider.getFactory());
        ArrayList<Product> products = productDAO.FindProductByName(product);
        request.setAttribute("products", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("displayProducts.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
        try {
            JadeGateway.execute(new OneShotBehaviour() {
                public void action() {
                    final ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
                    AID client1 = new AID("Consumer", false);
                    msg.addReceiver(client1);
                    msg.setConversationId("search-setup");
                    msg.setContent(product);
                    myAgent.send(msg);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}

