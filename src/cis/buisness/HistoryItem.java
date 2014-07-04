package cis.buisness;

public class HistoryItem
{
	private String 	discription;
	private Boolean checked;
	
	public HistoryItem()
	{
		discription = "null";
		checked 	= false;
	}
	
	public HistoryItem( String discription, Boolean checked )
	{
		if ( discription == null )
		{
			this.discription = "null";
		}
		else
		{
			this.discription 	= discription;
		}
		
		this.checked 		= checked;
	}
	
	public String getDisc()
	{
		return discription;
	}
	
	public Boolean getChecked()
	{
		return checked;
	}
	
	public void setDisc( String disc )
	{
		if ( disc != null )
		{
			discription = disc;
		}
		else
		{
			discription = "null";
		}
	}
	
	public void setCheck( Boolean check )
	{
		checked = check;
	}
	
	
	public void setCheckAndDisc( Boolean check, String disc )
	{
		checked = check;
		discription = disc;
	}
}
