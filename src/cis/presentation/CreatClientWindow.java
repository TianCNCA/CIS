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

public class CreatClientWindow {
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private Text text_7;
	private Text txtPrimaryReasonFor;
	private Text text_8;
	private Text text_9;
	private Text text_10;
	private Text text_11;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			CreatClientWindow window = new CreatClientWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		Shell shlCreatClient = new Shell();
		shlCreatClient.setSize(727, 441);
		shlCreatClient.setText("Creat Client");
		shlCreatClient.setLayout(new BorderLayout(0, 0));
		
		Composite composite = new Composite(shlCreatClient, SWT.NONE);
		composite.setLayoutData(BorderLayout.CENTER);
		composite.setLayout(null);
		
		TabFolder tabFolder = new TabFolder(composite, SWT.NONE);
		tabFolder.setBounds(0, 0, 711, 362);
		
		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("Client Information");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem.setControl(composite_1);
		composite_1.setLayout(new FormLayout());
		
		Label lblName = new Label(composite_1, SWT.NONE);
		FormData fd_lblName = new FormData();
		fd_lblName.top = new FormAttachment(0, 10);
		fd_lblName.left = new FormAttachment(0, 10);
		fd_lblName.bottom = new FormAttachment(0, 25);
		fd_lblName.right = new FormAttachment(0, 59);
		lblName.setLayoutData(fd_lblName);
		lblName.setText("Name:");
		
		text = new Text(composite_1, SWT.BORDER);
		FormData fd_text = new FormData();
		fd_text.top = new FormAttachment(0, 10);
		fd_text.right = new FormAttachment(lblName, 156, SWT.RIGHT);
		fd_text.left = new FormAttachment(lblName, 17);
		text.setLayoutData(fd_text);
		
		Label lblAddress = new Label(composite_1, SWT.NONE);
		FormData fd_lblAddress = new FormData();
		fd_lblAddress.left = new FormAttachment(text, 36);
		fd_lblAddress.top = new FormAttachment(lblName, 0, SWT.TOP);
		lblAddress.setLayoutData(fd_lblAddress);
		lblAddress.setText("Address:");
		
		text_1 = new Text(composite_1, SWT.BORDER);
		FormData fd_text_1 = new FormData();
		fd_text_1.top = new FormAttachment(text, -21);
		fd_text_1.bottom = new FormAttachment(text, 0, SWT.BOTTOM);
		fd_text_1.left = new FormAttachment(lblAddress, 7);
		fd_text_1.right = new FormAttachment(100, -145);
		text_1.setLayoutData(fd_text_1);
		
		Label lblCity = new Label(composite_1, SWT.NONE);
		FormData fd_lblCity = new FormData();
		fd_lblCity.top = new FormAttachment(lblName, 35);
		fd_lblCity.left = new FormAttachment(lblName, 0, SWT.LEFT);
		lblCity.setLayoutData(fd_lblCity);
		lblCity.setText("City:");
		
		text_2 = new Text(composite_1, SWT.BORDER);
		fd_text.bottom = new FormAttachment(text_2, -26);
		FormData fd_text_2 = new FormData();
		fd_text_2.top = new FormAttachment(lblCity, -3, SWT.TOP);
		fd_text_2.left = new FormAttachment(lblCity, 6);
		text_2.setLayoutData(fd_text_2);
		
		Label lblProv = new Label(composite_1, SWT.NONE);
		FormData fd_lblProv = new FormData();
		fd_lblProv.top = new FormAttachment(lblCity, 0, SWT.TOP);
		fd_lblProv.left = new FormAttachment(text_2, 18);
		lblProv.setLayoutData(fd_lblProv);
		lblProv.setText("Prov:");
		
		text_3 = new Text(composite_1, SWT.BORDER);
		FormData fd_text_3 = new FormData();
		fd_text_3.top = new FormAttachment(lblCity, -3, SWT.TOP);
		fd_text_3.left = new FormAttachment(lblProv, 17);
		text_3.setLayoutData(fd_text_3);
		
		Label lblPos = new Label(composite_1, SWT.NONE);
		lblPos.setText("Postal Code:");
		FormData fd_lblPos = new FormData();
		fd_lblPos.top = new FormAttachment(lblCity, 0, SWT.TOP);
		fd_lblPos.left = new FormAttachment(text_3, 35);
		lblPos.setLayoutData(fd_lblPos);
		
		text_4 = new Text(composite_1, SWT.BORDER);
		FormData fd_text_4 = new FormData();
		fd_text_4.top = new FormAttachment(lblCity, 0, SWT.TOP);
		fd_text_4.left = new FormAttachment(lblPos, 6);
		text_4.setLayoutData(fd_text_4);
		
		Label lblAge = new Label(composite_1, SWT.NONE);
		lblAge.setText("Age:");
		FormData fd_lblAge = new FormData();
		fd_lblAge.top = new FormAttachment(lblCity, 31);
		fd_lblAge.left = new FormAttachment(lblName, 0, SWT.LEFT);
		lblAge.setLayoutData(fd_lblAge);
		
