//Andrew Barrett, 821900567
import java.util.*;
public class Queue {
	ArrayList<Integer> queuememory = new ArrayList<Integer>();
	public boolean isEmpty() //Returns true when the queue holds zero items
	{
		return queuememory.isEmpty(); 
	}
	public void loadValue(int num) 
	//Inserts a value into the queue. Analogous to enqueue
	{
		queuememory.add(num);
	}
	public int nextValue() //Removes the first item from the queue. 
	//Analogous to dequeue.Returns - 1 if the queue is empty when called
	{
		if (queuememory.isEmpty())
		{
			return -1;
		}
		int poptop = queuememory.get(0);
		queuememory.remove(0);
		return poptop;
	}
	public void normalize() //Subtracts the value of the first item in the
	//queue from everything remaining.
	{
		int firstelement = queuememory.get(0);
		for (int i = 0; i < queuememory.size(); i++)
		{
			int placeholder = queuememory.get(0);
			queuememory.remove(0);
			queuememory.add(placeholder - firstelement);
		}
	}
	public int[] peek(int num) 
	//Supplies the caller with an array of the next num values.
	{
		int returnarray[] = new int[num];
		for (int i = 1; i <= num; i++)
		{
			returnarray[i-1] = queuememory.get(i);
		}
		return returnarray;
	}
	public int size() //returns the number of items in the queue
	{
		return queuememory.size();
	}
	
	Queue(){ //default constructor
		
	}
	Queue(ArrayList<Integer> inList){ //custom constructor
		queuememory = inList;
	}
}

