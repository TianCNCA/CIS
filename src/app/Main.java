package app;
import db.DBIntermediary;
import buisness.Client;

public class Main 
{
	public static final String DBName = "CIS";
	
	public static void main( String[] args )
	{
		DBIntermediary dataBase = new DBIntermediary();
		Client test1 = new Client( "Bob", "Saggat" );
		Client test2 = new Client( "Travis", "Almighty" );
		Client test3 = new Client( "Chris", "IsAwesome" );
		Client test4 = new Client( "Yuri", "Ukraine" );
		
		System.out.println( test1 );
		//Test the stub
		dataBase.insertClient( test1 );
		dataBase.insertClient( test2 );
		dataBase.insertClient( test3 );
		dataBase.insertClient( test4 );
		dataBase.DumpDB();
	}
}
