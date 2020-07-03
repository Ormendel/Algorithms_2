package Best;
import java.util.Arrays;

/**
 * ניתן ללכת במעגל על גבי המערך בעזרת שארית חלוקה, הוסבר בתרגול עם אור
 * for (int i = 0; i < sizeOfBest; i++)
	System.out.print(plusArr[(i+beginCircle) % plusArr.length]+"\t");
	beginCircle - index of the biggest best sub-array
 * @author ormen
 *
 */
public class CircleBest 
{
	int maxCircle;//משתנה לשמירת סכום תת המערך הגדול ביותר
	int sumAll;//משתנה עצם לשמירת סכום כל האיברים במערך
	int plusArr[];//מערך עזר חיובי
	int minusArr[];//אותו מערך עם הפעלה שלילית - כפל במינוס אחד
	int beginCircle;//שמירת נקודת התחלה
	int endCircle;//שמירת נקודת סיום
	int sizeOfBest;//משתנה עצם לשמירת תת גודל המערך הגדול ביותר

	public CircleBest(int arr[]) 
	{
		int size = arr.length;
		maxCircle = arr[0];
		sumAll = 0;
		plusArr = new int[size];
		minusArr = new int[size];
		beginCircle = 0;
		endCircle = 0;
		sizeOfBest = 0;
		for (int i = 0; i < size; i++) {
			plusArr[i] = arr[i];
			minusArr[i] = (-1)*arr[i];
			sumAll+=arr[i];//שמירת סכום האיברים כולו לטובת המשך התכנית
		}
	}

	/**
	 * find the Best sub Array in circle Array
	 */
	public int getCircleBest()
	{
		Best positive=new Best(plusArr);
		int maxPositive=positive.getBest();
		if(maxPositive<0)//all values are negative - the function will stop
		{
			maxCircle = maxPositive;
			beginCircle = positive.beginMax;
			endCircle = positive.end;
			sizeOfBest = 1;
			return maxCircle;
		}
		Best negative = new Best(minusArr);
		int maxNegative = negative.getBest();
		if(maxPositive>=sumAll+maxNegative)
		{
			maxCircle = maxPositive;
			beginCircle = positive.beginMax;
			endCircle = positive.end;
			sizeOfBest = endCircle - beginCircle + 1;
		}
		else
		{
			maxCircle = sumAll + maxNegative;
			beginCircle = negative.end+1;
			endCircle = negative.beginMax-1;
			sizeOfBest = plusArr.length - beginCircle + endCircle + 1;
		}
		return maxCircle;
	}

	public void printResult() 
	{
		System.out.println("the Array is: "+ Arrays.toString(plusArr));
		System.out.println("= = = = = = = = = = = = = = = = = = = = = =");
		System.out.println("the start index is: " + beginCircle);
		System.out.println("the end index is: " + endCircle);
		System.out.print(" the max sub Array is: [\t");
		for (int i = 0; i < sizeOfBest; i++) 
		{ // הדפסה מעגלית לפי כמות האיברים
			System.out.print(plusArr[(i+beginCircle) % plusArr.length]+"\t");
		}
		System.out.println("]");
		System.out.println("the max sum is: " + maxCircle);
		System.out.println("the number of element is: "+ sizeOfBest);
		System.out.println("============================================");
	}
	public static void main(String[]args)
	{
		int arr[]=new int[]{1,2,-5,5,-1};
		CircleBest cb=new CircleBest(arr);
		cb.getCircleBest();
		cb.printResult();
	}

}



