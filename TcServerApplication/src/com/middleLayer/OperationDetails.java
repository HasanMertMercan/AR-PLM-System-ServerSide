package com.middleLayer;

import java.util.ArrayList;
import java.util.List;

import com.teamcenter.soa.exceptions.NotLoadedException;

public class OperationDetails {
	
	private List<OperationProperties> operationList = new ArrayList<OperationProperties>();
	private XMLReaderOperation xmlReaderOperation = new XMLReaderOperation();
	private String[] currentOperation = null;

	//operationID --> operationId which comes from AR client
	public OperationDetails(String operationId) throws NotLoadedException 
	{
		operationList = xmlReaderOperation.getOperationPropertiesList();
		
		createCurrentOperation: {
			for(int i = 0; i < operationList.size(); i++) 
			{
				if(operationList.get(i).getId() == operationId) 	//Compare with operation's id not with userid
				{
					currentOperation[0] = operationList.get(i).getId();			//User Id
					currentOperation[1] = operationList.get(i).getName();		//User Name
					currentOperation[2] = operationList.get(i).getProfession();	//User Profession
					
					break createCurrentOperation;
				}
			}
		}
		
	}
	
	public String[] getCurrentUser() 
	{
		return currentOperation;
		
	}

}
