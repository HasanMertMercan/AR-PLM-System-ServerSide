package com.middleLayer;

import java.io.IOException;
import java.util.ArrayList;

public class GetMachineDataFromTeamcenter {
	
	//machineId will come from AR client
	
	private ArrayList<MachineProperties> machineData = new ArrayList<MachineProperties>();
	public GetMachineDataFromTeamcenter(String machineId, String fileName, String revisionId) throws IOException 
	{
		//TODO
		CADConverter cadConverter = new CADConverter(machineId, fileName, revisionId);
		machineData.get(0).setMachineCADFile(cadConverter.getCADFileFinal()); //This slot will be filled by String
	}
	
	public ArrayList<MachineProperties> getMachineData()
	{
		return machineData;
	}
	
	

}
