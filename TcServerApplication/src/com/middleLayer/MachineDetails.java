package com.middleLayer;

import java.util.ArrayList;
import java.util.List;

import com.teamcenter.soa.exceptions.NotLoadedException;

public class MachineDetails {
	
	private List<MachineProperties> machineList = new ArrayList<MachineProperties>();
	private XMLReaderMachine xmlReaderMachine = new XMLReaderMachine();
	private String[] currentMachine = null;
	private GetFactoryDataFromTeamcenter gmftc = new GetFactoryDataFromTeamcenter();

	public MachineDetails() throws NotLoadedException
	{
		machineList = xmlReaderMachine.getMachinePropertiesList();
		
		createCurrentMachine: {
			for(int i = 0; i < machineList.size(); i++) 
			{
				if(machineList.get(i).getId() == gmftc.getMachineIds().get(i).getId()) //Ikinci arguman dogru degil. Hangi makinada isek onun IDsi ile karsilastirmamiz lazim.
				{
					currentMachine[0] = machineList.get(i).getId();				//User Id
					currentMachine[1] = machineList.get(i).getxAxis();			//x-Axis
					currentMachine[2] = machineList.get(i).getyAxis();			//y-Axis
					currentMachine[3] = machineList.get(i).getErrorState();		//Error State
					
					break createCurrentMachine;
				}
			}
		}
		
	}
	
	public String[] getCurrentMachine() 
	{
		return currentMachine;
		
	}

}
