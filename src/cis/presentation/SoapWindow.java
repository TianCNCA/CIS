package cis.presentation;

import java.util.Date;

import org.eclipse.swt.widgets.Shell;
//import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;

import cis.buisness.Soap;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class SoapWindow extends Shell {
	private String clientName;
	private Soap theSoap;
	private Text text;

	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SoapWindow window = new SoapWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public SoapWindow(){
		open();
	}
	
	public SoapWindow(String clientName, Soap theSoap) {
		this.clientName = clientName;
		this.theSoap = theSoap;
		open();
	}
	
	@SuppressWarnings("deprecation")
	public void open(){
		setText("Soap");
		//setBackground(SWTResourceManager.getColor(255, 250, 205));
		
		Button btnCancel = new Button(this, SWT.NONE);
		btnCancel.setBounds(268, 226, 75, 25);
		btnCancel.setText("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent arg0) {
				dispose();
			}
		});
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		//lblNewLabel.setBackground(SWTResourceManager.getColor(255, 250, 205));
		//lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblNewLabel.setBounds(10, 10, 333, 30);
		lblNewLabel.setText(clientName);

		final DateTime dateTime = new DateTime(this, SWT.BORDER);
		int theDay = theSoap.getDate().getDay();
		int theMonth = theSoap.getDate().getMonth();
		int theYear = theSoap.getDate().getYear();
		
		dateTime.setDate(theDay, theMonth, theYear);
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
				theSoap.setInfo(text.toString());
				theSoap.setDate( new Date( dateTime.toString() ) );
				dispose();
			}
		});
	}

}
