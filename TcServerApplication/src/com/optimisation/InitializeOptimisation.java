package com.optimisation;

import java.io.IOException;
import java.util.ArrayList;

import com.middleLayer.MachineDetails;
import com.properties.MachineProperties;
import com.teamcenter.soa.exceptions.NotLoadedException;

public class InitializeOptimisation {
	
	private ArrayList<MachineProperties> machinesWithYellowError = new ArrayList<MachineProperties>();
	private ArrayList<MachineProperties> machinesWithRedError = new ArrayList<MachineProperties>();
	private ArrayList<MachineProperties> machinesWithGreenError = new ArrayList<MachineProperties>();
	private ArrayList<City> optimisedRootForGreenMachines = new ArrayList<City>();
	private ArrayList<City> optimisedRootForRedMachines = new ArrayList<City>();
	private ArrayList<MachineProperties> distributedListReturnedFromARClient = new ArrayList<MachineProperties>();
	
	public InitializeOptimisation(ArrayList<MachineProperties> completedMachineList) throws NotLoadedException, IOException 
	{
		//TODO
		
		SelectMachinesWithYellowErrorState smwys = new SelectMachinesWithYellowErrorState(completedMachineList);
		machinesWithYellowError = smwys.getYellowStateMachines();
		machinesWithGreenError = smwys.getGreenStateMachines();
		machinesWithRedError = smwys.getRedStateMachines();
		
		//Listeyi ARClienta gonderen bir method yaz ve "machinesWithError" listesini o methoda yolla
		//ARClient tarafinda kullaniciya sari makinalarin sorunlarini cozmek isteyip istemedigi sorulacak!
		//Ask user about yellow state!
		//Return a list from ARClient! (distributedListReturnedFromARClient)
		for(int i = 0; i < distributedListReturnedFromARClient.size(); i++) 
		{
			if(distributedListReturnedFromARClient.get(i).getErrorState().equals("2")) 
			{
				machinesWithRedError.add(distributedListReturnedFromARClient.get(i));
			}
			else if(distributedListReturnedFromARClient.get(i).getErrorState().equals("0")) 
			{
				machinesWithGreenError.add(distributedListReturnedFromARClient.get(i));
			}
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
		for(int i = 0; i < machineDetails.getCurrentMachine().size(); i++) 
		{
			System.out.println(machineDetails.getCurrentMachine().get(i).getId() + " " + machineDetails.getCurrentMachine().get(i).getErrorState());
			
		}
		SelectMachinesWithYellowErrorState smwys = new SelectMachinesWithYellowErrorState(machineDetails.getCurrentMachine());
		machinesWithYellowError = smwys.getYellowStateMachines(); 
		/*System.out.println("Yellow Stated Machines: ");
		for(int i = 0; i < machinesWithError.size(); i++) 
		{
			System.out.println(machinesWithError.get(i).getId());
		}*/
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
		System.out.println("Green stated machines:");
		for(int i = 0; i < smwys.getGreenStateMachines().size(); i++) 
		{
			machinesWithGreenError.add(smwys.getGreenStateMachines().get(i));
			//System.out.println(smwys.getGreenStateMachines().get(i).getId());
		}
		/*System.out.println("GREEN");
		for(int i = 0; i < machinesWithGreenError.size(); i++) 
		{
			System.out.println(machinesWithGreenError.get(i).getId());
		}
		
		System.out.println("red stated machines:");*/
		for(int i = 0; i < smwys.getRedStateMachines().size(); i++) 
		{
			machinesWithRedError.add(smwys.getRedStateMachines().get(i));
			//System.out.println(smwys.getRedStateMachines().get(i).getId());
		}
		/*System.out.println("RED");
		for(int i = 0; i < machinesWithRedError.size(); i++) 
		{
			System.out.println(machinesWithRedError.get(i).getId());
		}*/
		OptimiseRootInsidePlant optimiseRootInsidePlant = new OptimiseRootInsidePlant(machinesWithGreenError, 0);
		optimisedRootForGreenMachines = optimiseRootInsidePlant.getOptimisedRootMachineList();
		/*System.out.println("Green stated Machines:");
		for(int i = 0; i < optimisedRootForGreenMachines.size(); i++) 
		{
			System.out.println(optimisedRootForGreenMachines.get(i)._machineId);
		}*/

		OptimiseRootInsidePlant optimiseRootInsidePlant2 = new OptimiseRootInsidePlant(machinesWithRedError, 2);
		optimisedRootForRedMachines = optimiseRootInsidePlant2.getOptimisedRootMachineList();
		/*System.out.println("Red stated Machines:");
		for(int i = 0; i < optimisedRootForRedMachines.size(); i++) 
		{
			System.out.println(optimisedRootForRedMachines.get(i)._machineId);
		}*/
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
