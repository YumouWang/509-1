package controller;

import javax.swing.DefaultListModel;

import view.MainGUI;
import model.Dataset;
import model.Model;

public class CreateNewDatasetController extends BaseController {

	MainGUI mainGUI;
	
	public CreateNewDatasetController(Model model, MainGUI mainGUI) {
		super(model);
		this.mainGUI = mainGUI;
	}

	@Override
	public boolean act() {
		model.setDataset(new Dataset());
		DefaultListModel listModel = (DefaultListModel)mainGUI.list.getModel();
		listModel.removeAllElements();
		mainGUI.btnShowHideTrendLine.setEnabled(false);
		mainGUI.btnShowHideFormula.setEnabled(false);
		mainGUI.graphPanel.repaint();
		return true;
	}
}
