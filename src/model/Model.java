package model;

import java.util.Properties;

import dataset.ICommonProperties;
import dataset.IDataSet;

public class Model {
	
	public IDataSet dataSet;
	public Properties properties;
	private String graphName;
	
	public Model(){
		this.dataSet = new DataSet();
		this.properties = ApplicationProperties.getInstance().properties;
		this.graphName = ICommonProperties.cartesian;
		
	}
	
	public IDataSet getDataSet() {
		return dataSet;
	}
	
	public void setGraph(String graphName){
		this.graphName = graphName;
	}
	
	public String getGraphName(){
		return this.graphName;
	}
	
	public void clearDataset(){
		this.dataSet = new DataSet();
		properties.setProperty(ICommonProperties.trendLineVisible, Boolean.FALSE.toString());
		properties.setProperty(ICommonProperties.trendLineEquationVisible, Boolean.FALSE.toString());
	}

}
