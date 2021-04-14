import java.io.*;
import java.nio.file.*;
import java.util.*;
import javax.swing.*;


public class CSVDuplicateEmailRemover 
{
    static String defaultLayout = "ttf";
    
	public static void main(String[] args)
	{
		/*
			How to Use:
			
			When running CSVDuplicateEmailRemover, you will be prompted to enter in a CSV (with the default layout),
			a series of 3 letters in the form of "t" or "f" (for true and false) to create a custom layouts,
			or a single "f" to be able to input a .CSV file.
			
			The default layout can be changed with the variable "defaultLayout".
			
			If you are trying to copy and paste a list-style CSV file (one seperated with line breaks instead of commas),
			go to https://www.gillmeister-software.com/online-tools/text/remove-line-breaks.aspx and paste in the list-style CSV.
			Then replace all line breaks with commas "," which you can then copy and paste as normal.
			
			A custom layout has the option to turn on and off Show Input CSV, Show Removed Values, and Show in Table Format.
			To do this, enter in three consecutive letters, with no spaces between, of either t or f (for true and false).
			The default layout that appears when directly entering in a CSV is set through the variable defaultLayout.
			
			When entering in "f", a file explorer will open. The default directory is set to downloads.
			In order to sort by most recent, click once on the icon "Details" in the top right, which has 2 squares and 2 lines.
			Then click the "Modified" tab twice, until the arror points downwards, indicating a sort by most recent.
		*/
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter a CSV for Default Layout (Default is \"" + defaultLayout + "\")| \"f\" to select a .CSV or .TXT file | or \"[t/f][t/f][t/f]\"\r\nfor custom layout settings: \"[Show Input CSV][Show Removed Values][Show in Table Format]\"");
	    String initialInput = scanner.nextLine();
	    BufferedReader CSVReader = null;

	    if(initialInput.toLowerCase().equals("f"))
	    {
	    	File file = null;
	    	String pathToDownloads = Paths.get(System.getProperty("user.home"), "Downloads").toString();
	    	JFileChooser chooser = new JFileChooser(pathToDownloads);
	    	String customOrDefault = "";
	    	
			String fileContents = "";
			String CSVContents = "";
			CSV CSVInput = null;
	    	
	    	chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	    	System.out.println("\r\nChoose the file (.CSV or .TXT). The File Explorer may appear BEHIND all your tabs!\r\n");
	    	int response = chooser.showOpenDialog(null);
	    	
	    	if(response == JFileChooser.APPROVE_OPTION)
	    	{
	    		file = chooser.getSelectedFile();
	    	}
			
			try
			{
				CSVReader = new BufferedReader(new FileReader(file));
				
				while((fileContents = CSVReader.readLine()) != null)
				{
					CSVContents += fileContents + ",";
				}
				
				boolean fail = true;
				
				while(fail)
				{
					CSVInput = new CSV(CSVContents);
					fail = !getUserLayout(customOrDefault, CSVInput, scanner);
				}
				
			}	
			catch(Exception e)
			{
				e.printStackTrace();
			}
		
	    }
	    else if(checkIfCustom(initialInput))
	    {	
	    	System.out.println("\nEnter CSV");
		    CSV CSVInput = new CSV(scanner.nextLine());
	    	
		    printCustomLayout(initialInput, CSVInput);
	    }
	    else
	    {
	    	CSV CSVInput = new CSV(initialInput);
	    	
	    	printDefaultLayout(CSVInput);
	    }
	    
	    try
	    {
			if(CSVReader != null)
			{
	    		CSVReader.close();
				scanner.close();
			}
	    }
	    catch(Exception e)
	    {
	    	
	    }
	    
	}
	
	public static boolean checkIfCustom(String initialInput)
	{
		return
		(
			initialInput.toLowerCase().equals("ttt") ||
	    	initialInput.toLowerCase().equals("ftt") ||
	    	initialInput.toLowerCase().equals("fft") ||
	    	initialInput.toLowerCase().equals("fff") ||
	    	initialInput.toLowerCase().equals("ttf") ||
	    	initialInput.toLowerCase().equals("tff") ||
	    	initialInput.toLowerCase().equals("tft") ||
	    	initialInput.toLowerCase().equals("ftf")
	    );
	}
	
