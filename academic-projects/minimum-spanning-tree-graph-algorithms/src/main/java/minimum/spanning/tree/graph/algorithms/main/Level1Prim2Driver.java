package minimum.spanning.tree.graph.algorithms.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import minimum.spanning.tree.graph.algorithms.Prims2Algorithm;
import minimum.spanning.tree.graph.algorithms.model.Graph;

/**
 * Level 1 Driver
 * 
 * This driver servers as main class for Prim's 2 algorithm. 
 * 
 * @author G31 (Himanshu Kandwal and Dharmam Buch)
 *
 */
public class Level1Prim2Driver {
	
	public static void main(String[] args) {
		
		try {
			Scanner in;
			if (args.length > 0) {
				File inputFile = new File(args[0]);
				in = new Scanner(inputFile);
			} else {
				in = new Scanner(System.in);
			}
			
			Graph graph = Graph.readGraph(in, false);
			
			Prims2Algorithm algorithm = new Prims2Algorithm();
			algorithm.PrimMST(graph);
			
			System.out.println("Output:");
			System.out.println(algorithm.getMinimumCost());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
