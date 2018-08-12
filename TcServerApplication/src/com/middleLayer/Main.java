package com.middleLayer;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

import com.teamcenter.soa.client.FileManagementUtility;
import com.teamcenter.soa.common.ObjectPropertyPolicy;
import com.teamcenter.soa.exceptions.NotLoadedException;
import com.teamcenter.soa.client.model.strong.User;
import com.communication.CommunicationMain;
import com.optimisation.City;
import com.optimisation.OptimiseRootInsidePlant;
import com.optimisation.SelectMachinesWithYellowErrorState;
import com.properties.LoginProperties;
import com.properties.MachineProperties;
import com.teamcenter.clientx.*;
import com.teamcenter.services.strong.core.SessionService;

public class Main {

	public static void main(String[] args) throws IOException, NotLoadedException {
		// TODO Auto-generated method stub
		
		/*GetMachineDataFromTeamcenter getMachineDataFromTeamcenter = new GetMachineDataFromTeamcenter("C:/Teamcenter önemli/Zuccarello.obj");
		int size = getMachineDataFromTeamcenter.getMachineData().size();
		for(int i = 0; i < size; i++) 
		{
			System.out.println("Machine CAD Data " + i);
			System.out.println(getMachineDataFromTeamcenter.getMachineData().get(i).getMachineCADFile());
		}*/
		new Main();
		
		
	}
	
	private AppXSession session;
	private User user;
	@SuppressWarnings("unused")
	private FileManagementUtility fileManager;
	private boolean isSessionCreated;
	
