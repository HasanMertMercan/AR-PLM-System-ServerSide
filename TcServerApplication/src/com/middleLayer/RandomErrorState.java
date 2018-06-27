package com.middleLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomErrorState {

	private List<MachineProperties> machinesWithErrorList = new ArrayList<MachineProperties>();
	private XMLReaderMachine xmlReaderMachine = new XMLReaderMachine();
	
	public RandomErrorState() 
	{
		
		machinesWithErrorList = xmlReaderMachine.getMachinePropertiesList();
		
		for(int i = 0; i < machinesWithErrorList.size(); i++) 
		{
			int randomNum = ThreadLocalRandom.current().nextInt(0, 3);
			
			if(randomNum == 0) 
			{
				machinesWithErrorList.get(i).setErrorState("0");
			}
			else if(randomNum == 1) 
			{
				machinesWithErrorList.get(i).setErrorState("1");
			}
			else 
			{
				machinesWithErrorList.get(i).setErrorState("2");
			
		}
		
	}
}
	
	public List<MachineProperties> getMachinesWithErrorState() 
	{
		return machinesWithErrorList;
	}
}
