

import java.util.ArrayList;

import javax.swing.DefaultListModel;

public class NodeOperator {
	ArrayList<Node> heads = new ArrayList();
	ArrayList<Node> allNodes = new ArrayList();
	
	int currentCount = 0;
	
	public void addNode(String name, int dur, String[] preds, boolean starting) {
		for(int i = 0; i < allNodes.size(); i++) {
			if(name.equals(allNodes.get(i).getName())) {
				allNodes.get(i).setDuration(dur);
				allNodes.get(i).setPreds(preds);
				return;
			}
		}
		Node newNode = new Node(name, dur, preds);
		allNodes.add(newNode);
		if (starting) {
			heads.add(newNode);
		}
		System.out.print("Added");
	}
	
	public void setUpNexts() {
		
		for(int i = 0; i < allNodes.size(); i++) {
			allNodes.get(i).nexts.clear();
			allNodes.get(i).predecessors.clear();
		}
		
		for(int i = 0; i < allNodes.size(); i ++) {
			for(int j = 0 ; j < allNodes.get(i).predNames.size(); j++) {
				String temp = allNodes.get(i).predNames.get(j);
				for(int k = 0; k < allNodes.size(); k++) {
					if(temp.equals(allNodes.get(k).getName())) {
						allNodes.get(k).nexts.add(allNodes.get(i));
						allNodes.get(i).predecessors.add(allNodes.get(k));
					}
				}
			}
		}
		
		for(int i = 0; i < allNodes.size(); i++) {
			System.out.println(allNodes.get(i).getName() + " : ");
			for(int j = 0; j < allNodes.get(i).nexts.size(); j++) {
				System.out.print(allNodes.get(i).nexts.get(j).getName() + " ");
			}
		}
		
		
	}
	
	public ArrayList<Path> getAllPaths(){
		
		ArrayList<Path> paths = new ArrayList();
		for(int i = 0; i<heads.size();i++) {
			traverseAndAdd(heads.get(i), paths, new Path());
		}
		
		Path key;
		int j;
		for(int i = 1; i<paths.size();i++) {
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
		if(current.nexts.isEmpty()) { //
			pathList.add(newPath);
			return;
		}
		else {
			for (int i = 0; i< current.nexts.size(); i++) {
				traverseAndAdd(current.nexts.get(i),pathList,newPath);
			}
		}
		
		
	}
	
	public boolean checkForLoop() {
		setVisitedFalse();
		for(int i = 0; i<heads.size(); i++) {
			boolean result = traverseAndCheckLoop(heads.get(i));
			if(result == true)
				return true;
		}
		return false;
	}
	
	public boolean traverseAndCheckLoop(Node current) {
		if(current.getVisited() == true) {
			setVisitedFalse();
			return true;
		}
		else {
			current.setVisited(true);
			for (int i = 0; i< current.nexts.size(); i++) {
				if(traverseAndCheckLoop(current.nexts.get(i)) == true) {
					setVisitedFalse();
					return true;}
			}
		}
		setVisitedFalse();
		return false;
	}
	
	
	public void setVisitedFalse() {
		for(int i = 0; i < allNodes.size(); i++) {
			allNodes.get(i).setVisited(false);
		}
	}
	
	public boolean checkForDisconnect() {
		
		int totalNodes = allNodes.size();
		System.out.println("Total: " + totalNodes);
		for(int i = 0; i < heads.size(); i++) {
			
			int relCount = traverseAndCheckDisconnect(heads.get(i));
			
			if(relCount != totalNodes) {
				currentCount = 0;
				return true;
				}
		}
		setVisitedFalse();
		currentCount = 0;
		return false;
	}
	
	public int traverseAndCheckDisconnect(Node current) {
		int newCount=currentCount;
		System.out.println("Node: " + current.getName() + " curCount: " + currentCount);
		if(current.getVisited() == false) {
			newCount +=1;
			currentCount +=1;
			current.setVisited(true);
			for(int i = 0; i < current.nexts.size(); i++) {
				int temp = traverseAndCheckDisconnect(current.nexts.get(i));
				if (temp > newCount)
					newCount = temp;
			}
			for(int i = 0; i < current.predecessors.size(); i++) {
				int temp = traverseAndCheckDisconnect(current.predecessors.get(i));
				if (temp > newCount)
					newCount = temp;
			}
		}
		return newCount;
		
	}
	
	
	
	public ArrayList<Node> getAllNodes(){
		return allNodes;
	}
	
	public DefaultListModel getNodeModel() {
		DefaultListModel model = new DefaultListModel();
		for (int i = 0; i< allNodes.size();i++) {
			model.addElement(allNodes.get(i).toString());
		}
		return model;
	}
	
	public void clearAll() {
		heads.clear();
		allNodes.clear();
	}
	
} 
