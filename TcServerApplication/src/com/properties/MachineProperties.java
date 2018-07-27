package com.properties;


public class MachineProperties {
	
	private String id;
	private String xAxis;
	private String yAxis;
	private String fileName;
	private String revisionId;
	private String errorState;
	private String machineCADFile;
	private String operationToDo;
	
	public String getMachineCADFile() {
		return machineCADFile;
	}
	public void setMachineCADFile(String machineCADFile) {
		this.machineCADFile = machineCADFile;
	}
	public String getxAxis() {
        return xAxis;
    }
    public void setxAxis(String xAxis) {
        this.xAxis = xAxis;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getyAxis() {
        return yAxis;
    }
    public void setyAxis(String yAxis) {
        this.yAxis = yAxis;
    }
    
    public String getErrorState() 
    {
    	return errorState;
    }
    
    public void setErrorState(String errorState) 
    {
    	this.errorState = errorState;
    }
	public String getOperationToDo() {
		return operationToDo;
	}
	public void setOperationToDo(String operationToDo) {
		this.operationToDo = operationToDo;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getRevisionId() {
		return revisionId;
	}
	public void setRevisionId(String revisionId) {
		this.revisionId = revisionId;
	}

}
