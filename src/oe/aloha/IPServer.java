package oe.aloha;

import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

public class IPServer {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(8080);
		System.out.println("Server is listening on port 8080");
		new Thread(() -> {
			try {
				while (true) {
					Socket socket = serverSocket.accept();
					DataInputStream input = new DataInputStream(socket.getInputStream());
					DataOutputStream output = new DataOutputStream(socket.getOutputStream());
					String fqdn = input.readUTF();
					InetAddress[] records = InetAddress.getAllByName(fqdn);
					String response = "";
					for (int i = 0; i < records.length; i++) {
						response += records[i].getHostAddress();
						if (i + 1 < records.length) {
							response += ", ";
						}
					}
					if (response.equals("")) {
						response = "No address found for " + fqdn;
					}
					output.writeUTF(response);
					socket.close();
				}
			} catch (SocketException e) {
				System.out.println("Server is shutting down");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();
		Scanner scanner = new Scanner(System.in);
		while (true) {
			String message = scanner.nextLine();
			if (message.equals("exit")) {
				break;
			}
		}
		scanner.close();
		serverSocket.close();
	}
}