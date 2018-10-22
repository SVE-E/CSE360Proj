
import java.util.ArrayList;

public class Node {
	String name;
	int duration;
	ArrayList<Node> nexts = new ArrayList();
	ArrayList<Node> predecessors = new ArrayList();
	boolean visited;
	public Node() {
		name ="";
		duration = 0;
		visited = false;
	}
	
	public Node(String n, int dur, ArrayList<Node> p) {
		name = n;
		duration = dur;
		predecessors = p;
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
	
	public void addNext(Node n) {
		nexts.add(n);
	}
	
	public String toString() {
		String ret = "Name: "  + name + " Predecessors: ";
		for(int i = 0; i< predecessors.size(); i++) {
			ret += predecessors.get(i).getName() + " ";
		}
		return ret;
	}
	
}
