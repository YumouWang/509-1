package model;

import java.awt.Color;
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
				applicationProperties.properties.setProperty(ICommonProperties.color1, Color.red.getRed() + "," + Color.red.getGreen() + "," + Color.red.getBlue());
				applicationProperties.properties.setProperty(ICommonProperties.color1, Color.blue.getRed() + "," + Color.blue.getGreen() + "," + Color.blue.getBlue());
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return applicationProperties;
	}
}
