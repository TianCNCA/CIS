package cis.presentation;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;



public class ExampleWindow {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) display.sleep();
		}
		display.dispose();
	}

}
