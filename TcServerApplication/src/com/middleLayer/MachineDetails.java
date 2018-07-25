package com.middleLayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.teamcenter.soa.exceptions.NotLoadedException;

public class MachineDetails {
	
	private ArrayList<MachineProperties> machineList = new ArrayList<MachineProperties>();
	private XMLReaderMachine xmlReaderMachine = new XMLReaderMachine();
	private ArrayList<MachineProperties> currentMachine = new ArrayList<MachineProperties>();

	//Constructor for basic operations, dummy optimisation
	public MachineDetails() 
	{
		
		//machineList = xmlReaderMachine.getMachinePropertiesList();
		for(int j = 0; j < xmlReaderMachine.getMachinePropertiesList().size(); j++) 
		{
			/*currentMachine.get(j).setId(xmlReaderMachine.getMachinePropertiesList().get(j).getId());						//User Id
			currentMachine.get(j).setxAxis(xmlReaderMachine.getMachinePropertiesList().get(j).getxAxis());					//x-Axis
			currentMachine.get(j).setyAxis(xmlReaderMachine.getMachinePropertiesList().get(j).getyAxis());					//y-Axis
			int randomNum = ThreadLocalRandom.current().nextInt(0, 3);
			currentMachine.get(j).setErrorState(Integer.toString(randomNum));				//Error State
*/			
			
			currentMachine.add(xmlReaderMachine.getMachinePropertiesList().get(j));
			int randomNum = ThreadLocalRandom.current().nextInt(0, 3);
			currentMachine.get(j).setErrorState(Integer.toString(randomNum));
			//System.out.println(currentMachine.get(j).getId());
		}
	}
	
	//For optimisation
	public MachineDetails(String machineId) 
	{
		for(int j = 0; j < machineList.size(); j++) 
		{
			if(machineList.get(j).getId() == machineId) 
			{
				currentMachine.get(0).setId(machineList.get(j).getId());						//User Id
				currentMachine.get(0).setxAxis(machineList.get(j).getxAxis());					//x-Axis
				currentMachine.get(0).setyAxis(machineList.get(j).getyAxis());					//y-Axis
				currentMachine.get(0).setErrorState(machineList.get(j).getErrorState());		//Error State
				
				break;
			}
		}
	}
	
	//Constructor to get MachineCADData
	public MachineDetails(String machineId, String fileName, String revisionId) throws NotLoadedException, IOException
	{
		machineList = xmlReaderMachine.getMachinePropertiesList();
		GetMachineDataFromTeamcenter getMachineDataFromTeamcenter = new GetMachineDataFromTeamcenter(machineId, fileName, revisionId);
		
		for(int j = 0; j < machineList.size(); j++) 
		{
			if(machineList.get(j).getId() == machineId) 
			{
				currentMachine.get(0).setId(machineList.get(j).getId());						//User Id
				currentMachine.get(0).setxAxis(machineList.get(j).getxAxis());					//x-Axis
				currentMachine.get(0).setyAxis(machineList.get(j).getyAxis());					//y-Axis
				currentMachine.get(0).setErrorState(machineList.get(j).getErrorState());		//Error State
			}
		}
		currentMachine.get(0).setMachineCADFile(getMachineDataFromTeamcenter.getMachineData().get(0).getMachineCADFile());
		
	}
	
	public ArrayList<MachineProperties> getCurrentMachine() 
	{
		return currentMachine;
	}

}
