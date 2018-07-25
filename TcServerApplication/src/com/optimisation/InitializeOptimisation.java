package com.optimisation;

import java.util.ArrayList;

import com.middleLayer.AssignRandomError;
import com.middleLayer.MachineDetails;
import com.middleLayer.MachineProperties;

public class InitializeOptimisation {
	
	private ArrayList<MachineProperties> machinesWithError = new ArrayList<MachineProperties>();
	private ArrayList<MachineProperties> machinesWithRedError = new ArrayList<MachineProperties>();
	private ArrayList<MachineProperties> machinesWithGreenError = new ArrayList<MachineProperties>();
	private ArrayList<City> optimisedRootForGreenMachines = new ArrayList<City>();
	private ArrayList<City> optimisedRootForRedMachines = new ArrayList<City>();
	private ArrayList<MachineProperties> distributedListReturnedFromARClient = new ArrayList<MachineProperties>();
	
	public InitializeOptimisation(String factoryId) 
	{
		//TODO
		
		AssignRandomError assignRandomError = new AssignRandomError(factoryId);
		SelectMachinesWithYellowErrorState smwys = new SelectMachinesWithYellowErrorState(assignRandomError.getMachineErrorList());
		machinesWithError = smwys.getYellowStateMachines();
		//Listeyi ARClienta gonderen bir method yaz ve "machinesWithError" listesini o methoda yolla
		//ARClient tarafinda kullaniciya sari makinalarin sorunlarini cozmek isteyip istemedigi sorulacak!
		//Ask user about yellow state!
		//Return a list from ARClient!
		for(int i = 0; i < distributedListReturnedFromARClient.size(); i++) 
		{
			if(distributedListReturnedFromARClient.get(i).getErrorState().equals("2")) 
			{
				smwys.getRedStateMachines().add(distributedListReturnedFromARClient.get(i));
			}
			else if(distributedListReturnedFromARClient.get(i).getErrorState().equals("0")) 
			{
				smwys.getGreenStateMachines().add(distributedListReturnedFromARClient.get(i));
			}
		}
		
		for(int i = 0; i < smwys.getGreenStateMachines().size(); i++) 
		{
			machinesWithGreenError.add(smwys.getGreenStateMachines().get(i));
		}
		
		for(int i = 0; i < smwys.getRedStateMachines().size(); i++) 
		{
			machinesWithRedError.add(smwys.getRedStateMachines().get(i));
		}
		OptimiseRootInsidePlant optimiseRootInsidePlant = new OptimiseRootInsidePlant(machinesWithGreenError, 0);
		OptimiseRootInsidePlant optimiseRootInsidePlant2 = new OptimiseRootInsidePlant(machinesWithRedError, 2);
		optimisedRootForGreenMachines = optimiseRootInsidePlant.getOptimisedRootMachineList();
		optimisedRootForRedMachines = optimiseRootInsidePlant2.getOptimisedRootMachineList();
	}
	
	//Constructor for Dummy Data Test - No Teamcenter Connection
	public InitializeOptimisation() 
	{
		MachineDetails machineDetails = new MachineDetails();
		SelectMachinesWithYellowErrorState smwys = new SelectMachinesWithYellowErrorState(machineDetails.getCurrentMachine());
		machinesWithError = smwys.getYellowStateMachines(); 
		System.out.println("Yellow Stated Machines: " + machinesWithError);
		for(int i = 0; i < machinesWithError.size(); i++) 
		{
			System.out.println(machinesWithError.get(i).getId());
		}
		//Listeyi ARClienta gonderen bir method yaz ve "machinesWithError" listesini o methoda yolla
		//ARClient tarafinda kullaniciya sari makinalarin sorunlarini cozmek isteyip istemedigi sorulacak!
		//Ask user about yellow state!
		//Return a list from ARClient!
		for(int i = 0; i < distributedListReturnedFromARClient.size(); i++) 
		{
			if(distributedListReturnedFromARClient.get(i).getErrorState().equals("2")) 
			{
				smwys.getRedStateMachines().add(distributedListReturnedFromARClient.get(i));
			}
			else if(distributedListReturnedFromARClient.get(i).getErrorState().equals("0")) 
			{
				smwys.getGreenStateMachines().add(distributedListReturnedFromARClient.get(i));
			}
		}
		
		for(int i = 0; i < smwys.getGreenStateMachines().size(); i++) 
		{
			machinesWithGreenError.add(smwys.getGreenStateMachines().get(i));
		}
		
		for(int i = 0; i < smwys.getRedStateMachines().size(); i++) 
		{
			machinesWithRedError.add(smwys.getRedStateMachines().get(i));
		}
		//System.out.println("Green stated Machines:");
		OptimiseRootInsidePlant optimiseRootInsidePlant = new OptimiseRootInsidePlant(machinesWithGreenError, 0);
		
		optimisedRootForGreenMachines = optimiseRootInsidePlant.getOptimisedRootMachineList();
		for(int i = 0; i < optimisedRootForGreenMachines.size(); i++) 
		{
			//System.out.println(optimisedRootForGreenMachines.get(i)._machineId);
		}

		//System.out.println("Red stated Machines:");
		OptimiseRootInsidePlant optimiseRootInsidePlant2 = new OptimiseRootInsidePlant(machinesWithRedError, 2);
		optimisedRootForRedMachines = optimiseRootInsidePlant2.getOptimisedRootMachineList();
		for(int i = 0; i < optimisedRootForRedMachines.size(); i++) 
		{
			//System.out.println(optimisedRootForRedMachines.get(i)._machineId);
		}
	}
	
	//Display this to User
	public ArrayList<City> getOptimisedRootForGreenMachines() 
	{
		return optimisedRootForGreenMachines;
	}
	
	//Display this to User
	public ArrayList<City> getOptimisedRootForRedMachines() 
	{
		return optimisedRootForRedMachines;
	}

}
