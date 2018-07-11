package com.middleLayer;

import java.io.File;

public class ToolProperties {
	
	private String _toolId;
	private String _toolName;
	private File _toolCADFile;
	
	public String getToolId() 
	{
		return _toolId;
	}
	
	public void setToolId(String toolId) 
	{
		this._toolId = toolId;
	}
	
	public String getToolName() 
	{
		return _toolName;
	}
	
	public void setToolName(String toolName) 
	{
		this._toolName = toolName;
	}
	
	public File getToolCADFile() 
	{
		return _toolCADFile;
	}
	
	public void setToolCADFile(File toolCADFile) 
	{
		this._toolCADFile = toolCADFile;
	}
	

}
