package cis.presentation;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Button;

import swing2swt.layout.BorderLayout;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

import cis.buisness.Client;
import cis.buisness.DataAccess;
import cis.persistance.DBIntermediary;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class CreateClientWindow {
	private Text text_9;
	private Text text_10;
	private Text text_11;
	private Text text_12;
	private Text text_13;
	private Text text_14;
	private Text text_15;
	private Text text_16;
	private Text text_17;
	private Text text_18;
	private Text text_19;
	private Text text_20;
	private Text text_21;
	
	
	private DataAccess dataBase;
	private Client client;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private Text text_7;
	private Text text_8;
	private Text text_22;
	private Text text_23;
	private Text text_24;
	private Text text_25;
	private Text text_26;
	private Text text_27;
	private Text text_28;
	private Text text_29;

	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			CreateClientWindow window = new CreateClientWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public CreateClientWindow() {
		// TODO Auto-generated constructor stub
	}
	
	public CreateClientWindow(DataAccess dataBase) {
		this.dataBase = dataBase;
	}
	
	/**
	 * Open the window.
	 */
	public void open() {
		final Display display = Display.getDefault();
		final Shell shlCreatClient = new Shell(display);
		shlCreatClient.setSize(771, 442);
		shlCreatClient.setText("Creat Client");
		shlCreatClient.setLayout(new BorderLayout(0, 0));
		
		Composite composite = new Composite(shlCreatClient, SWT.NONE);
		composite.setLayoutData(BorderLayout.CENTER);
		composite.setLayout(null);
		
		TabFolder tabFolder = new TabFolder(composite, SWT.NONE);
		
		tabFolder.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				widgetSelected(arg0);
			}
		});
		tabFolder.setBounds(0, 0, 755, 362);
		
		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem.setControl(composite_1);
		composite_1.setEnabled(false);
		
		TabItem tbtmTest = new TabItem(tabFolder, SWT.NONE);
		tbtmTest.setText("Client Information");
		
		Composite composite_4 = new Composite(tabFolder, SWT.NONE);
		tbtmTest.setControl(composite_4);
		composite_4.setLayout(new FormLayout());
		
		Label label = new Label(composite_4, SWT.NONE);
		label.setText("Name:");
		FormData fd_label = new FormData();
		fd_label.bottom = new FormAttachment(0, 25);
		fd_label.top = new FormAttachment(0, 10);
		fd_label.right = new FormAttachment(0, 59);
		fd_label.left = new FormAttachment(0, 10);
		label.setLayoutData(fd_label);
		
		text_12 = new Text(composite_4, SWT.BORDER);
		FormData fd_text_12 = new FormData();
		fd_text_12.top = new FormAttachment(0, 10);
		fd_text_12.right = new FormAttachment(label, 156, SWT.RIGHT);
		fd_text_12.left = new FormAttachment(label, 17);
		text_12.setLayoutData(fd_text_12);
		
		Label label_1 = new Label(composite_4, SWT.NONE);
		label_1.setText("Address:");
		FormData fd_label_1 = new FormData();
		fd_label_1.top = new FormAttachment(label, 0, SWT.TOP);
		fd_label_1.left = new FormAttachment(text_12, 36);
		label_1.setLayoutData(fd_label_1);
		
		text_13 = new Text(composite_4, SWT.BORDER);
		FormData fd_text_13 = new FormData();
		fd_text_13.bottom = new FormAttachment(text_12, 0, SWT.BOTTOM);
		fd_text_13.top = new FormAttachment(text_12, -21);
		fd_text_13.right = new FormAttachment(100, -145);
		fd_text_13.left = new FormAttachment(label_1, 7);
		text_13.setLayoutData(fd_text_13);
		
		Label label_2 = new Label(composite_4, SWT.NONE);
		label_2.setText("City:");
		FormData fd_label_2 = new FormData();
		fd_label_2.top = new FormAttachment(label, 35);
		fd_label_2.left = new FormAttachment(label, 0, SWT.LEFT);
		label_2.setLayoutData(fd_label_2);
		
		text_14 = new Text(composite_4, SWT.BORDER);
		fd_text_12.bottom = new FormAttachment(text_14, -26);
		FormData fd_text_14 = new FormData();
		fd_text_14.top = new FormAttachment(label_2, -3, SWT.TOP);
		fd_text_14.left = new FormAttachment(label_2, 6);
		text_14.setLayoutData(fd_text_14);
		
		Label label_3 = new Label(composite_4, SWT.NONE);
		label_3.setText("Prov:");
		FormData fd_label_3 = new FormData();
		fd_label_3.top = new FormAttachment(label_2, 0, SWT.TOP);
		fd_label_3.left = new FormAttachment(text_14, 18);
		label_3.setLayoutData(fd_label_3);
		
		text_15 = new Text(composite_4, SWT.BORDER);
		FormData fd_text_15 = new FormData();
		fd_text_15.top = new FormAttachment(label_2, -3, SWT.TOP);
		fd_text_15.left = new FormAttachment(label_3, 17);
		text_15.setLayoutData(fd_text_15);
		
		Label label_4 = new Label(composite_4, SWT.NONE);
		label_4.setText("Postal Code:");
		FormData fd_label_4 = new FormData();
		fd_label_4.top = new FormAttachment(label_2, 0, SWT.TOP);
		fd_label_4.left = new FormAttachment(text_15, 35);
		label_4.setLayoutData(fd_label_4);
		
		text_16 = new Text(composite_4, SWT.BORDER);
		FormData fd_text_16 = new FormData();
		fd_text_16.top = new FormAttachment(label_2, 0, SWT.TOP);
		fd_text_16.left = new FormAttachment(label_4, 6);
		text_16.setLayoutData(fd_text_16);
		
		Label label_5 = new Label(composite_4, SWT.NONE);
		label_5.setText("Age:");
		FormData fd_label_5 = new FormData();
		fd_label_5.top = new FormAttachment(label_2, 31);
		fd_label_5.left = new FormAttachment(label, 0, SWT.LEFT);
		label_5.setLayoutData(fd_label_5);
		
		text_17 = new Text(composite_4, SWT.BORDER);
		FormData fd_text_17 = new FormData();
		fd_text_17.top = new FormAttachment(label_5, 0, SWT.TOP);
		fd_text_17.left = new FormAttachment(text_14, 0, SWT.LEFT);
		text_17.setLayoutData(fd_text_17);
		
		Label label_6 = new Label(composite_4, SWT.NONE);
		label_6.setText("Date of Birth:");
		FormData fd_label_6 = new FormData();
		fd_label_6.top = new FormAttachment(label_5, 0, SWT.TOP);
		fd_label_6.left = new FormAttachment(label_3, 0, SWT.LEFT);
		label_6.setLayoutData(fd_label_6);
		
		final DateTime dateTime_1 = new DateTime(composite_4, SWT.BORDER);
		FormData fd_dateTime_1 = new FormData();
		fd_dateTime_1.bottom = new FormAttachment(text_17, 0, SWT.BOTTOM);
		fd_dateTime_1.right = new FormAttachment(label_1, 0, SWT.RIGHT);
		dateTime_1.setLayoutData(fd_dateTime_1);
		
		Label label_7 = new Label(composite_4, SWT.NONE);
		label_7.setText("Phone: (Home)");
		FormData fd_label_7 = new FormData();
		fd_label_7.top = new FormAttachment(label_5, 29);
		fd_label_7.left = new FormAttachment(label, 0, SWT.LEFT);
		label_7.setLayoutData(fd_label_7);
		
		text_18 = new Text(composite_4, SWT.BORDER);
		FormData fd_text_18 = new FormData();
		fd_text_18.top = new FormAttachment(label_7, -3, SWT.TOP);
		fd_text_18.right = new FormAttachment(text_12, 0, SWT.RIGHT);
		fd_text_18.left = new FormAttachment(label_7, 25);
		text_18.setLayoutData(fd_text_18);
		
		Label label_8 = new Label(composite_4, SWT.NONE);
		label_8.setText("Phone: (Work)");
		FormData fd_label_8 = new FormData();
		fd_label_8.top = new FormAttachment(label_7, 0, SWT.TOP);
		fd_label_8.left = new FormAttachment(label_1, 0, SWT.LEFT);
		label_8.setLayoutData(fd_label_8);
		
		text_19 = new Text(composite_4, SWT.BORDER);
		FormData fd_text_19 = new FormData();
		fd_text_19.top = new FormAttachment(text_18, 0, SWT.TOP);
		fd_text_19.left = new FormAttachment(label_8, 19);
		text_19.setLayoutData(fd_text_19);
		
		Label label_9 = new Label(composite_4, SWT.NONE);
		label_9.setText("Are you presently under care of another professional?");
		FormData fd_label_9 = new FormData();
		fd_label_9.top = new FormAttachment(label_7, 24);
		fd_label_9.left = new FormAttachment(label, 0, SWT.LEFT);
		label_9.setLayoutData(fd_label_9);
		
		final Button button = new Button(composite_4, SWT.CHECK);
		button.setText("Physician");
		FormData fd_button = new FormData();
		fd_button.top = new FormAttachment(label_9, 17);
		fd_button.left = new FormAttachment(label, 0, SWT.LEFT);
		button.setLayoutData(fd_button);
		
		final Button button_1 = new Button(composite_4, SWT.CHECK);
		button_1.setText("Physiotherapist");
		FormData fd_button_1 = new FormData();
		fd_button_1.bottom = new FormAttachment(button, 0, SWT.BOTTOM);
		fd_button_1.left = new FormAttachment(label_3, 0, SWT.LEFT);
		button_1.setLayoutData(fd_button_1);
		
		final Button button_2 = new Button(composite_4, SWT.CHECK);
		button_2.setText("Chiropractor");
		FormData fd_button_2 = new FormData();
		fd_button_2.bottom = new FormAttachment(button, 0, SWT.BOTTOM);
		fd_button_2.left = new FormAttachment(label_4, 0, SWT.LEFT);
		button_2.setLayoutData(fd_button_2);
		
		Button button_3 = new Button(composite_4, SWT.CHECK);
		button_3.setText("Have you ever had a therapeutic massage before?");
		FormData fd_button_3 = new FormData();
		fd_button_3.top = new FormAttachment(button, 14);
		fd_button_3.left = new FormAttachment(label, 0, SWT.LEFT);
		button_3.setLayoutData(fd_button_3);
		
		text_20 = new Text(composite_4, SWT.NONE);
		text_20.setText("Primary reason for appointment:");
		FormData fd_text_20 = new FormData();
		fd_text_20.top = new FormAttachment(button_3, 6);
		fd_text_20.left = new FormAttachment(0, 10);
		text_20.setLayoutData(fd_text_20);
		
		text_21 = new Text(composite_4, SWT.BORDER);
		FormData fd_text_21 = new FormData();
		fd_text_21.top = new FormAttachment(text_20, 6);
		fd_text_21.right = new FormAttachment(text_16, 0, SWT.RIGHT);
		fd_text_21.left = new FormAttachment(label, 0, SWT.LEFT);
		text_21.setLayoutData(fd_text_21);
		
		TabItem tbtmNewItem_1 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_1.setText("Client History");

		// fist tab ends
		
		
		
		Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem_1.setControl(composite_2);
		composite_2.setLayout(new FillLayout(SWT.VERTICAL));
		
		Label lblNewLabel = new Label(composite_2, SWT.NONE);
		lblNewLabel.setText("Out of the following list, check all that apply to you");
		
		SashForm sashForm = new SashForm(composite_2, SWT.NONE);
		
		Button btnDf = new Button(sashForm, SWT.CHECK);
		btnDf.setText("Heart problems, chest discomfort/pressure/pain or heart disease?");
		
		text_1 = new Text(sashForm, SWT.BORDER);
		sashForm.setWeights(new int[] {1, 1});
		
		SashForm sashForm_2 = new SashForm(composite_2, SWT.NONE);
		
		Button btnCheckButton_1 = new Button(sashForm_2, SWT.CHECK);
		btnCheckButton_1.setText("Sudden tingling, numbness in arms, hands, chest, face, feet or legs?");
		
		text_2 = new Text(sashForm_2, SWT.BORDER);
		sashForm_2.setWeights(new int[] {1, 1});
		
		SashForm sashForm_3 = new SashForm(composite_2, SWT.NONE);
		
		Button btnCheckButton_2 = new Button(sashForm_3, SWT.CHECK);
		btnCheckButton_2.setText("High or abnormal blood pressure?");
		
		text_3 = new Text(sashForm_3, SWT.BORDER);
		sashForm_3.setWeights(new int[] {1, 1});
		
		SashForm sashForm_4 = new SashForm(composite_2, SWT.NONE);
		
		Button btnCheckButton_3 = new Button(sashForm_4, SWT.CHECK);
		btnCheckButton_3.setText("Respiratory or breathing problems (asthma, allergies, bronchitis)?");
		
		text_4 = new Text(sashForm_4, SWT.BORDER);
		sashForm_4.setWeights(new int[] {1, 1});
		
		SashForm sashForm_5 = new SashForm(composite_2, SWT.NONE);
		
		Button btnCheckButton_4 = new Button(sashForm_5, SWT.CHECK);
		btnCheckButton_4.setText("Diabetes?");
		
		text_5 = new Text(sashForm_5, SWT.BORDER);
		sashForm_5.setWeights(new int[] {1, 1});
		
		SashForm sashForm_6 = new SashForm(composite_2, SWT.NONE);
		
		Button btnCheckButton_5 = new Button(sashForm_6, SWT.CHECK);
		btnCheckButton_5.setText("Faintness, dizziness, lightheadedness or blackouts?");
		
		text_6 = new Text(sashForm_6, SWT.BORDER);
		sashForm_6.setWeights(new int[] {1, 1});
		
		SashForm sashForm_7 = new SashForm(composite_2, SWT.NONE);
		
		Button btnCheckButton_6 = new Button(sashForm_7, SWT.CHECK);
		btnCheckButton_6.setText("Frequent headaches?");
		
		text_7 = new Text(sashForm_7, SWT.BORDER);
		sashForm_7.setWeights(new int[] {1, 1});
		
		SashForm sashForm_8 = new SashForm(composite_2, SWT.NONE);
		
		Button btnCheckButton_7 = new Button(sashForm_8, SWT.CHECK);
		btnCheckButton_7.setText("Contact lenses?");
		
		text_8 = new Text(sashForm_8, SWT.BORDER);
		sashForm_8.setWeights(new int[] {1, 1});
		
		SashForm sashForm_9 = new SashForm(composite_2, SWT.NONE);
		
		Button btnCheckButton_8 = new Button(sashForm_9, SWT.CHECK);
		btnCheckButton_8.setText("Special shoes or orthotic supports?");
		
		text_22 = new Text(sashForm_9, SWT.BORDER);
		sashForm_9.setWeights(new int[] {1, 1});
		
		SashForm sashForm_10 = new SashForm(composite_2, SWT.NONE);
		
		Button btnCheckButton_9 = new Button(sashForm_10, SWT.CHECK);
		btnCheckButton_9.setText("Varicose veins?");
		
		text_23 = new Text(sashForm_10, SWT.BORDER);
		sashForm_10.setWeights(new int[] {1, 1});
		
		SashForm sashForm_11 = new SashForm(composite_2, SWT.NONE);
		
		Button btnCheckButton_10 = new Button(sashForm_11, SWT.CHECK);
		btnCheckButton_10.setText("Arthritis or osteoporosis? Joints affected.");
		
		text_24 = new Text(sashForm_11, SWT.BORDER);
		sashForm_11.setWeights(new int[] {1, 1});
		
		SashForm sashForm_12 = new SashForm(composite_2, SWT.NONE);
		
		Button btnCheckButton_11 = new Button(sashForm_12, SWT.CHECK);
		btnCheckButton_11.setText("Any type of cancer?");
		
		text_25 = new Text(sashForm_12, SWT.BORDER);
		sashForm_12.setWeights(new int[] {1, 1});
		
		SashForm sashForm_13 = new SashForm(composite_2, SWT.NONE);
		
		Button btnCheckButton_12 = new Button(sashForm_13, SWT.CHECK);
		btnCheckButton_12.setText("Chronic diarrhea or constipation?");
		
		text_26 = new Text(sashForm_13, SWT.BORDER);
		sashForm_13.setWeights(new int[] {1, 1});
		
		SashForm sashForm_1 = new SashForm(composite_2, SWT.NONE);
		
		Button btnCheckButton_13 = new Button(sashForm_1, SWT.CHECK);
		btnCheckButton_13.setText("Currently on medication?");
		
		text = new Text(sashForm_1, SWT.BORDER);
		sashForm_1.setWeights(new int[] {1, 1});
		
		SashForm sashForm_14 = new SashForm(composite_2, SWT.NONE);
		
		Button btnCheckButton_14 = new Button(sashForm_14, SWT.CHECK);
		btnCheckButton_14.setText("Receieved Cortisone injections?");
		
		text_27 = new Text(sashForm_14, SWT.BORDER);
		sashForm_14.setWeights(new int[] {1, 1});
		
		SashForm sashForm_15 = new SashForm(composite_2, SWT.NONE);
		
		Button btnCheckButton_15 = new Button(sashForm_15, SWT.CHECK);
		btnCheckButton_15.setText("Any skin conditions or reactions to lotions or creams?");
		
		text_28 = new Text(sashForm_15, SWT.BORDER);
		sashForm_15.setWeights(new int[] {1, 1});
		
		SashForm sashForm_16 = new SashForm(composite_2, SWT.NONE);
		
		Button btnCheckButton_16 = new Button(sashForm_16, SWT.CHECK);
		btnCheckButton_16.setText("Any other diagnosis or medical condition?");
		
		text_29 = new Text(sashForm_16, SWT.BORDER);
		sashForm_16.setWeights(new int[] {1, 1});
		
		TabItem tbtmNewItem_2 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_2.setText("Personal Habits and Lifestyle");
		
		Composite composite_3 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem_2.setControl(composite_3);
		composite_3.setLayout(null);
		
		Label lblOccupation = new Label(composite_3, SWT.NONE);
		lblOccupation.setBounds(10, 22, 55, 15);
		lblOccupation.setText("Occupation:");
		
		text_9 = new Text(composite_3, SWT.BORDER);
		text_9.setBounds(80, 16, 162, 21);
		
		Label lblListAnySportexercisehobbies = new Label(composite_3, SWT.NONE);
		lblListAnySportexercisehobbies.setBounds(10, 61, 272, 15);
		lblListAnySportexercisehobbies.setText("List any sport/exercise/hobbies you participate in:");
		
		text_10 = new Text(composite_3, SWT.BORDER);
		text_10.setBounds(308, 55, 177, 21);
		
		Label lblDescribeYourSleep = new Label(composite_3, SWT.NONE);
		lblDescribeYourSleep.setBounds(10, 107, 307, 15);
		lblDescribeYourSleep.setText("Describe your sleep pattern (usual position, quality, etc.) :");
		
		text_11 = new Text(composite_3, SWT.BORDER);
		text_11.setBounds(336, 107, 177, 21);
		
		Button btnSave = new Button(composite, SWT.NONE);
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent arg0) 
			{
				//TODO rest of client stuff
				client = new Client(text_12.getText());
				client.setAddress( text_13.getText() );
				client.setCity( text_14.getText() );
				client.setProvince( text_15.getText() );
				client.setPostCode( text_16.getText() );
				client.setDOB( dateTime_1.toString() );
				client.setHomePhone( text_18.getText() );
				client.setWorkPhone( text_18.getText() );
				client.setPhysician( button.getSelection() );
				client.setChiropractor( button_2.getSelection() );
				client.setPhysioTherapist( button_1.getSelection() );
				client.setReason( text_21.getText() );

				client.setOccupation( text_9.getText() );
				client.setSports( text_10.getText() );
				client.setSleepPattern( text_11.getText() );
				dataBase.insertClient(client);
				shlCreatClient.dispose();
			}
		});
		btnSave.setBounds(589, 368, 75, 25);
		btnSave.setText("Save");
		
		Button btnCancel = new Button(composite, SWT.NONE);
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent arg0) {
				shlCreatClient.dispose();
			}
		});
		btnCancel.setBounds(670, 368, 75, 25);
		btnCancel.setText("Cancel");
		
		tabFolder.setSelection(1);

		shlCreatClient.open();
		shlCreatClient.layout();
		while (!shlCreatClient.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
