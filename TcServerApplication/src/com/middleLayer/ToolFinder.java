package com.middleLayer;

import java.util.ArrayList;

public class ToolFinder {
	private OperationDetails operationDetails;
	private ArrayList<ToolProperties> currentTool = new ArrayList<ToolProperties>();

	//This tool id comes from AR client. When operator need to see the tool, this id will be sent by AR client to the server
	public ToolFinder(String toolId, String fileName, String revisionId) 
	{
		GetToolDataFromTeamcenter getToolDataFromTeamcenter = new GetToolDataFromTeamcenter(toolId, fileName, revisionId);
		for(int i = 0; i < operationDetails.getCurrentOperationsToolList().size(); i++) 
		{
			if(toolId == operationDetails.getCurrentOperationsToolList().get(i).getToolId()) 
			{
				//TODO
				//GetToolDataFromTeamcenter will be called!!								
				currentTool.get(0).setToolCADFile(getToolDataFromTeamcenter.getToolData().get(0).getToolCADFile());
				break;
			}
		}
	}
	
	public ArrayList<ToolProperties> getToolCADFile()
	{
		return currentTool;
	}
}
