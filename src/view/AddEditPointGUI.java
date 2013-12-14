package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AddOkController;
import controller.EditOkController;

import model.Model;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;

public class AddEditPointGUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
	Model model;
	MainGUI mainGUI;
	DefaultListModel listModel;
	JList list;
	JTextField textField_x;
	JTextField textField_y;
	JLabel warningLabel;
	private JButton okButton;
	private JButton cancelButton;
	private boolean isAddRequest;


	/**
	 * Create the dialog.
	 */
	public AddEditPointGUI(Model model, MainGUI mainGUI, boolean isAddRequest) {
		this.model = model;
		this.mainGUI = mainGUI;
		this.isAddRequest = isAddRequest;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		textField_x = new JTextField();
		textField_x.setColumns(10);
		
		textField_y = new JTextField();
		textField_y.setColumns(10);
		
		JLabel lblX = new JLabel("X");
		
		JLabel lblY = new JLabel("Y");
		{
			okButton = new JButton("OK");
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
			
			okButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                	if(AddEditPointGUI.this.isAddRequest){
                		new AddOkController(AddEditPointGUI.this.model, AddEditPointGUI.this).act();
                	}else{
                		new EditOkController(AddEditPointGUI.this.model, AddEditPointGUI.this).act();
                	}
                }
			});

		}
		{
			cancelButton = new JButton("Cancel");
			cancelButton.setActionCommand("Cancel");
			cancelButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                	AddEditPointGUI.this.dispose();
                }
		});
		}
		
		warningLabel = new JLabel("");
		warningLabel.setForeground(Color.RED);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap(136, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblX)
							.addGap(41))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblY, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(30)))
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_y, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_x, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(155))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(128)
					.addComponent(okButton)
					.addGap(40)
					.addComponent(cancelButton)
					.addContainerGap(144, Short.MAX_VALUE))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(175)
					.addComponent(warningLabel)
					.addContainerGap(238, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(67)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_x, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblX))
					.addGap(29)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_y, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblY))
					.addGap(31)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(okButton)
						.addComponent(cancelButton))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(warningLabel)
					.addGap(27))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
		
		setLocationRelativeTo(null); // make the dialog appear center in the screen
	}


	public JTextField getTextField_x() {
		return textField_x;
	}

	public JTextField getTextField_y() {
		return textField_y;
	}

	public JLabel getWarningLabel() {
		return warningLabel;
	}

	public MainGUI getMainGUI() {
		return mainGUI;
	}
}
