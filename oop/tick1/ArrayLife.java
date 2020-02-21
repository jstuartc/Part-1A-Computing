package uk.ac.cam.jsc89.oop.tick1;

public class ArrayLife {	

	private int mWidth;
    private int mHeight;
    private boolean[][] mWorld;
    private Pattern mPattern;
    
    public ArrayLife(String format) {
    	mPattern = new Pattern(format);
    	mHeight = mPattern.getHeight();
    	mWidth = mPattern.getWidth();
    	mWorld=new boolean[mHeight][mWidth];
    	for(int i = 0; i < mHeight; i++) {
    		for(int j = 0; j < mWidth; j++) {
    			mWorld[i][j]=false;
   			}
    	mWorld = mPattern.initialise(mWorld);
    
   		}
   	}
	
	public static void main(String[] args) throws Exception {
  		ArrayLife al = new ArrayLife(args[0]);
  		al.play();
	}
    
	public boolean getCell(int col, int row) {
   		if (row < 0 || row >= mHeight) return false;
   		if (col < 0 || col >= mWidth) return false;

   		return mWorld[row][col];
	}

    public void setCell(int col, int row, boolean value) {
    	if ((row < 0 || row >= mHeight)|(col < 0 || col >= mWidth)) {
    		
    	} else {
			mWorld[row][col] = value;
		}
    }
    
    public void print() { 
   		System.out.println("-"); 
   		for (int row = 0; row < mHeight; row++) { 
      		for (int col = 0; col < mWidth; col++) {
         		System.out.print(getCell(col, row) ? "#" : "_"); 
      		}
      	System.out.println(); 
   		} 
	}
	
	private int countNeighbours(int col, int row) {
		int Neighbours = 0;
		for (int ydev = -1; ydev < 2; ydev++) { 
      		for (int xdev = -1; xdev < 2; xdev++) {
         		if (getCell(col+xdev,row+ydev)==true & (xdev!=0 | ydev!=0)) {Neighbours = Neighbours + 1;
         		} 
      		} 
   		} 
   		return Neighbours;
	}
	
	private boolean computeCell(int col, int row) {

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
		
	 public void nextGeneration() {
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
		
	
	public void play() throws java.io.IOException {
   		int userResponse = 0;
   		while (userResponse != 'q') {
      		print();
      		userResponse = System.in.read();
      		nextGeneration();
   		}
	}
}