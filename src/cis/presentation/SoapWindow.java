package cis.presentation;

import java.util.Date;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;

import cis.buisness.DataAccess;
import cis.buisness.Soap;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class SoapWindow extends Shell {
	private String clientName;
	DataAccess dataAccess;
	private Text text;

	public SoapWindow(String clientName) {
		this.clientName = clientName;
	}
	
	@SuppressWarnings("deprecation")
	public void open(){
		setText("Soap");
		
		dataAccess = new DataAccess();
		
		Button btnCancel = new Button(this, SWT.NONE);
		btnCancel.setBounds(268, 226, 75, 25);
		btnCancel.setText("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent arg0) {
				getShell().dispose();
			}
		});
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBounds(10, 10, 333, 30);
		lblNewLabel.setText(clientName);

		final DateTime dateTime = new DateTime(this, SWT.BORDER);
		
		dateTime.setBounds(20, 46, 80, 24);
		
		Button btnSave = new Button(this, SWT.NONE);
		btnSave.setBounds(349, 226, 75, 25);
		btnSave.setText("Save");
		
		text = new Text(this, SWT.BORDER);
		text.setBounds(106, 46, 318, 175);
		setTabList(new Control[]{dateTime, text, btnCancel, btnSave});
		
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent arg0) {
				Date theDate = new Date(dateTime.toString());
				String info = text.getText();
				Soap newSoap = new Soap(theDate, info);
				dataAccess.insertSoap(newSoap, clientName);
				dispose();
			}
		});
	}

}
