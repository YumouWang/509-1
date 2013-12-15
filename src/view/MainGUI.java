package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import model.Model;
import controller.CreateNewDatasetController;
import controller.LoadFromFileController;
import controller.RemovePointController;
import controller.SaveToFileController;
import controller.ShowHideAxisLabelController;
import controller.ShowHideHorizontalLinesController;
import controller.ShowHideTrendLineEquationController;
import controller.ShowHideTrendLineController;
import controller.SwitchGraphController;

public class MainGUI extends JFrame {
	
	/**
	 * Create the JFrame.
	 */
	
	// keep track of model
	Model model;
	public GraphPanel graphPanel;
	public JList<String> list;
	public ButtonGroup buttonGroup;
	public JButton btnShowHideTrendLine ;
	public JButton btnShowHideFormula;
	public JRadioButton rdbtnCartesianPlot;
	public JRadioButton rdbtnColumnChart;
	public JRadioButton rdbtnHorizontalBarGraph;
	public JRadioButton rdbtnMultiplelines;
	public JButton btnShowHideBackGroundLines;
	public JButton btnShowhideAxisValues;
	public JButton btnCreateNewDataset;
	
	public MainGUI(Model m) {
		this.model = m;
		
		setLocation(new Point(600, 400));
		setSize(new Dimension(1024, 768));
		setTitle("My App");
		setRootPaneCheckingEnabled(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainButtonPanel = new JPanel();
		
		JPanel drawPanel = new JPanel();
		
		list = new JList<String>(new DefaultListModel<String>());
		graphPanel = new GraphPanel(model);
		
		rdbtnCartesianPlot = new JRadioButton("Cartesian Plot");
		rdbtnCartesianPlot.setSelected(true);
		rdbtnColumnChart = new JRadioButton("Column Chart");
		rdbtnColumnChart.setSelected(false);
		rdbtnHorizontalBarGraph = new JRadioButton("Horizontal Bar Graph");
		rdbtnHorizontalBarGraph.setSelected(false);
		rdbtnMultiplelines = new JRadioButton("Multiple Lines");
		rdbtnMultiplelines.setSelected(false);
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnCartesianPlot);
		buttonGroup.add(rdbtnColumnChart);
		buttonGroup.add(rdbtnHorizontalBarGraph);
		buttonGroup.add(rdbtnMultiplelines);
		
		rdbtnCartesianPlot.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {				
				new SwitchGraphController(model, MainGUI.this).act();
			}
		});
		
		rdbtnColumnChart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {				
				new SwitchGraphController(model, MainGUI.this).act();
			}
		});

		rdbtnHorizontalBarGraph.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {				
				new SwitchGraphController(model, MainGUI.this).act();
			}
		});
		
		rdbtnMultiplelines.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new SwitchGraphController(model, MainGUI.this).act();
			}
		});

		JButton btnAddAPoint = new JButton("Add a Point");
		btnAddAPoint.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				AddEditPointGUI gui = new AddEditPointGUI(model, MainGUI.this, true);
				gui.setModal(true);  // STOP EVERYTHING UNTIL USER SELECTS OK or CANCEL
				gui.setVisible(true);
			}
		});
		
		JButton btnRemoveAPoint = new JButton("Remove a Point");
		btnRemoveAPoint.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new RemovePointController(model, MainGUI.this).act();
			}
		});
		
		JButton btnEditAPoint = new JButton("Edit a Point");
		btnEditAPoint.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//new EditPointController(model, MainGUI.this).act();
				if(MainGUI.this.list.getSelectedIndex() != -1){
					AddEditPointGUI gui = new AddEditPointGUI(model, MainGUI.this, false);
					gui.setModal(true);  // STOP EVERYTHING UNTIL USER SELECTS OK or CANCEL
					gui.setVisible(true);
				}
			}
		});
		
		JButton btnLoadDataset = new JButton("Load Dataset");
		btnLoadDataset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new LoadFromFileController(model, MainGUI.this).act();
			}
		});
		
		JButton btnSaveDataset = new JButton("Save Dataset");
		btnSaveDataset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new SaveToFileController(model).act();
			}
		});
		
		btnShowhideAxisValues = new JButton("Show/Hide Axis Label");
		btnShowhideAxisValues.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new ShowHideAxisLabelController(model, MainGUI.this).act();
			}
		});
		
		
		btnShowHideBackGroundLines = new JButton("Show/Hide Horizontal Lines");
		btnShowHideBackGroundLines.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new ShowHideHorizontalLinesController(model, MainGUI.this).act();
			}
		});
		
		btnShowHideTrendLine = new JButton("Show/Hide Trend Line");
		btnShowHideTrendLine.setEnabled(false);
		btnShowHideTrendLine.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new ShowHideTrendLineController(model, MainGUI.this).act();
			}
		});
		
		btnShowHideFormula = new JButton("Show/Hide Equation");
		btnShowHideFormula.setEnabled(false);
		btnShowHideFormula.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new ShowHideTrendLineEquationController(model, MainGUI.this).act();
			}
		});
		
		btnCreateNewDataset = new JButton("Create New Dataset");
		btnCreateNewDataset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateNewDatasetController(model, MainGUI.this).act();
			}
		});
		
		
		JScrollPane scrollPane = new JScrollPane();
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(14)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(mainButtonPanel, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(drawPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(graphPanel, GroupLayout.PREFERRED_SIZE, 551, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(41)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(mainButtonPanel, GroupLayout.PREFERRED_SIZE, 425, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 621, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(graphPanel, GroupLayout.PREFERRED_SIZE, 481, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(drawPanel, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)))
					.addContainerGap())
		);
		
		
		scrollPane.setViewportView(list);
		

		
		
		GroupLayout gl_drawPanel = new GroupLayout(drawPanel);
		gl_drawPanel.setHorizontalGroup(
			gl_drawPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_drawPanel.createSequentialGroup()
					.addGroup(gl_drawPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_drawPanel.createSequentialGroup()
							.addContainerGap(41, Short.MAX_VALUE)
							.addGroup(gl_drawPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnShowHideTrendLine, Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
								.addComponent(btnShowhideAxisValues, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(54))
						.addGroup(gl_drawPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(rdbtnCartesianPlot)
							.addGap(18)
							.addComponent(rdbtnColumnChart)
							.addGap(22)))
					.addGroup(gl_drawPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_drawPanel.createSequentialGroup()
							.addComponent(rdbtnHorizontalBarGraph)
							.addGap(18)
							.addComponent(rdbtnMultiplelines))
						.addComponent(btnShowHideFormula)
						.addComponent(btnShowHideBackGroundLines))
					.addContainerGap())
		);
		gl_drawPanel.setVerticalGroup(
			gl_drawPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_drawPanel.createSequentialGroup()
					.addGap(15)
					.addGroup(gl_drawPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnCartesianPlot)
						.addComponent(rdbtnColumnChart)
						.addComponent(rdbtnHorizontalBarGraph)
						.addComponent(rdbtnMultiplelines))
					.addGap(30)
					.addGroup(gl_drawPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnShowhideAxisValues)
						.addComponent(btnShowHideBackGroundLines))
					.addGap(18)
					.addGroup(gl_drawPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnShowHideTrendLine)
						.addComponent(btnShowHideFormula))
					.addContainerGap(47, Short.MAX_VALUE))
		);
		drawPanel.setLayout(gl_drawPanel);

		GroupLayout gl_mainButtonPanel = new GroupLayout(mainButtonPanel);
		gl_mainButtonPanel.setHorizontalGroup(
			gl_mainButtonPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainButtonPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_mainButtonPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnCreateNewDataset, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnSaveDataset, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnLoadDataset, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnEditAPoint, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnRemoveAPoint, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnAddAPoint, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		gl_mainButtonPanel.setVerticalGroup(
			gl_mainButtonPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainButtonPanel.createSequentialGroup()
					.addGap(40)
					.addComponent(btnAddAPoint)
					.addGap(42)
					.addComponent(btnRemoveAPoint)
					.addGap(42)
					.addComponent(btnEditAPoint)
					.addGap(46)
					.addComponent(btnLoadDataset)
					.addGap(42)
					.addComponent(btnSaveDataset)
					.addPreferredGap(ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
					.addComponent(btnCreateNewDataset)
					.addGap(22))
		);
		mainButtonPanel.setLayout(gl_mainButtonPanel);
		getContentPane().setLayout(groupLayout);

		this.setLocationRelativeTo(null);	// to make the Jframe center 
		graphPanel.setBackground(new Color(255,255,255));
		
	}
}
