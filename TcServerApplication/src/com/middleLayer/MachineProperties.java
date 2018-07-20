package com.middleLayer;


public class MachineProperties {
	
	private String id;
	private String xAxis;
	private String yAxis;
	private String errorState;
	private String machineCADFile;
	
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

}
