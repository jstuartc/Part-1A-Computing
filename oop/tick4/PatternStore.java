package uk.ac.cam.jsc89.oop.tick4;

import java.io.*;
import java.net.*;
import java.util.*;

public class PatternStore {

	private Pattern p;
	private List<Pattern> mPatterns = new LinkedList<>();
	private Map<String,List<Pattern>> mMapAuths = new HashMap<>();
	private Map<String,Pattern> mMapName = new HashMap<>();

   	public PatternStore(String source) throws IOException {
       	if (source.startsWith("http://")) {
          loadFromURL(source);
       	}
       	else {
          	loadFromDisk(source);
    	}
   }
    
   public PatternStore(Reader source) throws IOException {
      load(source);
   }
    
   private void load(Reader r) throws IOException {
      BufferedReader b = new BufferedReader(r);
		String line = b.readLine();
		while ( line != null) {
   			try {
   				p = new Pattern(line);
   				mPatterns.add(p);
   				
   				List<Pattern> mL = mMapAuths.get(p.getAuthor());
   				if (mL==null) {
   					mL = new LinkedList<>();
   					mMapAuths.put(p.getAuthor(),mL);
   				}
   				mL.add(p);
   				
   				mMapName.put(p.getName(),p);
   			} catch (Exception e) {
   					System.out.println(e.getMessage());
   			}
   			line=b.readLine();
   		}
   			
   		
	}
    
    
   private void loadFromURL(String url) throws IOException {
    URL destination = new URL(url);
	URLConnection conn = destination.openConnection();
	Reader r = new java.io.InputStreamReader(conn.getInputStream());
	load(r);
   }

   private void loadFromDisk(String filename) throws IOException {
    Reader r = new FileReader(filename);
    load(r);
   }

   public static void main(String args[]) throws IOException {
      PatternStore p = new PatternStore(args[0]);
   }
   
	
	public List<Pattern> getPatternsNameSorted() {
		Collections.sort(mPatterns);
		List<Pattern> copy = new LinkedList<Pattern>();
 		for (int i = 0;i<mPatterns.size();i++) {
			Pattern p = new Pattern(mPatterns.get(i));
			copy.add(p);
		}
		return copy;
	}

	public List<Pattern> getPatternsAuthorSorted() {	
   		Collections.sort(mPatterns, new Comparator<Pattern>() { //compares authors first, then if equal compares names
   			public int compare(Pattern p1, Pattern p2) {
   				if ((p1.getAuthor()).compareTo(p2.getAuthor()) == 0) {
   					return ((p1.getName()).compareTo(p2.getName()));
   				} else {
      				return (p1.getAuthor()).compareTo(p2.getAuthor());
      			}
   			}
 		});
 		List<Pattern> copy = new LinkedList<Pattern>();
 		for (int i = 0;i<mPatterns.size();i++) {
			Pattern p = new Pattern(mPatterns.get(i));
			copy.add(p);
		}
 		
 		return copy;
	}

	public List<Pattern> getPatternsByAuthor(String author) throws PatternNotFound {
		if (mMapAuths.get(author) ==null) {
			throw new PatternNotFound();
		} else {
		List<Pattern> copy = new LinkedList<Pattern>();
		List<Pattern> ByAuthor = new LinkedList<Pattern>(mMapAuths.get(author));
		for (int i = 0;i<ByAuthor.size();i++) {
			System.out.println(ByAuthor.get(i).getName());
			Pattern p = new Pattern(ByAuthor.get(i));
			if (copy.contains(p)==false){copy.add(p);}
		}
		Collections.sort(copy);
		return copy;
		}	
	}
	
	public Pattern getPatternByName(String name) throws PatternNotFound {
		if (mMapName.get(name) ==null) {
			throw new PatternNotFound();
		} else {
			Pattern copy = new Pattern(mMapName.get(name));
			return (copy);
		}
	}

	public List<String> getPatternAuthors() {
		getPatternsAuthorSorted();
		List<String> AuthorList = new LinkedList<String>();
		for (int i = 0;i<mPatterns.size();i++) {
			if (AuthorList.contains(mPatterns.get(i).getAuthor())==false){
				AuthorList.add((mPatterns.get(i)).getAuthor());
			}
		}
		System.out.println(AuthorList);
		return AuthorList;
	}

	public List<String> getPatternNames() {
	getPatternsNameSorted();
		List<String> NameList = new LinkedList<String>();
		for (int i = 0;i<mPatterns.size();i++) {
			if (NameList.contains(mPatterns.get(i).getName())==false){
				NameList.add((mPatterns.get(i)).getName());
			}
		}
		return NameList;
	}
	
}