package app;

import org.eclipse.swt.widgets.Display;

import cis.persistance.DataBaseAccess;
import cis.presentation.MainWindow;

public class Main
{
	private static DataBaseAccess database;
	
	public static void main( String[] args )
	{
		database = new DataBaseAccess();
		database.init();
		database.genClients();
		
		new MainWindow();
		
		database.shutdownDB();
	}
	
	public static DataBaseAccess getDB()
	{
		return database;
	}
}
