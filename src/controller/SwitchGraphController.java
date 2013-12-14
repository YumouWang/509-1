package controller;

import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;

import model.Model;
import view.MainGUI;

public class SwitchGraphController extends BaseController{

	MainGUI mainGUI;
	ButtonGroup buttonGroup;
	
	public SwitchGraphController(Model model, MainGUI mainGUI) {
		super(model);
		this.mainGUI = mainGUI; 
	}

	@Override
	public boolean act() {
		switchGraph();
		mainGUI.graphPanel.repaint();
		return true;
	}
	
	// switch graph
	void switchGraph(){
		Enumeration<AbstractButton> enu = this.mainGUI.buttonGroup.getElements();
		while(enu.hasMoreElements()){
			/*
			JRadioButton button = (JRadioButton)enu.nextElement();
			if(button.isSelected()){
				if(button == mainGUI.rdbtnCartesianPlot && !(model.getGraph() instanceof CartesianPlot)){
					model.switchGraph(new CartesianPlot());
					if(model.getDataset().getPoints().size() <= 1){
						mainGUI.btnShowHideTrendLine.setEnabled(false);
					}else{
						mainGUI.btnShowHideTrendLine.setEnabled(true);
					}
					mainGUI.btnShowHideFormula.setEnabled(false);
				}else if(button == mainGUI.rdbtnColumnChart && !(model.getGraph() instanceof ColumnChart)){
					model.switchGraph(new ColumnChart());
					mainGUI.btnShowHideFormula.setEnabled(false);
					mainGUI.btnShowHideTrendLine.setEnabled(false);
				}
			}
			*/
		}
	}
}
