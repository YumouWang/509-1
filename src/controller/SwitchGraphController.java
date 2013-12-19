package controller;

import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import dataset.ICommonProperties;

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
			JRadioButton button = (JRadioButton)enu.nextElement();
			if(button.isSelected()){
				if(button == mainGUI.rdbtnCartesianPlot && !(ICommonProperties.cartesian.equals(model.getGraphName()))){
					model.setGraph(ICommonProperties.cartesian);
					if(model.getDataSet().size() <= 1){
						mainGUI.btnShowHideTrendLine.setEnabled(false);
					}else{
						mainGUI.btnShowHideTrendLine.setEnabled(true);
					}
					mainGUI.btnShowHideFormula.setEnabled(false);
				}else if(button == mainGUI.rdbtnColumnGraph && !(ICommonProperties.column.equals(model.getGraphName()))){
					model.setGraph(ICommonProperties.column);
					mainGUI.btnShowHideFormula.setEnabled(false);
					mainGUI.btnShowHideTrendLine.setEnabled(false);
				}else if(button == mainGUI.rdbtnHorizontalBarGraph && !(ICommonProperties.horizontalBarGraph.equals(model.getGraphName()))){
					model.setGraph(ICommonProperties.horizontalBarGraph);
					mainGUI.btnShowHideFormula.setEnabled(false);
					mainGUI.btnShowHideTrendLine.setEnabled(false);
				}else if(button == mainGUI.rdbtnMultipleLines && !(ICommonProperties.multipleLines.equals(model.getGraphName()))){
					model.setGraph(ICommonProperties.multipleLines);
					mainGUI.btnShowHideFormula.setEnabled(false);
					mainGUI.btnShowHideTrendLine.setEnabled(false);
				}
			}
		}
	}
}
