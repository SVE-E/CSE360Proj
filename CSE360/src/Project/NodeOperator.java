package Project;

import java.util.ArrayList;

public class NodeOperator {
	ArrayList<Node> heads = new ArrayList();
	
	
	public void addNode(Node newNode, ArrayList<Node> preds, boolean starting) {
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
	
	
} 
