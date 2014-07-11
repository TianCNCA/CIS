package cis.buisness;

import java.util.*;

public class SoapBox
{
	private ArrayList<Soap> visits;
	private UUID clientID;


	public SoapBox( UUID clientID )
	{
		visits = new ArrayList<Soap>();
		this.clientID = clientID;
	}


	public SoapBox()
	{
		visits = new ArrayList<Soap>();
		clientID = null;
	}


	public void add( Date date, String info )
	{
		Soap newSoap = new Soap( date, info );
		visits.add( newSoap );
	}
	
	
	public void add( Soap soap )
	{
		visits.add( soap );
	}


	public UUID getClientID()
	{
		return clientID;
	}


	public void setClientID( UUID clientID )
	{
		this.clientID = clientID;
	}
	
	public boolean isEmpty(){
		return(visits.size()==0);
	}


	public Soap getSoapByIndex( int i )
	{
		if ( visits.size() > 0 && i < visits.size() )
		{
			return visits.get( i );
		}
		else
		{
			return null;
		}
	}


	public ArrayList<Soap> getSoaps()
	{
		return visits;
	}
	
	
	public void updateSoap( Soap soapToUpdate )
	{
		if ( soapToUpdate == null )
		{
			return;
		}
		
		for ( Soap soap: visits )
		{
			if ( soap.getKey() == soapToUpdate.getKey() )
			{
				soap = soapToUpdate;
				break;
			}
		}
	}


	public int numSoaps()
	{
		return visits.size();
	}
}
