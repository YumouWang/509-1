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
			model.properties.setProperty(ICommonProperties.trendLineVisible, Boolean.FALSE.toString());
			model.properties.setProperty(ICommonProperties.trendLineEquationVisible, Boolean.FALSE.toString());
			mainGUI.btnShowHideFormula.setEnabled(false);
		}else{
			model.properties.setProperty(ICommonProperties.trendLineVisible, Boolean.TRUE.toString());
			model.properties.setProperty(ICommonProperties.trendLineEquationVisible, Boolean.TRUE.toString());
			mainGUI.btnShowHideFormula.setEnabled(true);
		}
		mainGUI.graphPanel.repaint();
		return true;
	}
}
