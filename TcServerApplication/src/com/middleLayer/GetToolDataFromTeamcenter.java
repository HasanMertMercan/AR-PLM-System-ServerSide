package com.middleLayer;

import java.util.ArrayList;

public class GetToolDataFromTeamcenter {
	
	//TODO
	//Bring tool CAD file for specified toolId.
	//toolId comes from ToolFinder
	//This method will be called in ToolFinder
	
	private ArrayList<ToolProperties> toolData = new ArrayList<ToolProperties>();
	
	public GetToolDataFromTeamcenter(String toolId, String fileName, String revisionId) 
	{
		GetFileFromTeamcenter getFileFromTeamcenter = new GetFileFromTeamcenter(toolId, fileName, revisionId);
		//The CAD converter will be called here!!
		toolData.get(0).setToolCADFile(getFileFromTeamcenter.getFile());
	}

	public ArrayList<ToolProperties> getToolData()
	{
		return toolData;
	}
}
