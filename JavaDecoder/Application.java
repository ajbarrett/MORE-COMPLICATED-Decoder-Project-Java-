//Andrew Barrett, 8219000567
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;
public class Application {
	public static void main (String args[]) throws FileNotFoundException {
		if(args.length == 2)
		{
			System.out.println("Running program with Valuefile=" + args[0]+ 
					" & Patternfile="+args[1]);;
		}
		else
		{
			System.out.println("Program only accepts 2 arguements. "
					+ "Enter name of Patterns File, then name of Values File");
			return;
		}
	
		ArrayList<Integer[]> codeList = new ArrayList<Integer[]>();
		Queue valueQueue = new Queue();
		
		String codeIn = args[0]; //this block scans in the PATTERNS 
		File codeFile = new File(codeIn);
		try {
			Scanner scanCode = new Scanner(codeFile); 
			while (scanCode.hasNext()) {
				String data = scanCode.next();
				String sValues[] = data.split(",");
				Integer[] tempArray = new Integer[sValues.length];
				for(int i=0; i<sValues.length; i++)
				{
					tempArray[i] = Integer.parseInt(sValues[i]);
				}
				codeList.add(tempArray);
			}
			scanCode.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
			
		String valIn = args[1]; //this block scans in the VALUES
		File valFile = new File(valIn);
		try {
			Scanner scanVal = new Scanner(valFile); 
			while (scanVal.hasNext()) {
				String data = scanVal.next();
				String sValues[] = data.split(",");
				for(int i=0; i<sValues.length; i++)
				{
					valueQueue.loadValue(Integer.parseInt(sValues[i]));
				}
			}
			scanVal.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		Decoder myDecoder = new Decoder(valueQueue, codeList);
		Queue dataToWrite = myDecoder.decode();
		FileOutputStream fos = new FileOutputStream("Output.csv");
		PrintWriter pw = new PrintWriter(fos);
		
		while(!dataToWrite.isEmpty())
		{
			pw.print(dataToWrite.nextValue());
			if(!dataToWrite.isEmpty())
			{
				pw.print(",");
			}
		}
		pw.close();
		System.out.println("Output.csv created");	
	}
}
