package uk.ac.cam.jsc89.prejava.ex2;

public class TinyLife {
	public static void main(String[] args) throws java.io.IOException {
   		play(Long.decode(args[0]));
	}
    
    
    public static boolean getCell(long world, int col, int row) {
    	if (0 <= col & col <=7 & 0<= row & row <=7) {
    		int cell = row*8 + col;
    		return PackedLong.get(world, cell);
    	} else {
    		return false;
    	}
    }
    
    public static long setCell(long world, int col, int row, boolean newval) {
    	if (0 <= col & col <=7 & 0<= row & row <=7) {
    		int cell = row*8 + col;
    		return PackedLong.set(world,cell,newval);
    	} else {
    		return world;
    	}
    }
    
    public static void print(long world) { 
   		System.out.println("-"); 
   		for (int row = 0; row < 8; row++) { 
      		for (int col = 0; col < 8; col++) {
         		System.out.print(getCell(world, col, row) ? "#" : "_"); 
      		}
      	System.out.println(); 
   		} 
	}
	
	public static int countNeighbours(long world, int col, int row) {
		int Neighbours = 0;
		for (int ydev = -1; ydev < 2; ydev++) { 
      		for (int xdev = -1; xdev < 2; xdev++) {
         		if (getCell(world,col+xdev,row+ydev)==true & (xdev!=0 | ydev!=0)) {Neighbours = Neighbours + 1;
         		} 
      		} 
   		} 
   		return Neighbours;
	}
	
	public static boolean computeCell(long world,int col,int row) {

   		// liveCell is true if the cell at position (col,row) in world is live
   		boolean liveCell = getCell(world, col, row);
   		// neighbours is the number of live neighbours to cell (col,row)
   		int neighbours = countNeighbours(world, col, row);
   		// we will return this value at the end of the method to indicate whether 
   		// cell (col,row) should be live in the next generation
   		boolean nextCell = false;
   		//A live cell with less than two neighbours dies (underpopulation)
   		if (neighbours < 2) {
      		nextCell = false;
   		}
   		//A live cell with two or three neighbours lives (a balanced population)
   		if ((neighbours == 2 | neighbours==3) & liveCell==true) {
      		nextCell = true;
   		}
   		//A live cell with with more than three neighbours dies (overcrowding)
   		if (neighbours>3 & liveCell==true) {
      		nextCell = false;
   		}
   		//A dead cell with exactly three live neighbours comes alive
   		if (neighbours==3) {
      		nextCell = true;
   		}
   		return nextCell;
	}
	
	public static long nextGeneration(long oldWorld) {
		long newWorld=0L;
		int col;
		int row;
		for (int cell = 63; cell>=0; cell--){
			row = cell/8;
			col = cell%8;
			newWorld = newWorld<<1;
			if ( computeCell(oldWorld,col,row) ==true) {
				newWorld = newWorld+1;
			}
		}
	return newWorld;
	}
	
	public static void play(long world) throws java.io.IOException {
   		int userResponse = 0;
   		while (userResponse != 'q') {
      		print(world);
      		userResponse = System.in.read();
      		world = nextGeneration(world);
   	}
}
	
}