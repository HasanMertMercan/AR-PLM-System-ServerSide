package com.communication;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import com.google.gson.Gson;

public class OpenSocketCommunication {
	
	public void OpenSocketCommunication() 
	{
		Gson gson = new Gson();
	      ServerSocket serverSocket = new ServerSocket(16001);
	      System.out.println("Listening on port 16001. " 
	              + "Press enter to quit "
	              + "after the next connection.");
	      while (System.in.available() == 0) {
	           Socket socket = serverSocket.accept();
	           System.out.println("A client has connected." 
	               + " Sending a new Guy down the pipe.");
	           PrintWriter out =
	               new PrintWriter(socket.getOutputStream(),
	                   true);
	           
	           //The object which will be send to client will be serilaized here!!
	           Guy guy = Guy.getRandomGuy();
	           String json = gson.toJson(guy);
	           out.print(json);
	           out.close();
	           socket.close();
	       }
	      System.out.println("Quitting...");
	      serverSocket.close();
	}
	

}
