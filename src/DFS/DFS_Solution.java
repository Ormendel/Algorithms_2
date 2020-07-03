package DFS;

import java.util.LinkedList;

public class DFS_Solution 
{
	public static final int white = 0;
	public static final int gray = 1;
	public static final int black = 2;
	public static final int NIL = -1;
	int color[];
	int first[];
	int last[];
	int pred[];
	LinkedList<Integer>[] list;
	int size;
	int time;

	/**
	 * Constructor
	 * @param st the start vertex
	 * @param n the number of vertex
	 */
	public DFS_Solution(int n,int st)
	{
		size = n;
		list = new LinkedList[size];
		// white=0, gray=1, black=2
		color=new int[size];//מאופס בצבע לבן
		first=new int[size];//מאופס באפס
		last=new int[size];//מאופס באפס
		pred=new int[size];
		for(int i=st;i<size;++i)
			pred[i]=NIL;//מאופס במינוס אחד
		list=InPut_0to5();//עדכון הרשימה על פי הדוגמא המבואת להלן
		time=0;
		PrintGraph_0to5();//פונקציה להדפסת הדוגמא בצורה של גרף
		//ריצה על כל הקודקודים - כל קודקוד לבן מופעלת עליו הפונקציה הרקורסיבית, וראה הערות בתיאוריה דלעיל 
		for (int i=0; i<n; i++)
		{
			if (color[i] == white) 
			{
				System.out.println("White Cons");
				DFS(i);
			}
		}
	}

	/**
	 * Function finds all the tracks from st Vertex - Search in depth
	 * @param start the vertex that sent to the DFS Recursion function
	 */
	public void DFS(int start) { // פונקציה רקורסיבית - מתחילה לעבוד מתוך הבנאי
		printArrays(color,pred,first,last);
		int v;
		time = time+1;
		first[start] = time;
		color[start] = gray;
		//רצים על כל השכנים של הקודקוד - על פי האיברים שקיימים בתוך הלינק ליסט
		for (int i=0; i<list[start].size(); i++)
		{
			v=list[start].get(i);//קבלת קודקוד מתוך הרשימה המקושרת
			if (color[v] == white) 
			{
				pred[v] = start;
				DFS(v);//הפעלת רקורסיה על קודקוד שכן - לבן
			}
		}
		color[start]=black;//הקודקוד שהוכנס לפונקציה יהפוך לשחור רק בדרך חזור, כלומר כאשר אין לו שכנים לבנים
		last[start]=++time;
	}

	/**
	 * @return a graph as we learned in class
	 */
	public LinkedList<Integer>[] InPut_0to5()
	{
		for (int i=0; i<size; i++) list[i] = new LinkedList<Integer>();
		list[0].add(1); list[0].add(3); list[1].add(0); list[1].add(4);
		list[2].add(4); list[2].add(5); list[3].add(0); list[3].add(4);
		list[4].add(1); list[4].add(2); list[4].add(3); list[5].add(2);
		return list;
	}

	/** Drawing the graph from the lesson */
	public void PrintGraph_0to5() {
		System.out.println("====== Graph 0 to 5 ======\n");
		System.out.println("(0)--------(1) (2)");
		System.out.println(" | | /| ");
		System.out.println(" | | / | ");
		System.out.println(" | | / | ");
		System.out.println(" | | / | ");
		System.out.println(" | | / | ");
		System.out.println(" | | / | ");
		System.out.println(" | | / | ");
		System.out.println(" | | / | ");
		System.out.println(" | | / | ");
		System.out.println("(3)--------(4) (5)");
		System.out.println("\n==========================\n");
	}

	/**
	 * Helped static function - vertex data printing
	 * @param color the color of the vertex
	 * @param pred the "father" of the vertex
	 * @param first the Distance from the start in the first track
	 * @param last the Distance from the start in the second track
	 */
	public static void printArrays(int color[], int pred[], int first[], int last[]) {
		for (int i=0; i<color.length; i++) {
			String temp = "";
			if (color[i]==0) temp = "White";
			if (color[i]==1) temp = "Gray";
			if (color[i]==2) temp = "Black";
			System.out.println("Vertex "+i+"\tPred="+pred[i]+
					"\t\tFirst="+first[i]+"----Last="+last[i]+"\tThe color: "+temp);
		}
		System.out.println("()()()()()()()()()()()()()()()()()()()()()\n");
	}
}