		text_5 = new Text(composite_1, SWT.BORDER);
		FormData fd_text_5 = new FormData();
		fd_text_5.top = new FormAttachment(lblAge, 0, SWT.TOP);
		fd_text_5.left = new FormAttachment(text_2, 0, SWT.LEFT);
		text_5.setLayoutData(fd_text_5);
		
		Label lblDateOfBirth = new Label(composite_1, SWT.NONE);
		lblDateOfBirth.setText("Date of Birth:");
		FormData fd_lblDateOfBirth = new FormData();
		fd_lblDateOfBirth.top = new FormAttachment(lblAge, 0, SWT.TOP);
		fd_lblDateOfBirth.left = new FormAttachment(lblProv, 0, SWT.LEFT);
		lblDateOfBirth.setLayoutData(fd_lblDateOfBirth);
		
		DateTime dateTime = new DateTime(composite_1, SWT.BORDER);
		FormData fd_dateTime = new FormData();
		fd_dateTime.bottom = new FormAttachment(text_5, 0, SWT.BOTTOM);
		fd_dateTime.right = new FormAttachment(lblAddress, 0, SWT.RIGHT);
		dateTime.setLayoutData(fd_dateTime);
		
		Label lblPhone = new Label(composite_1, SWT.NONE);
		lblPhone.setText("Phone: (Home)");
		FormData fd_lblPhone = new FormData();
		fd_lblPhone.top = new FormAttachment(lblAge, 29);
		fd_lblPhone.left = new FormAttachment(lblName, 0, SWT.LEFT);
		lblPhone.setLayoutData(fd_lblPhone);
		
		text_6 = new Text(composite_1, SWT.BORDER);
		FormData fd_text_6 = new FormData();
		fd_text_6.top = new FormAttachment(lblPhone, -3, SWT.TOP);
		fd_text_6.left = new FormAttachment(lblPhone, 25);
		fd_text_6.right = new FormAttachment(text, 0, SWT.RIGHT);
		text_6.setLayoutData(fd_text_6);
		
		Label lblPhonework = new Label(composite_1, SWT.NONE);
		lblPhonework.setText("Phone: (Work)");
		FormData fd_lblPhonework = new FormData();
		fd_lblPhonework.top = new FormAttachment(lblPhone, 0, SWT.TOP);
		fd_lblPhonework.left = new FormAttachment(lblAddress, 0, SWT.LEFT);
		lblPhonework.setLayoutData(fd_lblPhonework);
		
		text_7 = new Text(composite_1, SWT.BORDER);
		FormData fd_text_7 = new FormData();
		fd_text_7.top = new FormAttachment(text_6, 0, SWT.TOP);
		fd_text_7.left = new FormAttachment(lblPhonework, 19);
		text_7.setLayoutData(fd_text_7);
		
		Label lblAreYouPresently = new Label(composite_1, SWT.NONE);
		FormData fd_lblAreYouPresently = new FormData();
		fd_lblAreYouPresently.top = new FormAttachment(lblPhone, 24);
		fd_lblAreYouPresently.left = new FormAttachment(lblName, 0, SWT.LEFT);
		lblAreYouPresently.setLayoutData(fd_lblAreYouPresently);
		lblAreYouPresently.setText("Are you presently under care of another professional?");
		
		Button btnCheckButton = new Button(composite_1, SWT.CHECK);
		FormData fd_btnCheckButton = new FormData();
		fd_btnCheckButton.top = new FormAttachment(lblAreYouPresently, 17);
		fd_btnCheckButton.left = new FormAttachment(lblName, 0, SWT.LEFT);
		btnCheckButton.setLayoutData(fd_btnCheckButton);
		btnCheckButton.setText("Physician");
		
		Button btnPhysio = new Button(composite_1, SWT.CHECK);
		btnPhysio.setText("Physiotherapist");
		FormData fd_btnPhysio = new FormData();
		fd_btnPhysio.bottom = new FormAttachment(btnCheckButton, 0, SWT.BOTTOM);
		fd_btnPhysio.left = new FormAttachment(lblProv, 0, SWT.LEFT);
		btnPhysio.setLayoutData(fd_btnPhysio);
		
		Button btnChiroparactor = new Button(composite_1, SWT.CHECK);
		btnChiroparactor.setText("Chiropractor");
		FormData fd_btnChiroparactor = new FormData();
		fd_btnChiroparactor.bottom = new FormAttachment(btnCheckButton, 0, SWT.BOTTOM);
		fd_btnChiroparactor.left = new FormAttachment(lblPos, 0, SWT.LEFT);
		btnChiroparactor.setLayoutData(fd_btnChiroparactor);
		
