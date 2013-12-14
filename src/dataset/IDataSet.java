package dataset;

public interface IDataSet {
	 int size();
	 double getMinX();
	 double getMaxX();
	 double getMinY();
	 double getMaxY();
	 
	 /**
	  * Retrieves the desired coordinate for the value of the 
	  * data at the given index location.
	  * 
	  * index must be between 0 and size()
	  * dimension must be either 0 (for X) or 1 (for Y)
	  * 
	  * If index is invalid, throws ArrayIndexOutOfBoundsException
	  * If dimension is invalid (0 or 1 only) throws IllegalArgumentException
	  */
	 double getCoordinate(int index, int dimension);	 
}