	public Main() throws IOException, NotLoadedException
	{
		CommunicationMain communicationMain = new CommunicationMain();
		
		ServerSocket LoginServerSocket = new ServerSocket(35010);
		ServerSocket FactoryQRSocket = new ServerSocket(35011);
		ServerSocket OptimisationSocket = new ServerSocket(35012);
		
		
		System.out.println("Waiting a client");
		ArrayList<LoginProperties> loginProperties = new ArrayList<LoginProperties>();
		loginProperties.add(communicationMain.receiveLoginArguments(LoginServerSocket).get(0));
		String userName = loginProperties.get(0).getUsername();
		String password = loginProperties.get(0).getPassword();
		//createTeamcenterSession("http://127.0.0.1", userName, password);
		if(/*!this.user.get_user_id().equals(userName)*/ userName.equals("e1") && password.equals("123hm123")) 
		{
			isSessionCreated = true;
		}
		else 
		{
			isSessionCreated = false;
			System.out.println("There is no teamcenter session!");
		}
		//Send LoginResponse to Client
		communicationMain.SendLoginResponse(isSessionCreated, LoginServerSocket);
		
		//Get Machines from teamcenter based on the qrCode of the plant
		CommunicationMain communicationMain2 = new CommunicationMain();
		ArrayList<MachineProperties> machineList = new ArrayList<MachineProperties>();
		machineList = communicationMain2.receiveFactoryQrCode(FactoryQRSocket);
		
		//AssignRandomError
		AssignRandomError assignRandomError = new AssignRandomError(machineList);
		ArrayList<MachineProperties> machinesWithError = new ArrayList<MachineProperties>();
		machinesWithError = assignRandomError.getMachineErrorList();
		
		//AssignOperations Based on Errors
		AssignOperation assignOperation = new AssignOperation(machinesWithError);
		ArrayList<MachineProperties> completedMachineList = new ArrayList<MachineProperties>();
		completedMachineList = assignOperation.getCompletedMachineList();
		
		//SelectYellowStateMachines
		SelectMachinesWithYellowErrorState smwyes = new SelectMachinesWithYellowErrorState(completedMachineList);
		ArrayList<MachineProperties> yellowStateMachines = new ArrayList<MachineProperties>();
		ArrayList<MachineProperties> machinesWithRedError = new ArrayList<MachineProperties>();
		ArrayList<MachineProperties> machinesWithGreenError = new ArrayList<MachineProperties>();
		yellowStateMachines = smwyes.getYellowStateMachines();
		machinesWithGreenError = smwyes.getGreenStateMachines();
		machinesWithRedError = smwyes.getRedStateMachines();
				
		//Send Yellow State Machines to Client
		communicationMain2.SendYellowStateMachines(yellowStateMachines, FactoryQRSocket);
		
		//Get machines which has updated status
		CommunicationMain communicationMain3 = new CommunicationMain();
		ArrayList<MachineProperties> updatedMachineList = new ArrayList<MachineProperties>();
		updatedMachineList = communicationMain3.receiveUpdatedMachineList(OptimisationSocket);
		int size = updatedMachineList.size();
		
		//Distribute the updated list
		for(int i = 0; i < size; i++) 
		{
			if(updatedMachineList.get(i).getErrorState().equals("2")) 
			{
				machinesWithRedError.add(updatedMachineList.get(i));
			}
			else if(updatedMachineList.get(i).getErrorState().equals("0")) 
			{
				machinesWithGreenError.add(updatedMachineList.get(i));
			}
		}

		//Send Updated List to Optimisation
		OptimiseRootInsidePlant optimiseRootInsidePlant = new OptimiseRootInsidePlant(machinesWithGreenError, 0);
		OptimiseRootInsidePlant optimiseRootInsidePlant2 = new OptimiseRootInsidePlant(machinesWithRedError, 2);
		ArrayList<City> optimisedRootForGreenCities = new ArrayList<City>();
		ArrayList<City> optimisedRootForRedCities = new ArrayList<City>();
		optimisedRootForGreenCities = optimiseRootInsidePlant.getOptimisedRootMachineList();
		optimisedRootForRedCities = optimiseRootInsidePlant2.getOptimisedRootMachineList();
		int optimisedRootForGreenCitiesSize = optimisedRootForGreenCities.size();
		int optimisedRootForRedCitiesSize = optimisedRootForRedCities.size();
		
		//Convert type to MachineProperties from City
		ArrayList<MachineProperties> optimisedRootForGreenMachines = new ArrayList<MachineProperties>();
		ArrayList<MachineProperties> optimisedRootForRedMachines = new ArrayList<MachineProperties>();
		for(int i = 0; i < optimisedRootForGreenCitiesSize; i++) 
		{
			MachineProperties machineProperties = new MachineProperties();
			machineProperties.setId(optimisedRootForGreenCities.get(i).getMachineId());
			machineProperties.setxAxis(Integer.toString(optimisedRootForGreenCities.get(i).getX()));
			machineProperties.setyAxis(Integer.toString(optimisedRootForGreenCities.get(i).getY()));
			optimisedRootForGreenMachines.add(machineProperties);
		}
		for(int i = 0; i < optimisedRootForRedCitiesSize; i++) 
		{
			MachineProperties machineProperties = new MachineProperties();
			machineProperties.setId(optimisedRootForRedCities.get(i).getMachineId());
			machineProperties.setxAxis(Integer.toString(optimisedRootForRedCities.get(i).getX()));
			machineProperties.setyAxis(Integer.toString(optimisedRootForRedCities.get(i).getY()));
			optimisedRootForRedMachines.add(machineProperties);
		}
		
		
		//Complete the optimisedList with all the informations
		int optimisedRootForGreenMachinesSize = optimisedRootForGreenMachines.size();
		int completedMachineListSize = completedMachineList.size();
		int optimisedRootForRedMachinesSize = optimisedRootForRedMachines.size();
		
		for(int i = 0; i < optimisedRootForGreenMachinesSize; i++) 
		{
			for(int j = 0; j < completedMachineListSize; j++) 
			{
				if(completedMachineList.get(j).getId().equals(optimisedRootForGreenMachines.get(i).getId())) 
				{
					optimisedRootForGreenMachines.get(i).setOperationToDo(completedMachineList.get(j).getOperationToDo());
				}
			}
		}
		
		for(int i = 0; i < optimisedRootForRedMachinesSize; i++) 
		{
			for(int j = 0; j < completedMachineListSize; j++) 
			{
				if(completedMachineList.get(j).getId().equals(optimisedRootForRedMachines.get(i).getId())) 
				{
					optimisedRootForRedMachines.get(i).setOperationToDo(completedMachineList.get(j).getOperationToDo());
				}
			}
		}
		
		//Send Fully Optimised List to Client
		CommunicationMain sendGreenOptimisedListToClient = new CommunicationMain();
		CommunicationMain sendRedOprimisedListTiClient = new CommunicationMain();
		sendGreenOptimisedListToClient.SendOptimisedList(optimisedRootForGreenMachines, OptimisationSocket);
		sendRedOprimisedListTiClient.SendOptimisedList(optimisedRootForRedMachines, OptimisationSocket);
		
		
		
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
		
	public void createTeamcenterSession(String host, String username, String password)
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
