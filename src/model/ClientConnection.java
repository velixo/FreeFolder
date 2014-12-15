package model;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class ClientConnection {
	private ObjectOutputStream outputStream = null;
	private Socket socket;
	private String ipAddress;
	private int port = 9876;
	
	private int connectionAttempts;
	private byte[] fileData;
	private boolean isConnected;
	
	public ClientConnection(String ip) {
		isConnected = false;
		connectionAttempts = 0;
	}
	
	public void connect() {
		while(connectionAttempts < 5) {
			try {
				socket = new Socket(ipAddress, port);
				outputStream = new ObjectOutputStream(socket.getOutputStream());
				isConnected = true;
			} catch (IOException e) {
				e.printStackTrace();
				connectionAttempts++;
			}
		}
		System.out.println("Connection to " + ipAddress + ":" + port + " failed after 5 tries");
	}
	
	public void checkFilesUpdated(List<File> files) {
		
	}
	
	public void syncFiles(List<File> files) {
		
	}
}
