package uk.ac.cam.jsc89.oop.tick4;

public class ArrayWorld extends World implements Cloneable {

    private boolean[][] mWorld;
    private boolean[] mDeadRow;
    
    public ArrayWorld(Pattern serial) throws Exception {
    	super(serial);
		mWorld=new boolean[getHeight()][getWidth()];
		mDeadRow = new boolean[getWidth()];
    	getPattern().initialise(this);
    	setDeadRows();
    	
    }
    
    public ArrayWorld(String serial) throws Exception {
    	super(serial);
		mWorld=new boolean[getHeight()][getWidth()];
		mDeadRow = new boolean[getWidth()];
    	getPattern().initialise(this);
    	setDeadRows();
    }
    
    public ArrayWorld(ArrayWorld tocopy) {
    	super(tocopy);
    	mWorld = new boolean[tocopy.getHeight()][tocopy.getWidth()];
    	mDeadRow = tocopy.getDeadRow();
		
		for(int i = 0; i < getHeight(); i++) {
    		for(int j = 0; j < getWidth(); j++) {
    			setCell(i,j,tocopy.getCell(i,j));
   			}
    	}
    	setDeadRows();
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
    	ArrayWorld clone = (ArrayWorld)super.clone();
    	clone.mWorld = new boolean[getHeight()][getWidth()];
    	for(int i = 0; i < getHeight(); i++) {
    		for(int j = 0; j < getWidth(); j++) {
    			clone.setCell(i,j,getCell(i,j));
   			}
    	}
    	clone.setDeadRows();
    	return clone;
    }
    
    public boolean[] getDeadRow() {
    	return mDeadRow;
    }
    
    public boolean getCell(int col, int row) {
   		if (row < 0 || row >= getHeight()) return false;
   		if (col < 0 || col >= getWidth()) return false;

   		return mWorld[row][col];
	}

    public void setCell(int col, int row, boolean value) {
    	if ((row < 0 || row >= getHeight())|(col < 0 || col >= getWidth())) {
    		
    	} else {
			mWorld[row][col] = value;
		}
    }
    
    public void nextGenerationImpl() {
        boolean[][] nextGeneration = new boolean[mWorld.length][];
        for (int y = 0; y < mWorld.length; ++y) {
            nextGeneration[y] = new boolean[mWorld[y].length];
            for (int x = 0; x < mWorld[y].length; ++x) {
                boolean nextCell = computeCell(x, y);
                nextGeneration[y][x]=nextCell;
            }
        }
        mWorld = nextGeneration;
    }
    
    public void setDeadRows() {
    	for(int i = 0; i < getHeight(); i++) {
    		boolean setDead = true;
    		for(int j = 0; j < getWidth(); j++) {
    			if (getCell(i,j)==true) {setDead=false;}
   			} 
    		if (setDead==true) {
    			mWorld[i] = mDeadRow;
    		}
    	}
    }

}