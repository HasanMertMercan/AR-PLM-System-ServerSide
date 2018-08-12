package com.communication;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.middleLayer.GetFactoryDataFromTeamcenter;
import com.properties.LoginProperties;
import com.properties.MachineProperties;


public class CommunicationMain {
	
	private ArrayList<MachineProperties> machineList = new ArrayList<MachineProperties>();

	public CommunicationMain() throws IOException 
	{
		
		
	}
	
	public ArrayList<MachineProperties> receiveUpdatedMachineList(ServerSocket OptimisationSocket) throws IOException
	{
		GetUpdatedMachineList getUpdatedMachineList = new GetUpdatedMachineList(OptimisationSocket);
		ArrayList<MachineProperties> updatedMachineList = new ArrayList<MachineProperties>();
		updatedMachineList = getUpdatedMachineList.getUpdatedMachineList();
		return updatedMachineList;
	}
	
	public ArrayList<MachineProperties> receiveFactoryQrCode(ServerSocket FactoryQrSocket) throws IOException
	{
		GetFactoryQRCode newFactoryQrCode = new GetFactoryQRCode(FactoryQrSocket);
		String factoryId = newFactoryQrCode.getFactoryQrCode();
		GetFactoryDataFromTeamcenter getFactoryDataFromTeamcenter = new GetFactoryDataFromTeamcenter(factoryId);
		machineList = getFactoryDataFromTeamcenter.getMachineIds();
		return machineList;		
	}
	
	public void SendOptimisedList(ArrayList<MachineProperties> OptimisedRootForMachines, ServerSocket OptimisationSocket) throws IOException 
	{
		Socket socket = OptimisationSocket.accept();
		
		OutputStream output = socket.getOutputStream();
	    PrintWriter writer = new PrintWriter(output, true);
	    
	    Gson gson = new GsonBuilder().create();
	    String input = gson.toJson(OptimisedRootForMachines);
	    writer.println(input);
	}
	
	public void SendYellowStateMachines(ArrayList<MachineProperties> YellowStateMachines, ServerSocket FactoryQrSocket) throws IOException 
	{
		Socket socket = FactoryQrSocket.accept();
		
		OutputStream output = socket.getOutputStream();
	    PrintWriter writer = new PrintWriter(output, true);
	    
	    Gson gson = new GsonBuilder().create();
	    String input = gson.toJson(YellowStateMachines);
	    writer.println(input);
	}
	
	public ArrayList<LoginProperties> receiveLoginArguments(ServerSocket LoginServerSocket) throws IOException
	{
		GetLoginArguments getLoginArguments = new GetLoginArguments(LoginServerSocket);
		ArrayList<LoginProperties> loginProperties = new ArrayList<LoginProperties>();
		loginProperties.add(getLoginArguments.GetClientData().get(0));
		return loginProperties;
	}
	
	public void SendLoginResponse(boolean isSessionCreated, ServerSocket LoginServerSocket) throws IOException 
	{
		//Create connection to AR
		//Send isSessionCreated to AR
		//ServerSocket serverSocket = new ServerSocket(35010);
		Socket socket = LoginServerSocket.accept();
		
		OutputStream output = socket.getOutputStream();
	    PrintWriter writer = new PrintWriter(output, true);
		
		if(isSessionCreated == true) 
		{
		    writer.println("1"); //1 stands for true
		}
		else 
		{
			writer.println("0"); //0 stands for false
		}
		
		LoginServerSocket.close();
    }
		
		

}
