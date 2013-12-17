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
			model.properties.setProperty(ICommonProperties.xAxisLabel, Boolean.FALSE.toString());
			model.properties.setProperty(ICommonProperties.yAxisLabel, Boolean.FALSE.toString());
		}else{
			model.properties.setProperty(ICommonProperties.xAxisLabel, Boolean.TRUE.toString());
			model.properties.setProperty(ICommonProperties.yAxisLabel, Boolean.TRUE.toString());
		}
		mainGUI.graphPanel.repaint();
		return true;
	}
}
