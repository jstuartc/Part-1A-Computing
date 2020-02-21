package uk.ac.cam.jsc89.oop.tick5;

public class PackedWorld extends World implements Cloneable {

    private long mWorld;
    
    public PackedWorld(Pattern serial) throws Exception {
    	super(serial);
    	if (getPattern().getHeight()*getPattern().getWidth() >64) {
    		throw new java.io.IOException("Board too big");
    	} else {
			mWorld=0L;
    		getPattern().initialise(this);
    	}
    }
    
    public PackedWorld(String serial) throws Exception {
    
    	super(serial);
    	if (getPattern().getHeight()*getPattern().getWidth() >64) {
    		throw new java.io.IOException("Board too big");
    	} else {
			mWorld=0L;
    		getPattern().initialise(this);
    	}
    }
    
    public PackedWorld(PackedWorld tocopy) {
    	super(tocopy);
    	mWorld = 0L;
		
		for(int i = 0; i < getHeight(); i++) {
    		for(int j = 0; j < getWidth(); j++) {
    			setCell(i,j,tocopy.getCell(i,j));
   			}
    	}
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
    	PackedWorld clone = (PackedWorld)super.clone();
    	clone.mWorld = 0L;
    	for(int i = 0; i < getHeight(); i++) {
    		for(int j = 0; j < getWidth(); j++) {
    			clone.setCell(i,j,getCell(i,j));
   			}
    	}
    	return clone;
    }
    
    
    public boolean getCell(int col, int row) {
   		if (row < 0 || row >= getHeight()) return false;
   		if (col < 0 || col >= getWidth()) return false;
   		int position = (row*getWidth()) + col;
		long check = 1 & (mWorld>>position);
		return check==1;
	}

    public void setCell(int col, int row, boolean value) {
    	if ((row < 0 || row >= getHeight())|(col < 0 || col >= getWidth())) {
    		
    	} else {
    		int position = (row*getWidth()) + col;
			if (value) {
        		mWorld = (mWorld | 1L<<position);
      		} else {
        		mWorld = ~(~mWorld | 1L<<position);
      		}
		}
    }
    
    public void nextGenerationImpl() {
        long nextGeneration = 0L;
        for (int cell = (getWidth()*getHeight()-1); cell>=0; cell--){
			int row = cell/getWidth();
			int col = cell%getWidth();
			if (computeCell(col,row)) {
				nextGeneration =(nextGeneration | 1L<<cell);
			}
		}
        mWorld = nextGeneration;
    }

}
