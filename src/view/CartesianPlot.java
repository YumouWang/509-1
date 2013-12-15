package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
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
	boolean isTrendLineVisible;
	boolean isTrendLineEquationVisible;

	public CartesianPlot() {
	}

	public void setDataSet(IDataSet ds) {
		this.iDataSet = ds;
	}

	public void draw(Graphics g, JPanel panel) {
		reset(panel);
		drawAxisLines(g, panel);
		drawPoints(g, panel);
		drawAxisLabel(g, panel);
		drawHorizontalLines(g, panel);
		if (this.iDataSet.size() >= 2) {
			calculateTrendLineEquation();
			drawTrendLineAndFormula(g, panel);
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

	void calculateTrendLineEquation() {
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

	void setAxisBounds(JPanel panel) {
		this.maxXOnG = 0;
		this.maxYOnG = 0;
		this.minXOnG = 0;
		this.minYOnG = 0;
		if (this.iDataSet.size() > 0) {
			double rX;
			if (this.iDataSet.getMaxX() != 0 || this.iDataSet.getMinX() != 0) {
				if (this.iDataSet.getMinX() > 0) {
					rX = Math.abs(this.iDataSet.getMaxX());
				} else {
					if (this.iDataSet.getMaxX() < 0) {
						rX = Math.abs(this.iDataSet.getMinX());
					} else {
						rX = Math.abs(this.iDataSet.getMaxX()
								- this.iDataSet.getMinX());
					}
				}
				this.numericUnitX = rX / 10;
				int dLength = Double.toString(rX).length() - 1
						- Double.toString(rX).indexOf(".");
				this.numericUnitX = this.numericUnitX * Math.pow(10, dLength);
				this.numericUnitX += 0.5;
				this.numericUnitX = (long) this.numericUnitX;
				this.numericUnitX = this.numericUnitX / Math.pow(10d, dLength);
			}
			double rY;
			if (this.iDataSet.getMaxY() != 0 || this.iDataSet.getMinY() != 0) {
				if (this.iDataSet.getMinY() > 0) {
					rY = Math.abs(this.iDataSet.getMaxY());
				} else {
					if (this.iDataSet.getMaxY() < 0) {
						rY = Math.abs(this.iDataSet.getMinY());
					} else {
						rY = Math.abs(this.iDataSet.getMaxY()
								- this.iDataSet.getMinY());
					}
				}
				this.numericUnitY = rY / 10;
				int dLength = Double.toString(rY).length() - 1
						- Double.toString(rY).indexOf(".");
				this.numericUnitY = this.numericUnitY * Math.pow(10, dLength);
				this.numericUnitY += 0.5;
				this.numericUnitY = (long) this.numericUnitY;
				this.numericUnitY = this.numericUnitY / Math.pow(10d, dLength);
			}
		} else {
			this.numericUnitX = 1;
			this.numericUnitY = 1;
		}
		for (int i = this.originX + this.xCursorUnit; i < panel.getWidth() - 5; i += this.xCursorUnit) {
			this.maxXOnG += this.numericUnitX;
		}
		for (int i = this.originX - this.xCursorUnit; i >= 0; i -= this.xCursorUnit) {
			this.minXOnG -= this.numericUnitX;
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
		double maxX = this.iDataSet.getMaxX();
		double maxY = this.iDataSet.getMaxY();
		double minX = this.iDataSet.getMinX();
		double minY = this.iDataSet.getMinY();
		int hLimit = (int) Math.round(panel.getWidth() * 0.07);
		int vLimit = (int) Math.round(panel.getHeight() * 0.07);
		if (this.iDataSet.size() == 0) {
			this.originX = (int) Math.round(panel.getWidth() / 2.00);
			this.originY = (int) Math.round(panel.getHeight() / 2.00);
		} else {
			if (minX >= 0) {
				this.originX = hLimit;
			} else {
				if (maxX < 0) {
					this.originX = panel.getWidth() - hLimit - 1;
				} else {
					this.originX = (int) Math.round(hLimit
							+ (panel.getWidth() - 2 * hLimit)
							* (-minX / (maxX - minX)));
				}
			}
			if (minY >= 0) {
				this.originY = panel.getHeight() - vLimit - 1;
			} else {
				if (maxY < 0) {
					this.originY = vLimit;
				} else {
					this.originY = (int) Math.round(vLimit
							+ (panel.getHeight() - 2 * vLimit)
							* (maxY / (maxY - minY)));
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
		setAxisBounds(panel);
		for (int i = (int) Math.round(this.minXOnG / this.numericUnitX); i <= Math
				.round(this.maxXOnG / this.numericUnitX); i++) {
			g.drawLine(this.originX + this.xCursorUnit * i, this.originY - 4,
					this.originX + this.xCursorUnit * i, this.originY);
		}
		for (int i = (int) Math.round(this.minYOnG / this.numericUnitY); i <= Math
				.round(this.maxYOnG / this.numericUnitY); i++) {
			g.drawLine(this.originX, this.originY - this.yCursorUnit * i,
					this.originX + 4, this.originY - this.yCursorUnit * i);
		}
	}

	void drawPoints(Graphics g, JPanel panel) {
		g.setColor(new Color(255, 0, 0));
		for (int i = 0; i < this.iDataSet.size(); i++) {
			int centerX = (int) Math.round(this.originX
					+ (this.iDataSet.getCoordinate(i, 0) / this.numericUnitX)
					* this.xCursorUnit);
			int centerY = (int) Math.round(this.originY
					- (this.iDataSet.getCoordinate(i, 1) / this.numericUnitY)
					* this.yCursorUnit);
			g.drawOval(centerX - 2, centerY - 2, 4, 4);
		}
	}

	void drawAxisLabel(Graphics g, JPanel panel) {
		g.setColor(new Color(0, 0, 0));
		if (this.isXAxisLabelVisible) {
			int dLength = Double.toString(this.numericUnitX).length() - 1
					- Double.toString(this.numericUnitX).indexOf(".");
			for (int i = (int) Math.round(this.minXOnG / this.numericUnitX); i <= Math
					.round(this.maxXOnG / this.numericUnitX); i++) {
				double numberToWrite = i * this.numericUnitX;
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

	void drawTrendLineAndFormula(Graphics g, JPanel panel) {
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
						- (this.originX / (this.xCursorUnit / this.numericUnitX))
						* this.slope;
				int leftYOnG = (int) Math
						.round(this.originY
								- (numericLeftY / this.numericUnitY)
								* this.yCursorUnit);
				double numericRightY = this.intercept
						+ ((panel.getWidth() - 1 - this.originX) / (this.xCursorUnit / this.numericUnitX))
						* this.slope;
				int rightYOnG = (int) Math.round(this.originY
						- (numericRightY / this.numericUnitY)
						* this.yCursorUnit);
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
						yOfString = (int) Math.round(rightYOnG
								- (fm.stringWidth(this.equation) + 2)
								* ((rightYOnG - leftYOnG) / (double) panel
										.getWidth()));
					} else {
						if (this.slope > 0) {
							rightmostVisibleXOnTrendLine = (int) Math
									.round(panel.getWidth()
											* ((leftYOnG) / (double) (leftYOnG - rightYOnG)));
						} else {
							rightmostVisibleXOnTrendLine = (int) Math
									.round(panel.getWidth()
											* ((panel.getHeight() - 1 - leftYOnG) / (double) (rightYOnG - leftYOnG)));
						}
					}
					if (rightmostVisibleXOnTrendLine < panel.getWidth()
							- fm.stringWidth(this.equation) - 2) {
						xOfString = rightmostVisibleXOnTrendLine;
					} else {
						xOfString = panel.getWidth()
								- fm.stringWidth(this.equation) - 2;
					}
					yOfString = (int) Math.round(rightYOnG
							- (panel.getWidth() - xOfString)
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
