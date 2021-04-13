import java.util.*;

public class CSV
{
	// Instance variables (accessible with getters): CSV as string, ArrayList of the no duplicate CSV, and an ArrayList of the removed values.
	private String StringCSV;
	private ArrayList<String> removedValuesArrayListCSV;
	private ArrayList<String> removedValuesArrayList;
	
	// Constructor takes in CSV.
	public CSV(String StringCSV)
	{
		this.StringCSV = StringCSV;
		this.removedValuesArrayList = new ArrayList<String>();
		this.removedValuesArrayListCSV = new ArrayList<String>();
	}
	
	// Converts a CSV into an ArrayList.
	public ArrayList<String> toArrayList(String StringCSV)
	{
		return new ArrayList<String>(Arrays.asList(this.StringCSV.split(",")));
	}
	
	// Saves and returns a CSV as an ArrayList with no duplicates and saves an ArrayList with the removed values.
	public ArrayList<String> removeDuplicateValues(ArrayList<String> arrayListCSV)
	{
		ArrayList<String> removedValuesArrayListCSV = new ArrayList<String>();
		ArrayList<String> removedValuesArrayList = new ArrayList<String>();
		
		for(int i = 0; i < arrayListCSV.size(); i ++)
		{
			removedValuesArrayListCSV.add(arrayListCSV.get(i));
		}

		for(int i = 0; i < removedValuesArrayListCSV.size(); i ++)
		{
			for(int j = removedValuesArrayListCSV.size() - 1; j >= 0; j --)
			{
				if(removedValuesArrayListCSV.get(i).equals(removedValuesArrayListCSV.get(j)) && i != j)
				{
					removedValuesArrayList.add(removedValuesArrayListCSV.get(j));
					removedValuesArrayListCSV.remove(j);
					i --;
					break;
				}
			}
		}
		
		this.removedValuesArrayList = removedValuesArrayList;
		this.removedValuesArrayListCSV = removedValuesArrayListCSV;
		return removedValuesArrayListCSV;
	}
	
	// Converts any ArrayList to a CSV string.
	public String toString(ArrayList<String> removedValuesArrayList)
	{
		String string = "";
		
		for(int i = 0; i < removedValuesArrayList.size(); i ++)
		{
			if(i < removedValuesArrayList.size() - 1)
			{
				string += removedValuesArrayList.get(i) + ",";
			}
			else
			{
				string += removedValuesArrayList.get(i);
			}
		}
		
		return string;
	}
	
	// Separates any ArrayList into entries separated by a line break.
	public String toReadableString(ArrayList<String> arr)
	{
		String string = "";
		
		for(int i = 0; i < arr.size(); i ++)
		{
			if(i < arr.size() - 1)
			{
				string += arr.get(i) + "\n";
			}
			else
			{
				string += arr.get(i);
			}
		}
		return string;
	}
	
	@Override
	// Converts CSV object to string with original CSV, no duplicate CSV, and removed values, each separated by a line break.
	public String toString()
	{
		String CSV
		=
			"Original CSV: " + StringCSV + "\n" +
			"No Duplicate CSV: " + this.toString(removedValuesArrayListCSV) + "\n" +
			"Removed Values: " + this.toString(removedValuesArrayList)
		;
		
		return CSV;
	}
	
	public String getStringCSV()
	{
		return this.StringCSV;
	} 
	
	public ArrayList<String> getRemovedValuesArrayList()
	{
		return this.removedValuesArrayList;
	} 
	
	public ArrayList<String> getRemovedValuesArrayListCSV()
	{
		return this.removedValuesArrayListCSV;
	} 
}