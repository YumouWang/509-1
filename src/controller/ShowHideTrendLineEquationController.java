package controller;

import model.Model;
import view.MainGUI;
import dataset.ICommonProperties;

public class ShowHideTrendLineEquationController extends BaseController {

	MainGUI mainGUI;

	public ShowHideTrendLineEquationController(Model model, MainGUI mainGUI) {
		super(model);
		this.mainGUI = mainGUI;
	}

	@Override
	public boolean act() {

		if (Boolean.parseBoolean(model.properties
				.getProperty(ICommonProperties.trendLineEquationVisible))) {
			model.properties.setProperty(
					ICommonProperties.trendLineEquationVisible, Boolean.FALSE.toString());
		} else {
			model.properties.setProperty(
					ICommonProperties.trendLineEquationVisible, Boolean.TRUE.toString());
		}
		mainGUI.graphPanel.repaint();
		return true;
	}
}
