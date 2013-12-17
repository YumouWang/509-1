package controller;

import dataset.ICommonProperties;
import view.MainGUI;
import model.Model;

public class ShowHideHorizontalLinesController extends BaseController {

	MainGUI mainGUI;
	
	public ShowHideHorizontalLinesController(Model model, MainGUI mainGUI) {
		super(model);
		this.mainGUI = mainGUI;
	}

	@Override
	public boolean act() {
		if(Boolean.parseBoolean(model.properties.getProperty(ICommonProperties.horizontalLines))){
			model.properties.setProperty(ICommonProperties.horizontalLines, Boolean.FALSE.toString());
		}else{
			model.properties.setProperty(ICommonProperties.horizontalLines, Boolean.TRUE.toString());
		}
		mainGUI.graphPanel.repaint();
		return true;
	}

}
