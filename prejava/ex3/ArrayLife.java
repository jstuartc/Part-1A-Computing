package uk.ac.cam.jsc89.prejava.ex3;

public class ArrayLife {	
	public static boolean getFromPackedLong(long packed, int position) {
        return ((packed >>> position) & 1) == 1;
	}

	public static void main(String[] args) throws java.io.IOException {
   		int size = Integer.parseInt(args[0]);
   		long initial = Long.decode(args[1]);
   		boolean[][] world = new boolean[size][size];
   		//place the long representation of the game board in the centre of "world"
   		for(int i = 0; i < 8; i++) {
    		for(int j = 0; j < 8; j++) {
        		world[i+size/2-4][j+size/2-4] = getFromPackedLong(initial,i*8+j);
    		}
   		}
   		play(world);
	}
    
    public static boolean getCell(boolean[][] world, int col, int row) {
    	if (row < 0 || row > world.length - 1) return false;
   		if (col < 0 || col > world[row].length - 1) return false;

   		return world[row][col];
    }

    public static void setCell(boolean[][] world, int col, int row, boolean value) {
    	if ((row < 0 || row > world.length - 1)|(col < 0 || col > world[row].length - 1)) {
    		
    	} else {
			world[row][col] = value;
		}
    }
    
    public static void print(boolean[][] world) { 
   		System.out.println("-"); 
   		for (int row = 0; row < world.length; row++) { 
      		for (int col = 0; col < world[row].length; col++) {
         		System.out.print(getCell(world, col, row) ? "#" : "_"); 
      		}
      	System.out.println(); 
   		} 
	}
	
	public static int countNeighbours(boolean[][] world, int col, int row) {
		int Neighbours = 0;
		for (int ydev = -1; ydev < 2; ydev++) { 
      		for (int xdev = -1; xdev < 2; xdev++) {
         		if (getCell(world,col+xdev,row+ydev)==true & (xdev!=0 | ydev!=0)) {Neighbours = Neighbours + 1;
         		} 
      		} 
   		} 
   		return Neighbours;
	}
	
	public static boolean computeCell(boolean[][] world, int col, int row) {

   		boolean liveCell = getCell(world, col, row);
   		int neighbours = countNeighbours(world, col, row);
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
	
	public static boolean[][] nextGeneration(boolean[][] world) {
		boolean[][] newWorld = new boolean[world.length][world[0].length];
		for (int row = 0; row<world.length; row++) {
			for (int col = 0; col<world[row].length; col++) {
				newWorld[row][col] = computeCell(world,col,row);
			}
		}
		return newWorld;
		
	}
	
	public static void play(boolean[][] world) throws java.io.IOException {
   		int userResponse = 0;
   		while (userResponse != 'q') {
      		print(world);
      		userResponse = System.in.read();
      		world = nextGeneration(world);
   		}
	}
}