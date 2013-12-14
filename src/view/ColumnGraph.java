package view;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import javax.swing.JPanel;

import dataset.ICommonProperties;
import dataset.IDataSet;
import dataset.IGraph;

public class ColumnGraph implements IGraph {

	IDataSet iDataSet;
	String formula;
	double slope;
	double intercept;

	int originX;
	int originY;
	double numericUnitX;
	double numericUnitY;
	int cursorUnit;
	double maxXOnG;
	double minXOnG;
	double maxYOnG;
	double minYOnG;
	Properties properties;

	boolean isXAxisLabelVisible;
	boolean isYAxisLabelVisible;
	boolean isHorizontalLinesVisible;

	public ColumnGraph() {
		this.originX = 0;
		this.originY = 0;
		this.numericUnitX = 1;
		this.numericUnitY = 1;
		this.maxXOnG = 0;
		this.maxYOnG = 0;
		this.minXOnG = 0;
		this.minYOnG = 0;
		this.cursorUnit = 40;
	}

	public void setDataSet(IDataSet ds) {
		this.iDataSet = ds;
	}

	public void draw(Graphics g, JPanel panel) {
		drawColumnChartAxisLines(g, panel);
		drawColumnChartStripes(g, panel);
		drawColumnChartAxisLabel(g, panel);
		drawColumnChartHorizontalLines(g, panel);
	}

	public void setProperties(Properties p) {
		this.properties = p;
		this.isXAxisLabelVisible = Boolean.parseBoolean(p
				.getProperty(ICommonProperties.xAxisLabel));
		this.isYAxisLabelVisible = Boolean.parseBoolean(p
				.getProperty(ICommonProperties.yAxisLabel));
		this.isHorizontalLinesVisible = Boolean.parseBoolean(p
				.getProperty(ICommonProperties.horizontalLines));
	}

	void reset() {
		this.originX = 0;
		this.originY = 0;
		this.numericUnitX = 1;
		this.numericUnitY = 1;
		this.maxXOnG = 0;
		this.maxYOnG = 0;
		this.minXOnG = 0;
		this.minYOnG = 0;
		this.cursorUnit = 40;
	}

	void setColumnChartAxisBounds(double upperBound, double lowerBound,
			JPanel panel) {
		this.maxXOnG = 0;
		this.maxYOnG = 0;
		this.minYOnG = 0;
		if (this.iDataSet.size() > 0) {
			int i = 1;
			double bound;
			double rX = this.iDataSet.size();
			if (rX >= 10) {
				bound = 0;
				while (rX >= Math.pow(10, i)) {
					i++;
				}
				bound = Math.pow(10, i);
				for (int j = 2; j <= 10; j++) {
					if (j * bound > rX) {
						this.numericUnitX = (j - 1) * bound / 5;
						break;
					}
				}
			}
			double rY;
			if (upperBound != 0 || lowerBound != 0) {
				bound = 0;
				if (Math.abs(upperBound) > Math.abs(lowerBound)) {
					rY = Math.abs(upperBound);
				} else {
					rY = Math.abs(lowerBound);
				}
				i = 1;
				if (rY >= 10) {
					while (rY >= Math.pow(10, i)) {
						i++;
					}
					bound = Math.pow(10, i - 1);
				} else {
					while (rY < Math.pow(10, i)) {
						i--;
					}
					bound = Math.pow(10, i);
				}
				for (int j = 2; j <= 10; j++) {
					if (j * bound > rY) {
						this.numericUnitY = (j - 1) * bound / 5;
						break;
					}
				}
			}
		} else {
			this.numericUnitX = 1;
			this.numericUnitY = 1;
		}
		for (int i = this.originX + this.cursorUnit; i < panel.getWidth() - 20; i += this.cursorUnit) {
			this.maxXOnG += this.numericUnitX;
		}
		for (int i = this.originY - this.cursorUnit; i > 20; i -= this.cursorUnit) {
			this.maxYOnG += this.numericUnitY;
		}
		for (int i = this.originY + this.cursorUnit; i < panel.getHeight() - 20; i += this.cursorUnit) {
			this.minYOnG -= this.numericUnitY;
		}
	}

