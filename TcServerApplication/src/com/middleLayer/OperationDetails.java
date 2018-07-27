package com.middleLayer;

import java.util.ArrayList;

import com.properties.OperationProperties;
import com.teamcenter.soa.exceptions.NotLoadedException;
import com.xmlreaders.XMLReaderOperation;

public class OperationDetails {
	
	private ArrayList<OperationProperties> operationList = new ArrayList<OperationProperties>();
	private XMLReaderOperation xmlReaderOperation = new XMLReaderOperation();
	private ArrayList<OperationProperties> currentOperation = new ArrayList<OperationProperties>();

	//operationID --> operationId which comes from AR client
	public OperationDetails(String operationId) throws NotLoadedException 
	{
		operationList = xmlReaderOperation.getOperationPropertiesList();
		int operationSize = xmlReaderOperation.getOperationPropertiesList().size();
		
		for(int i = 0; i < operationSize; i++) 
		{
			if(operationList.get(i).getId().equals(operationId)) 	
			{
				currentOperation.add(operationList.get(i));
			}
		}
	}
	
	public ArrayList<OperationProperties> getCurrentOperation() 
	{
		return currentOperation;
	}
	
	//Ne zaman lazim olacak?
	//Operation gosterildigi zaman 
	public String[] getCurrentOperationTools() 
	{
		String[] tools = currentOperation.get(0).getToolIds().split("-");
		return tools;
	}

}
