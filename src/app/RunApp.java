package app;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import acceptanceTests.Register;
import acceptanceTests.EventLoop;
import cis.business.DataAccess;
import cis.presentation.AppWindow;


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
		
		DataAccess dataAccess = new DataAccess();

		// open the main window
		try {
			Display display = Display.getDefault();
			Shell appWindow = new AppWindow(display, dataAccess);
			
			Image icon = new Image( display, "images/icon.ico" );
			appWindow.setImage( icon );
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
