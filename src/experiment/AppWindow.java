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

import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.graphics.Point;

public class AppWindow extends Shell {
	private DataAccess dataAccess;
	private Table table;
	private TableItem selected;
	private Button btnAddClient;
	private Button btnEditClient;
	private Text text;
	private Button btnSearch;
	private Button btnClear;

	//TODO delete this
	
	public AppWindow(Display display, DataAccess dataAccess) {
		super(display);
		setMinimumSize(new Point(794, 518));
		this.dataAccess = dataAccess;

		setText("Client Information System");
		setSize(794, 518);
		setLayout(new FormLayout());
		setBackgroundImage( new Image( null, "images/bg.png") );
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		FormData fd_table = new FormData();
		fd_table.right = new FormAttachment(100, -331);
		fd_table.left = new FormAttachment(0, 10);
		fd_table.top = new FormAttachment(0, 93);
		fd_table.bottom = new FormAttachment(100, -53);
		table.setLayoutData(fd_table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				btnEditClient.setEnabled(true);
		        TableItem[] selection = table.getSelection();
		        selected = selection[0];
		        System.out.println("Selection={" + selected + "}");
			}
		});

		TableColumn tblclmnName = new TableColumn(table, SWT.NONE);
		tblclmnName.setWidth(217);
		tblclmnName.setText("Name");
		ArrayList<Client> clients = dataAccess.getAllClients();
		fillTable(table, clients);

		btnAddClient = new Button(this, SWT.NONE);
		btnAddClient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				addClient();
			}
		});
		FormData fd_btnAddClient = new FormData();
		fd_btnAddClient.top = new FormAttachment(table, 6);
		btnAddClient.setLayoutData(fd_btnAddClient);
		btnAddClient.setText("Add Client");
		
		btnEditClient = new Button(this, SWT.NONE);
		btnEditClient.setEnabled(false);
		btnEditClient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				editClient();
			}
		});
		FormData fd_btnEditClient = new FormData();
		fd_btnEditClient.top = new FormAttachment(btnAddClient, 0, SWT.TOP);
		fd_btnEditClient.left = new FormAttachment(btnAddClient, 6);
		btnEditClient.setLayoutData(fd_btnEditClient);
		btnEditClient.setText("Edit Client");
		
		Button btnExit = new Button(this, SWT.NONE);
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				getShell().dispose();
			}
		});
		FormData fd_btnExit = new FormData();
		fd_btnExit.left = new FormAttachment(100, -78);
		fd_btnExit.bottom = new FormAttachment(100, -9);
		fd_btnExit.right = new FormAttachment(100, -10);
		btnExit.setLayoutData(fd_btnExit);
		btnExit.setText("Exit");
		
		text = new Text(this, SWT.BORDER);
		text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
					searchClient();
			}
		});
		FormData fd_text = new FormData();
		fd_text.top = new FormAttachment(table, 11);
		fd_text.left = new FormAttachment(0, 10);
		text.setLayoutData(fd_text);
		
		btnSearch = new Button(this, SWT.NONE);
		fd_text.right = new FormAttachment(btnSearch, -15);
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				searchClient();
			}
		});
		FormData fd_btnSearch = new FormData();
		fd_btnSearch.top = new FormAttachment(table, 6);
		fd_btnSearch.left = new FormAttachment(0, 191);
		btnSearch.setLayoutData(fd_btnSearch);
		btnSearch.setText("Search");
		
		btnClear = new Button(this, SWT.NONE);
		fd_btnAddClient.left = new FormAttachment(btnClear, 6);
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				refreshTable();
				text.setText( "" );
			}
		});
		FormData fd_btnClear = new FormData();
		fd_btnClear.top = new FormAttachment(table, 6);
		fd_btnClear.left = new FormAttachment(btnSearch, 6);
		fd_btnClear.right = new FormAttachment(100, -476);
		btnClear.setLayoutData(fd_btnClear);
		btnClear.setText("Clear");
		
				Label lblClientInformationSystem = new Label(this, SWT.NONE);
				lblClientInformationSystem.setLayoutData(new FormData());
				lblClientInformationSystem.setImage(new Image(null, "images/logo.png"));
				lblClientInformationSystem.setAlignment(SWT.CENTER);
				lblClientInformationSystem.setBounds(0, 0, 580, 196);
	}

	public void refreshTable() {
		clearTable();
		ArrayList<Client> clients = dataAccess.getAllClients();
		fillTable(table, clients);
		btnEditClient.setEnabled(false);
		
	}
	
	private void clearTable() {
		table.removeAll();;
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
		System.out.println("CHars:" + partialText);
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
