package oe.aloha;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class timeServer {
    public static void main(String[] args) {


        try {

            ServerSocket server = new ServerSocket(1337);

            System.out.println("Serveren forbundet");

            Socket socket = server.accept();

            System.out.println("Serveren har modtaget en forbindelse fra " + socket.getRemoteSocketAddress().toString());

                DataOutputStream out = new DataOutputStream
                        (socket.getOutputStream());
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                out.writeUTF(now.format(formatter));

        } catch (IOException e) {
            System.out.println("Serveren er fucked!");
            e.printStackTrace();
        }
    }
}
