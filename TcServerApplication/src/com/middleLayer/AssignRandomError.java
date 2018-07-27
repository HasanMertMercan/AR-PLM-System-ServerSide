package com.middleLayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.properties.MachineProperties;
import com.properties.OperationProperties;
import com.teamcenter.soa.exceptions.NotLoadedException;
import com.xmlreaders.XMLReaderOperation;

public class AssignRandomError {

	private ArrayList<MachineProperties> machineList = new ArrayList<MachineProperties>();
	private XMLReaderOperation xmlReaderOperation = new XMLReaderOperation();
	//private XMLReaderMachine xmlReaderMachine = new XMLReaderMachine();
	
	private ArrayList<OperationProperties> temporaryOperationList = new ArrayList<OperationProperties>();
	
	public AssignRandomError(String factoryId) throws NotLoadedException, IOException 
	{

		//machineList = xmlReaderMachine.getMachinePropertiesList();
		GetFactoryDataFromTeamcenter getFactoryDataFromTeamcenter = new GetFactoryDataFromTeamcenter(factoryId);
		
		int size = getFactoryDataFromTeamcenter.getMachineIds().size();
		
		for(int i = 0; i < size; i++) 
		{
			MachineDetails machineDetails = new MachineDetails(getFactoryDataFromTeamcenter.getMachineIds().get(i).getId());
			machineList.add(machineDetails.getCurrentMachine().get(0));
			//machineList.get(i).setId(getFactoryDataFromTeamcenter.getMachineIds().get(i).getId());
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
		

		temporaryOperationList = xmlReaderOperation.getOperationPropertiesList();
		int operationSize = temporaryOperationList.size();
		
		//Assign Operations to Every Machine based on their ErrorState (Every error cannot occur on every error state)
		for(int i = 0; i < size; i++) 
		{
			for(int j = 0; j < operationSize; j++) 
			{
				if(temporaryOperationList.get(j).get_state().equals(machineList.get(i).getErrorState()))
				{
					machineList.get(i).setOperationToDo(temporaryOperationList.get(j).getId());
				}
			}
		}
		
		
	}
	
	//Send this list to ARClient and ask user to select yellow errors
	public ArrayList<MachineProperties> getMachineErrorList()
	{
		return machineList;
	}
}
