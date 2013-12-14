package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.text.NumberFormat;
import java.util.Properties;

import javax.swing.JPanel;

import dataset.ICommonProperties;
import dataset.IDataSet;
import dataset.IGraph;

public class CartesianPlot implements IGraph {
	IDataSet iDataSet;
	String equation;
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
	boolean isTrendLineVisible;
	boolean isTrendLineEquationVisible;

	public CartesianPlot() {
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
		reset();
		drawCartesianPlotAxisLines(g, panel);
		drawCartesianPlotPoints(g, panel);
		drawCartesianPlotAxisLabel(g, panel);
		drawCartesianPlotHorizontalLines(g, panel);
		if (this.iDataSet.size() >= 2) {
			calculateFormula();
			drawCartesianPlotTrendLineAndFormula(g, panel);
		}
	}

	public void setProperties(Properties p) {
		this.properties = p;
		this.isXAxisLabelVisible = Boolean.parseBoolean(p
				.getProperty(ICommonProperties.xAxisLabel));
		this.isYAxisLabelVisible = Boolean.parseBoolean(p
				.getProperty(ICommonProperties.yAxisLabel));
		this.isHorizontalLinesVisible = Boolean.parseBoolean(p
				.getProperty(ICommonProperties.horizontalLines));
		this.isTrendLineVisible = Boolean.parseBoolean(p
				.getProperty(ICommonProperties.trendLineVisible));
		this.isTrendLineEquationVisible = Boolean.parseBoolean(p
				.getProperty(ICommonProperties.trendLineEquationVisible));
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

	void calculateFormula() {
		int size = this.iDataSet.size();
		double aveX = 0;
		double aveY = 0;
		for (int i = 0; i < size; i++) {
			aveX += iDataSet.getCoordinate(i, 0) / size;
			aveY += iDataSet.getCoordinate(i, 1) / size;
		}
		double aveDXDY = 0;
		double deviation = 0;
		for (int i = 0; i < size; i++) {
			double x = iDataSet.getCoordinate(i, 0);
			double y = iDataSet.getCoordinate(i, 1);
			aveDXDY += ((x - aveX) * (y - aveY)) / size;
			deviation += Math.pow(x - aveX, 2) / size;
		}
		this.slope = aveDXDY / deviation;
		this.intercept = aveY - this.slope * aveX;
		this.equation = new String("y = ");
		if (this.slope != 0) {
			this.equation += this.slope + " * x";
		}
		if (this.intercept > 0) {
			if (this.slope != 0) {
				this.equation += " + ";
			}
			this.equation += Double.toString(this.intercept);
		} else if (this.intercept < 0) {
			this.equation += " - " + Math.abs(this.intercept);
		}
	}

	void setCartesianPlotAxisBounds(JPanel panel) {
		this.maxXOnG = 0;
		this.maxYOnG = 0;
		this.minXOnG = 0;
		this.minYOnG = 0;
		if (this.iDataSet.size() > 0) {
			int i = 1;
			double bound;
			double rX;
			if (this.iDataSet.getMaxX() != 0 || this.iDataSet.getMinX() != 0) {
				if (Math.abs(this.iDataSet.getMaxX()) > Math.abs(this.iDataSet
						.getMinX())) {
					rX = Math.abs(this.iDataSet.getMaxX());
				} else {
					rX = Math.abs(this.iDataSet.getMinX());
				}
				bound = 0;
				if (rX > 10) {
					while (rX >= Math.pow(10, i)) {
						i++;
					}
					bound = Math.pow(10, i);
				} else {
					while (rX < Math.pow(10, i)) {
						i--;
					}
					bound = Math.pow(10, i);
				}
				for (int j = 2; j <= 10; j++) {
					if (j * bound > rX) {
						this.numericUnitX = (j - 1) * bound / 5;
						break;
					}
				}
			}
			double rY;
			if (this.iDataSet.getMaxY() != 0 || this.iDataSet.getMinY() != 0) {

				if (Math.abs(this.iDataSet.getMaxY()) > Math.abs(this.iDataSet
						.getMinY())) {
					rY = Math.abs(this.iDataSet.getMaxY());
				} else {
					rY = Math.abs(this.iDataSet.getMinY());
				}
				i = 1;
				if (rY >= 10) {
					while (rY >= Math.pow(10, i)) {
						i++;
					}
					bound = Math.pow(10, i);
				} else {
					while (rY <= Math.pow(10, i)) {
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
		for (int i = originX - cursorUnit; i > 20; i -= this.cursorUnit) {
			this.minXOnG -= this.numericUnitX;
		}
		for (int i = originY - cursorUnit; i > 20; i -= this.cursorUnit) {
			this.maxYOnG += this.numericUnitY;
		}
		for (int i = originY + cursorUnit; i < panel.getHeight() - 20; i += this.cursorUnit) {
			this.minYOnG -= this.numericUnitY;
		}
	}

	void drawCartesianPlotAxisLines(Graphics g, JPanel panel) {
		g.setColor(new Color(0, 0, 0));
		double maxX = this.iDataSet.getMaxX();
		double maxY = this.iDataSet.getMaxY();
		double minX = this.iDataSet.getMinX();
		double minY = this.iDataSet.getMinY();
		if (this.iDataSet.size() == 0) {
			this.originX = panel.getWidth() / 2;
			this.originY = panel.getHeight() / 2;
		} else {
			if (minX >= 0) {
				this.originX = 40;
			} else if (maxX < 0) {
				this.originX = panel.getWidth() - 41;
			} else {
				this.originX = (int) (40 + (panel.getWidth() - 80)
						* (-minX / (maxX - minX)));
			}
			if (minY >= 0) {
				this.originY = panel.getHeight() - 41;
			} else if (maxY < 0) {
				this.originY = 40;
			} else {
				this.originY = (int) (40 + (panel.getHeight() - 80)
						* (maxY / (maxY - minY)));
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
		setCartesianPlotAxisBounds(panel);
		for (int i = (int) (this.minXOnG / this.numericUnitX); i <= (int) (this.maxXOnG / this.numericUnitX); i++) {
			g.drawLine(this.originX + this.cursorUnit * i, this.originY - 5,
					this.originX + this.cursorUnit * i, this.originY);
		}
		for (int i = (int) (this.minYOnG / this.numericUnitY); i <= (int) (this.maxYOnG / this.numericUnitY); i++) {
			g.drawLine(this.originX, this.originY - this.cursorUnit * i,
					this.originX + 5, this.originY - this.cursorUnit * i);
		}
	}

	void drawCartesianPlotPoints(Graphics g, JPanel panel) {
		g.setColor(new Color(255, 0, 0));
		for (int i = 0; i < iDataSet.size(); i++) {
			int centerX = (int) (this.originX + (iDataSet.getCoordinate(i, 0) / this.numericUnitX)
					* this.cursorUnit);
			int centerY = (int) (this.originY - (iDataSet.getCoordinate(i, 1) / this.numericUnitY)
					* this.cursorUnit);
			g.drawOval(centerX - 2, centerY - 2, 4, 4);
		}
	}

	void drawCartesianPlotAxisLabel(Graphics g, JPanel panel) {
		g.setColor(new Color(0, 0, 0));
		if (this.isXAxisLabelVisible) {
			String unitX = Double.toString(this.numericUnitX);
			for (int i = (int) (this.minXOnG / this.numericUnitX); i <= (int) (this.maxXOnG / this.numericUnitX); i++) {
				NumberFormat nf = NumberFormat.getNumberInstance();
				nf.setMaximumFractionDigits(unitX.length() - 1
						- unitX.indexOf("."));
				String s = nf.format(i * this.numericUnitX);
				g.drawString(s, this.originX + this.cursorUnit * i,
						this.originY + 10);
			}

		}
		if (this.isYAxisLabelVisible) {
			String unitY = Double.toString(this.numericUnitY);
			FontMetrics fm = g.getFontMetrics();
			for (int i = (int) (this.minYOnG / this.numericUnitY); i <= (int) (this.maxYOnG / this.numericUnitY); i++) {
				NumberFormat nf = NumberFormat.getNumberInstance();
				nf.setMaximumFractionDigits(unitY.length() - 1
						- unitY.indexOf("."));
				String s = nf.format(i * this.numericUnitY);
				g.drawString(s, this.originX - fm.stringWidth(s) - 2,
						this.originY - this.cursorUnit * i);
			}
		}
	}

	void drawCartesianPlotHorizontalLines(Graphics g, JPanel panel) {
		g.setColor(new Color(0, 0, 0, 20));
		if (this.isHorizontalLinesVisible) {
			for (int i = (int) (this.minYOnG / this.numericUnitY); i <= (int) (this.maxYOnG / this.numericUnitY); i++) {
				g.drawLine(0, this.originY - this.cursorUnit * i, panel.getWidth(),
						this.originY - this.cursorUnit * i);
			}
		}
	}

	void drawCartesianPlotTrendLineAndFormula(Graphics g, JPanel panel) {
		if (this.isTrendLineVisible) {
			if (Double.isNaN(this.slope)) {
				Font font = g.getFont();
				font.deriveFont(20);
				g.setFont(font);
				g.setColor(new Color(255, 0, 0));
				g.drawString("The slope is Not a Number", 100, 100);
			} else {
				g.setColor(new Color(0, 0, 255, 20));
				double numericLeftY = this.intercept
						- (this.originX / (this.cursorUnit / this.numericUnitX))
						* this.slope;
				int leftYOnG = (int) (this.originY - (numericLeftY / this.numericUnitY)
						* this.cursorUnit);
				double numericRightY = this.intercept
						+ ((panel.getWidth() - 1 - this.originX) / (this.cursorUnit / this.numericUnitX))
						* this.slope;
				int rightYOnG = (int) (this.originY - (numericRightY / this.numericUnitY)
						* this.cursorUnit);
				g.drawLine(0, leftYOnG, panel.getWidth() - 1, rightYOnG);
				if (this.isTrendLineEquationVisible) {
					g.setColor(new Color(0, 0, 255, 200));
					FontMetrics fm = g.getFontMetrics();
					int rightmostVisibleXOnTrendLine;
					int xOfString;
					int yOfString;
					if (rightYOnG >= 0 && rightYOnG < panel.getHeight()) {
						rightmostVisibleXOnTrendLine = panel.getWidth() - 1;
						xOfString = rightmostVisibleXOnTrendLine
								- fm.stringWidth(this.equation) - 2;
						yOfString = (int) (rightYOnG - (fm
								.stringWidth(this.equation) + 2)
								* ((rightYOnG - leftYOnG) / (double) panel
										.getWidth()));
					} else {
						if (this.slope > 0) {
							rightmostVisibleXOnTrendLine = (int) (panel
									.getWidth() * ((leftYOnG) / (double) (leftYOnG - rightYOnG)));
						} else {
							rightmostVisibleXOnTrendLine = (int) (panel
									.getWidth() * ((panel.getHeight() - 1 - leftYOnG) / (double) (rightYOnG - leftYOnG)));
						}
					}
					if (rightmostVisibleXOnTrendLine < panel.getWidth()
							- fm.stringWidth(this.equation) - 2) {
						xOfString = rightmostVisibleXOnTrendLine;
					} else {
						xOfString = panel.getWidth()
								- fm.stringWidth(this.equation) - 2;
					}
					yOfString = (int) (rightYOnG - (panel.getWidth() - xOfString)
							* ((rightYOnG - leftYOnG) / (double) (panel
									.getWidth())));
					if (this.slope > 0) {
						if (yOfString > panel.getHeight()) {
							g.drawString(this.equation, xOfString,
									panel.getHeight() - 1);
						} else {
							g.drawString(this.equation, xOfString,
									yOfString + 10);
						}
					} else {
						if (yOfString < 0) {
							g.drawString(this.equation, xOfString, 10);
						} else {
							g.drawString(this.equation, xOfString,
									yOfString - 10);
						}
					}
				}
			}
		}
	}

}
