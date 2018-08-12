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
import com.properties.MachineProperties;

public class GetUpdatedMachineList {
	
	private ArrayList<MachineProperties> updatedMachineList = new ArrayList<MachineProperties>();
	
	public GetUpdatedMachineList(ServerSocket serverSocket) throws IOException 
	{
		Socket socket = serverSocket.accept();
       System.out.println("A client has connected." + " Receiving a message");
       
       InputStream input = socket.getInputStream();
       BufferedReader reader = new BufferedReader(new InputStreamReader(input));
       String line = reader.readLine();    // reads a line of text
       
       
       //Deserilaize Login Arguments
       Gson gson = new GsonBuilder().create();
       MachineProperties machineProperties = gson.fromJson(line, MachineProperties.class);
       
       updatedMachineList.add(machineProperties);
       
       //serverSocket.close();
	}
	
	public ArrayList<MachineProperties> getUpdatedMachineList()
	{
		return updatedMachineList;
	}

}
