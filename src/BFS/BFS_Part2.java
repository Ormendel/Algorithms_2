package BFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
public class BFS_Part2 
{
	public static final int white = 0;
	public static final int gray = 1;
	public static final int black = 2;
	public static final int NIL = -1;
	int color[];
	int dist[];
	int pred[];
	int part[];
	LinkedList<Integer>[] list;
	int size;
	int start;
	public BFS_Part2(int numOfV, LinkedList <Integer>list[]) 
	{
		size = numOfV;
		color = new int [size];
		this.start = 0;
		this.list = list;
		dist = new int[size];
		pred = new int[size];
		part = new int[size];
		PrintGraph_0to7_NotConnected();
	}
	public boolean BFS(int st)
	{
		for (int i=0; i<size; i++) 
		{
			dist[i] = NIL;
			pred[i] = NIL;
		}
		int v,u;
		boolean SpiltGraph = true;
		start = st;
		dist[start] = 0;
		color[start] = gray;
		part[start] = 1;
		Queue <Integer> Que = new ArrayBlockingQueue<Integer>(100);
		Que.add(start);
		while (!Que.isEmpty()) {
			v = Que.poll();
			while (!list[v].isEmpty()) {
				u = list[v].removeFirst();
				if (part[u]==part[v]) SpiltGraph = false;
				if (color[u]==white) {
					color[u] = gray;
					dist[u] = dist[v] + 1;
					pred[u] = v;
					part[u] = 3-part[v];
					Que.add(u);
				}
			}
			color[v] = black;
		}
		printArrays(dist,pred,color);
		return SpiltGraph;
	}

	/** The function checks how many connected elements inside the graph and prints them */
	public void compoents() 
	{
		boolean flag = false;
		String comp = new String();
		int t[] = new int[size]; // מערך עזר!
		for (int i=0; i<size; i++)
		{
			t[i]=NIL;//Filling the array with -1
		}
		int count = 1;
		int index;
		while (!flag)
		{
			PrintArrayLL();
			comp = new String();
			for (int i=0; i<size; i++) 
			{
				if (dist[i]!=NIL) 
				{
					comp = comp + i + "->";
					t[i] = dist[i];
				}
			}
			System.out.println("\nThe count is: " + count);
			System.out.println(comp);
			flag = true;
			int nextIndex = 0;
			for (index = 0; flag && index<size; index++) 
			{
				if(t[index]==NIL)
				{
					flag = false;
					count++;
					nextIndex=index;//Activate BFS by the new component
				}
			}
			BFS(nextIndex);
		}
	}

	/**
	 * If there is a track between the vertex - function will prints it
	 * @param end Vertex which you want to reach
	 */
	public void PrintTrack(int end) 
	{
		String ans = "" + end;
		int temp = pred[end];
		while (temp!=-1) {
			ans = temp +"->"+ ans;
			temp = pred[temp];
		}
		//תנאי הבודק האם הקודקוד המבוקש נמצא על אותו רכיב קשירות
		if (dist[end]==-1) 
			System.out.println("there is no Track");
		else System.out.println("the track from "+start+" to "+end+" is: " + ans);
	}

	/**
	 * @return a graph as we learned in class
	 */
	public static LinkedList<Integer>[] InPut_0to7_NotTie()
	{
		int size = 8;
		LinkedList <Integer>list[] = new LinkedList[size];
		for (int i=0; i<size; i++) list[i] = new LinkedList<Integer>();
		list[0].add(1); list[0].add(3);
		list[1].add(0); list[1].add(2);
		list[2].add(1); list[2].add(3);
		list[3].add(0); list[3].add(2);
		list[4].add(5); list[4].add(6);
		list[5].add(4); list[5].add(6);
		list[6].add(4); list[6].add(5);
		list[7].add(7);
		return list;
	}

	/**
	 * Drawing the graph from the lesson
	 */
	public static void PrintGraph_0to7_NotConnected()
	{
		System.out.println("======== Graph 0 to 7 NotTie========\n");
		System.out.println("(0)--------(1) (4) ");
		System.out.println(" | | /| ");
		System.out.println(" | | / | ");
		System.out.println(" | | / | ");
		System.out.println(" | | / | ");
		System.out.println(" | | / | (7) ");
		System.out.println(" | | / | ");
		System.out.println(" | | / | ");
		System.out.println(" | | / | ");
		System.out.println(" | | / | ");
		System.out.println("(3)--------(2) (5)-------(6) ");
		System.out.println("\n===================================\n");
	}
	
	/**
	 * Helped function to print array data
	 */
	public void PrintArrayLL() 
	{
		System.out.println("************the Array LinkedList of Graph is: ************");
		for (int i=0; i<size; i++) 
			System.out.println("vertex "+i+" -> "+list[i].toString());
		System.out.println("******************************************************\n");
	}
	
	/**
	 * Helped function vertex data printing
	 * @param dist the Distance from the start
	 * @param pred the "father" of the vertex
	 * @param color the color of the vertex
	 */
	public static void printArrays(int dist[], int pred[], int color[])
	{
		for (int i=0; i<dist.length; i++) 
			{
			String temp = "";
			if (color[i]==0) temp = "White";
			if (color[i]==1) temp = "Gray";
			if (color[i]==2) temp = "Black";
			System.out.println("Vertex "+i+"\tDist="+dist[i]+
					"\t\tPred="+pred[i]+"\t\tThe color: "+temp);
		}
		System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
	}

}