	void drawColumnChartAxisLines(Graphics g, JPanel panel) {
		g.setColor(new Color(0, 0, 0));
		ArrayList<Double> xyBounds = new ArrayList<Double>();
		xyBounds.add(this.iDataSet.getMaxX());
		xyBounds.add(this.iDataSet.getMaxY());
		xyBounds.add(this.iDataSet.getMinX());
		xyBounds.add(this.iDataSet.getMinY());
		double upperBound = -Double.MAX_VALUE;
		double lowerBound = Double.MAX_VALUE;
		Iterator<Double> iter = xyBounds.iterator();
		while (iter.hasNext()) {
			double temp = iter.next();
			if (temp > upperBound) {
				upperBound = temp;
			}
			if (temp < lowerBound) {
				lowerBound = temp;
			}
		}
		this.originX = 40;
		if (this.iDataSet.size() == 0) {
			this.originY = panel.getHeight() / 2;
		} else {
			if (lowerBound >= 0) {
				this.originY = panel.getHeight() - 41;
			} else if (upperBound <= 0) {
				this.originY = 41;
			} else {
				this.originY = (int) ((40 + (panel.getHeight() - 80)
						* (upperBound / (upperBound - lowerBound))));
			}
		}

		g.drawLine(this.originX, 0, this.originX, panel.getHeight());
		g.drawLine(0, this.originY, panel.getWidth(), this.originY);
		g.drawLine(panel.getWidth() - 5, this.originY - 5,
				panel.getWidth() - 1, this.originY);
		g.drawLine(panel.getWidth() - 5, this.originY + 5,
				panel.getWidth() - 1, this.originY);
		g.drawLine(this.originX - 5, 5, this.originX, 0);
		g.drawLine(this.originX + 5, 5, this.originX, 0);
		setColumnChartAxisBounds(upperBound, lowerBound, panel);
		for (int i = 0; i <= (int) (this.maxXOnG / this.numericUnitX); i++) {
			g.drawLine(this.originX + this.cursorUnit * i, this.originY - 5,
					this.originX + this.cursorUnit * i, this.originY);
		}
		for (int i = (int) (this.minYOnG / this.numericUnitY); i <= (int) (this.maxYOnG / this.numericUnitY); i++) {
			g.drawLine(this.originX, this.originY - this.cursorUnit * i,
					this.originX + 5, this.originY - this.cursorUnit * i);
		}
	}

	void drawColumnChartStripes(Graphics g, JPanel panel) {
		for (int i = 0; i < this.iDataSet.size(); i++) {
			int a = i + 1;
			int centerXOfStripes = (int) (this.originX + (a / this.numericUnitX)
					* this.cursorUnit);
			int upperBoundOfXStripe = (int) (this.originY - (this.iDataSet
					.getCoordinate(i, 0) / this.numericUnitY) * this.cursorUnit);
			int upperBoundOfYStripe = (int) (this.originY - (this.iDataSet
					.getCoordinate(i, 1) / this.numericUnitY) * this.cursorUnit);
			g.setColor(new Color(0, 0, 255));
			g.fillRect(centerXOfStripes - 3, this.originY, 3,
					upperBoundOfXStripe - this.originY);
			g.setColor(new Color(255, 0, 0));
			g.fillRect(centerXOfStripes + 1, this.originY, 3,
					upperBoundOfYStripe - this.originY);
		}
	}

	void drawColumnChartAxisLabel(Graphics g, JPanel panel) {
		g.setColor(new Color(0, 0, 0));
		if (this.isXAxisLabelVisible) {
			String unitX = Double.toString(this.numericUnitX);
			for (int i = 0; i <= (int) (this.maxXOnG / this.numericUnitX); i++) {
				NumberFormat nf = NumberFormat.getNumberInstance();
				nf.setMaximumFractionDigits(unitX.length() - 1
						- unitX.indexOf("."));
				String s = nf.format(i * this.numericUnitX);
				g.drawString(s, this.originX + this.cursorUnit * i,
						originY + 10);
			}
		}
		if (this.isYAxisLabelVisible) {
			FontMetrics fm = g.getFontMetrics();
			String unitY = Double.toString(this.numericUnitY);
			for (int i = (int) (this.minYOnG / this.numericUnitY); i <= (int) (this.maxYOnG / this.numericUnitY); i++) {
				NumberFormat nf = NumberFormat.getNumberInstance();
				nf.setMaximumFractionDigits(unitY.length() - 1
						- unitY.indexOf("."));
				String s = nf.format(i * this.numericUnitY);
				g.drawString(s, originX - fm.stringWidth(s) - 2, originY
						- cursorUnit * i);
			}
		}
	}

	void drawColumnChartHorizontalLines(Graphics g, JPanel panel) {
		g.setColor(new Color(0, 0, 0, 20));
		if (this.isHorizontalLinesVisible) {
			for (int i = (int) (this.minYOnG / this.numericUnitY); i <= (int) (this.maxYOnG / this.numericUnitY); i++) {
				g.drawLine(0, this.originY - this.cursorUnit * i, panel.getWidth(),
						this.originY - this.cursorUnit * i);
			}
		}
	}
}
