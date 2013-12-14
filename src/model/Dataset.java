package model;

import java.util.ArrayList;
import java.util.Iterator;

import dataset.IDataSet;

public class Dataset implements IDataSet{
	ArrayList<Point> points;
	public static final int MAXNumOfPoints = 2048;
	double maxX;
	double maxY;
	double minX;
	double minY;

	public Dataset(){
		points = new ArrayList<Point>();
	}
	
	void calculateBounds(){
		double maxx = -Double.MAX_VALUE;
		double maxy = -Double.MIN_VALUE;
		double minx = Double.MAX_VALUE;
		double miny = Double.MAX_VALUE;
		Iterator<Point> iter = points.iterator();
		while(iter.hasNext()){
			Point p = iter.next();
			double x = p.getX();
			double y = p.getY();
			if(x > maxx){
				maxx = x;
			}
			if(x < minx){
				minx = x;
			}
			if(y > maxy){
				maxy = y;
			}
			if(y < miny){
				miny = y;
			}
		}
		maxX = maxx;
		maxY = maxy;
		minX = minx;
		minY = miny;
	}
	
	
	public boolean addPoint(Point p){
		if(points.size() > MAXNumOfPoints)
			return false;
		boolean result = this.points.add(p);
		if(result){
			calculateBounds();
		}
		return result;
	}
	
	public boolean updatePoint(int index, Point p){
		if(index < 0 || index >= points.size())
			return false;
		this.points.set(index, p);
		calculateBounds();
		return true;
	}
	
	public boolean removePoint(int index){
		if(index < 0 || index >= points.size())
			return false;
		points.remove(index);
		calculateBounds();
		return true;
	}
	
	public ArrayList<Point> getPoints() {
		return points;
	}

	public double getMaxX() {
		return maxX;
	}

	public double getMaxY() {
		return maxY;
	}

	public double getMinX() {
		return minX;
	}

	public double getMinY() {
		return minY;
	}
	
	// used for test
	Point getNth(int i){
		return this.points.get(i);
	}
	
	public int size(){
		return this.points.size();
	}

	public double getCoordinate(int index, int dimension) {
		Point p = this.getNth(index);
		if(dimension == 0){
			return p.getX();
		}else{
			return p.getY();
		}
	}
	
}
