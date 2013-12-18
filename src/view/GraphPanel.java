package view;

import java.awt.Graphics;
import java.util.Properties;

import javax.swing.JPanel;

import model.Model;
import dataset.ICommonProperties;
import dataset.IGraph;

public class GraphPanel extends JPanel {

	/**
	 * Create the Graph panel.
	 */
	Model model;
	Properties properties;
	
	public GraphPanel(Model m) {
		this.model = m;
		this.properties = m.properties;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		try{
			Class clazz = null;
			if(ICommonProperties.cartesian.equals(model.graphName)){
				clazz = Class.forName(this.properties.getProperty(ICommonProperties.cartesian));
			}else if(ICommonProperties.column.equals(model.graphName)){
				clazz = Class.forName(this.properties.getProperty(ICommonProperties.column));
			}else if(ICommonProperties.horizontalBarGraph.equals(model.graphName)){
				clazz = Class.forName(this.properties.getProperty(ICommonProperties.horizontalBarGraph));
			}else if(ICommonProperties.multipleLines.equals(model.graphName)){
				clazz = Class.forName(this.properties.getProperty(ICommonProperties.multipleLines));
			}
			
			IGraph graph = (IGraph)clazz.newInstance();
			graph.setDataSet(model.getDataSet());
			graph.setProperties(properties);
			graph.draw(g, this);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
