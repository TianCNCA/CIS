package cis.presentation;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;

import cis.buisness.Client;
import cis.buisness.DataAccess;
import cis.buisness.Soap;

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class SoapBoxWindow extends Shell {
	private Table table;
	private Client theClient;
	DataAccess dataAccess;
	
	public SoapBoxWindow(String clientName) {
		dataAccess = new DataAccess();
		this.theClient = dataAccess.readClient(clientName);
		open();
	}
	
	public void open(){
		
		setMinimumSize(new Point(550, 175));
		setText("Soap Box");

		setLayout(new FormLayout());
		
		Button btnClose = new Button(this, SWT.NONE);
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent arg0) {
				getShell().dispose();
			}
		});
		FormData fd_btnClose = new FormData();
		fd_btnClose.right = new FormAttachment(100, -10);
		btnClose.setLayoutData(fd_btnClose);
		btnClose.setText("Close");
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		fd_btnClose.top = new FormAttachment(table, 5);

		FormData fd_table = new FormData();
		fd_table.right = new FormAttachment(btnClose, 0, SWT.RIGHT);
		fd_table.bottom = new FormAttachment(100, -40);
		fd_table.left = new FormAttachment(0, 10);
		table.setLayoutData(fd_table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("Date");
		
		ArrayList<Soap> soaps = theClient.getSoapBox().getSoaps();
		final TableColumn [] columns = table.getColumns ();
		for (int i=0; i<soaps.size(); i++) //iterate through the whole list here and fill the table with data
		{
			TableItem item = new TableItem (table, SWT.NONE);
			for (int j=0; j<columns.length; j++)
		    {
				item.setText(soaps.get(i).getDate().toString());
		    }
		}
		
		for (int i=0; i<columns.length; i++)
			columns[i].pack();
		
		TableColumn tblclmnSoap = new TableColumn(table, SWT.NONE);
		tblclmnSoap.setToolTipText("The information about the client's visit on this day");
		tblclmnSoap.setWidth(400);
		tblclmnSoap.setText("Soap");

		final TableColumn [] soapColumns = table.getColumns ();
		for (int i=0; i<soaps.size(); i++) //iterate through the whole list here and fill the table with data
		{
			TableItem item = new TableItem (table, SWT.NONE);
			for (int j=0; j<soapColumns.length; j++)
		    {
				item.setText(soaps.get(i).getInfo());
		    }
		}
		
		for (int i=0; i<soapColumns.length; i++)
			soapColumns[i].pack();
		
		Button btnAdd = new Button(this, SWT.NONE);
		fd_btnClose.left = new FormAttachment(btnAdd, 6);
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent arg0) {
				SoapWindow window = new SoapWindow(theClient);
				window.open();
			}
		});
		FormData fd_btnAdd = new FormData();
		fd_btnAdd.top = new FormAttachment(btnClose, 0, SWT.TOP);
		fd_btnAdd.right = new FormAttachment(100, -76);
		fd_btnAdd.left = new FormAttachment(100, -123);
		btnAdd.setLayoutData(fd_btnAdd);
		btnAdd.setText("Add");
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		fd_table.top = new FormAttachment(lblNewLabel, 6);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.right = new FormAttachment(100, -131);
		fd_lblNewLabel.left = new FormAttachment(0, 10);
		fd_lblNewLabel.bottom = new FormAttachment(100, -212);
		fd_lblNewLabel.top = new FormAttachment(0, 10);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel.setText(theClient.getName());
		setTabList(new Control[]{table, btnClose, btnAdd});
	}
}

