package com.middleLayer;

import java.util.ArrayList;
import com.teamcenter.soa.exceptions.NotLoadedException;

public class OperationDetails {
	
	private ArrayList<OperationProperties> operationList = new ArrayList<OperationProperties>();
	private ArrayList<ToolProperties> toolList = new ArrayList<ToolProperties>();
	private ArrayList<ToolProperties> currentOperationToolList = new ArrayList<ToolProperties>();
	private XMLReaderOperation xmlReaderOperation = new XMLReaderOperation();
	private ArrayList<OperationProperties> currentOperation = null;

	//operationID --> operationId which comes from AR client
	public OperationDetails(String operationId) throws NotLoadedException 
	{
		operationList = xmlReaderOperation.getOperationPropertiesList();
		toolList = xmlReaderOperation.getToolPropertiesList();
		
		createCurrentOperation: {
			for(int i = 0; i < operationList.size(); i++) 
			{
				if(operationList.get(i).getId() == operationId) 	
				{
					currentOperation.get(0)._id = operationList.get(i).getId();					//Operation Id
					currentOperation.get(0)._name = operationList.get(i).getName();				//Operation Name
					currentOperation.get(0)._profession = operationList.get(i).getProfession();	//Operation Profession
					currentOperation.get(0)._toolIds = operationList.get(i).getToolIds();		//Operation`s toolList
					
					break createCurrentOperation;
				}
			}

			int counter = 0; //Counter for the new list. Needed to have reliable counter for the new list
			for(int i = 0; i < toolList.size(); i++) 
			{
				for(int j = 0; j < currentOperation.get(0)._toolIds.length; j++) 
				{
					if(currentOperation.get(0)._toolIds[j] == toolList.get(i).getToolId()) 
					{
						currentOperationToolList.get(counter).setToolId(toolList.get(i).getToolId());
						counter++;
					}
				}
			}
		}
		
	}
	
	public ArrayList<OperationProperties> getCurrentOperation() 
	{
		return currentOperation;
		
	}
	
	//Returns current operations tool list. 
	public ArrayList<ToolProperties> getCurrentOperationsToolList()
	{
		return currentOperationToolList;
	}

}
