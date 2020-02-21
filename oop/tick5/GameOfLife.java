package uk.ac.cam.jsc89.oop.tick5;

import java.io.*;
import java.net.*;
import java.util.*;

public class GameOfLife {

    private World mWorld;
   	private PatternStore mStore;
	private ArrayList<World> mCachedWorlds;
   
    public GameOfLife(PatternStore s) {
        mStore = s;
        mCachedWorlds = new ArrayList();
    }
    
    private World copyWorld(boolean useCloning) throws CloneNotSupportedException{
    	World copyW = null;
   		if (useCloning == false) {
   			if (mWorld instanceof ArrayWorld) {
   				copyW = new ArrayWorld((ArrayWorld)mWorld);
   			}
   			else if (mWorld instanceof PackedWorld) {
   				copyW = new PackedWorld((PackedWorld)mWorld);
   			}
   		} else {
   			if (mWorld instanceof ArrayWorld) {
   				copyW = (ArrayWorld)mWorld.clone();
   			}
   			else if (mWorld instanceof PackedWorld) {
   				copyW = (PackedWorld)mWorld.clone();
   			}
   		}
   	return copyW;
	}
   		
   		
   		
    
    public void print() { 
   		System.out.println("- " + mWorld.getGenerationCount()); 
   		for (int row = 0; row < mWorld.getHeight(); row++) { 
      		for (int col = 0; col < mWorld.getWidth(); col++) {
         		System.out.print(mWorld.getCell(col, row) ? "#" : "_"); 
      		}
      	System.out.println(); 
   		} 
   	}

    
    public void play() throws Exception {
        
        String response="";
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                
        System.out.println("Please select a pattern to play (l to list:");
        while (!response.equals("q")) {
                response = in.readLine();
                System.out.println(response);
                if (response.equals("f")) {
                        if (mWorld==null) System.out.println("Please select a pattern to play (l to list):");
                        else {
            				if (mWorld.getGenerationCount()+2<=mCachedWorlds.size()) {
                        		mWorld = mCachedWorlds.get(mWorld.getGenerationCount()+1);
                        	} else {
                        		mCachedWorlds.add(mWorld.getGenerationCount()+1,copyWorld(true));
                        		mCachedWorlds.get(mWorld.getGenerationCount()+1).nextGeneration();
                        		mWorld = mCachedWorlds.get(mWorld.getGenerationCount()+1);
                        	}
                        	
                            print();
                        }
                }
                else if (response.equals("b")) {
                    	if (mWorld==null) System.out.println("Please select a pattern to play (l to list):");
                    	else if (mWorld.getGenerationCount()==0) {
                    	mWorld = mCachedWorlds.get(0);
                    	print();
                    	}
            			else {
                        		mWorld = mCachedWorlds.get(mWorld.getGenerationCount()-1);
                        		print();
                        	} 
                }
                else if (response.equals("l")) {
                        List<Pattern> names = mStore.getPatternsNameSorted();
                        int i=0;
                        for (Pattern p : names) {
                                System.out.println(i+" "+p.getName()+"  ("+p.getAuthor()+")");
                                i++;
                        }
                }
                else if (response.startsWith("p")) {
                   List<Pattern> names = mStore.getPatternsNameSorted();
                   String[] nresponses = response.split(" ");
                   int Xnum = Integer.parseInt(nresponses[1]);
                   if (((names.get(Xnum)).getWidth())*((names.get(Xnum)).getHeight()) <=64) { // checking board size
                   		mCachedWorlds.add(0,new PackedWorld(names.get(Xnum)));
                   		mWorld = (PackedWorld)mCachedWorlds.get(0);
                   } else {
                   		mCachedWorlds.add(0,new ArrayWorld(names.get(Xnum)));
                   		mWorld = mCachedWorlds.get(0);
                   }
                   print();
                }
                
        }
    }

    public static void main(String args[]) throws Exception {
        
        if (args.length!=1) {
                System.out.println("Usage: java GameOfLife <path/url to store>");
                return;
        }
        
        try {
                PatternStore ps = new PatternStore(args[0]);
                GameOfLife gol = new GameOfLife(ps);    
                gol.play();
        }
        catch (java.io.IOException ioe) {
                System.out.println("Failed to load pattern store");
        }
        
        
    }

}