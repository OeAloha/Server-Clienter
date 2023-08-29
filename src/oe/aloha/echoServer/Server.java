package oe.aloha.echoServer;

import javax.xml.crypto.Data;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serversocket = new ServerSocket(2001);
        Socket socket = serversocket.accept();
        System.out.println("Connection estabilished " + socket.getRemoteSocketAddress().toString());

        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        while (true) {
            String response = in.readUTF();
            System.out.println("Client: " + response);
            out.writeUTF("Server: " + response);
            out.flush();
        }
    }
}
