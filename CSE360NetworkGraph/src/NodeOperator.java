

import java.util.ArrayList;

public class NodeOperator {
	ArrayList<Node> heads = new ArrayList();
	ArrayList<Node> allNodes = new ArrayList();
	
	public void addNode(Node newNode, ArrayList<Node> preds, boolean starting) {
		allNodes.add(newNode);
		if (starting) {
			heads.add(newNode);
		}
		else {
			for(int i = 0; i < heads.size(); i++)
				setUpNexts(heads.get(i), newNode, preds);
		}
	}
	
	public void setUpNexts(Node current, Node newNode, ArrayList<Node> preds) {
		if(preds.contains(current)) {
			if(!current.nexts.contains(newNode))
				current.addNext(newNode);
		}
		
		if(current.nexts.isEmpty()) { //stopping condition
			return;
		}
		else {
			for(int i = 0; i<current.nexts.size(); i++)
				setUpNexts(current.nexts.get(i), newNode, preds);
			}
	}
	
	public ArrayList<Path> getAllPaths(){
		ArrayList<Path> paths = new ArrayList();
		for(int i = 0; i<heads.size();i++) {
			traverseAndAdd(heads.get(i), paths, new Path());
		}
		
		Path key;
		int j;
		for(int i = 2; i<paths.size();i++) {
			key = paths.get(i);
			j=i-1;
			while(j >=0 && paths.get(j).getLength() < key.getLength()) {
				paths.set(j+1, paths.get(j));
				j--;
			}
			paths.set(j+1, key);
		}
		return paths;
	}
	
	public void traverseAndAdd(Node current, ArrayList<Path> pathList, Path currentPath) {
		Path newPath = new Path(currentPath);
		newPath.addNextNode(current);
		if(current.nexts.isEmpty()) {
			pathList.add(newPath);
			return;
		}
		else {
			for (int i = 0; i< current.nexts.size(); i++) {
				traverseAndAdd(current.nexts.get(i),pathList,newPath);
			}
		}
		
		
	}
	
	public ArrayList<Node> getAllNodes(){
		return allNodes;
	}
	
	
} 
