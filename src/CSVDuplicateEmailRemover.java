import java.io.*;
import java.util.*;

public class CSVDuplicateEmailRemover 
{
	public static void main(String[] args)
	{
		/*
			How to Use:
			
			When running CSVDuplicateEmailRemover, you will be prompted to enter "custom" or a CSV.
			Entering a CSV will produce a CSV with duplicate values removed, and a CSV of the values removed.
			Entering custom will prompt you to enter in a pair of booleans, one for Show Removed Values and one for Table Format.
			The default it true,false meaning it WILL Show Removed Values and WILL NOT present in Table Format.
			Standard format returns a CSV while table format shows each value with a line break in between.
			Uncommenting line 67
		*/
		
		Scanner Scanner = new Scanner(System.in);  // Create a Scanner object
	    System.out.println("Enter \"custom\" for Custom Layout or a CSV for a Standard Layout:");
	    String input = Scanner.nextLine();
	    
	    if(input.toLowerCase().equals("custom"))
	    {
	    	System.out.println("Enter \"[boolean],[boolean]\" for Show Removed Values and Table Format (Default is \"true,false\") respectively:");
	    	input = Scanner.nextLine();
		    CSV CSVInput = null;

		    if(input.toLowerCase().equals("true,false") || (input.toLowerCase().equals("true,true")) || (input.toLowerCase().equals("false,false")) || (input.toLowerCase().equals("false,true")))
		    {
			    System.out.println("Enter CSV");
			    CSVInput = new CSV(Scanner.nextLine());
		    }
	    
		    else
		    {
		    	System.out.println("Make sure there is no space between \"[boolean],[boolean]\"!");
		    }
		    
		    if(input.toLowerCase().equals("true,false"))
		    {
		    	System.out.println("\nDuplicate-Free CSV: " + CSVInput.toString(CSVInput.removeDuplicateValues(CSVInput.toArrayList(CSVInput.getStringCSV()))) + "\n");
				System.out.println("Removed Values: " + CSVInput.toString(CSVInput.getRemovedValuesArrayList()));
		    }
		    
		    else if(input.toLowerCase().equals("true,true"))
		    {
		    	System.out.println("\nDuplicate-Free CSV:\n" + CSVInput.toReadableString(CSVInput.removeDuplicateValues(CSVInput.toArrayList(CSVInput.getStringCSV()))) + "\n");
			    System.out.print("Removed Values:\n" + CSVInput.toReadableString(CSVInput.getRemovedValuesArrayList()));
		    }
		    
		    else if(input.toLowerCase().equals("false,false"))
		    {
		    	System.out.println("\nDuplicate-Free CSV: " + CSVInput.toString(CSVInput.removeDuplicateValues(CSVInput.toArrayList(CSVInput.getStringCSV()))) + "\n");
		    }
		    
		    else if(input.toLowerCase().equals("false,true"))
		    {
		    	System.out.println("\nDuplicate-Free CSV:\n" + CSVInput.toReadableString(CSVInput.removeDuplicateValues(CSVInput.toArrayList(CSVInput.getStringCSV()))) + "\n");

		    }
		    
	    }
	    else
	    {
	    	CSV CSVInput = new CSV(input);
	    	Uncomment for table with original values: System.out.println("Original Values:\n" + CSVInput.toReadableString(CSVInput.toArrayList(CSVInput.getStringCSV())));
	    	System.out.println("Duplicate-Free CSV:\n" + CSVInput.toString(CSVInput.removeDuplicateValues(CSVInput.toArrayList(CSVInput.getStringCSV()))) + "\n");
			System.out.println("Removed Values:\n" + CSVInput.toString(CSVInput.getRemovedValuesArrayList()) + "\n");
	    }	
	    
	    Scanner.close();
	}
}