		Button btnHaveYouEver = new Button(composite_1, SWT.CHECK);
		btnHaveYouEver.setText("Have you ever had a therapeutic massage before?");
		FormData fd_btnHaveYouEver = new FormData();
		fd_btnHaveYouEver.top = new FormAttachment(btnCheckButton, 37);
		fd_btnHaveYouEver.left = new FormAttachment(lblName, 0, SWT.LEFT);
		btnHaveYouEver.setLayoutData(fd_btnHaveYouEver);
		
		txtPrimaryReasonFor = new Text(composite_1, SWT.NONE);
		txtPrimaryReasonFor.setText("Primary reason for appointment:");
		FormData fd_txtPrimaryReasonFor = new FormData();
		fd_txtPrimaryReasonFor.top = new FormAttachment(btnHaveYouEver, 16);
		fd_txtPrimaryReasonFor.left = new FormAttachment(lblName, 0, SWT.LEFT);
		txtPrimaryReasonFor.setLayoutData(fd_txtPrimaryReasonFor);
		
		text_8 = new Text(composite_1, SWT.BORDER);
		FormData fd_text_8 = new FormData();
		fd_text_8.right = new FormAttachment(text_4, 0, SWT.RIGHT);
		fd_text_8.top = new FormAttachment(txtPrimaryReasonFor, 6);
		fd_text_8.left = new FormAttachment(lblName, 0, SWT.LEFT);
		text_8.setLayoutData(fd_text_8);
		
		TabItem tbtmNewItem_1 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_1.setText("Client History");
		
		Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem_1.setControl(composite_2);
		composite_2.setLayout(new FillLayout(SWT.VERTICAL));
		
		Label lblNewLabel = new Label(composite_2, SWT.NONE);
		lblNewLabel.setText("Out of the following list, check all that apply to you");
		
		Button btnDf = new Button(composite_2, SWT.CHECK);
		btnDf.setText("Heart problems, chest discomfort/pressure/pain or heart disease?");
		
		Button btnCheckButton_1 = new Button(composite_2, SWT.CHECK);
		btnCheckButton_1.setText("Sudden tingling, numbness in arms, hands, chest, face, feet or legs?");
		
		Button btnCheckButton_2 = new Button(composite_2, SWT.CHECK);
		btnCheckButton_2.setText("High or abnormal blood pressure?");
		
		Button btnCheckButton_3 = new Button(composite_2, SWT.CHECK);
		btnCheckButton_3.setText("Respiratory or breathing problems (asthma, allergies, bronchitis)?");
		
		Button btnCheckButton_4 = new Button(composite_2, SWT.CHECK);
		btnCheckButton_4.setText("Diabetes?");
		
		Button btnCheckButton_5 = new Button(composite_2, SWT.CHECK);
		btnCheckButton_5.setText("Faintness, dizziness, lightheadedness or blackouts?");
		
		Button btnCheckButton_6 = new Button(composite_2, SWT.CHECK);
		btnCheckButton_6.setText("Frequent headaches?");
		
		Button btnCheckButton_7 = new Button(composite_2, SWT.CHECK);
		btnCheckButton_7.setText("Contact lenses?");
		
		Button btnCheckButton_8 = new Button(composite_2, SWT.CHECK);
		btnCheckButton_8.setText("Special shoes or orthotic supports?");
		
		Button btnCheckButton_9 = new Button(composite_2, SWT.CHECK);
		btnCheckButton_9.setText("Varicose veins?");
		
		Button btnCheckButton_10 = new Button(composite_2, SWT.CHECK);
		btnCheckButton_10.setText("Arthritis or osteoporosis? Joints affected.");
		
		Button btnCheckButton_11 = new Button(composite_2, SWT.CHECK);
		btnCheckButton_11.setText("Any type of cancer?");
		
		Button btnCheckButton_12 = new Button(composite_2, SWT.CHECK);
		btnCheckButton_12.setText("Chronic diarrhea or constipation?");
		
		Button btnCheckButton_13 = new Button(composite_2, SWT.CHECK);
		btnCheckButton_13.setText("Currently on medication?");
		
		Button btnCheckButton_14 = new Button(composite_2, SWT.CHECK);
		btnCheckButton_14.setText("Receieved Cortisone injections?");
		
		Button btnCheckButton_15 = new Button(composite_2, SWT.CHECK);
		btnCheckButton_15.setText("Any skin conditions or reactions to lotions or creams?");
		
		Button btnCheckButton_16 = new Button(composite_2, SWT.CHECK);
		btnCheckButton_16.setText("Any other diagnosis or medical condition?");
		
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
		btnSave.setBounds(467, 368, 75, 25);
		btnSave.setText("Save");
		
		Button btnCancel = new Button(composite, SWT.NONE);
		btnCancel.setBounds(576, 368, 75, 25);
		btnCancel.setText("Cancel");
		
		Button btnSoapBox = new Button(composite, SWT.NONE);
		btnSoapBox.setBounds(22, 368, 75, 25);
		btnSoapBox.setText("Soap Box");

		shlCreatClient.open();
		shlCreatClient.layout();
		while (!shlCreatClient.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
