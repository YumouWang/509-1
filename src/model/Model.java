package model;

import java.util.Properties;

import dataset.ICommonProperties;
import dataset.IDataSet;

public class Model {
	
	public IDataSet dataSet;
	public Properties properties;
	public String graphName;
	
	public Model(){
		this.dataSet = new DataSet();
		this.properties = ApplicationProperties.getInstance().properties;
		this.graphName = ICommonProperties.cartesian;
		
	}
	
	public IDataSet getDataSet() {
		return dataSet;
	}
	
	public void setDataset(DataSet dataSet){
		this.dataSet = dataSet;
	}

}
