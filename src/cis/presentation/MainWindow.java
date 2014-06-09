package cis.presentation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

import db.DBIntermediary;
//import org.eclipse.wb.swt.SWTResourceManager; 

public class MainWindow extends Shell {
	private Text clientTextBox;
	private Table table;
	private DBIntermediary dataBase;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			MainWindow shell = new MainWindow(display);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * @param display
	 */
	public MainWindow(final Display display) {
		super(display, SWT.SHELL_TRIM);
		dataBase = new DBIntermediary();
		
		Button btnExit = new Button(this, SWT.NONE);
		btnExit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				display.dispose();
			}
		});
		btnExit.setBounds(615, 323, 75, 25);
		btnExit.setText("Exit");
		
		Button btnNewButton_1 = new Button(this, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) 
			{
				TableItem[] list;
				list = table.getSelection();
				
				//TODO search for the string here, list contains the whole row selected from the table
				//list[0] should contain First Name, will need to store them in a string
			}
		});
		btnNewButton_1.setBounds(591, 257, 137, 25);
		btnNewButton_1.setText("Edit Client Information");
		
		Button btnNewButton_2 = new Button(this, SWT.NONE);
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent arg0) {
				try {
					CreatClientWindow window = new CreatClientWindow(dataBase);
					window.open();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) 
			{
				//TODO add client creation GUI here
				//new ClientCreationWindow();
			}
		});
		btnNewButton_2.setBounds(591, 199, 137, 25);
		btnNewButton_2.setText("Add New Client");
		
		clientTextBox = new Text(this, SWT.BORDER);
		clientTextBox.setBounds(36, 116, 391, 21);
		
		Button btnNewButton_3 = new Button(this, SWT.NONE);
		btnNewButton_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) 
			{
				//get the string to be searched for
				String clientName = clientTextBox.getText();
				if(clientName != "")
				{
					//TODO search for the client
				}
			}
		});
		btnNewButton_3.setBounds(445, 116, 98, 21);
		btnNewButton_3.setText("Search");
		
		Label lblSearcgForA = new Label(this, SWT.NONE);
		lblSearcgForA.setBounds(36, 95, 167, 15);
		lblSearcgForA.setText("Enter client name to search for: ");
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(36, 153, 507, 242);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		createContents();
		
		//create 4 columns in the table
		TableColumn lName = new TableColumn(table, SWT.NONE);
		lName.setText("Last Name");
		TableColumn fName= new TableColumn(table, SWT.NONE);
		fName.setText("First Name");
		TableColumn address = new TableColumn(table, SWT.NONE);
		address.setText("Address");
		TableColumn city = new TableColumn(table, SWT.NONE);
		city.setText("City");
		
		lName.setWidth(116);
		fName.setWidth(100);
		address.setWidth(146);
		city.setWidth(140);
		
		Label lblClientInformationSystem = new Label(this, SWT.NONE);
		//lblClientInformationSystem.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblClientInformationSystem.setAlignment(SWT.CENTER);
		lblClientInformationSystem.setBounds(36, 24, 500, 40);
		lblClientInformationSystem.setText("Client Information System");
		
		final TableColumn [] columns = table.getColumns ();
		for (int i=0; i<12; i++) //iterate through the whole list here and fill the table with data
		{
			TableItem item = new TableItem (table, SWT.NONE);
			for (int j=0; j<columns.length; j++)
		    {
				//TODO add text here from the DB
				//item.setText (j, "Item " + i);
		    }
		}
		
		 for (int i=0; i<columns.length; i++) columns [i].pack ();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents()
	{
		setText("Client Information Information System");
		setSize(779, 477);
	}

	@Override
	protected void checkSubclass() 
	{
		// Disable the check that prevents subclassing of SWT components
	}
}
