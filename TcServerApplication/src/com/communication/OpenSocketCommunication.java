package com.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.properties.LoginProperties;

public class OpenSocketCommunication {
	
	private ServerSocket serverSocket = new ServerSocket(35010);
	//private PrintWriter out;
	private ArrayList<LoginProperties> Output = new ArrayList<LoginProperties>();
	
	public OpenSocketCommunication() throws IOException
	{		
        System.out.println("Listening on port 35010. " + "Press enter to quit " + "after the next connection.");
       Socket socket = serverSocket.accept();
       System.out.println("A client has connected." + " Receiving a message");
       
       InputStream input = socket.getInputStream();
       
       BufferedReader reader = new BufferedReader(new InputStreamReader(input));
       String line = reader.readLine();    // reads a line of text
       
       Gson gson = new GsonBuilder().create();
       LoginProperties loginProperties = gson.fromJson(line, LoginProperties.class);
       System.out.println(loginProperties.getUsername());
       System.out.println(loginProperties.getPassword());
       Output.add(loginProperties);
	}
	
	public ArrayList<LoginProperties> GetClientData() 
	{
		return Output;
	}
	
	public void closeConnection() throws IOException 
	{
		serverSocket.close();
	}
	

}
