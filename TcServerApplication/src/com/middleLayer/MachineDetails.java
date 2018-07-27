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
	
	private ArrayList<MachineProperties> temporaryMachineList = new ArrayList<MachineProperties>();

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
	
	//For optimisation with Teamcenter information. Used by Initialize optimisation class
	/*public MachineDetails(String machineId) 
	{
		int size = xmlReaderMachine.getMachinePropertiesList().size();
		for(int j = 0; j < size; j++) 
		{
			if(xmlReaderMachine.getMachinePropertiesList().get(j).getId() == machineId) 
			{
				currentMachine.add(xmlReaderMachine.getMachinePropertiesList().get(j));
			}
		}
	}*/
	
	//Constructor to get MachineCADData
	//Ana algorithmada makine id si okutulunca bu methodu cagirma. Dogrudan daha evvel gelen (AssignRandomError) listedeki makinelerin arasinda bul ve datayi dondur.
	public MachineDetails(String machineId) throws NotLoadedException, IOException
	{
		temporaryMachineList = xmlReaderMachine.getMachinePropertiesList();
		int size = temporaryMachineList.size();
		
		for(int j = 0; j < size; j++) 
		{
			if(temporaryMachineList.get(j).getId().equals(machineId)) 
			{
				String fileName = temporaryMachineList.get(j).getFileName();
				String revisionId = temporaryMachineList.get(j).getRevisionId();
				GetMachineDataFromTeamcenter getMachineDataFromTeamcenter = new GetMachineDataFromTeamcenter(machineId, fileName, revisionId);
				temporaryMachineList.get(j).setMachineCADFile(getMachineDataFromTeamcenter.getMachineData().get(0).getMachineCADFile());
				currentMachine.add(temporaryMachineList.get(j));
				break;
			}
		}
		
	}
	
	public ArrayList<MachineProperties> getCurrentMachine() 
	{
		return currentMachine;
	}

}
