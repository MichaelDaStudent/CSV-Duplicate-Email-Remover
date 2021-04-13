# CSV-Duplicate-Email-Remover
CSV Duplicate Email Remover can remove any duplicates in a CSV of any type, and return the resulting CSV and removed values in both a CSV and a table.

How to Use:

When running CSVDuplicateEmailRemover, you will be prompted to enter "custom" or a CSV.
Entering a CSV will produce a CSV with duplicate values removed, and a CSV of the values removed.
Entering custom will prompt you to enter in a pair of booleans, one for Show Removed Values and one for Table Format.
The default it true,false meaning it WILL Show Removed Values and WILL NOT present in Table Format.
Standard format returns a CSV while table format shows each value with a line break in between.
Uncommenting line 67 (CSVDuplicateEmailRemover.java:67) will allow the option to return the original CSV in Table Format.
