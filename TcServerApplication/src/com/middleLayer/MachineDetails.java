package com.middleLayer;

import java.util.ArrayList;
import java.util.List;

import com.teamcenter.soa.client.model.strong.User;
import com.teamcenter.soa.exceptions.NotLoadedException;

public class MachineDetails {
	
	private List<MachineProperties> machineList = new ArrayList<MachineProperties>();
	private XMLReaderMachine xmlReaderMachine = new XMLReaderMachine();
	private String[] currentMachine = null;

	public MachineDetails()
	{
		machineList = xmlReaderMachine.getMachinePropertiesList();
		
		createCurrentMachine: {
			for(int i = 0; i < machineList.size(); i++) 
			{
				try {
					if(machineList.get(i).getId() == user.get_user_id()) //Compare with machine's id which comes from Teamcenter not with userid
					{
						currentMachine[0] = machineList.get(i).getId();				//User Id
						currentMachine[1] = machineList.get(i).getxAxis();			//x-Axis
						currentMachine[2] = machineList.get(i).getyAxis();			//y-Axis
						currentMachine[3] = machineList.get(i).getErrorState();		//Error State
						
						break createCurrentMachine;
					}
				} catch (NotLoadedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public String[] getCurrentMachine() 
	{
		return currentMachine;
		
	}

}
