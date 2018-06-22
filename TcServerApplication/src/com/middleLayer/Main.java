package com.middleLayer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.teamcenter.soa.client.FileManagementUtility;
import com.teamcenter.soa.common.ObjectPropertyPolicy;
import com.teamcenter.soa.exceptions.NotLoadedException;
import com.teamcenter.soa.client.model.strong.User;
import com.teamcenter.services.strong.core._2008_06.DataManagement.GetItemAndRelatedObjectsInfo;
import com.teamcenter.services.strong.core._2008_06.DataManagement.GetItemAndRelatedObjectsItemOutput;
import com.teamcenter.services.strong.core._2008_06.DataManagement.GetItemAndRelatedObjectsResponse;
import com.teamcenter.services.strong.core._2008_06.DataManagement.RevisionOutput;

import com.teamcenter.clientx.*;
import com.teamcenter.services.strong.core.DataManagementService;
import com.teamcenter.services.strong.core.SessionService;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	private AppXSession session;
	private User user;
	private FileManagementUtility fileManager;
	
	public Main(String teamcenterHost, String username, String password)
	{
		createTeamcenterSession(teamcenterHost, username, password);
	}
	
	@SuppressWarnings("null")
	private List<String> getInstructions(String id) throws Exception
	{
		List<String> amateurList = new ArrayList<String>();
		UserDetails userDetails = null;
		String[] gcu = userDetails.getCurrentUser(); 
		
	    try
	    {
	      amateurList = Files.readAllLines(getFilesFromTeamcenter(id, "").toPath());
	    }
	 
	    catch (IOException e)
	    {
	      e.printStackTrace();
	    }
	    
	    List<String> expertList = new ArrayList<String>();
	    List<String> mediumList = new ArrayList<String>();
	    
	    //Separate lists based on the users experience
	    for(int i = 0; i<amateurList.size(); i++) 
	    {
	    	if(amateurList.get(i) == "1")
	    		expertList.add(amateurList.get(i));
	    	
	    	if(amateurList.get(i) == "1" || amateurList.get(i) == "2")
	    		mediumList.add(amateurList.get(i));	
	    }
	    
	    
	    //Write the code which determines which list will be returned based on user level!!
	    if(Integer.parseInt(gcu[3]) == 1 || /*Something Represents the Amateur User*/) {
	    	return amateurList;
	    }
	    else if(Integer.parseInt(gcu[3]) == 2 /*Something Represents the Medium User*/) {
	    	return mediumList;
	    }
	    else {
	    	return expertList;
	    }
	}
	
	//Request for additional information
	@SuppressWarnings("null")
	public int updateUserStatus() throws NotLoadedException 
	{
		UserDetails userDetails = null;
		String[] gcu = userDetails.getCurrentUser(); 
		int status = Integer.parseInt(gcu[3]);
		
		if(Integer.parseInt(gcu[3]) != 3) 
		{
			status++;
		}
		else 
		{
			System.out.println("The information which has been displayed is the most basic information. Please contact with your supervisor for mor info");
		}
		
		return status;
	}
	
	//Get operation's Id and the instruction file
	
	/*public File getFilesFromTeamcenter(String id, String filename) throws Exception
	{
		DataManagementService dmService = DataManagementService.getService(AppXSession.getConnection());

		*//** Prepare the search criteria *//*
		GetItemAndRelatedObjectsInfo[] infos = new GetItemAndRelatedObjectsInfo[1];
		infos[0] = new GetItemAndRelatedObjectsInfo();
		*//** To be able to call the service method, a client ID seems to be needed (it does not have to be a UUID) *//*
		infos[0].clientId = clientId.toString();
		infos[0].itemInfo.ids = new AttrInfo[]{new AttrInfo()};
		String[] idParts = id.split("/");
		*//** Get items associated with the ID stored in the QR code *//*
		infos[0].itemInfo.ids[0] = new AttrInfo();
		infos[0].itemInfo.ids[0].name = "item_id";
		infos[0].itemInfo.ids[0].value = idParts[0];
		infos[0].itemInfo.useIdFirst = true;
		infos[0].revInfo.processing = "All"; // Process/retrieve all revisions associated to a found item
		infos[0].datasetInfo.filter.processing = "All"; // Process/retrieve all data sets associated to a found item
		*//** This filter only retrieves data sets with this particular name *//*
		*//** Note: It does not apply to the name of the actual file contained in the data set *//*
		infos[0].datasetInfo.filter.name = filename; // recycling_instructions.txt and recycling_instructions.png
		*//**********************//*
		*//** Call the services *//*
		*//**********************//*
		GetItemAndRelatedObjectsResponse response = dmService.getItemAndRelatedObjects(infos);
		if (response.output.length == 0)
		throw new Exception("No item with the ID " + id + " was found");
		else if (response.output.length > 1)
		throw new Exception("More than one item with the ID " + id + " was found");
		GetItemAndRelatedObjectsItemOutput itemOutput = response.output[0];
		*//** Print some debug info *//*
		String name = itemOutput.item.get_object_name();
		System.out.println("Item name: " + name);
		System.out.println("Item object string: " + itemOutput.item.get_object_string());
		System.out.println("Item ID: " + itemOutput.item.get_item_id());
		System.out.println("Object type: " + itemOutput.item.get_object_type());
		System.out.println("Object string: " + itemOutput.item.get_object_string());
	
		return null;
		
	}*/
	
	private void createTeamcenterSession(String host, String username, String password)
	{
		System.out.println("Starting a session on " + host); // prints on screen the host for the session
		this.session = new AppXSession (host);
		System.out.println("Logging in with username " + username); // prints on screen the session's user
		this.user = this.session.login(username, password); // uses username and password to log into Teamcenter
		
		try
		{
			System.out.println("logged in as user " + this.user.get_user_name());
		}
		catch (NotLoadedException e)
		{
			throw new RuntimeException(e);
		}
		/** Get the session service and set the object property policy in Teamcenter */
		SessionService sessionService = SessionService.getService(AppXSession.getConnection());
		ObjectPropertyPolicy oppolicy = new ObjectPropertyPolicy();
		String path = Main.class.getResource("/oppolicy.xml").toString();
		
		try
		{
			oppolicy.load(path);
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
		
		sessionService.setObjectPropertyPolicy(oppolicy);
		this.fileManager = new FileManagementUtility(AppXSession.getConnection()); // Get and cache the file manager
	}

	
}
