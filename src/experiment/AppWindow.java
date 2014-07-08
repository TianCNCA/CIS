package experiment;

import java.util.ArrayList;
import java.util.Collections;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import cis.buisness.Client;
import cis.buisness.DataAccess;
import cis.buisness.Soap;
import cis.buisness.SoapBox;
import cis.presentation.SoapWindow;

import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.hsqldb.Tokens;

public class AppWindow extends Shell {
	private DataAccess dataAccess;
	private Table table;
	private TableItem selected;
	private Client selected_client;
	private Button btnAddClient;
	private Button btnEditClient;
	private Text text;
	Label lblClientName;
	private Button btnSearch;
	private Button btnClear;
	private Table soapTable;
	private TableColumn tblclmnDate;
	private TableColumn tblclmnSoap;

	//TODO delete this
	
	public AppWindow(Display display, DataAccess dataAccess) {
		super(display);
		setMinimumSize(new Point(794, 518));
		this.dataAccess = dataAccess;

		setText("Client Information System");
		setSize(794, 518);
		setLayout(new FormLayout());
		setBackgroundImage( new Image( null, "images/bg.png") );

		
		soapTable = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		FormData fd_soapTable = new FormData();
		fd_soapTable.top = new FormAttachment(text, 127);
		fd_soapTable.right = new FormAttachment(100, -10);
		soapTable.setLayoutData(fd_soapTable);
		soapTable.setHeaderVisible(true);
		soapTable.setLinesVisible(true);
		
		tblclmnDate = new TableColumn(soapTable, SWT.NONE);
		soapTable.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
		        TableItem[] selection = table.getSelection();
		        selected = selection[0];
		        //TODO
		        System.out.println("Selection={" + selected + "}");
			}
		});
		tblclmnDate.setWidth(115);
		tblclmnDate.setText("Date");
		
		tblclmnSoap = new TableColumn(soapTable, SWT.NONE);
		tblclmnSoap.setWidth(318);
		tblclmnSoap.setText("Soap");
		
		lblClientName = new Label(this, SWT.NONE);
		FormData fd_lblClientName = new FormData();
		fd_lblClientName.bottom = new FormAttachment(soapTable, -6);
		lblClientName.setLayoutData(fd_lblClientName);
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		FormData fd_table = new FormData();
		fd_table.right = new FormAttachment(soapTable, -20);
		fd_table.left = new FormAttachment(0, 10);
		table.setLayoutData(fd_table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				btnEditClient.setEnabled(true);
		        TableItem[] selection = table.getSelection();
		        selected = selection[0];
		        select(selected);
		        fillSoaps();
		        System.out.println("Selection={" + selected + "}");
			}
		});

		TableColumn tblclmnName = new TableColumn(table, SWT.NONE);
		tblclmnName.setWidth(296);
		tblclmnName.setText("Name");
		ArrayList<Client> clients = dataAccess.getAllClients();
		fillTable(table, clients);
		selected_client = clients.get(0);
		fillSoaps();

		btnAddClient = new Button(this, SWT.NONE);
		fd_table.bottom = new FormAttachment(100, -41);
		btnAddClient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				addClient();
			}
		});
		FormData fd_btnAddClient = new FormData();
		fd_btnAddClient.top = new FormAttachment(table, 6);
		fd_btnAddClient.left = new FormAttachment(0, 156);
		btnAddClient.setLayoutData(fd_btnAddClient);
		btnAddClient.setText("Add Client");
		
		btnEditClient = new Button(this, SWT.NONE);
		fd_btnAddClient.right = new FormAttachment(btnEditClient, -6);
		btnEditClient.setEnabled(false);
		btnEditClient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				editClient();
			}
		});
		FormData fd_btnEditClient = new FormData();
		fd_btnEditClient.top = new FormAttachment(table, 6);
		fd_btnEditClient.right = new FormAttachment(table, 0, SWT.RIGHT);
		fd_btnEditClient.left = new FormAttachment(0, 236);
		btnEditClient.setLayoutData(fd_btnEditClient);
		btnEditClient.setText("Edit Client");
		
		Button btnExit = new Button(this, SWT.NONE);
		fd_soapTable.bottom = new FormAttachment(100, -41);
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				getShell().dispose();
			}
		});
		FormData fd_btnExit = new FormData();
		fd_btnExit.top = new FormAttachment(btnAddClient, 0, SWT.TOP);
		fd_btnExit.left = new FormAttachment(soapTable, -74);
		fd_btnExit.right = new FormAttachment(soapTable, 0, SWT.RIGHT);
		btnExit.setLayoutData(fd_btnExit);
		btnExit.setText("Exit");
		
		text = new Text(this, SWT.BORDER);
		fd_lblClientName.top = new FormAttachment(text, 0, SWT.TOP);
		text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
					searchClient();
			}
		});
		FormData fd_text = new FormData();
		fd_text.left = new FormAttachment(0, 10);
		fd_text.top = new FormAttachment(0, 97);
		text.setLayoutData(fd_text);
		
		btnSearch = new Button(this, SWT.NONE);
		fd_lblClientName.right = new FormAttachment(btnSearch, 494);
		fd_table.top = new FormAttachment(btnSearch, 7);
		fd_text.right = new FormAttachment(btnSearch, -6);
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				searchClient();
			}
		});
		FormData fd_btnSearch = new FormData();
		fd_btnSearch.top = new FormAttachment(0, 95);
		fd_btnSearch.left = new FormAttachment(0, 199);
		btnSearch.setLayoutData(fd_btnSearch);
		btnSearch.setText("Search");
		
		btnClear = new Button(this, SWT.NONE);
		fd_lblClientName.left = new FormAttachment(btnClear, 20);
		fd_soapTable.left = new FormAttachment(0, 330);
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				refreshTable();
				text.setText( "" );
			}
		});
		FormData fd_btnClear = new FormData();
		fd_btnClear.right = new FormAttachment(table, 0, SWT.RIGHT);
		fd_btnClear.left = new FormAttachment(btnSearch, 6);
		fd_btnClear.top = new FormAttachment(0, 95);
		btnClear.setLayoutData(fd_btnClear);
		btnClear.setText("Clear");
		
		Label lblClientInformationSystem = new Label(this, SWT.NONE);
		lblClientInformationSystem.setLayoutData(new FormData());
		lblClientInformationSystem.setImage(new Image(null, "images/logo.png"));
		lblClientInformationSystem.setAlignment(SWT.CENTER);
		lblClientInformationSystem.setBounds(0, 0, 580, 196);
		
		Button btnAddSoap = new Button(this, SWT.NONE);
		btnAddSoap.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent arg0) {
				SoapWindow window = new SoapWindow(selected_client.getName());
				window.open();
			}
		});
		FormData fd_btnAddSoap = new FormData();
		fd_btnAddSoap.right = new FormAttachment(lblClientName, 74);
		fd_btnAddSoap.left = new FormAttachment(btnEditClient, 20);
		fd_btnAddSoap.top = new FormAttachment(btnAddClient, 0, SWT.TOP);
		btnAddSoap.setLayoutData(fd_btnAddSoap);
		btnAddSoap.setText("Add Soap");
	}

	public void refreshTable() {
		clearTable();
		ArrayList<Client> clients = dataAccess.getAllClients();
		fillTable(table, clients);
		btnEditClient.setEnabled(false);
		
	}
	
	private void clearTable() {
		table.removeAll();
	}
	
	private void clearSoapTable(){
		soapTable.removeAll();
	}

	private void fillSoaps()
	{
		clearSoapTable();

		lblClientName.setText(selected_client.getName());
		
		SoapBox soapbox = selected_client.getSoaps();
		ArrayList<Soap> soaps = soapbox.getSoaps();
		final TableColumn[] columns = soapTable.getColumns();
		Collections.sort( soaps );

		for (int i = 0; i < soaps.size(); i++) 
		{
			final TableItem item = new TableItem(soapTable, SWT.NONE);
			String parsedDateString = "";
			String tokens[] = soaps.get( i ).getDate().toString().split( "\\s+" );
			
			if ( tokens.length > 0 )
			{
				parsedDateString = tokens[0] + " " + tokens[1] + " " + tokens[2];
			}

			item.setText( new String[] { parsedDateString, soaps.get(i).getInfo() } );
		}
		
		for (int i = 0; i < columns.length; i++)
		{
			columns[i].pack();
		}

	}

	
	private void select(TableItem selected){
		selected_client = dataAccess.readClient(selected.getText());
	}
	
	private void fillTable(Table table, ArrayList<Client> clients) {
		final TableColumn[] columns = table.getColumns();
		Collections.sort( clients );

		for (int i = 0; i < clients.size(); i++) {
			final TableItem item = new TableItem(table, SWT.NONE);
			item.setText(clients.get(i).getName());
		}

		for (int i = 0; i < columns.length; i++)
			columns[i].pack();

	}

	private void addClient() {
		Shell clientWindow = new ClientWindow(this, dataAccess,
				null);
		clientWindow.open();
		clientWindow.layout();
	}
	
	private void editClient() {
		Shell clientWindow = new ClientWindow(this, dataAccess,
				dataAccess.readClient(selected.getText()));
		clientWindow.open();
		clientWindow.layout();
		
	}
	
	private void searchClient() {
		clearTable();
		String partialText = text.getText();
		//System.out.println("Chars:" + partialText);
		ArrayList<Client> clients = dataAccess.getAllClients();
		ArrayList<Client> searchList = new ArrayList<Client>();
		
		for ( Client client : clients )
		{
			String clientName = client.getName().toLowerCase();
			String searchText = partialText.toLowerCase();
			if ( clientName.contains( searchText ) )
			{
				searchList.add( client );
			}
		}
	
		fillTable(table, searchList);	
	}
	
	private void messageBox(String text, String message, int style) {
		MessageBox msg = new MessageBox(getShell(), style);
		msg.setText(text);
		msg.setMessage(message);
		msg.open();
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
