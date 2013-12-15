package view;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import javax.swing.JPanel;

import dataset.ICommonProperties;
import dataset.IDataSet;
import dataset.IGraph;

public class MultipleLines implements IGraph {
	IDataSet iDataSet;

	int originX;
	int originY;
	double numericUnitX;
	double numericUnitY;
	int xCursorUnit;
	int yCursorUnit;
	double maxXOnG;
	double minXOnG;
	double maxYOnG;
	double minYOnG;
	Properties properties;

	boolean isXAxisLabelVisible;
	boolean isYAxisLabelVisible;
	boolean isHorizontalLinesVisible;

	public MultipleLines() {
	}

	public void setDataSet(IDataSet ds) {
		this.iDataSet = ds;
	}

	public void draw(Graphics g, JPanel panel) {
		reset(panel);
		drawAxisLines(g, panel);
		drawPointsAndLines(g, panel);
		drawAxisLabel(g, panel);
		drawHorizontalLines(g, panel);
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

	void reset(JPanel panel) {
		this.originX = 0;
		this.originY = 0;
		this.numericUnitX = 1;
		this.numericUnitY = 1;
		this.maxXOnG = 0;
		this.maxYOnG = 0;
		this.minXOnG = 0;
		this.minYOnG = 0;
		this.xCursorUnit = (int) Math.round(panel.getWidth() * 0.08);
		this.yCursorUnit = (int) Math.round(panel.getHeight() * 0.08);
	}

	void setAxisBounds(double upperBound, double lowerBound, JPanel panel) {
		this.maxXOnG = 0;
		this.maxYOnG = 0;
		this.minYOnG = 0;
		if (this.iDataSet.size() > 0) {
			double rX = this.iDataSet.size();
			if (rX > 10) {
				double bound = 0;
				int i = 2;
				while (rX >= Math.pow(10, i)) {
					i++;
				}
				bound = Math.pow(10, i - 1);
				for (int j = 2; j <= 10; j++) {
					if (j * bound > rX) {
						this.numericUnitX = j * bound / 10;
						break;
					}
				}
			}
			double rY;
			if (upperBound != 0 || lowerBound != 0) {
				if (lowerBound > 0) {
					rY = Math.abs(upperBound);
				} else {
					if (upperBound < 0) {
						rY = Math.abs(lowerBound);
					} else {
						rY = upperBound - lowerBound;
					}
				}
				this.numericUnitY = rY / 10;
				this.numericUnitY = this.numericUnitY
						* Math.pow(10, Double.toString(rY).length() - 1
								- (Double.toString(rY).indexOf(".")));
				this.numericUnitY += 0.5d;
				this.numericUnitY = (long) this.numericUnitY;
				this.numericUnitY = this.numericUnitY
						/ Math.pow(10d, Double.toString(rY).length() - 1
								- (Double.toString(rY).indexOf(".")));
			}
		} else {
			this.numericUnitX = 1;
			this.numericUnitY = 1;
		}
		for (int i = this.originX + this.xCursorUnit; i < panel.getWidth() - 5; i += this.xCursorUnit) {
			this.maxXOnG += this.numericUnitX;
		}
		for (int i = this.originY - this.yCursorUnit; i >= 5; i -= this.yCursorUnit) {
			this.maxYOnG += this.numericUnitY;
		}
		for (int i = this.originY + this.yCursorUnit; i < panel.getHeight(); i += this.yCursorUnit) {
			this.minYOnG -= this.numericUnitY;
		}
	}

	void drawAxisLines(Graphics g, JPanel panel) {
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
		this.originX = (int) Math.round(panel.getWidth() * 0.07);
		int vLimit = (int) Math.round(panel.getHeight() * 0.07);
		if (this.iDataSet.size() == 0) {
			this.originY = Math.round(panel.getHeight() / 2);
		} else {
			if (lowerBound >= 0) {
				this.originY = panel.getHeight() - vLimit - 1;
			} else {
				if (upperBound <= 0) {
					this.originY = vLimit;
				} else {
					this.originY = (int) Math.round(vLimit
							+ (panel.getHeight() - 2 * vLimit)
							* (upperBound / (upperBound - lowerBound)));
				}
			}
		}

		g.drawLine(this.originX, 0, this.originX, panel.getHeight());
		g.drawLine(0, this.originY, panel.getWidth(), this.originY);
		g.drawLine(panel.getWidth() - 5, this.originY - 4,
				panel.getWidth() - 1, this.originY);
		g.drawLine(panel.getWidth() - 5, this.originY + 4,
				panel.getWidth() - 1, this.originY);
		g.drawLine(this.originX - 4, 4, this.originX, 0);
		g.drawLine(this.originX + 4, 4, this.originX, 0);
		setAxisBounds(upperBound, lowerBound, panel);
		for (int i = 0; i <= (int) Math.round(this.maxXOnG / this.numericUnitX); i++) {
			g.drawLine(this.originX + this.xCursorUnit * i, this.originY - 4,
					this.originX + this.xCursorUnit * i, this.originY);
		}
		for (int i = (int) Math.round(this.minYOnG / this.numericUnitY); i <= (int) Math
				.round(this.maxYOnG / this.numericUnitY); i++) {
			g.drawLine(this.originX, this.originY - this.yCursorUnit * i,
					this.originX + 4, this.originY - this.yCursorUnit * i);
		}
	}

	void drawPointsAndLines(Graphics g, JPanel panel) {
		int prevCenterXOfPoints = 0;
		int prevcenterYOfXPoint = 0;
		int prevcenterYOfYPoint = 0;

		for (int i = 0; i < this.iDataSet.size(); i++) {
			int a = i + 1;
			int centerXOfPoints = (int) Math.round(this.originX
					+ (a / this.numericUnitX) * this.xCursorUnit);
			int centerYOfXPoint = (int) (this.originY - (this.iDataSet
					.getCoordinate(i, 0) / this.numericUnitY)
					* this.yCursorUnit);
			int centerYOfYPoint = (int) (this.originY - (this.iDataSet
					.getCoordinate(i, 1) / this.numericUnitY)
					* this.yCursorUnit);
			g.setColor(new Color(0, 0, 255));
			g.drawOval(centerXOfPoints - 2, centerYOfXPoint - 2, 4, 4);
			if (i > 0) {
				g.drawLine(prevCenterXOfPoints, prevcenterYOfXPoint,
						centerXOfPoints, centerYOfXPoint);
			}
			g.setColor(new Color(255, 0, 0));
			g.drawOval(centerXOfPoints - 2, centerYOfYPoint - 2, 4, 4);
			if (i > 0) {
				g.drawLine(prevCenterXOfPoints, prevcenterYOfYPoint,
						centerXOfPoints, centerYOfYPoint);
			}
			prevCenterXOfPoints = centerXOfPoints;
			prevcenterYOfXPoint = centerYOfXPoint;
			prevcenterYOfYPoint = centerYOfYPoint;
		}
	}

	void drawAxisLabel(Graphics g, JPanel panel) {
		g.setColor(new Color(0, 0, 0));
		if (this.isXAxisLabelVisible) {
			for (int i = 0; i <= Math.round(this.maxXOnG / this.numericUnitX); i++) {
				String s = Integer.toString((int) (i * this.numericUnitX));
				g.drawString(s, this.originX + this.xCursorUnit * i,
						this.originY + 10);
			}
		}
		if (this.isYAxisLabelVisible) {
			FontMetrics fm = g.getFontMetrics();
			int dLength = Double.toString(this.numericUnitY).length() - 1
					- Double.toString(this.numericUnitY).indexOf(".");
			for (int i = (int) Math.round(this.minYOnG / this.numericUnitY); i <= Math
					.round(this.maxYOnG / this.numericUnitY); i++) {
				double numberToWrite = i * this.numericUnitY;
				String s;
				if (numberToWrite % 1 != 0) {
					numberToWrite = numberToWrite * Math.pow(10, dLength);
					if (numberToWrite >= 0) {
						numberToWrite += 0.5;
					} else {
						numberToWrite -= 0.5;
					}
					numberToWrite = (long) numberToWrite;
					numberToWrite = numberToWrite / Math.pow(10d, dLength);
					s = Double.toString(numberToWrite);
				} else {
					s = Integer.toString((int) numberToWrite);
				}
				g.drawString(s, this.originX - fm.stringWidth(s) - 2,
						this.originY - this.yCursorUnit * i);
			}
		}
	}

	void drawHorizontalLines(Graphics g, JPanel panel) {
		g.setColor(new Color(0, 0, 0, 20));
		if (this.isHorizontalLinesVisible) {
			for (int i = (int) Math.round(this.minYOnG / this.numericUnitY); i <= Math
					.round(this.maxYOnG / this.numericUnitY); i++) {
				g.drawLine(0, this.originY - this.yCursorUnit * i,
						panel.getWidth(), this.originY - this.yCursorUnit * i);
			}
		}
	}
}
