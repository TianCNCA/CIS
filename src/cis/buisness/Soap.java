package cis.buisness;

import java.util.*;

import app.DBService;

public class Soap implements Comparable<Soap>
{
	private Date    date;
	private String  info;
	private int 	key;


	public Soap()
	{
		date = null;
		info = null;
		key  = DBService.getCurrentKey();
	}

	public Soap( Date date, String info )
	{
		this.date = date;
		this.info = info;
		key = DBService.getCurrentKey();
	}


	@Override
	public String toString()
	{
		return info + " - " + date.toString();
	}


	@Override
	public int compareTo( Soap other )
	{
		//return this.date.compareTo( other.getDate() );
		return 1;
	}


	public void setDate( Date date )
	{
		this.date = date;
	}


	public void setInfo( String info )
	{
		this.info = info;
	}


	public String getDate()
	{
		return date.toString();
	}


	public String getInfo()
	{
		return info;
	}
	
	
	public void setKey( int key )
	{
		if ( key > -1 )
		{
			this.key = key;
		}
	}
	
	
	public int getKey()
	{
		return key;
	}
}
