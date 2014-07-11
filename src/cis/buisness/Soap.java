package cis.buisness;

import java.util.*;

import app.DBService;

public class Soap implements Comparable<Soap>
{
	private Date    date;
	private String  info;
	private UUID 	key;


	public Soap()
	{
		date = null;
		info = null;
		key  = null;
	}

	public Soap( Date date, String info )
	{
		this.date = date;
		this.info = info;
		key  = null;
	}


	@Override
	public String toString()
	{
		return info + " - " + date.toString();
	}


	@Override
	public int compareTo( Soap other )
	{
		return this.date.compareTo( other.getDate() );
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
	
	
	public void genKey()
	{
		key = UUID.randomUUID();
	}
}
