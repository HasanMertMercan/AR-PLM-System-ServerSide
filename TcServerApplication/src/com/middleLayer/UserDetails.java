package com.middleLayer;

import com.middleLayer.XMLReaderUser;
import java.util.ArrayList;
import java.util.List;
import com.teamcenter.soa.client.model.strong.User;
import com.teamcenter.soa.exceptions.NotLoadedException;

public class UserDetails {
	
	private User user;
	private List<UserProperties> userList = new ArrayList<UserProperties>();
	private XMLReaderUser xmlReaderUser = new XMLReaderUser();
	private String[] currentUser = null;

	public UserDetails() 
	{
		userList = xmlReaderUser.getUserPropertiesList();
		
		createCurrentUser: {
			for(int i = 0; i < userList.size(); i++) 
			{
				try {
					if(userList.get(i).getId() == user.get_user_id()) 
					{
						currentUser[0] = userList.get(i).getId();			//User Id
						currentUser[1] = userList.get(i).getName();			//User Name
						currentUser[2] = userList.get(i).getProfession();	//User Profession
						currentUser[3] = userList.get(i).getLevel();		//User Level
						
						break createCurrentUser;
					}
				} catch (NotLoadedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("User " + user + "could not load!");
				}
			}
		}
		
	}
	
	public String[] getCurrentUser() 
	{
		return currentUser;
		
	}
}
