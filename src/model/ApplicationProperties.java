package model;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

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
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return applicationProperties;
	}
}
