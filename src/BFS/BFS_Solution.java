package BFS;

import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;


public class BFS_Solution 
{
	/**
	 * Pseudo code: 
	 * First we need to initialize the distance to be 0, color gray.
	 * We insert the value to priority queue.
	 * We run in loop - as long as the queue not empty - continue.
	 * It will be empty when we finish move on all vertex with neighbors.
	 * 
	 * 
	 * white - haven't taken care yet
	 * gray - in process, didn't finish
	 * black - finished.
	 * 
	 * Given vertex v- neighbors of v will be in list[v];
	 * while(!list[1].isEmpty())
	 * {
	 * 	u=list[1].removeFirst(); -> 0
	 * 	if(color[0]==white)
	 * 	{
	 * 		color[0]=gray;
	 * 		dist[0] = dist[1] + 1; -> 1
	 * 		pred[u] = v; -> 0
	 * 		Que.add(1);
	 * 	}
	 * }
	 * @param args
	 */


	/**
	* Constructor
	* @param numOfV the number of Vertex
	* @param list the LinkedList of all edges
	*/
	public BFS_Solution(int numOfV, LinkedList <Integer>list[])
	{
		size = numOfV;
		color = new int [size];
		this.start=0;// ברירת מחדל עד להפעלת הפונקציה של המחלקה
		this.list = list;
		dist = new int[size];
		pred = new int[size];
		PrintGraph_0to7();//Printing the graph
	}
	/**
	 * Drawing the graph from the lesson
	 */
	public static void PrintGraph_0to7() 
	{
		System.out.println("=========== Graph 0 to 7 ===========\n");
		System.out.println("(0)--------(1) (2)--------(3)");
		System.out.println(" | | /| /| ");
		System.out.println(" | | / | / | ");
		System.out.println(" | | / | / | ");
		System.out.println(" | | / | / | ");
		System.out.println(" | | / | / | ");
		System.out.println(" | | / | / | ");
		System.out.println(" | | / | / | ");
		System.out.println(" | | / | / | ");
		System.out.println(" | | / | / | ");
		System.out.println("(4) (5)--------(6)--------(7)");
		System.out.println("\n===================================\n");

	}

	//Important variables
	public static final int white = 0;
	public static final int gray = 1;
	public static final int black = 2;
	public static final int NIL = -1;
	int color[];
	int dist[];
	int pred[];
	LinkedList<Integer>[] list;
	int size;
	int start;


	public static void main(String[] args) 
	{
		PrintGraph_0to7();
	}

}
