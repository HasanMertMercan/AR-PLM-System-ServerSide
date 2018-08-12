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

public class GetLoginArguments{
	
	//private PrintWriter out;
	private ArrayList<LoginProperties> LoginArguments = new ArrayList<LoginProperties>();
	
	public GetLoginArguments(ServerSocket serverSocket) throws IOException
	{		
       Socket socket = serverSocket.accept();
       System.out.println("A client has connected." + " Receiving a message");
       
       InputStream input = socket.getInputStream();
       BufferedReader reader = new BufferedReader(new InputStreamReader(input));
       String line = reader.readLine();    // reads a line of text
       
       
       //Deserilaize Login Arguments
       Gson gson = new GsonBuilder().create();
       LoginProperties loginProperties = gson.fromJson(line, LoginProperties.class);
       
       System.out.println(loginProperties.getUsername());
       System.out.println(loginProperties.getPassword());
       LoginArguments.add(loginProperties);
       
       //serverSocket.close();
	}
	
	public ArrayList<LoginProperties> GetClientData() 
	{
		return LoginArguments;
	}
	

}
