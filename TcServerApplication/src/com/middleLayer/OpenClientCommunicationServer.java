package com.middleLayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class OpenClientCommunicationServer {

	public OpenClientCommunicationServer() 
	{
		int portNumber = 8005;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(portNumber);
			Socket clientSocket = serverSocket.accept();
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			//Receive the data which sent from AR Client
			//Send data with the same port
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
