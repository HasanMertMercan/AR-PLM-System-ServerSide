package com.communication;

import java.io.IOException;

public class CommunicationMain {
	
	public CommunicationMain() throws IOException 
	{
		
	}
	
	public void startCommunication() throws IOException 
	{
		OpenSocketCommunication osc = new OpenSocketCommunication();
		System.out.println(osc.GetClientData().get(0).getUsername());
		System.out.println(osc.GetClientData().get(0).getPassword());
		osc.closeConnection();
	}

}
