package silat.servicios_negocio;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Hashtable;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.naming.Context;

public class ServerDetails {
    private static MBeanServerConnection connection;
    private static JMXConnector connector;
    private static final ObjectName service;

    static {
        try {
            service =
                    new ObjectName("com.bea:Name=DomainRuntimeService,Type=weblogic.management.mbeanservers.domainruntime.DomainRuntimeServiceMBean");
        } catch (MalformedObjectNameException e) {
            throw new AssertionError(e.getMessage());
        }
    }

    public static void initConnection(String hostname, String portString, String username,
                                      String password) throws IOException, MalformedURLException {
        System.out.println("ServerDetails---Started in initConnection");
        String protocol = "t3";
        Integer portInteger = Integer.valueOf(portString);
        int port = portInteger.intValue();
        String jndiroot = "/jndi/";
        String mserver = "weblogic.management.mbeanservers.domainruntime";
        JMXServiceURL serviceURL = new JMXServiceURL(protocol, hostname, port, jndiroot + mserver);
        Hashtable h = new Hashtable();
        h.put(Context.SECURITY_PRINCIPAL, username);
        h.put(Context.SECURITY_CREDENTIALS, password);
        h.put(JMXConnectorFactory.PROTOCOL_PROVIDER_PACKAGES, "weblogic.management.remote");
        connector = JMXConnectorFactory.connect(serviceURL, h);
        connection = connector.getMBeanServerConnection();
    }

    public static ObjectName[] getServerRuntimes() throws Exception {
        return (ObjectName[])connection.getAttribute(service, "ServerRuntimes");
    }

    public void printServerDetails() throws Exception {
        ObjectName[] serverRT = getServerRuntimes();
        for (int i = 0; i < serverRT.length; i++) {
            String name = (String)connection.getAttribute(serverRT[i], "Name");
            String listenAddress = (String)connection.getAttribute(serverRT[i], "ListenAddress");
            Integer listenPort = (Integer)connection.getAttribute(serverRT[i], "ListenPort");
            System.out.println("Server Name : " + name + "\t Address: " + listenAddress + "\t Port: " + listenPort);
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("ServerDetails---Started");
        String hostname = "dflores";//args[0];
        String portString = "7101";//args[1];
        String username = "weblogic";//args[2];
        String password = "666sic666";//args[3];
        System.out.println("Hostname : " + hostname);
        System.out.println("PortString : " + portString);
        System.out.println("Username : " + username);
        System.out.println("Password : " + password); 
        ServerDetails sd = new ServerDetails();
        initConnection(hostname, portString, username, password);
        sd.printServerDetails();
        connector.close();
    }
}