package cis.buisness;

import java.util.*;

public class SoapBox
{
	private ArrayList<Soap> visits;
	private UUID clientID;
	private int order;


	public SoapBox( UUID clientID )
	{
		visits = new ArrayList<Soap>();
		this.clientID = clientID;
		order = 0;
	}


	public SoapBox()
	{
		visits = new ArrayList<Soap>();
		clientID = null;
		order = 0;
	}


	public void add( Date date, String info )
	{
		Soap newSoap = new Soap( date, info );
		newSoap.setOrder( order );
		order++;
		visits.add( newSoap );
		
	}
	
	
	public void add( Soap soap )
	{
		soap.setOrder( order );
		order++;
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
		//Collections.sort( visits );
		System.out.println("Not Sorted: " + visits);
		//sort();
		System.out.println("Sorted: " + visits);
		return visits;
	}
	
	
	public void sort()
	{
		int j;
		for ( int i = 1; i < visits.size(); i++ )
        {
			Soap tempVisit = visits.get( i );
			
	        for ( j = i; j > 0; j-- )
            {
	            if ( visits.get( j - 1 ).getOrder() < tempVisit.getOrder() )
	            {
	            	visits.set( j, visits.get( j - 1 ) );
	            }
            }
	        
	        visits.set( j, tempVisit );
        }
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
	
	public void setOrder( int order )
	{
		if ( order > 0 )
		{
			this.order = order;
		}
	}
	
	public int getOrder()
	{
		return order;
	}
}
