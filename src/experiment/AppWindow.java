package experiment;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.custom.CBanner;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;

import cis.buisness.Client;
import cis.buisness.DataAccess;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class AppWindow extends Shell {
	private DataAccess dataAccess;
	private Table table;
	private Button btnAddClient;
	private Button btnEditClient;

	public AppWindow(Display display, DataAccess dataAccess) {
		super(display);
		this.dataAccess = dataAccess;

		setText("Client Information System");
		setSize(745, 484);
		setLayout(new FormLayout());

		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		FormData fd_table = new FormData();
		fd_table.top = new FormAttachment(0, 34);
		fd_table.left = new FormAttachment(0, 10);
		fd_table.bottom = new FormAttachment(100, -40);
		fd_table.right = new FormAttachment(0, 308);
		table.setLayoutData(fd_table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnName = new TableColumn(table, SWT.NONE);
		tblclmnName.setWidth(217);
		tblclmnName.setText("Name");
		fillTable(table);

		btnAddClient = new Button(this, SWT.NONE);
		btnAddClient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				addClient();
			}
		});
		FormData fd_btnAddClient = new FormData();
		fd_btnAddClient.bottom = new FormAttachment(table, -6);
		fd_btnAddClient.left = new FormAttachment(table, 0, SWT.LEFT);
		btnAddClient.setLayoutData(fd_btnAddClient);
		btnAddClient.setText("Add Client");
		
		btnEditClient = new Button(this, SWT.NONE);
		FormData fd_btnEditClient = new FormData();
		fd_btnEditClient.top = new FormAttachment(table, 6);
		fd_btnEditClient.left = new FormAttachment(table, 0, SWT.LEFT);
		btnEditClient.setLayoutData(fd_btnEditClient);
		btnEditClient.setText("Edit Client");
		
		Button btnExit = new Button(this, SWT.NONE);
		FormData fd_btnExit = new FormData();
		fd_btnExit.left = new FormAttachment(100, -78);
		fd_btnExit.bottom = new FormAttachment(100, -9);
		fd_btnExit.right = new FormAttachment(100, -10);
		btnExit.setLayoutData(fd_btnExit);
		btnExit.setText("Exit");

	}

	private void fillTable(Table table) {
		ArrayList<Client> clients = dataAccess.getAllClients();
		final TableColumn[] columns = table.getColumns();

		for (int i = 0; i < clients.size(); i++) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(clients.get(i).getName());
		}

		for (int i = 0; i < columns.length; i++)
			columns[i].pack();

	}

	private void addClient() {
		Shell clientWindow = new ClientWindow(this.getDisplay(), dataAccess,
				null);
		clientWindow.open();
		clientWindow.layout();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
