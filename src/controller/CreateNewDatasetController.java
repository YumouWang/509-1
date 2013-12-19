package controller;

import javax.swing.DefaultListModel;

import model.Model;
import view.MainGUI;

public class CreateNewDatasetController extends BaseController {

	MainGUI mainGUI;
	
	public CreateNewDatasetController(Model model, MainGUI mainGUI) {
		super(model);
		this.mainGUI = mainGUI;
	}

	@Override
	public boolean act() {
		model.clearDataset();
		DefaultListModel listModel = (DefaultListModel)mainGUI.list.getModel();
		listModel.removeAllElements();
		mainGUI.btnShowHideTrendLine.setEnabled(false);
		mainGUI.btnShowHideFormula.setEnabled(false);
		mainGUI.graphPanel.repaint();
		return true;
	}
}
