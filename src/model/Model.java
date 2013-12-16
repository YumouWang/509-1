package model;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import dataset.ICommonProperties;
import dataset.IDataSet;

public class Model {
	
	private static Model model;
	
	public IDataSet dataset;
	public Properties properties;
	public String graphName;
	
	private Model(){
	}
	
	public static Model getInstance(){
		if(model == null){
			model = new Model();
			model.dataset = new Dataset();
			model.properties = ApplicationProperties.getInstance().properties;
			File f = new File("./application.properties");
			try{
				model.properties.load(new FileInputStream(f));
				model.graphName = ICommonProperties.cartesian;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return model;
	}
	
	public IDataSet getDataset() {
		return dataset;
	}
	
	public void setDataset(Dataset dataset){
		this.dataset = dataset;
	}

}
