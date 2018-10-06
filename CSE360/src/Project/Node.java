package Project;

import java.util.ArrayList;

public class Node {
	String name;
	int duration;
	ArrayList<Node> nexts = new ArrayList();
	public Node() {
		name ="";
		duration = 0;
	}
	
	public Node(String n, int dur) {
		name = n;
		duration = dur;
	}
	
	public String getName() {
		return name;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public void addNext(Node n) {
		nexts.add(n);
	}
}
