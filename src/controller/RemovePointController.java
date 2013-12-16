package controller;

import javax.swing.DefaultListModel;

import model.Dataset;
import model.Model;
import view.MainGUI;
import dataset.ICommonProperties;

public class RemovePointController extends BaseController{

	MainGUI mainGUI;
	
	public RemovePointController(Model model, MainGUI mainGUI) {
		super(model);
		this.mainGUI = mainGUI;
	}

	@Override
	public boolean act() {
		// remove in the model
		if(mainGUI.list.getSelectedIndex() == -1)
			return false;
		boolean removeResult = ((Dataset) model.getDataset()).removePoint(mainGUI.list.getSelectedIndex());
		if(removeResult){ // remove the selected point in JList
			DefaultListModel listModel = (DefaultListModel)mainGUI.list.getModel();
			listModel.removeElementAt(mainGUI.list.getSelectedIndex());
		}else{
			return false;
		}
		
		if(ICommonProperties.cartesian.equals(model.graphName)){
			if(model.getDataset().size() == 1){
				model.properties.setProperty(ICommonProperties.trendLineVisible, "false");
				model.properties.setProperty(ICommonProperties.trendLineEquationVisible, "false");
				mainGUI.btnShowHideFormula.setEnabled(false);
				mainGUI.btnShowHideTrendLine.setEnabled(false);
			}
		}
		
		// do the repaint stuff
		mainGUI.graphPanel.repaint();
		return true;
	}
}
