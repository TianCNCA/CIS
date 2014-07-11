package cis.buisness;

import java.util.*;

public class Soap implements Comparable<Soap>
{
	private Date    date;
	private String  info;
	private UUID 	key;
	private int 	order;


	public Soap()
	{
		date = null;
		info = null;
		key  = null;
		order = -1;
	}

	public Soap( Date date, String info )
	{
		this.date = date;
		this.info = info;
		key  = null;
		order = -1;
	}


	@Override
	public String toString()
	{
		return info + " - " + date.toString() + " " + order;
	}


	@Override
	public int compareTo( Soap other )
	{
		return Integer.compare( other.getOrder(), order );
	}


	public void setDate( Date date )
	{
		this.date = date;
	}


	public void setInfo( String info )
	{
		this.info = info;
	}


	public Date getDate()
	{
		return date;
	}


	public String getInfo()
	{
		return info;
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
	
	
	public void setOrder( int order )
	{
		this.order = order;
	}
	
	public int getOrder()
	{
		return order;
	}
	
	
	public void genKey()
	{
		key = UUID.randomUUID();
	}
}
