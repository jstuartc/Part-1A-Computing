package uk.ac.cam.jsc89.oop.SupervisionWork;

import java.io.*;
import java.net.*;
import java.util.*;

public class FilePrinter {
	private int[][] mArray;
	
	public FilePrinter(String filename) throws Exception {
		Reader r = new FileReader(filename);
		BufferedReader b = new BufferedReader(r);
		b.mark(1000);
		String line = b.readLine();
		int length = -1;
		while ( line != null) {
			length++;
			line=b.readLine();
		}
		mArray = new int[length][1];
		b.reset();
		line=b.readLine();
		for(int i = 0;i<=length;i++) {
			String[] nline = line.split(",");
			mArray[i][0] = Integer.parseInt(nline[0]);
			mArray[i][1] = Integer.parseInt(nline[1]);
			line=b.readLine();
		}	
	}
	
	public void print() {
		java.util.Arrays.sort(mArray, new Comparator<int[]>() {
			public int compare(int[] a1,int[] a2) {
				if (a1[0] ==a2[0]) {
					if (a1[1]>a2[1]) {return 1;}
					else { return (-1);}
				} else {
					if (a1[1]>a2[1]) {return 1;}
					else { return (-1);}
				}
			}
		});
		for (int i = 0;i<mArray.length;i++) {
			System.out.println(mArray[i][0] + "," + mArray[i][1]);
		}
	}
	
}