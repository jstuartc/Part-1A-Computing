package uk.ac.cam.jsc89.oop.SupervisionWork;

import java.io.*;
import java.net.*;
import java.util.*;

public class Supo4_a {
	
}

class Forum {

	protected Set<Topic> topics = new TreeSet<>();
	
	public void addTopic(String name) {
		topics.add(new Topic(name));
	}
	
	public void displayTopics(){
	
	}

}

class Topic {
	
	protected Set<Message> messages = new LinkedHashSet<>();
	private String mName;
	
	public Topic(String name) {
		mName = name;
	}
	
	public List<String> displayMessages() {
		List<String> output = new LinkedList<>();
		for (Message m : messages) {
			output.add(m.display());
		}
		return output;
	}
	
	
	
}

class Message {
	
	private String text;

	public Message(String Text) {text = Text;}
	
	public String display() {
		return text;
	}
}