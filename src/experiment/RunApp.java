package experiment;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import cis.buisness.DataAccess;
import app.DBService;

import acceptanceTests.Register;
import acceptanceTests.EventLoop;

public class RunApp {

	public static void main(String[] args) {
		// initialize the data base
		DBService service = new DBService();
		service.initializeDB();
		if (!service.getValid()) {
			System.out.println("Error in building DB, exiting now");
			service.shutDownDB();
			System.exit(0);
		}
		service.genClients();
		DataAccess dataAccess = new DataAccess();
		

		// open the main window
		try {
			Display display = Display.getDefault();
			Shell appWindow = new AppWindow(display, dataAccess);
			appWindow.open();
			appWindow.layout();

			if (EventLoop.isEnabled())
			{
				while (!appWindow.isDisposed()) {
					if (!display.readAndDispatch()) {
						display.sleep();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		service.shutDownDB();
	}

}
