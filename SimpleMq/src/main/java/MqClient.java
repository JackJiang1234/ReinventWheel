import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class MqClient {
    public static void produce(String message) throws Exception {
        try (Socket socket = new Socket(InetAddress.getLocalHost(), BrokerServer.SERVICE_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream())) {
            out.println(message);
            out.flush();
        }
    }

    public static String consume() throws Exception {
        try (Socket socket = new Socket(InetAddress.getLocalHost(), BrokerServer.SERVICE_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream());
        ) {
            out.println("CONSUME");
            out.flush();
            String message = in.readLine();
            return message;
        }
    }
}