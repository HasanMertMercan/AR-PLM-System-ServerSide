package com.middleLayer;

import java.util.ArrayList;
import java.util.List;

import com.teamcenter.soa.client.model.strong.User;
import com.teamcenter.soa.exceptions.NotLoadedException;

public class OperationDetails {
	
	private List<OperationProperties> operationList = new ArrayList<OperationProperties>();
	private XMLReaderOperation xmlReaderOperation = new XMLReaderOperation();
	private String[] currentOperation = null;

	public OperationDetails() 
	{
		operationList = xmlReaderOperation.getOperationPropertiesList();
		
		createCurrentOperation: {
			for(int i = 0; i < operationList.size(); i++) 
			{
				try {
					if(operationList.get(i).getId() == user.get_user_id()) 	//Compare with operation's id not with userid
					{
						currentOperation[0] = operationList.get(i).getId();			//User Id
						currentOperation[1] = operationList.get(i).getName();		//User Name
						currentOperation[2] = operationList.get(i).getProfession();	//User Profession
						
						break createCurrentOperation;
					}
				} catch (NotLoadedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public String[] getCurrentUser() 
	{
		return currentOperation;
		
	}

}
