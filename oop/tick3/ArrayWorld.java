package uk.ac.cam.jsc89.oop.tick3;

public class ArrayWorld extends World {

    private boolean[][] mWorld;
    
    public ArrayWorld(Pattern serial) throws Exception {
    	super(serial);
		mWorld=new boolean[getHeight()][getWidth()];
    	getPattern().initialise(this);
    
    }
    
    public ArrayWorld(String serial) throws Exception {
    	super(serial);
		mWorld=new boolean[getHeight()][getWidth()];
    	getPattern().initialise(this);
    
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

}