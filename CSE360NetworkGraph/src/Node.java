
import java.util.ArrayList;

public class Node {
	String name;
	int duration;
	ArrayList<Node> nexts = new ArrayList();
	ArrayList<Node> predecessors = new ArrayList();
	ArrayList<String> predNames = new ArrayList();
	boolean visited;
	public Node() {
		name ="";
		duration = 0;
		visited = false;
	}

	public Node(String n, int dur, String[] p) {
		name = n;
		duration = dur;
		for(int i = 0; i< p.length; i++) {
			predNames.add(p[i]);
		}
	}
	
	public void setVisited(boolean v) {
		visited = v;
	}
	
	public boolean getVisited() {
		return visited;
	}
	
	public String getName() {
		return name;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public void setDuration(int d) {
		duration = d;
	}
	
	public void setPreds(String[] p) {
		predNames.clear();
		for(int i = 0; i< p.length; i++) {
			predNames.add(p[i]);
		}
	}
	
	public void addNext(Node n) {
		nexts.add(n);
	}
	
	public String toString() {
		String ret = "Name: "  + name + " Duration:  " + duration + " Predecessors: ";
		System.out.println(predNames.size());
		for(int i = 0; i< predNames.size(); i++) {
			ret += predNames.get(i) + " ";
		}
		return ret;
	}
	
}
