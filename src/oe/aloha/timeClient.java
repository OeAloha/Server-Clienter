package oe.aloha;
import java.io.DataInputStream;
import java.net.Socket;


public class timeClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1337);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            System.out.println("Dato fra server: " + input.readUTF());

        } catch (Exception e) {
            e.printStackTrace();

        }

    }
}