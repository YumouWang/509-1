package model;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import dataset.ICommonProperties;

public class ApplicationProperties {

	private static ApplicationProperties applicationProperties;
	
	public Properties properties;
	
	private ApplicationProperties(){
		
	}
	
	public static ApplicationProperties getInstance(){
		if(applicationProperties == null){
			applicationProperties = new ApplicationProperties();
			applicationProperties.properties = new Properties();
			File f = new File("./application.properties");
			try{
				applicationProperties.properties.load(new FileInputStream(f));
				
				// set default values
				applicationProperties.properties.setProperty(ICommonProperties.trendLineVisible, Boolean.FALSE.toString());
				applicationProperties.properties.setProperty(ICommonProperties.trendLineEquationVisible, Boolean.FALSE.toString());
				applicationProperties.properties.setProperty(ICommonProperties.horizontalLines, Boolean.FALSE.toString());
				applicationProperties.properties.setProperty(ICommonProperties.xAxisLabel, Boolean.FALSE.toString());
				applicationProperties.properties.setProperty(ICommonProperties.yAxisLabel, Boolean.FALSE.toString());
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return applicationProperties;
	}
}
