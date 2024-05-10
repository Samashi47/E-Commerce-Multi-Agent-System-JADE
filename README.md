# E-Commerce-Multi-Agent-System-JADE
This project is a multi-agent system for e-commerce using JADE (Java Agent Development Framework). The system is composed of three types of agents: vendors, consumers, and a mediator. The system allows consumers to search for products, view product details, and buy products. Sellers can add products, view products, and view orders. The mediator is responsible for managing the communication between consumers and sellers. The system uses a blackboard architecture to store product information and orders. The system also uses a JSP web interface to interact with the agents.
## Prerequisites
- Java 1.8
- Apache Tomcat 9
- IntelliJ IDEA
- JADE (Java Agent Development Framework)
- MySQL
## Getting Started
Clone the repository
```bash
git clone https://github.com/Samashi47/E-Commerce-Multi-Agent-System-JADE.git
```
## To run the project
1. Start MySQL and create a database named `store-sma`.
2. Run the project by running a Tomcat server configuration in IntelliJ IDEA.
3. Start the main container by running the MainContainer class.
4. start the mediator agent by running the IntermediateContainer class.
5. start the vendor agent by running the VendorContainer class.
6. Open a web browser and go to http://localhost:8080/
7. You can now interact with the agents using the web interface.

## To view the agents
1. After running the project, you can view the agents by going to the JADE GUI which should already be started after running the main container.
2. You can view the agents and their details in the JADE GUI.
3. Sniff the agents to view the messages being sent between the agents.