	public static void printCustomLayout(String initialInput, CSV CSVInput)
	{
		// Show: Input CSV, Duplicate-Free CSV, and Removed Values
	    // Format: Table
	    if(initialInput.toLowerCase().equals("ttt"))
	    {
	    	System.out.println("\r\nInput CSV:\r\n" + CSVInput.toReadableString(CSVInput.toArrayList(CSVInput.getStringCSV())));
	    	System.out.println("\r\nDuplicate-Free CSV:\r\n" + CSVInput.toReadableString(CSVInput.removeDuplicateValues(CSVInput.toArrayList(CSVInput.getStringCSV()))) + "\r\n");
		    System.out.print("Removed Values:\r\n" + CSVInput.toReadableString(CSVInput.getRemovedValuesArrayList()));
	    }
	    
	    // Show: Duplicate-Free CSV, and Removed Values
	    // Format: Table
	    else if(initialInput.toLowerCase().equals("ftt"))
	    {
	    	System.out.println("\r\nDuplicate-Free CSV:\r\n" + CSVInput.toReadableString(CSVInput.removeDuplicateValues(CSVInput.toArrayList(CSVInput.getStringCSV()))) + "\r\n");
		    System.out.print("Removed Values:\r\n" + CSVInput.toReadableString(CSVInput.getRemovedValuesArrayList()));
	    }
	    
	    // Show: Duplicate-Free CSV
	    // Format: Table
	    else if(initialInput.toLowerCase().equals("fft"))
	    {
	    	System.out.println("\r\nDuplicate-Free CSV:\r\n" + CSVInput.toReadableString(CSVInput.removeDuplicateValues(CSVInput.toArrayList(CSVInput.getStringCSV()))) + "\r\n");
	    }
	    
	    // Show: Duplicate-Free CSV
	    // Format: CSV
	    else if(initialInput.toLowerCase().equals("fff"))
	    {
	    	System.out.println("\r\nDuplicate-Free CSV:\r\n" + CSVInput.toString(CSVInput.removeDuplicateValues(CSVInput.toArrayList(CSVInput.getStringCSV()))) + "\r\n");
	    }
	    
	    // Show: Input CSV, Duplicate-Free CSV, and Removed Values
	    // Format: CSV
	    else if(initialInput.toLowerCase().equals("ttf"))
	    {
	    	System.out.println("\r\nInput CSV:\r\n" + CSVInput.toString(CSVInput.toArrayList(CSVInput.getStringCSV())));
	    	System.out.println("\r\nDuplicate-Free CSV:\r\n" + CSVInput.toString(CSVInput.removeDuplicateValues(CSVInput.toArrayList(CSVInput.getStringCSV()))) + "\r\n");
		    System.out.print("Removed Values:\r\n" + CSVInput.toString(CSVInput.getRemovedValuesArrayList()));
	    }
	    
	    // Show: Input CSV, Duplicate-Free CSV
	    // Format: CSV
	    else if(initialInput.toLowerCase().equals("tff"))
	    {
	    	System.out.println("\r\nInput CSV:\r\n" + CSVInput.toString(CSVInput.toArrayList(CSVInput.getStringCSV())));
	    	System.out.println("\r\nDuplicate-Free CSV:\r\n" + CSVInput.toString(CSVInput.removeDuplicateValues(CSVInput.toArrayList(CSVInput.getStringCSV()))) + "\r\n");
	    }
	    
	    // Show: Input CSV, Duplicate-Free CSV
	    // Format: Table
	    else if(initialInput.toLowerCase().equals("tft"))
	    {
	    	System.out.println("\r\nInput CSV:\r\n" + CSVInput.toReadableString(CSVInput.toArrayList(CSVInput.getStringCSV())));
	    	System.out.println("\r\nDuplicate-Free CSV:\r\n" + CSVInput.toReadableString(CSVInput.removeDuplicateValues(CSVInput.toArrayList(CSVInput.getStringCSV()))) + "\r\n");
	    }
	    
	    // Show: Duplicate-Free CSV, and Removed Values
	    // Format: CSV
	    else if(initialInput.toLowerCase().equals("ftf"))
	    {
	    	System.out.println("\r\nDuplicate-Free CSV:\r\n" + CSVInput.toString(CSVInput.removeDuplicateValues(CSVInput.toArrayList(CSVInput.getStringCSV()))) + "\r\n");
		    System.out.print("Removed Values:\r\n" + CSVInput.toString(CSVInput.getRemovedValuesArrayList()));
	    }
	    
	}
	
	public static void printDefaultLayout(CSV CSVInput)
	{
		printCustomLayout(defaultLayout, CSVInput);
	}
	
	public static boolean getUserLayout(String customOrDefault, CSV CSVInput, Scanner scanner) {
		System.out.println("Enter \"d\" for Default Layout (Default is \"" + defaultLayout + "\") | or \"[t/f][t/f][t/f]\"\r\nfor custom layout settings: \"[Show Input CSV][Show Removed Values][Show in Table Format]\"");
		customOrDefault = scanner.nextLine();
		
		if(checkIfCustom(customOrDefault))
		{
			printCustomLayout(customOrDefault, CSVInput);
		}
		else if(customOrDefault.toLowerCase().equals("d"))
		{
			printDefaultLayout(CSVInput);
		}
		else
		{
			System.out.println("Error: Invalid Input\r\n");
			return false;
		}
		
		return true;
	}
}