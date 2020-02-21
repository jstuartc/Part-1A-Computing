package uk.ac.cam.jsc89.oop.SupervisionWork;

import java.io.*;
import java.net.*;
import java.util.*;

public class StudentScores {
	
	private List<String> mStudents = new LinkedList<>();
	private Map<String,Integer> mMapScore = new HashMap<>();
	
	private void addStudent(String Name,int Score) {
		mStudents.add(Name);
		mMapScore.put(Name,Score);
	}
	
	private List<String> ListStudents() {
		Collections.sort(mStudents);
		
		List<String> output = new LinkedList<>();
		for (int i= 0;i<mStudents.size();i++) {
			output.add(mStudents.get(i));
		}
		return output;
	}
	
	private List<String> TopScore(int score) {
		List<String> output = new LinkedList<>();
		for (int i= 0;i<mStudents.size();i++) {
			if(mMapScore.get(mStudents.get(i))>score) {
				output.add(mStudents.get(i));
			}
		}
		return output;
	}
	
	private int Median() {
		List<Integer> TScores = new LinkedList<>();
		for (int i= 0;i<mStudents.size();i++) {
			TScores.add(mMapScore.get(mStudents.get(i)));
		}
		Collections.sort(TScores);
		int size = TScores.size();
		if (size%2==0) {
			return ((TScores.get(size/2)+TScores.get((size/2)+1))/2);
		} else {
			return (TScores.get((size/2)+1));
		}
	}
	
}