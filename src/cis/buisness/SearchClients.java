package cis.buisness;

import java.util.ArrayList;

public class SearchClients
{
	private DataAccess dataAccess;
	private ArrayList<Client> clientList = dataAccess.getAllClients();

	SearchClients(){};
	
	ArrayList<Client> SearchClient(String clientName)
	{
		ArrayList<Client> searchSet = new ArrayList<Client>();
		
		for (Client c : clientList)
		{
			if (c.getName().toLowerCase().contains(clientName.toLowerCase())) // TODO: code smell? more like code REEKS! refactor this ASAP
				{
					searchSet.add(c);
				}
		}
		return searchSet;
	}
	
}