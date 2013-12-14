package controller;

import dataset.ICommonProperties;
import view.MainGUI;
import model.Model;

public class ShowHideAxisLabelController extends BaseController {

	MainGUI mainGUI;
	
	public ShowHideAxisLabelController(Model model, MainGUI mainGUI) {
		super(model);
		this.mainGUI = mainGUI;
	}

	@Override
	public boolean act() {
		if(Boolean.parseBoolean(model.properties.getProperty(ICommonProperties.xAxisLabel))){
			model.properties.setProperty(ICommonProperties.xAxisLabel, "false");
			model.properties.setProperty(ICommonProperties.yAxisLabel, "false");
		}else{
			model.properties.setProperty(ICommonProperties.xAxisLabel, "true");
			model.properties.setProperty(ICommonProperties.yAxisLabel, "true");
		}
		mainGUI.graphPanel.repaint();
		return true;
	}
}
