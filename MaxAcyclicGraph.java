//Java code for a 1/2 approximation algorithm solution to the Maximum Acyclic Subgraph problem.
//Computer Science 170, UC Berkeley
//Fall 2015
//Dan March

// pseudocode:

// define MaxAcyclicGraphSolver(G = (V,E)):
	// dict = empty HashSet
	// for each vertex in V:
	// 	map vertex to random number in dict
	// set1, set2 = empty set, empty set
	// for each edge in E:
	// 	right_ver = vertex on right side
	// 	left_ver = vertex on left side
	// 	if dict[left_ver] < dict[right_ver]:
	// 		add edge to set1
	// 	else:
	// 		add edge to set2
	// max_set = max(set1,set2) based on cardinality
	// return topological sort of max_set

import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Arrays;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;

public class MaxAcyclicGraph {

	//No instance variables for this class.

	public static List<Integer> firstSolver(int[][] arr) {

		//Map from vertices (as integers) to ArrayLists of integers.
		HashMap<Integer,ArrayList<Integer>> edgeMap = new HashMap<Integer,ArrayList<Integer>>();

		//List for iterating through the set of vertices.
		List<Integer> vertexList = new ArrayList<Integer>();
		List<int[]> firstList = new ArrayList<int[]>();
		List<int[]> secondList = new ArrayList<int[]>();

		//Iterates through the adjacency matrix. Converts it to adjacency lists for easier
		//access to edges.
		int count1 = 0;
		while (count1 < arr.length) {
			ArrayList<Integer> mapList = new ArrayList<Integer>();
			edgeMap.put(count1,mapList);
			int count2 = 0;
			while (count2 < arr[0].length) {
				if (arr[count1][count2] == 1) {
					edgeMap.get(count1).add(count2);
				}
				count2++;
			}
			count1++;
		}

		vertexList.addAll(edgeMap.keySet());

		for (Integer vertex: vertexList) {
			ArrayList<Integer> iteratorList = edgeMap.get(vertex);
			for (Integer element: iteratorList) {
				int[] valueList = {vertex,element};
				if (vertex < element) {
					firstList.add(valueList);
				}
				else {
					secondList.add(valueList);
				}
			}
		}

		//Topological sort of the DAG
		List<Integer> sortedList = new ArrayList<Integer>();
		Set<Integer> noInc = new HashSet<Integer>();
		List<Integer> noIncList = new ArrayList<Integer>();
		Set<Integer> secondElms = new HashSet<Integer>();

		for (int[] listElm : firstList) {
			secondElms.add(listElm[1]);
		}

		for (int[] listElm : firstList) {
			if (!secondElms.contains(listElm[0])) {
				noInc.add(listElm[0]);
			}
		}

		noIncList.addAll(noInc);
		if (noIncList.size() == 0) {
			noIncList.add(1);
			noIncList.add(2);
			noIncList.add(3);
			noIncList.add(4);
		if (arr.length > 10) {
			noIncList.add(5);
			noIncList.add(6);
			noIncList.add(7);
			noIncList.add(8);
			noIncList.add(9);
			noIncList.add(10);
			}
		}

		while (noIncList.size() > 0) {
			int removed_node = noIncList.remove(0);
			if (sortedList.contains(removed_node)) {
				continue;
			}
			sortedList.add(removed_node);

			if (edgeMap.get(removed_node) == null) {
				continue;
			}

			for (Integer index : edgeMap.get(removed_node)) {
				int counter3 = 0;
				int mySwitch = 0;
				arr[removed_node][index] = 0;
				while (counter3 < arr.length) {
					if(arr[counter3][index] == 1 && counter3 != removed_node) {
						mySwitch = 1;

					}
					counter3++;
				}
				if(mySwitch == 0) {
					noIncList.add(index);
				}
			}
			edgeMap.remove(removed_node);
		}
		return sortedList;
	}

