package Bottles_Problem;

import java.util.Arrays;

public class MySolution 
{

	/**
	פונקציה המקבלת ערך של כל בקבוק ומחזירה את המיקום של האינדקס *
	בשורה או בעמודה של המטריצה *
	 * @param i Value of first bottle
	 * @param j Value of second bottle
	 * @param n The size of the matrix
	 * @return the position in the matrix corresponds to the value of two bottles
	 */
	public static int getIndex(int i, int j, int n)
	{
		return (n+1)*i+j;
	}
	/**
	פונקציה זו יוצרת מטריצה המייצגת צלעות בין 2 קודקודים *
	לכל קודקוד המייצג את המצב של 2 הבקבוקים יש אפשרות להגיע למקס' 6 אפשרויות *
	 * @param n The maximum value of the first bottle
	 * @param m The maximum value of the second bottle
	 * @return Matrix of true&false, false - no connection, true - there is a connection
	 */

	public static boolean[][]bottlesPorblem(int n,int m)
	{
		System.out.println("The maximum value of the first bottle(n) = "+n);
		System.out.println("The maximum value of the second bottle(m) = "+m);
		System.out.println("********************["+n+","+m+"]********************");
		System.out.println("*********************************************\n");
		int len=(n+1)*(m+1);
		boolean ans[][]=new boolean[len][len];
		int index1,index2;
		for(int i=0;i<=n;++i)
		{
			for(int j=0;j<=m;++j)
			{
				index1=getIndex(i,j,m);
				ans[index1][getIndex(0,j,m)]=true;//Case 1
				ans[index1][getIndex(i,0,m)]=true;//Case 2
				ans[index1][getIndex(n,j,m)]=true;//Case 3
				ans[index1][getIndex(i,m,m)]=true;//Case 4	

				index2=getIndex(Math.min(i+j, n),i+j-Math.min(i+j, n),m);
				ans[index1][index2]=true;//Case 5

				index2=getIndex(i+j-Math.min(i+j, m),Math.min(i+j, m),m);
				ans[index1][index2]=true;//Case 6

			}
		}
		//Distance between each vertex to himeself is ZERO
		for (int i=0; i<len; i++) ans[i][i] = false;
		return ans;
	}
	private static void PrintMat(boolean[][]mat)
	{
		int rows = mat.length;
		int cols = mat[0].length;
		for (int i = 0; i < rows; i++) 
		{
			for (int j = 0; j < cols; j++) 
			{
				System.out.print(mat[i][j]+"\t");
			}
			System.out.println();
		}
	}

	/**
	פונקציה להדפסה מסודרת של טבלת הבקבוקים כפי שהובא לעיל בתאוריה *
	 * @param mat Matrix after FW
	 * @param n The maximum value of the first bottle
	 * @param m The maximum value of the second bottle
	 */
	public static void SmartPrint(boolean mat[][], int n, int m)
	{
		int stop = 0;
		String arr[] = new String[2*n*m + 2];
		int go = 0;
		while (stop<=n) 
		{
			for (int j=0; j<=m; j++) 
			{
				arr[go] = "["+stop+","+j+"]\t";
				go++;
				System.out.print("\t["+stop+","+j+"]");
			}
			stop++;
			if (stop>n) break;
			for (int j=0; j<=m; j++) {
				System.out.print("\t["+stop+","+j+"]");
				arr[go] = "["+stop+","+j+"]\t";
				go++;
			}
			stop++;
		}
		System.out.println("\n");
		for (int i=0; i<mat.length; i++) {
			System.out.print(arr[i]);
			for (int j=0; j<mat[0].length; j++) {
				System.out.print(" "+mat[i][j]+"\t");
			}
			System.out.println("\n");
		}
	}


	//LESSON 2
	/**
	הפונקציה הופכת כל מיקום במטריצה לאמת אם קיים מסלול כשלהו בין קודקוד לקודקוד *
	ושומרת את כל המסלולים במטריצת המחרוזות *
	 * @param mat the Boolean matrix we get in lesson 1
	 * @param n the size of one Bottle
	 * @return String matrix with all tracks
	 */

