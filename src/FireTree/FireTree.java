package FireTree;
import java.util.ArrayList;

public class FireTree 
{
	ArrayList<Integer>tree[];//���� �� ARRAYLIST
	int n;//���� ��������� ����
	int degree[];//���� ��� ������ �� ����� ��������� ������
	int radius;//����� �� ���
	int diameter;//���� �� ���
	public FireTree(ArrayList<Integer>AL[])
	{
		tree=AL;
		n=AL.length;
		degree=new int[n];
		radius=0;
		diameter=0;
	}

	/**
	 * �������� ���� ����� �� ������ ��� ������ ����� �� �������
	 * @return the diameter of the tree
	 */
	public int findDiameter()
	{
		//Declaring array list for saving leaves of the tree
		ArrayList<Integer>leaves=new ArrayList<Integer>();

		for (int i = 0; i < n; i++) 
		{
			degree[i] = tree[i].size();
			if (degree[i]==1)
				leaves.add(i);
		}
		int leaf = 0;
		int vertex = 0;
		while(n>2)//�� ��� �� ������ �� ������ ���� �� ����� - �����
		{
			ArrayList<Integer> futureLeaves = new ArrayList<Integer>();
			for(int i=0;i<leaves.size();++i)
			{
				leaf=leaves.get(i);
				degree[leaf]=0;//Out
				for (int j = 0; j < tree[leaf].size(); ++j)
				{
					vertex=tree[leaf].get(j);
					if(degree[vertex]>0)
					{
						//����� ������ ������ ���� ���
						degree[vertex]--;
						if(degree[vertex]==1)
							futureLeaves.add(vertex);
					}
				}
				n--;//Reducing one leaf pair round of the loop
			}
			radius++;//�� �� ����� ����� ������� �� ������
			leaves=futureLeaves;
		}
		if(leaves.size()==2)
		{
			radius++;
			diameter=2*radius-1;
		}
		else
			diameter=2*radius;
		System.out.println("the radius is: " + radius + ", the diameter is: " + diameter + ", and center is: " + leaves);
		return diameter;

	}
	public static void main(String[]args)
	{
		int n=7;//How many vertexes
		ArrayList<Integer>AL[]=new ArrayList[n];
		for (int i = 0; i < n; i++) 
		{ 
            AL[i] = new ArrayList<Integer>(); 
        } 
		AL[0].add(1);
		AL[1].add(0);AL[1].add(2);AL[1].add(4);
		AL[2].add(1);AL[2].add(3);
		AL[3].add(2);
		AL[4].add(1);AL[4].add(5);
		AL[5].add(4);AL[5].add(6);
		AL[6].add(5);
		FireTree ft=new FireTree(AL);
		ft.findDiameter();
	}
}
