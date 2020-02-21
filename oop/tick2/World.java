package uk.ac.cam.jsc89.oop.tick2;

public abstract class World {

	private int mGeneration;
	private Pattern mPattern;
	
	public World(String format) {
    	mPattern = new Pattern(format);
    	mGeneration = 0;
   	}

	public int getWidth() {
       return mPattern.getWidth();
    }
    
    public int getHeight() {
       return mPattern.getHeight();
    }
    
    public int getGenerationCount() {
    	return mGeneration;
    }
    
    protected Pattern getPattern() {
    	return mPattern;
    }

	protected void incrementGenerationCount() {
		mGeneration++;
	}
	
	
    public abstract boolean getCell(int c, int r);
	public abstract void setCell(int c, int r, boolean value);
	
	public void nextGeneration() {
        nextGenerationImpl();
        mGeneration++;
	}
    
protected abstract void nextGenerationImpl();
	
	protected int countNeighbours(int col, int row) {
		int Neighbours = 0;
		for (int ydev = -1; ydev < 2; ydev++) { 
      		for (int xdev = -1; xdev < 2; xdev++) {
         		if (getCell(col+xdev,row+ydev)==true & (xdev!=0 | ydev!=0)) {Neighbours = Neighbours + 1;
         		} 
      		} 
   		} 
   		return Neighbours;
	}
	
	protected boolean computeCell(int col, int row) {

   		boolean liveCell = getCell(col, row);
   		int neighbours = countNeighbours(col, row);
   		boolean nextCell = false;
   		if (neighbours < 2) {
      		nextCell = false;
   		}
   		if ((neighbours == 2 | neighbours==3) & liveCell==true) {
      		nextCell = true;
   		}
   		if (neighbours>3 & liveCell==true) {
      		nextCell = false;
   		}
   		if (neighbours==3) {
      		nextCell = true;
   		}
   		return nextCell;
	}
	
	
	
}