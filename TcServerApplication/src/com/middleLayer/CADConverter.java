package com.middleLayer;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class CADConverter {
	
	private String objText = "";
	private String CADtxtFile;
	private String CADFileFinal;
	private File oldFile, actualFile;
	private String newDirection, path, newFileName;
	private String[] arr;
	
	//This method will be called whenever a jt file taken from the teamcenter (Machine data, tool data)
	public CADConverter(String id, String fileName, String revisionId) throws IOException 
	{
		GetFileFromTeamcenter getFileFromTeamcenter = new GetFileFromTeamcenter(id, fileName, revisionId);
		oldFile = new File(getFileFromTeamcenter.getFile().getAbsolutePath());
		newDirection = "C:\\CADConversion";
		actualFile = new File(newDirection, fileName);
		FileUtils.copyFile(oldFile, actualFile);
		new ExportOBJ(actualFile.getAbsolutePath()); //This method creates .obj file in the same path!
		path = actualFile.getAbsolutePath();
		arr = path.split(".");
		newFileName = arr[0] + ".obj"; 
		CADtxtFile = changeFileExtension(newFileName); //The new file which comes from converter will be the parameter of this line
		CADFileFinal = readCADtxtFile(CADtxtFile); //This is the final string for Objects CAD data
		
	}
	
	//This method allows us to convert file to .txt
	private String changeFileExtension(String OBJFileName	/*This will be the filename and path comes from OBJ converter*/) 
	{
		String[] extension = OBJFileName.split(".");
		File txtFile = new File(extension[0] + ".txt");
		OBJFileName = txtFile.getPath();
		
		return OBJFileName;
	}
	
	private String readCADtxtFile(String TxtFilePath) throws IOException 
	{
		String line = "";
		BufferedReader br = new BufferedReader(new FileReader(TxtFilePath)); 
		while((line = br.readLine()) != null) 
		{
			objText += line;
			objText += "\n";
		}
		objText = objText.substring(0, objText.length() - 2);
		br.close();
		
		return objText;
	}
	
	public String getCADFileFinal() 
	{
		return CADFileFinal;
	}
}
