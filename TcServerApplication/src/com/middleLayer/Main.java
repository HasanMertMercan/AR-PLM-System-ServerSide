package com.middleLayer;

import java.io.IOException;

import com.teamcenter.soa.client.FileManagementUtility;
import com.teamcenter.soa.common.ObjectPropertyPolicy;
import com.teamcenter.soa.exceptions.NotLoadedException;
import com.teamcenter.soa.client.model.strong.User;
import com.optimisation.InitializeOptimisation;
import com.teamcenter.clientx.*;
import com.teamcenter.services.strong.core.SessionService;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InitializeOptimisation initializeOptimisation = new InitializeOptimisation();
		initializeOptimisation.getOptimisedRootForGreenMachines();
	}
	
	private AppXSession session;
	private User user;
	@SuppressWarnings("unused")
	private FileManagementUtility fileManager;
	
	public Main(String teamcenterHost, String username, String password)
	{
		
		//createTeamcenterSession(teamcenterHost, username, password);
	}
	
	/*This section will be in the AR application
	//Request for additional information
	@SuppressWarnings("null")
	public int updateUserStatus() throws NotLoadedException 
	{
		UserDetails userDetails = null;
		String[] gcu = userDetails.getCurrentUser(); 
		int status = Integer.parseInt(gcu[3]);
		
		if(!gcu[3].equals("3")) 
		{
			status++;
		}
		else 
		{
			System.out.println("The information which has been displayed is the most basic information. Please contact with your supervisor for more info");
		}

		return status;
	}*/
		
	private void createTeamcenterSession(String host, String username, String password)
	{
		System.out.println("Starting a session on " + host); // prints on screen the host for the session
		this.session = new AppXSession (host);
		System.out.println("Logging in with username " + username); // prints on screen the session's user
		this.user = this.session.login(username, password); // uses username and password to log into Teamcenter
		
		try
		{
			System.out.println("logged in as user " + this.user.get_user_name());
		}
		catch (NotLoadedException e)
		{
			throw new RuntimeException(e);
		}
		/** Get the session service and set the object property policy in Teamcenter */
		SessionService sessionService = SessionService.getService(AppXSession.getConnection());
		
		ObjectPropertyPolicy oppolicy = new ObjectPropertyPolicy();
		
		//TODO
		//Add necessary attirbutes to oppolicy.xml file. 
		String path = Main.class.getResource("/oppolicy.xml").toString();
		
		try
		{
			oppolicy.load(path);
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
		
		sessionService.setObjectPropertyPolicy(oppolicy);
		this.fileManager = new FileManagementUtility(AppXSession.getConnection()); // Get and cache the file manager
	}

	
}
