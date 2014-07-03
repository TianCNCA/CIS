package cis.buisness;

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
		this.discription 	= discription;
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
			discription = "";
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