	public static String[][] ConnectPossibleVertex(boolean mat[][],int m)
	{
		int dim = mat.length;
		int a0=0, b0=0, a1=0, b1=0;
		String path[][] = new String[dim][dim];
		for (int i=0; i<dim; ++i) 
		{
			for (int j=0; j<dim; ++j) 
			{
				a0 = i / (m+1);
				b0 = i % (m+1);
				a1 = j / (m+1);
				b1 = j % (m+1);
				if (mat[i][j]==true) 
					path[i][j] ="["+a0+","+b0+"]-D->["+a1+","+b1+"]";
				else path[i][j] = new String();
			}
		}
		for (int k=0; k<dim; k++) 
		{
			for (int i=0; i<dim; i++)
			{
				for (int j=0; j<dim; j++) 
				{
					if (mat[i][j]==false) 
					{
						mat[i][j] = mat[i][k] && mat[k][j];
						if (mat[i][j] == true)
						{
							//האם באמת קיים קשר בין הקודקודים
							path[i][j] =path[i][k] + " >-i-> " + path[k][j];
						}
					}
				}
			}
		}
		return path;
	}

	/**
	פונקציה בוליאנית הבודקת האם גרף הוא מכוון ע"י השוואה בין משולש עליון לתחתון במטריצה *
	 * @param mat the Boolean Matrix we get in lesson 1
	 * @return true if the Graph is not Directed, else return false
	 */
	public static boolean isDirectedGraph(boolean mat[][]) 
	{
		int n = mat.length;
		int m = mat[0].length;
		for (int i=0; i<n; i++) 
		{
			for (int j=i+1; j<m; j++) 
			{
				if (mat[i][j]!=mat[j][i])
					return false;
			}
		}
		return true;
	}

	/**
	בתוכנית זו נבדוק האם הגרף הוא קשיר וראה להלן תוכנית הבודקת כמה רכיבי קשירות יש לגרף *
	כאן נשתמש במה שמימשנו בשיעור שעבר )מציאת כל המסלולים( עם שינוי קטן, ז"א: *
	אם נמצא קודקוד שנותן ערך "שקר" - זה אומר שהגרף אינו קשיר *
	 * @param mat the Boolean Matrix we get in lesson 1
	 * @return if the graph is connected - return true, else - return false
	 */
	public static boolean isConnectedGraph(boolean mat[][]) 
	{
		//We are not looking for the routes
		int dim = mat.length;
		for (int k=0; k<dim; k++) 
		{
			for (int i=0; i<dim; i++) 
			{
				for (int j=0; j<dim; j++)
				{
					if (mat[i][j]==false) 
						mat[i][j] = mat[i][k] && mat[k][j];
				}
			}
		}
		//If it is connected graph, all values in the matrix should be true!
		for (int i=0; i<dim; i++)
		{
			for (int j=0; j<dim; j++) 
			{
				if (mat[i][j]==false) 
					return false;
			}
		}
		return true;
	}
	/**
	את הפונקציה הזו קיבלנו מוכנה, החלק ששומר מהם רכיבי הקשירות דורש בירור מעמיק יותר *
	 * @param matAfterFW boolean mat - after isDirectedGraph() function from lesson 2
	 * @return How many components in the graph
	 */
	public static int HowManyComponents(boolean matAfterFW[][])
	{
		int count = 0;
		int dim = matAfterFW.length;
		int comps[] = new int[dim];
		for (int i=0; i<dim; i++) 
		{
			if (comps[i]==0) count++;
			for (int j=i; j<dim; j++) 
			{
				if (comps[j]==0 && matAfterFW[i][j])
					comps[j] = count;
			}
		}
		String cs[] = new String[count];
		for (int i=0; i<count; i++) 
		{
			cs[i] = "";
		}
		for (int i=0; i<dim; i++)
			cs[comps[i]-1] = cs[comps[i]-1]+ i +"\t";
		System.out.println("The components are:");
		for (int i=0; i<count; i++)
			System.out.println(cs[i].toString());
		return count;
	}

public static void main(String[] args)
{
	int m = 2; // first bottle
	int n = 1; // second bottle
	boolean[][] mat = bottlesPorblem(n, m);
	//		PrintMat(mat);
	System.out.print("\nPrinting in aesthetical way:\n");
	SmartPrint(mat,n,m);
	String[][]cpv=ConnectPossibleVertex(mat,m);
	for(String[]a:cpv)
		System.out.println(Arrays.toString(a));

	System.out.println("\nIs directed graph? "+isDirectedGraph(mat));
	System.out.println("Is connected graph? "+isConnectedGraph(mat));
	System.out.println("The number of components = "+HowManyComponents(mat));
}

}
