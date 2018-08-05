package com.middleLayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.properties.MachineProperties;
import com.teamcenter.soa.exceptions.NotLoadedException;
import com.xmlreaders.XMLReaderMachine;

public class MachineDetails {
	
	private XMLReaderMachine xmlReaderMachine = new XMLReaderMachine();
	private ArrayList<MachineProperties> currentMachine = new ArrayList<MachineProperties>();

	//Constructor for basic operations, Used for testing purposes 
	public MachineDetails() 
	{
		int size = xmlReaderMachine.getMachinePropertiesList().size();
		for(int j = 0; j < size; j++) 
		{	
			currentMachine.add(xmlReaderMachine.getMachinePropertiesList().get(j));
			int randomNum = ThreadLocalRandom.current().nextInt(0, 3);
			currentMachine.get(j).setErrorState(Integer.toString(randomNum));
			//System.out.println(currentMachine.get(j).getId());
		}
	}
	
	//Constructor to get MachineCADData
	//machineId --> ARClient, completedMachineList --> The list which returned from AssignOperation
	public MachineDetails(String machineId, ArrayList<MachineProperties> completedMachineList) throws NotLoadedException, IOException
	{
		int size = completedMachineList.size();
		
		for(int j = 0; j < size; j++) 
		{
			if(completedMachineList.get(j).getId().equals(machineId)) 
			{
				String fileName = completedMachineList.get(j).getFileName();
				String revisionId = completedMachineList.get(j).getRevisionId();
				GetMachineDataFromTeamcenter getMachineDataFromTeamcenter = new GetMachineDataFromTeamcenter(machineId, fileName, revisionId);
				completedMachineList.get(j).setMachineCADFile(getMachineDataFromTeamcenter.getMachineData().get(0).getMachineCADFile());
				currentMachine.add(completedMachineList.get(j));
				break;
			}
		}
	}
	
	public ArrayList<MachineProperties> getCurrentMachine() 
	{
		return currentMachine;
	}

}
