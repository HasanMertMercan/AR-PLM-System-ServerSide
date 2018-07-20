package com.middleLayer;

import java.io.IOException;
import java.util.ArrayList;

public class GetToolDataFromTeamcenter {
	
	//TODO
	//Bring tool CAD file for specified toolId.
	//toolId comes from ToolFinder
	//This method will be called in ToolFinder
	
	private ArrayList<ToolProperties> toolData = new ArrayList<ToolProperties>();
	
	public GetToolDataFromTeamcenter(String toolId, String fileName, String revisionId) throws IOException 
	{
		CADConverter cadConverter = new CADConverter(toolId, fileName, revisionId);
		toolData.get(0).setToolCADFile(cadConverter.getCADFileFinal()); //This slot will be filled by String
	}

	public ArrayList<ToolProperties> getToolData()
	{
		return toolData;
	}
}
