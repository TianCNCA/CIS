package cis.objects;

import java.util.UUID;

import app.DBService;

public class BodyDiagram 
{
	private UUID clientID, key;
	private int width, height;
	
	public BodyDiagram( UUID clientID, UUID key )
	{
		this.clientID = clientID;
		this.key = key;
	}
	
	public BodyDiagram( UUID clientID )
	{
		this.clientID = clientID;
		key = null;
	}
	
	
	public void setKey( UUID key )
	{
		if ( key != null )
		{
			this.key = key;
		}
	}

	public UUID getKey()
	{
		return key;
	}
	
	public void setClientID( UUID clientID )
	{
		if ( clientID != null )
		{
			this.clientID = clientID;
		}
	}
	
	public UUID getClientID()
	{
		return clientID;
	}
}
