package buisness;

/*------------------------------------------------------
* CLASS:			Client
*
* REMARKS:			
*
------------------------------------------------------*/
public class Client 
{
	private String 	firstName;
	private String 	lastName;
	private int 	id;
	
	public Client( String firstName, String lastName )
	{
		this.firstName = firstName;
		this.lastName 	= lastName;
		setID();
	}
	
	//Default constructor
	public Client()
	{
		id = -999;
		firstName = null;
		lastName = null;
	}
	
	public Boolean equals( Client rhs )
	{
		return ( this.id == rhs.getID() );
	}
	
	public String getFullName()
	{
		return ( firstName + lastName );
	}
	
	public int getID()
	{
		return id;
	}
	
	public void setID()
	{
		id = 0;
	}
	
	public String toString()
	{
		return ( firstName + " " + lastName );
	}
}
