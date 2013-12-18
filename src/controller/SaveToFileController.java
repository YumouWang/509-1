package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.swing.JFileChooser;

import model.Model;

public class SaveToFileController extends BaseController{
	JFileChooser fc;
	public SaveToFileController(Model model) {
		super(model);
		fc = new JFileChooser();
	}

	@Override
	public boolean act() {
		int option = fc.showSaveDialog(null);
        if (option == JFileChooser.APPROVE_OPTION) {
            try{
                FileWriter fw = new FileWriter(fc.getSelectedFile().getPath());
                BufferedWriter bw = new BufferedWriter(fw);
                for(int i = 0; i < model.getDataSet().size() - 1; i++){
                	bw.write(model.getDataSet().getCoordinate(i, 0) + "," + model.getDataSet().getCoordinate(i, 1));
                	bw.newLine();
                }
				bw.write(model.getDataSet().getCoordinate(
						model.getDataSet().size() - 1, 0)
						+ ","
						+ model.getDataSet().getCoordinate(
								model.getDataSet().size() - 1, 1));
				bw.close();
				fw.close();
                return true;
            }catch(Exception e){
            	e.printStackTrace();
            }
        }
        return false;
	}
}
