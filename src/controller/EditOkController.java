package controller;

import javax.swing.DefaultListModel;

import dataset.IDataSet;
import model.Dataset;
import model.Model;
import model.Point;
import view.AddEditPointGUI;
import view.MainGUI;

public class EditOkController extends BaseController{
	/*
	 *  this controller is used to add
	 */
	AddEditPointGUI addEditPointGUI;
	MainGUI mainGUI;
	
	public EditOkController(Model model, AddEditPointGUI addEditPointGUI) {
		super(model);
		this.addEditPointGUI = addEditPointGUI;
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
		
		IDataSet iDataSet = model.getDataset();
		boolean updatePointResult = ((Dataset)iDataSet).updatePoint(mainGUI.list.getSelectedIndex(), p);
		if(updatePointResult){
			DefaultListModel listModel = (DefaultListModel)mainGUI.list.getModel();
			listModel.set(mainGUI.list.getSelectedIndex(), p.toString());
			addEditPointGUI.dispose();
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