	public static List<Integer> secondSolver(int[][] arr) {
		//Map from vertices (as integers) to
		HashMap<Integer,ArrayList<Integer>> edgeMap2 = new HashMap<Integer,ArrayList<Integer>>();

		//List for iterating through the set of vertices.
		List<Integer> vertexList2 = new ArrayList<Integer>();
		List<int[]> firstList2 = new ArrayList<int[]>();
		List<int[]> secondList2 = new ArrayList<int[]>();

		//Iterates through the adjacency matrix. Converts it to adjacency lists for easier
		//access to edges.

		int count1_2 = 0;
		while (count1_2 < arr.length) {
			ArrayList<Integer> mapList2 = new ArrayList<Integer>();
			edgeMap2.put(count1_2,mapList2);
			int count2_2 = 0;
			while (count2_2 < arr[0].length) {
				if (arr[count1_2][count2_2] == 1) {
					edgeMap2.get(count1_2).add(count2_2);
				}
				count2_2++;
			}
			count1_2++;
		}

		vertexList2.addAll(edgeMap2.keySet());

		for (Integer vertex: vertexList2) {
			ArrayList<Integer> iteratorList2 = edgeMap2.get(vertex);
			for (Integer element: iteratorList2) {
				int[] valueList2 = {vertex,element};
				if (vertex < element) {
					firstList2.add(valueList2);
				}
				else {
					secondList2.add(valueList2);
				}
			}
		}
		// Iterator<int[]> iter = firstList2.iterator();
		// while (iter.hasNext()) {
		// 	System.out.println(Arrays.toString(iter.next()));
		// }
		// System.out.println("\n\n\n\n\n");
		// Iterator<int[]> iter2 = secondList2.iterator();
		// while (iter2.hasNext()) {
		// 	System.out.println(Arrays.toString(iter2.next()));
		// }

		//Second topological sort
		List<Integer> sortedList2 = new ArrayList<Integer>();
		Set<Integer> noInc2 = new HashSet<Integer>();
		List<Integer> noIncList2 = new ArrayList<Integer>();
		HashSet<Integer> secondElms2 = new HashSet<Integer>();
		for (int[] listElm : secondList2) {
			secondElms2.add(listElm[1]);
		}
		for (int[] listElm : secondList2) {
			if (!secondElms2.contains(listElm[0])) {
				noInc2.add(listElm[0]);
			}
		}
		noIncList2.addAll(noInc2);

		if (noIncList2.size() == 0) {
			noIncList2.add(1);
			noIncList2.add(2);
			noIncList2.add(3);
			noIncList2.add(4);
			if (arr.length > 10) {
				noIncList2.add(5);
				noIncList2.add(6);
				noIncList2.add(7);
				noIncList2.add(8);
				noIncList2.add(9);
				noIncList2.add(10);
			}
		}

		while (noIncList2.size() > 0) {
			int removed_node = noIncList2.remove(0);
			sortedList2.add(removed_node);
			for (Integer index : edgeMap2.get(removed_node)) {
				int counter4 = 0;
				int mySwitch2 = 0;
				while (counter4 < arr.length) {
					if(arr[counter4][index] == 1) {
						mySwitch2 = 1;
					}
					counter4++;
				}
				if(mySwitch2 == 0) {
					noIncList2.add(index);
				}
			}
			edgeMap2.remove(removed_node);
		}
		return sortedList2;
	}

	public static List<Integer> finalCall(List<Integer> inputList, int arraySize) {
		int myInt = 0;
		while (myInt < arraySize) {
			if (!inputList.contains(myInt)) {
				inputList.add(myInt);
			}
			myInt++;
		}
		return inputList;
	}

	public static void main(String[] args) {
		Writer writer = null;
		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream("AndHisNameIsJohnCena.out"), "utf-8"));
		    for(int i= 1; i <= 621; i++) {
			  		solve("./instances/" + Integer.toString(i) + ".in", args[1], writer);
						System.out.println(i);
					}
		} catch (IOException ex) {
		  // report
		} finally {
		   try {writer.close();} catch (Exception ex) {/*ignore*/}
		}

	}

	public static void solve(String in, String out, Writer writer) throws IOException {

		File input = new File(in);
		File output = new File(out);
		Scanner newScan = null;
		try {
			newScan = new Scanner(input);
		} catch(Exception e) {
			System.out.println(e);
		}

		int size = Integer.parseInt(newScan.nextLine().split(" ")[0]);
		int[][] myArray = new int[size][size];
		int counter = 0;

		while(newScan.hasNext()) {
			String line = newScan.nextLine();
			String[] nextLine = line.split(" ");

			int counter2 = 0;

			while(counter2 < size) {
				myArray[counter][counter2] = Integer.parseInt(nextLine[counter2]);
				counter2++;
			}
			counter++;
		}

		int[][] myArray2 = new int[size][size];
		int ind1 = 0;
		while (ind1 < myArray.length) {
			int ind2 = 0;
			while (ind2 < myArray[0].length) {
				myArray2[ind1][ind2] = myArray[ind1][ind2];
				ind2++;
			}
			ind1++;
		}

		List<Integer> firstSolved = firstSolver(myArray);
		//Since firstSolver is a destructive method, we pass in the array copy
		List<Integer> secondSolved = secondSolver(myArray2);
		List<Integer> larger;
		if (firstSolved.size() >= secondSolved.size()) {
			larger = firstSolved;
		}
		else {
			larger = secondSolved;
		}
		List<Integer> returnList = finalCall(larger,size);
		for(int i = 0; i < returnList.size(); i++) {
			returnList.set(i, returnList.get(i) + 1);
		}
		String newList = returnList.toString().replaceAll("[\\[\\]]", "").replaceAll(",", " ") + "\n";
		System.out.println(newList);

		writer.write(newList);

	}

}
