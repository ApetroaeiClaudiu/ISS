import com.claudiu.proto.client.IServer;
import com.claudiu.proto.client.ProtoProxy;

import java.io.IOException;
import java.util.Properties;

public class Client {
    private static int defaultPort=55560;
    private static String defaultServer="localhost";

    public static void main(String[] args) {

        Properties clientProps=new Properties();
        try {
            clientProps.load(Client.class.getResourceAsStream("/client.properties"));
            clientProps.list(System.out);
        } catch (IOException e) {
            System.err.println("Cannot find client.properties "+e);
            return;
        }
        String serverIP=clientProps.getProperty("server.host",defaultServer);
        int serverPort=defaultPort;
        try{
            serverPort=Integer.parseInt(clientProps.getProperty("server.port"));
        }catch(NumberFormatException ex){
            System.err.println("Wrong port number "+ex.getMessage());
            System.out.println("Using default port: "+defaultPort);
        }

        IServer server=new ProtoProxy(serverIP, serverPort);
            ClientCtrl ctrl=new ClientCtrl(server);


        LoginWindow logWin=new LoginWindow("Login Window", ctrl);
        logWin.setSize(400,400);
        logWin.setLocation(150,150);
        logWin.setVisible(true);

    }
}
