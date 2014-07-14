package cis.objects;

public class HistoryItem
{
	private String 	discription;
	private Boolean checked;
	
	public HistoryItem()
	{
		discription = "";
		checked 	= false;
	}
	
	public HistoryItem( String discription, Boolean checked )
	{
		if ( discription == null )
		{
			this.discription = "";
		}
		else
		{
			this.discription = discription;
		}
		
		if ( checked == null )
		{
			this.checked = false;
		}
		else
		{
			this.checked = checked;
		}
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
		if ( disc == null)
		{
			discription = "";
		}
		else
		{
			discription = disc;
		}
	}
	
	public void setCheck( Boolean check )
	{
		if ( check == null )
		{
			check = false;
		}
		else
		{
			checked = check;
		}
	}
	
	
	public void setCheckAndDisc( Boolean check, String disc )
	{
		if ( check == null )
		{
			check = false;
		}
		else
		{
			checked = check;
		}
		
		if ( disc == null)
		{
			discription = "";
		}
		else
		{
			discription = disc;
		}
	}
}
