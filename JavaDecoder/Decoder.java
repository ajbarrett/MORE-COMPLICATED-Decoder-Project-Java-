//Andrew Barrett, 821900567
import java.util.*;
public class Decoder {
	ArrayList<Integer[]> codeList;
	Queue valueQueue;
	
	
	Decoder(){
		
	}
	Decoder(Queue queue, ArrayList<Integer[]> codes){
		codeList = codes;
		valueQueue = queue;		
	}
	
	
	private static ArrayList<Integer> integerAtoL(int[] b){
		ArrayList<Integer> returnList = new ArrayList<Integer>();
		for(int copy=0;copy<b.length;copy++)
		{
			returnList.add(b[copy]);
		}
		return returnList;
	}
	
	private static ArrayList<Integer> integerAtoL(Integer[] b){
		ArrayList<Integer> returnList = new ArrayList<Integer>();
		for(int copy=0;copy<b.length;copy++)
		{
			returnList.add(b[copy]);
		}
		return returnList;
	}
	
	public Queue decode()
	{
		Queue returnData = new Queue();
		valueQueue.normalize();

		while(!valueQueue.isEmpty())
		{
			
			int restofqueue[] = valueQueue.peek(valueQueue.size()-1); 
			ArrayList<Integer> potential = integerAtoL(restofqueue);  
			//^ place rest of the queue into an arraylist to iterate through 
			
			valueQueue.nextValue(); //removes "0" from the front of the queue
									//bc it's redundant
			for(int j=0;j<codeList.size();j++) //checks for each code
			{	
				
				int currentCodeindex = j;
				
				ArrayList<Integer> currentCode = 
						integerAtoL(codeList.get(currentCodeindex));
				Queue codeQueue = new Queue(currentCode);
				codeQueue.nextValue();
				int numTimestoLoop = codeQueue.size();
				int indexFirstVal = 100;
				int indexLastVal = -10;
				//indexOfFirstValue & indexOfLastValue use a garbage 
				//declaration to ensure that an accidental break doesn't occur
				
				for(int k=1;k<=numTimestoLoop;k++) 
					//checks if values match currentCode
				{
					int valueToTest = codeQueue.nextValue();

					if(potential.contains(valueToTest))
					{
						if(k==1) 
							{
							indexFirstVal = potential.indexOf(valueToTest);
							}
						else if(k==numTimestoLoop-1) 
							{
							indexLastVal = potential.indexOf(valueToTest);
							}
					}
					else
					{
						if(codeQueue.isEmpty())
						{
							int noiseMeter = indexLastVal - indexFirstVal;
							if(noiseMeter > 
								codeList.get(currentCodeindex).length -2)
							//causes break if more than 1 noise in sequence
							{
								break;
							}
							Integer codetodisplay[] = 
										codeList.get(currentCodeindex);
							returnData.loadValue(
									codetodisplay[codetodisplay.length -1]); 
							//loads the value of the code onto return statement
							for(int x=0; x<noiseMeter;x++)
							{
								valueQueue.nextValue();
							}
						}
						break;
					}
				}				
			} // code loop
			try {
				valueQueue.normalize();
			}
			catch (IndexOutOfBoundsException e)
			{	
			}
		} //poptop loop
		return returnData;
	}
}
