package com.middleLayer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class GetOperationDataFromTeamcenter {

	private UserDetails userDetails = null;
	private String[] gcu = userDetails.getCurrentUser(); 
	private List<String> amateurList = new ArrayList<String>();
    private List<String> expertList = new ArrayList<String>();
    private List<String> mediumList = new ArrayList<String>();
	
	public GetOperationDataFromTeamcenter(String operationId) 
	{
		
	    try
	    {
	      amateurList = Files.readAllLines(getInstructionsFromTeamcenter(operationId, "").toPath());
	    }
	 
	    catch (IOException e)
	    {
	      e.printStackTrace();
	    }
	    
	    //Separate lists based on the users experience
	    for(int i = 0; i<amateurList.size(); i++) 
	    {
	    	if(amateurList.get(i) == "1")
	    		expertList.add(amateurList.get(i));
	    	
	    	if(amateurList.get(i) == "1" || amateurList.get(i) == "2")
	    		mediumList.add(amateurList.get(i));	
	    }
	    
	}
	
	public List<String> getInstructionList()
	{

	    //Write the code which determines which list will be returned based on user level!!
	    if(gcu[3].equals("3")/*|| operationType == userProfession*/) {
	    	return amateurList;
	    }
	    else if(gcu[3].equals("2")) {
	    	return mediumList;
	    }
	    else {
	    	return expertList;
	    }
	}
	
	private File getInstructionsFromTeamcenter(String operationId, String fileName) 
	{
		//TODO
		//Return associated instruction file from temacenter.
		return null;
	}
}
