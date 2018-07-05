package com.middleLayer;

public class OperationProperties {
	
	String _id;
	String _name;
	String _profession;
	String[] _toolIds;
	
	public String getName() {
        return _name;
    }
    public void setName(String name) {
        this._name = name;
    }
    public String getId() {
        return _id;
    }
    public void setId(String id) {
        this._id = id;
    }
    public String getProfession() {
        return _profession;
    }
    public void setProfession(String profession) {
        this._profession = profession;
    }
    
    public String[] getToolIds() 
    {
    	return _toolIds;
    }
    
    public void setToolIds(String[] toolIds) 
    {
    	this._toolIds = toolIds;
    }

}
