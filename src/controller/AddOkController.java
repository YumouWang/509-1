package controller;

import javax.swing.DefaultListModel;

import model.DataSet;
import model.Model;
import model.Point;
import view.AddEditPointGUI;
import view.MainGUI;
import dataset.ICommonProperties;
import dataset.IDataSet;

public class AddOkController extends BaseController{
	/*
	 *  this controller is used to add
	 */
	AddEditPointGUI addEditPointGUI;
	MainGUI mainGUI;
	
	public AddOkController(Model model, AddEditPointGUI gui) {
		super(model);
		this.addEditPointGUI = gui;
		this.mainGUI = this.addEditPointGUI.getMainGUI();
	}

	// do the add point process
	@Override
	public boolean act() {
		if(!validatePoint(addEditPointGUI.getTextField_x().getText(), addEditPointGUI.getTextField_y().getText())){
			addEditPointGUI.getWarningLabel().setText("invalid input");
			return false;
		}
		
		Point p = new Point(Double.parseDouble(addEditPointGUI.getTextField_x().getText()), Double.parseDouble(addEditPointGUI.getTextField_y().getText()));
		
		IDataSet iDataSet = model.getDataSet();
		boolean addPointResult = ((DataSet)iDataSet).addPoint(p);
		if(addPointResult){
			DefaultListModel listModel = (DefaultListModel)this.mainGUI.list.getModel();
			listModel.addElement(p.toString());
			addEditPointGUI.dispose();
		}else{
			return false;
		}
		
		if(ICommonProperties.cartesian.equals(model.getGraphName())){
			if(model.getDataSet().size() <= 2){
				
				model.properties.setProperty(ICommonProperties.trendLineVisible, Boolean.FALSE.toString());
				model.properties.setProperty(ICommonProperties.trendLineEquationVisible, Boolean.FALSE.toString());
				
				mainGUI.btnShowHideFormula.setEnabled(false);
				mainGUI.btnShowHideTrendLine.setEnabled(false);
				if(model.getDataSet().size() == 2){
					mainGUI.btnShowHideTrendLine.setEnabled(true);
				}
			}
		}
		
		// do the repaint stuff
		mainGUI.graphPanel.repaint();
		return true;
	}
	
	boolean validatePoint(String s1, String s2){
		try{
			Double.parseDouble(s1);
			Double.parseDouble(s2);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
}
