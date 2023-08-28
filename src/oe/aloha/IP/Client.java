package oe.aloha.IP;

import java.net.Socket;
import java.util.Scanner;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.UnknownHostException;

public class Client {
	public static void main(String[] args) throws IOException, UnknownHostException {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Indtast et domænenavn: ");
		String fqdn = scanner.nextLine();
		scanner.close();
		Socket socket = new Socket("localhost", 8080);
		DataInputStream input = new DataInputStream(socket.getInputStream());
		DataOutputStream output = new DataOutputStream(socket.getOutputStream());
		output.writeUTF(fqdn);
		String response = input.readUTF();
		System.out.println("Response from Server: " + response);
		socket.close();
	}
}
