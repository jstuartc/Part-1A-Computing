package uk.ac.cam.jsc89.oop.tick2;

public class PackedWorld extends World {

    private long mWorld;
    
    public PackedWorld(String serial) throws java.io.IOException {
    
    	super(serial);
    	if (getPattern().getHeight()*getPattern().getWidth() >64) {
    		throw new java.io.IOException("Board too big");
    	} else {
			mWorld=0L;
    		getPattern().initialise(this);
    	}
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
