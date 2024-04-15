package com.sma.ecom.servlets;

import com.sma.ecom.entities.Product;
import com.sma.ecom.entities.ProductDAO;
import com.sma.ecom.helper.FactoryProvider;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.wrapper.ControllerException;
import jade.wrapper.gateway.JadeGateway;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class BuyServlet extends HttpServlet {
    public BuyServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirect to the JSP page
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String str;
        while ((str = reader.readLine()) != null) {
            sb.append(str);
        }
        String json = sb.toString();
        System.out.println(json);
        Pattern pattern = Pattern.compile("\"productName\":\"(.*?)\"");
        Matcher matcher = pattern.matcher(json);
        String productName;
        if (matcher.find()) {
            productName = matcher.group(1);
            System.out.println(productName);  // Outputs: hamid
            if (!productName.equals("")) {
            ProductDAO productDAO = new ProductDAO(FactoryProvider.getFactory());
            ArrayList<Product> product = productDAO.FindProductByName(productName);
            String product1 = String.valueOf(product.get(0).getId());
                RequestDispatcher dispatcher = request.getRequestDispatcher("complete.jsp");
                try {
                    System.out.println("product name: " + productName);
                    JadeGateway.execute(new OneShotBehaviour() {
                        public void action() {
                            final ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                            msg.addReceiver(new AID("Consumer", false));
                            msg.setConversationId("buy-product");
                            msg.setContent(product1);
                            myAgent.send(msg);
                        }
                    });
                    dispatcher.forward(request, response);
                } catch (ServletException | IOException | ControllerException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    System.out.println("product name: " + productName);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
                    JadeGateway.execute(new OneShotBehaviour() {
                        public void action() {
                            final ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                            msg.addReceiver(new AID("Consumer", false));
                            msg.setConversationId("buy-product");
                            msg.setContent("No products");
                            myAgent.send(msg);
                        }
                    });
                dispatcher.forward(request, response);
            } catch (ServletException | IOException | ControllerException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
