package dataset;

import java.awt.*;
import javax.swing.*;
import java.util.*;

public interface IGraph {
	/** Register data set with drawer. */
	void setDataSet(IDataSet ds);

	/** 
	 * Actually draws the graph into given JPanel object.
	 * Note that Graphics object g is the one associated with this
	 * JPanel. 
	 * 
	 * It is assumed the graphing object already has dataset
	 * because setDataSet(ds) was invoked before drawing 
	 */
	void draw(Graphics g, JPanel panel);

	/** Prior to drawing, make sure properties are available. */
	void setProperties(Properties p);
}