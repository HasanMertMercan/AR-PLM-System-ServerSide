package com.middleLayer;

import java.io.IOException;
import java.util.ArrayList;

import com.teamcenter.soa.exceptions.NotLoadedException;

public class MachineDetails {
	
	private ArrayList<MachineProperties> machineList = new ArrayList<MachineProperties>();
	private XMLReaderMachine xmlReaderMachine = new XMLReaderMachine();
	private ArrayList<MachineProperties> currentMachine = null;

	//machineId --> machineId which comes from AR client
	public MachineDetails(String machineId, String fileName, String revisionId, Boolean cadData, Boolean optimisation) throws NotLoadedException, IOException
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
				
				//ErrorState yalnizca optimizasyon yapilacagi zaman gelecek
				if(optimisation == true)
					currentMachine.get(0).setErrorState(machineList.get(j).getErrorState());		//Error State
				
				break;
			}
		}
		
		//CAD datasi yalnizca cagirildiginda gelecek. Optimizasyon icin bu method cagirildiginda CAD dosyasi cekilmeyecek
		if(cadData == true)
		currentMachine.get(0).setMachineCADFile(getMachineDataFromTeamcenter.getMachineData().get(0).getMachineCADFile());
		
	}
	
	public ArrayList<MachineProperties> getCurrentMachine() 
	{
		return currentMachine;
	}

}
