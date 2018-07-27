package com.middleLayer;

import java.io.IOException;
import java.util.ArrayList;

import com.properties.ToolProperties;
import com.xmlreaders.XMLReaderTool;

public class ToolFinder {
	private XMLReaderTool xmlReaderTool;
	private ArrayList<ToolProperties> currentTool = new ArrayList<ToolProperties>();

	//This tool id comes from AR client. When operator need to see the tool, this id will be sent by AR client to the server
	//These parameter will be sent (This method will be called) whenever the user wants to see the tool!
	//These informations are already stored in the 
	public ToolFinder(String toolId, String fileName, String revisionId) throws IOException 
	{
		GetToolDataFromTeamcenter getToolDataFromTeamcenter = new GetToolDataFromTeamcenter(toolId, fileName, revisionId);
		int size = xmlReaderTool.getToolPropertiesList().size();;
		for(int i = 0; i < size; i++) 
		{
			if(toolId == xmlReaderTool.getToolPropertiesList().get(i).getToolId()) 
			{
				currentTool.add(xmlReaderTool.getToolPropertiesList().get(i));
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
