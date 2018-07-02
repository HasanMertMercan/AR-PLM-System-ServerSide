package com.middleLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.teamcenter.soa.exceptions.NotLoadedException;

public class MachineDetails {
	
	private List<MachineProperties> machineList = new ArrayList<MachineProperties>();
	private XMLReaderMachine xmlReaderMachine = new XMLReaderMachine();
	private String[] currentMachine = null;

	//machineId --> machineId which comes from AR client
	public MachineDetails(String machineId) throws NotLoadedException
	{
		machineList = xmlReaderMachine.getMachinePropertiesList();
		
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
		
		
		for(int j = 0; j < machineList.size(); j++) 
		{
			if(machineList.get(j).getId() == machineId) 
			{
				currentMachine[0] = machineList.get(j).getId();				//User Id
				currentMachine[1] = machineList.get(j).getxAxis();			//x-Axis
				currentMachine[2] = machineList.get(j).getyAxis();			//y-Axis
				currentMachine[3] = machineList.get(j).getErrorState();		//Error State
				
				break;
			}
		}
		
	}
	
	public String[] getCurrentMachine() 
	{
		return currentMachine;
		
	}

}
