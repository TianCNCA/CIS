package experiment;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import cis.buisness.Client;
import cis.buisness.ClientHistory;
import cis.buisness.DataAccess;

import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Sash;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class ClientWindow extends Shell {
	private DataAccess dataAccess;
	private Client client;
	private ClientHistory history;

	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private Text text_7;
	private Text text_8;
	private Text text_9;

	// For history tab TODO use of numbers should really be avoided, but...
	private Button[] btns = new Button[17];
	private Text[] texts = new Text[17];
	
	private Button btnCheckButton;
	private Button btnCheckButton_1;
	private Button btnCheckButton_2;
	private Button btnCheckButton_3;
	private Composite composite_1;

	public ClientWindow(Display display, DataAccess dataAccess, Client client) {
		super(display);
		setSize(719, 538);
		this.dataAccess = dataAccess;
		this.client = client;

		// TODO need a better coding
		if (null == this.client)
			this.client = new Client();
		setLayout(new FormLayout());

		CTabFolder tabFolder = new CTabFolder(this, SWT.BORDER);
		FormData fd_tabFolder = new FormData();
		fd_tabFolder.right = new FormAttachment(0, 693);
		fd_tabFolder.top = new FormAttachment(0);
		fd_tabFolder.left = new FormAttachment(0);
		tabFolder.setLayoutData(fd_tabFolder);
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));

		// 1st tab: Client Information start
		CTabItem tbtmNewItem = new CTabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("Client Information");

		Composite composite = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem.setControl(composite);
		composite.setLayout(new FormLayout());

		Label lblName = new Label(composite, SWT.NONE);
		FormData fd_lblName = new FormData();
		fd_lblName.top = new FormAttachment(0, 10);
		fd_lblName.left = new FormAttachment(0, 10);
		lblName.setLayoutData(fd_lblName);
		lblName.setText("Name:");

		text = new Text(composite, SWT.BORDER);
		FormData fd_text = new FormData();
		fd_text.right = new FormAttachment(lblName, 126, SWT.RIGHT);
		text.setLayoutData(fd_text);

		Label label = new Label(composite, SWT.BORDER | SWT.SEPARATOR
				| SWT.HORIZONTAL);
		fd_text.bottom = new FormAttachment(label, -6);
		FormData fd_label = new FormData();
		fd_label.top = new FormAttachment(0, 37);
		fd_label.right = new FormAttachment(0, 697);
		fd_label.left = new FormAttachment(0);
		label.setLayoutData(fd_label);

		Label lblAddress = new Label(composite, SWT.NONE);
		FormData fd_lblAddress = new FormData();
		fd_lblAddress.top = new FormAttachment(label, 14);
		fd_lblAddress.left = new FormAttachment(0, 10);
		lblAddress.setLayoutData(fd_lblAddress);
		lblAddress.setText("Address:");

		text_1 = new Text(composite, SWT.BORDER);
		fd_label.bottom = new FormAttachment(text_1, -8);
		fd_text.left = new FormAttachment(text_1, 0, SWT.LEFT);
		FormData fd_text_1 = new FormData();
		fd_text_1.top = new FormAttachment(0, 47);
		fd_text_1.right = new FormAttachment(lblAddress, 158, SWT.RIGHT);
		fd_text_1.left = new FormAttachment(lblAddress, 6);
		text_1.setLayoutData(fd_text_1);

		Label lblCity = new Label(composite, SWT.NONE);
		FormData fd_lblCity = new FormData();
		fd_lblCity.top = new FormAttachment(lblAddress, 0, SWT.TOP);
		fd_lblCity.left = new FormAttachment(text_1, 16);
		lblCity.setLayoutData(fd_lblCity);
		lblCity.setText("City:");

		text_2 = new Text(composite, SWT.BORDER);
		FormData fd_text_2 = new FormData();
		fd_text_2.bottom = new FormAttachment(lblAddress, 0, SWT.BOTTOM);
		fd_text_2.left = new FormAttachment(lblCity, 6);
		text_2.setLayoutData(fd_text_2);

		Label lblProv = new Label(composite, SWT.NONE);
		FormData fd_lblProv = new FormData();
		fd_lblProv.top = new FormAttachment(lblAddress, 0, SWT.TOP);
		fd_lblProv.left = new FormAttachment(text_2, 16);
		lblProv.setLayoutData(fd_lblProv);
		lblProv.setText("Prov:");

		text_3 = new Text(composite, SWT.BORDER);
		FormData fd_text_3 = new FormData();
		fd_text_3.left = new FormAttachment(lblProv, 6);
		fd_text_3.top = new FormAttachment(label, 8);
		text_3.setLayoutData(fd_text_3);

		Label lblPost = new Label(composite, SWT.NONE);
		fd_text_3.right = new FormAttachment(100, -240);
		FormData fd_lblPost = new FormData();
		fd_lblPost.bottom = new FormAttachment(lblAddress, 0, SWT.BOTTOM);
		fd_lblPost.left = new FormAttachment(text_3, 16);
		lblPost.setLayoutData(fd_lblPost);
		lblPost.setText("Postal Code:");

		text_4 = new Text(composite, SWT.BORDER);
		FormData fd_text_4 = new FormData();
		fd_text_4.bottom = new FormAttachment(lblAddress, 0, SWT.BOTTOM);
		fd_text_4.right = new FormAttachment(lblPost, 108, SWT.RIGHT);
		fd_text_4.left = new FormAttachment(lblPost, 6);
		text_4.setLayoutData(fd_text_4);

		Label label_1 = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		FormData fd_label_1 = new FormData();
		fd_label_1.top = new FormAttachment(lblAddress, 26);
		fd_label_1.right = new FormAttachment(label, 0, SWT.RIGHT);
		fd_label_1.left = new FormAttachment(label, 0, SWT.LEFT);
		label_1.setLayoutData(fd_label_1);

		Label lblAge = new Label(composite, SWT.NONE);
		FormData fd_lblAge = new FormData();
		fd_lblAge.left = new FormAttachment(lblName, 0, SWT.LEFT);
		lblAge.setLayoutData(fd_lblAge);
		lblAge.setText("Age:");

		text_5 = new Text(composite, SWT.BORDER);
		fd_label_1.bottom = new FormAttachment(text_5, -16);
		FormData fd_text_5 = new FormData();
		fd_text_5.top = new FormAttachment(lblAge, -3, SWT.TOP);
		fd_text_5.left = new FormAttachment(text, 0, SWT.LEFT);
		text_5.setLayoutData(fd_text_5);

		Label lblDateOfBirth = new Label(composite, SWT.NONE);
		FormData fd_lblDateOfBirth = new FormData();
		fd_lblDateOfBirth.top = new FormAttachment(lblAge, 0, SWT.TOP);
		lblDateOfBirth.setLayoutData(fd_lblDateOfBirth);
		lblDateOfBirth.setText("Date of Birth:");

		Label lblPhonehome = new Label(composite, SWT.NONE);
		FormData fd_lblPhonehome = new FormData();
		fd_lblPhonehome.left = new FormAttachment(lblName, 0, SWT.LEFT);
		lblPhonehome.setLayoutData(fd_lblPhonehome);
		lblPhonehome.setText("Phone (Home):");

		text_6 = new Text(composite, SWT.BORDER);
		FormData fd_text_6 = new FormData();
		fd_text_6.top = new FormAttachment(lblAge, -3, SWT.TOP);
		fd_text_6.right = new FormAttachment(lblPost, 0, SWT.RIGHT);
		text_6.setLayoutData(fd_text_6);

		text_7 = new Text(composite, SWT.BORDER);
		FormData fd_text_7 = new FormData();
		fd_text_7.top = new FormAttachment(lblPhonehome, -3, SWT.TOP);
		fd_text_7.left = new FormAttachment(lblPhonehome, 6);
		fd_text_7.right = new FormAttachment(text_1, 0, SWT.RIGHT);
		text_7.setLayoutData(fd_text_7);

		Label lblPhonework = new Label(composite, SWT.NONE);
		fd_lblDateOfBirth.left = new FormAttachment(lblPhonework, 0, SWT.LEFT);
		lblPhonework.setText("Phone (Work):");
		FormData fd_lblPhonework = new FormData();
		fd_lblPhonework.top = new FormAttachment(lblPhonehome, 0, SWT.TOP);
		lblPhonework.setLayoutData(fd_lblPhonework);

		text_8 = new Text(composite, SWT.BORDER);
		fd_text_6.left = new FormAttachment(text_8, 0, SWT.LEFT);
		fd_lblPhonework.right = new FormAttachment(text_8, -14);
		FormData fd_text_8 = new FormData();
		fd_text_8.top = new FormAttachment(lblPhonehome, -3, SWT.TOP);
		fd_text_8.left = new FormAttachment(0, 431);
		fd_text_8.right = new FormAttachment(100, -135);
		text_8.setLayoutData(fd_text_8);

		Label label_3 = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		fd_lblPhonehome.bottom = new FormAttachment(label_3, -16);
		FormData fd_label_3 = new FormData();
		fd_label_3.top = new FormAttachment(0, 199);
		fd_label_3.right = new FormAttachment(label, 0, SWT.RIGHT);
		fd_label_3.left = new FormAttachment(label, 0, SWT.LEFT);
		label_3.setLayoutData(fd_label_3);

		Label lblNewLabel = new Label(composite, SWT.NONE);
		fd_label_3.bottom = new FormAttachment(lblNewLabel, -20);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.left = new FormAttachment(lblName, 0, SWT.LEFT);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel
				.setText("Are you presently under care of another professional?");

		btnCheckButton = new Button(composite, SWT.CHECK);
		fd_lblNewLabel.bottom = new FormAttachment(btnCheckButton, -26);
		FormData fd_btnCheckButton = new FormData();
		fd_btnCheckButton.left = new FormAttachment(lblName, 0, SWT.LEFT);
		btnCheckButton.setLayoutData(fd_btnCheckButton);
		btnCheckButton.setText("Physician");

		btnCheckButton_1 = new Button(composite, SWT.CHECK);
		FormData fd_btnCheckButton_1 = new FormData();
		fd_btnCheckButton_1.top = new FormAttachment(btnCheckButton, 0, SWT.TOP);
		fd_btnCheckButton_1.left = new FormAttachment(btnCheckButton, 28);
		btnCheckButton_1.setLayoutData(fd_btnCheckButton_1);
		btnCheckButton_1.setText("Physiotherapist");

		btnCheckButton_2 = new Button(composite, SWT.CHECK);
		FormData fd_btnCheckButton_2 = new FormData();
		fd_btnCheckButton_2.top = new FormAttachment(btnCheckButton, 0, SWT.TOP);
		fd_btnCheckButton_2.left = new FormAttachment(text_2, 0, SWT.LEFT);
		btnCheckButton_2.setLayoutData(fd_btnCheckButton_2);
		btnCheckButton_2.setText("Chiropractor");

		Label label_4 = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		fd_btnCheckButton.bottom = new FormAttachment(label_4, -17);
		FormData fd_label_4 = new FormData();
		fd_label_4.top = new FormAttachment(0, 295);
		fd_label_4.right = new FormAttachment(label, 0, SWT.RIGHT);
		fd_label_4.left = new FormAttachment(label, 0, SWT.LEFT);
		label_4.setLayoutData(fd_label_4);

		btnCheckButton_3 = new Button(composite, SWT.CHECK);
		fd_label_4.bottom = new FormAttachment(btnCheckButton_3, -22);
		FormData fd_btnCheckButton_3 = new FormData();
		fd_btnCheckButton_3.left = new FormAttachment(lblName, 0, SWT.LEFT);
		btnCheckButton_3.setLayoutData(fd_btnCheckButton_3);
		btnCheckButton_3
				.setText("Have you ever had a therapeutic massage before?");

		Label label_5 = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		fd_btnCheckButton_3.bottom = new FormAttachment(label_5, -18);
		FormData fd_label_5 = new FormData();
		fd_label_5.top = new FormAttachment(0, 353);
		fd_label_5.right = new FormAttachment(label, 0, SWT.RIGHT);
		fd_label_5.left = new FormAttachment(label, 0, SWT.LEFT);
		label_5.setLayoutData(fd_label_5);

		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		fd_label_5.bottom = new FormAttachment(lblNewLabel_1, -6);
		FormData fd_lblNewLabel_1 = new FormData();
		fd_lblNewLabel_1.bottom = new FormAttachment(100, -64);
		fd_lblNewLabel_1.left = new FormAttachment(lblName, 0, SWT.LEFT);
		lblNewLabel_1.setLayoutData(fd_lblNewLabel_1);
		lblNewLabel_1.setText("Primary reason for appointment:");

		text_9 = new Text(composite, SWT.BORDER);
		FormData fd_text_9 = new FormData();
		fd_text_9.right = new FormAttachment(100, -340);
		fd_text_9.left = new FormAttachment(0, 10);
		fd_text_9.bottom = new FormAttachment(100, -27);
		fd_text_9.top = new FormAttachment(lblNewLabel_1, 16);
		text_9.setLayoutData(fd_text_9);
		
		Label label_2 = new Label(composite, SWT.BORDER | SWT.SEPARATOR | SWT.HORIZONTAL);
		fd_lblAge.bottom = new FormAttachment(label_2, -14);
		FormData fd_label_2 = new FormData();
		fd_label_2.top = new FormAttachment(text_7, -21, SWT.TOP);
		fd_label_2.right = new FormAttachment(lblPost, 158, SWT.RIGHT);
		fd_label_2.left = new FormAttachment(label, 0, SWT.LEFT);
		fd_label_2.bottom = new FormAttachment(text_7, -19);
		label_2.setLayoutData(fd_label_2);
		// 1st tab: Client Information end
		
		
		// 2nd tab: Client History start
		CTabItem tbtmNewItem_1 = new CTabItem(tabFolder, SWT.NONE);
		tbtmNewItem_1.setText("Client History");
		
		composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem_1.setControl(composite_1);
		composite_1.setLayout(new GridLayout(2, false));
		
		drawHistoryTab();
		

		
		
		CTabItem tbtmNewItem_2 = new CTabItem(tabFolder, SWT.NONE);
		tbtmNewItem_2.setText("New Item");
		
		Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem_2.setControl(composite_2);
		composite_2.setLayout(new FormLayout());

		Button btnSave = new Button(this, SWT.NONE);
		fd_tabFolder.bottom = new FormAttachment(btnSave, -6);
		FormData fd_btnSave = new FormData();
		fd_btnSave.left = new FormAttachment(0);
		fd_btnSave.bottom = new FormAttachment(100);
		btnSave.setLayoutData(fd_btnSave);
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				saveClient();
			}
		});
		btnSave.setText("Save");

		Button btnNewButton = new Button(this, SWT.NONE);
		fd_btnSave.right = new FormAttachment(btnNewButton, -529);
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.top = new FormAttachment(btnSave, 0, SWT.TOP);
		fd_btnNewButton.right = new FormAttachment(100, -10);
		fd_btnNewButton.left = new FormAttachment(0, 611);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				getShell().dispose();
			}
		});
		btnNewButton.setText("Exit");
	}

	private void drawHistoryTab() {
		// check button texts
		String[] strs = {
				"Heart problems, chest discomfort/pressure/pain or heart disease?",
				"Sudden tingling, numbness in arms, hands, chest, face, feet or legs?",
				"High or abnormal blood pressure?",
				"Respiratory or breathing problems (asthma, allergies, bronchitis)?",
				"Diabetes?",
				"Faintness, dizziness, lightheadedness or blackouts?",
				"Frequent headaches?",
				"Contact lenses?",
				"Special shoes or orthotic supports?",
				"Varicose veins?",
				"Arthritis or osteoporosis? Joints affected.",
				"Any type of cancer?",
				"Chronic diarrhea or constipation?",
				"Currently on medication?",
				"Receieved Cortisone injections?",
				"Any skin conditions or reactions to lotions or creams?",
				"Any other diagnosis or medical condition?"
		};
		
		for (int i = 0; i < btns.length; i++) {
			// check button
			btns[i] = new Button(composite_1, SWT.CHECK);
			btns[i].setLayoutData(new GridData(SWT.LEFT, SWT.BEGINNING, true, false));
			btns[i].setText(strs[i]);
			
			// text
			texts[i] = new Text(composite_1, SWT.BORDER);
			GridData gd_text = new GridData(SWT.RIGHT, SWT.BEGINNING, true, false);
			gd_text.widthHint = 250;
			texts[i].setLayoutData(gd_text);
		}
		
		
	}
	
	private void saveClient() {
		if (validateTexts()) {
			saveClientInfo();
			saveHistory();

			try {
				dataAccess.insertClient(client);
				messageBox("Success", "Client added successfully!",
						SWT.ICON_INFORMATION);
			} catch (Exception e) {
				e.printStackTrace();
				messageBox("Whoops!", e.getMessage(), SWT.ICON_ERROR);
			}
		}
	}

	private void saveClientInfo() {
		client.setName(text.getText());
		client.setAddress(text_1.getText());
		client.setCity(text_2.getText());
		client.setProvince(text_3.getText());
		client.setPostCode(text_4.getText());
		client.setAge(Integer.parseInt(text_5.getText()));
		client.setDOB(text_6.getText());
		client.setHomePhone(text_7.getText());
		client.setWorkPhone(text_8.getText());
		client.setPhysician(btnCheckButton.getSelection());
		client.setPhysioTherapist(btnCheckButton_1.getSelection());
		client.setChiropractor(btnCheckButton_2.getSelection());
		client.setExperience(btnCheckButton_3.getSelection());
		client.setReason(text_9.getText());

	}

	private void saveHistory() {
		ClientHistory history;
		
		Boolean[] bools = new Boolean[17];
		String[] strs = new String[17];
		for (int i = 0; i < bools.length; i++) {
			bools[i] = btns[i].getSelection();
			strs[i] = texts[i].getText();
		}

		history = new ClientHistory(client.getName(), bools, strs);
		client.setHistory(history);
		
	}

	private boolean validateTexts() {
		boolean rc = true;

		if (text.getText() == "") {
			rc = false;
			messageBox("No Client Name",
					"Sorry, client name must not be empty.", SWT.ICON_ERROR);
		}

		try {
			Integer.parseInt(text_5.getText());
		} catch (NumberFormatException e) {
			rc = false;
			messageBox("Age is not correct",
					"Sorry, age must not be an integer.", SWT.ICON_ERROR);
		}

		return rc;
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
