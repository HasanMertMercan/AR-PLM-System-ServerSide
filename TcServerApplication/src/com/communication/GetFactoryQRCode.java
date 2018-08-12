package com.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class GetFactoryQRCode {
	
	private String qrCode;
	
	public GetFactoryQRCode(ServerSocket FactoryQrSocket) throws IOException 
	{
		Socket socket = FactoryQrSocket.accept();
	    System.out.println("A client has connected." + " Receiving a message");
	    
	    InputStream input = socket.getInputStream();
	    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
	    qrCode = reader.readLine();    // reads a line of text
	}
	
	public String getFactoryQrCode() 
	{
		return qrCode;
	}

}
