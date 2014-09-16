package cis.objects;

import java.util.UUID;

import app.DBService;

public class Signature 
{
	private UUID clientID, key;
	private int width, height;
	
	public Signature( UUID clientID, UUID key )
	{
		this.clientID = clientID;
		this.key = key;
	}
	
	public Signature( UUID clientID )
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
