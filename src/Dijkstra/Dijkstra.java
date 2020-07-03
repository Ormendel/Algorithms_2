package Dijkstra;

import java.util.PriorityQueue;

public class Dijkstra
{
	public static Vertex[] initGraph()
	{
		//Creating the vertexes
		Vertex v0=new Vertex(0);
		Vertex v1=new Vertex(1);
		Vertex v2=new Vertex(2);
		Vertex v3=new Vertex(3);
		Vertex v4=new Vertex(4);

		//Creating the edges for all vertexes
		v0.edge = new Edge[]{new Edge(1,10), new Edge(4,5)};
		v1.edge = new Edge[]{new Edge(2,1), new Edge(4,2)};
		v2.edge = new Edge[]{new Edge(3,6)};
		v3.edge = new Edge[]{new Edge(2,4), new Edge(0,7)};
		v4.edge = new Edge[]{new Edge(3,2), new Edge(2,9),new Edge(1,3)};

		//array of vertexes
		Vertex vs[]= {v0,v1,v2,v3,v4};
		return vs;
	}

	/**
	 * @param ver the Array of the Vertex
	 * @param num the start Vertex
	 */
	public static void Dijkstra(Vertex ver[], int num)
	{
		PriorityQueue <Vertex> q = new PriorityQueue<Vertex>();
		Vertex start = ver[num];
		q.add(start);//קודקוד התחלתי נכנס
		start.dist=0;//מרחקו שווה לאפס
		while (!q.isEmpty())
		{
			//מוציאים איבר מהתור ומטפלים בכל השכנים שלו
			Vertex u = q.poll();
			System.out.println("and the poll is: "+u.name);
			PrintDij(ver);//קריאה לפונקציית עזר המדפיסה את הנתונים בכל שלב
			//להבנת דברים בצורה טובה יותר - להסתכל בתאוריה
			for (int i=0; i<u.edge.length; i++) 
			{
				Edge e = u.edge[i];
				Vertex v = ver[e.vertex];
				if(!v.visit)//כל עוד קיימים שכנים
					if (v.dist > u.dist + e.weight)
					{
						v.dist = u.dist + e.weight;
						v.prev = ver[u.name].name;
						q.remove(v);//הוצאה - רק אם האיבר באמת קיים
						q.add(v);
					}
			}
			u.visit=true;
		}

	}
	/**
	 * static fun' to print the vertex
	 * @param v the Array of vertex
	 */
	public static void PrintDij(Vertex v[]) 
	{
		for (int i=0; i<v.length; i++) 
			System.out.println	("ver "+i+", dist: "+v[i].dist+", prev: "+v[i].prev+", visit: "+v[i].visit);
		System.out.println("*********************************\n");
	}

	/**
	 * @param v the Array of vertex after Dijkstra
	 * @param start the Start vertex
	 * @param end the vertex we want to find his track
	 * @return String of track
	 */
	public static String getTrack(Vertex v[], int start, int end) {
		String ans = "";
		if (v[end].dist == Double.POSITIVE_INFINITY)
			return "there is no track!";
		while (v[end].dist != 0) 
		{
			ans = end + " --> " + ans;
			end = v[end].prev;
		}
		ans = start + " --> " + ans;
		return ans;
	}
	
	/**
	 * Finding the cheapest track between start to end vertexes
	 * חשוב לשים לב שלכל קודקוד יש את הקודקוד הקודם לו, ולכן ניתן ללכת מקודקוד המטרה אחורה - עד
להגעה לקודקוד ההתחלה. במקרה והמרחק הקודקוד המבוקש הוא "אינסוף" - הרי שאין כלל מסלול המחבר	
את 2 הקודקודים.
*
*double sum = v[end].dist; //the length of the cheapest track
	 */

}


