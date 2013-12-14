package controller;

import model.Model;
import view.MainGUI;
import dataset.ICommonProperties;

public class ShowHideTrendLineController extends BaseController {

	MainGUI mainGUI;
	
	public ShowHideTrendLineController(Model model, MainGUI mainGUI) {
		super(model);
		this.mainGUI = mainGUI;
	}

	@Override
	public boolean act() {
		//CartesianPlot graph = (CartesianPlot)model.getGraph();
		if(Boolean.parseBoolean(model.properties.getProperty(ICommonProperties.trendLineVisible))){
			model.properties.setProperty(ICommonProperties.trendLineVisible, "false");
			model.properties.setProperty(ICommonProperties.trendLineEquationVisible, "false");
			mainGUI.btnShowHideFormula.setEnabled(false);
		}else{
			model.properties.setProperty(ICommonProperties.trendLineVisible, "true");
			model.properties.setProperty(ICommonProperties.trendLineEquationVisible, "true");
			mainGUI.btnShowHideFormula.setEnabled(true);
		}
		mainGUI.graphPanel.repaint();
		return true;
	}
}
