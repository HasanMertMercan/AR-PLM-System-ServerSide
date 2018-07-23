package com.middleLayer;

import java.util.ArrayList;

public class SelectMachinesWithYellowErrorState {
	
	private ArrayList<MachineProperties> yellowStateMachines = new ArrayList<MachineProperties>();
	
	public SelectMachinesWithYellowErrorState(String factoryId) 
	{
		AssignRandomError assignRandomError = new AssignRandomError(factoryId);
		for(int i = 0; i < assignRandomError.getMachineErrorList().size(); i++) 
		{
			if(assignRandomError.getMachineErrorList().get(i).getErrorState().equals("1"))
			yellowStateMachines.get(i).setErrorState(assignRandomError.getMachineErrorList().get(i).getErrorState());
		}
	}
	
	public ArrayList<MachineProperties> getYellowStateMachines()
	{
		return yellowStateMachines;
	}

}
