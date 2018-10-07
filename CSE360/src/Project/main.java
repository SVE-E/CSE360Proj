package Project;
import java.util.ArrayList;

import javax.swing.*;

public class main {

	public static void main(String[] args) {
		NodeOperator nodeTree = new NodeOperator();
		ArrayList<Node> pred = new ArrayList();
		ArrayList<Node> allNodes = new ArrayList();
		
		
		Node A = new Node("A",5);
		Node B = new Node("B",7);
		nodeTree.addNode(A,pred, true);
		nodeTree.addNode(B, pred, true);
		pred.add(A);
		pred.add(B);
		Node C = new Node("C",1);
		nodeTree.addNode(C, pred, false);
		pred.clear();
		Node D = new Node("D",3);
		pred.add(C);
		nodeTree.addNode(D, pred, false);
		Node E = new Node("E",4);
		nodeTree.addNode(E, pred, false);
		pred.clear();
		pred.add(D);
		pred.add(E);
		Node F = new Node("F", 7);
		nodeTree.addNode(F, pred, false);
		
		allNodes.add(A);
		allNodes.add(B);
		allNodes.add(C);
		allNodes.add(D);
		allNodes.add(E);
		allNodes.add(F);
		
		for(int i = 0; i<allNodes.size();i++) {
			System.out.println(allNodes.get(i).getName()+": ");
			for(int j = 0; j< allNodes.get(i).nexts.size();j++) {
				System.out.print(allNodes.get(i).nexts.get(j).getName()+ " ");
			}
			System.out.println();
		}
		
		
		ArrayList<Path> allPaths = nodeTree.getAllPaths();
		for(int i = 0; i< allPaths.size();i++) {
			System.out.println("Path: " + allPaths.get(i).getPath() + " Length: " + allPaths.get(i).getLength());
		}

	}

}
