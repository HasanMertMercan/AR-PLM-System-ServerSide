package com.middleLayer;

import java.util.ArrayList;

public class GetMachineDataFromTeamcenter {
	
	//machineId will come from AR client
	
	private ArrayList<MachineProperties> machineData = new ArrayList<MachineProperties>();
	public GetMachineDataFromTeamcenter(String machineId, String fileName, String revisionId) 
	{
		//TODO
		GetFileFromTeamcenter getFileFromTeamcenter = new GetFileFromTeamcenter(machineId, fileName, revisionId);
		//The CAD converter will be called here!!
		machineData.get(0).setMachineCADFile(getFileFromTeamcenter.getFile());
	}
	
	public ArrayList<MachineProperties> getMachineData()
	{
		return machineData;
	}
	
	

}
