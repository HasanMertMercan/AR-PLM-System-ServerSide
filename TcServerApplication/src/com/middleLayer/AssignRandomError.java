package com.middleLayer;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class AssignRandomError {

	private ArrayList<MachineProperties> machineList = new ArrayList<MachineProperties>();
	private XMLReaderMachine xmlReaderMachine = new XMLReaderMachine();

	public AssignRandomError(String factoryId) 
	{

		machineList = xmlReaderMachine.getMachinePropertiesList();
		GetFactoryDataFromTeamcenter getFactoryDataFromTeamcenter = new GetFactoryDataFromTeamcenter(factoryId);
		
		for(int i = 0; i < getFactoryDataFromTeamcenter.getMachineIds().size(); i++) 
		{
			machineList.get(i).setId(getFactoryDataFromTeamcenter.getMachineIds().get(i).getId());
		}
		
		//This code gives random error states to machines
		for(int i = 0; i < machineList.size(); i++) 
		{
			int randomNum = ThreadLocalRandom.current().nextInt(0, 3);
			
			if(randomNum == 0) 
			{
				machineList.get(i).setErrorState("0");		//Green State, Regular Maintenance
			}
			else if(randomNum == 1) 
			{
				machineList.get(i).setErrorState("1");		//Yellow State, There is a problem but not urgent
			}
			else 
			{
				machineList.get(i).setErrorState("2");		//Red State, Needs to be taken care as soon as possible
			}
		}
		
	}
	
	public ArrayList<MachineProperties> getMachineErrorList()
	{
		return machineList;
	}
}
