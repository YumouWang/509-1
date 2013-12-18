package controller;

import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;

import model.DataSet;
import model.Model;
import model.Point;
import view.MainGUI;
import dataset.ICommonProperties;

public class LoadFromFileController extends BaseController{

	MainGUI mainGUI;
	JFileChooser fc;
	
	public LoadFromFileController(Model model, MainGUI mainGUI) {
		super(model);
		this.mainGUI = mainGUI;
		this.fc = new JFileChooser();
	}

	@Override
	public boolean act() {
		int option = fc.showOpenDialog(null);
        if (option == JFileChooser.APPROVE_OPTION) {
            try{
                FileReader fr = new FileReader(fc.getSelectedFile().getPath());
                BufferedReader br = new BufferedReader(fr);
                clearPoints();
                String str;
                while((str = br.readLine()) != null){
                	addPoint(str);
                }
                br.close();
                fr.close();
            }catch(Exception e){
            	e.printStackTrace();
            }
            
    		// do the repaint and format reset stuff
    		clearFormat();
    		return true;
        }
        return false;
	}
	
	// clear all the format
	void clearFormat(){
        if(model.getDataSet().size() >= 2){
        	mainGUI.btnShowHideTrendLine.setEnabled(true);
        } else {
        	mainGUI.btnShowHideTrendLine.setEnabled(false);
        }
        mainGUI.btnShowHideFormula.setEnabled(false);
        mainGUI.graphPanel.repaint();
	}
	
	// clear all points
	void clearPoints(){
		model.setDataset(new DataSet());
		model.graphName = ICommonProperties.cartesian;
		mainGUI.rdbtnCartesianPlot.setSelected(true);
		DefaultListModel listModel = (DefaultListModel)mainGUI.list.getModel();
		listModel.removeAllElements();
	}
	
	// add point
	void addPoint(String str){
		String[] s = str.split(",");
    	if(validatePoint(s[0], s[1])){
    		Point p = new Point(Double.parseDouble(s[0]), Double.parseDouble(s[1]));
			((DataSet) model.getDataSet()).addPoint(p);
        	DefaultListModel listModel = (DefaultListModel)mainGUI.list.getModel();
        	listModel.addElement(p.toString());
    	}
	}
	
	// validate if the point is valid or not
	boolean validatePoint(String s1, String s2){
		try{
			Double.parseDouble(s1);
			Double.parseDouble(s2);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
}
