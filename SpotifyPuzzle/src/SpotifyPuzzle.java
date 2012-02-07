/*
 * Author: Karishma Bhatia
 * bkrish19@gmail.com
 * Objective: Find the best before date for a given set of integers
 */
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.GregorianCalendar;


public class SpotifyPuzzle {

	
    private static String dateString = "";
    private static int[] bestDateinput = {0,0,0};
    public static void main(String[] args) throws IOException {
    	int[] InputDateArray = new int[3];
		Scanner keyboard = new Scanner(System.in);
		String str = keyboard.nextLine();
		if (!str.isEmpty())
		{
			 dateString = str;
			 String delims = "[/]";
		     String[] tokens = str.split(delims);  
		     InputDateArray = ConvertToInt(tokens);
		  	 Arrays.sort(InputDateArray);
		  	 FindBestBefore(InputDateArray);    
		     PrintBestDate(bestDateinput);
		}
		else
		PrintIllegalDate(dateString);
	}
    
    /*
     * Converts the input given as string to an int array for manipulation
     */
    private static int[] ConvertToInt(String[] strArray)
    {
    	int[] dateArray = new int[3];
    	int num1=0, num2=0,num3=0;
    	try 
    	{          
	    	if ((strArray[0]!=null) && (strArray[1]!=null) && (!strArray[2].isEmpty()))
	    	{
	    	num1 = Integer.parseInt(strArray[0]);
	    	num2 = Integer.parseInt(strArray[1]);
	    	num3 = Integer.parseInt(strArray[2]);
	    	dateArray[0] = num1;
	    	dateArray[1] = num2;
	    	dateArray[2] = num3;
	    	}
    	}
        catch (ArrayIndexOutOfBoundsException e){
        	PrintIllegalDate(dateString);
        	}
        catch (NumberFormatException e){
        	PrintIllegalDate(dateString);
        	}
   
   	return dateArray;
    }

    /*
     *Takes in all the combinations and finds the legal date
     *Calls CheckDate method
     *Takes in sorted input integer array created from user input 
     */
    
    private static void FindBestBefore(int []inputDateArray)
    {
    	
    	int n1 = inputDateArray[0];
    	int n2 = inputDateArray[1];
    	int n3 = inputDateArray[2];
    	
    	// Check all permutations 
    	if (checkDate(n1,n2,n3))
    	{
    		int[]  bestDate = {n3,n2,n1};
    	    bestDateinput = bestDate;
    	}
    	else if (checkDate(n1,n3,n2))
    	{
    		int[]  bestDate = {n2,n3,n1};
    		bestDateinput = bestDate;
    	}
    	else if (checkDate(n2,n1,n3))
    	{
    		int[]  bestDate = {n3,n1,n2};
    		bestDateinput = bestDate;
    	}
    	else if (checkDate(n2,n3,n1))
    	{
    		int[]  bestDate = {n1,n3,n2};
    		bestDateinput = bestDate;
    	}
    	else if (checkDate(n3,n1,n2))
    	{
    		int[]  bestDate = {n2,n1,n3};
    		bestDateinput = bestDate;
    	}
    	else if (checkDate(n3,n2,n1))
    	{
    		int[]  bestDate = {n1,n2,n3};
    		bestDateinput = bestDate;
    	}
    	else
    	{
    		PrintIllegalDate(dateString);
    	}
    }
    
    /*
     *  To Print the illegal date message and exit the program
     */

    private static void PrintIllegalDate(String dateStringInput){
        System.out.println(dateStringInput + " is illegal");
        System.exit(0);
    }
    
    /*
     *  This method checks for legal date
     */
    private static boolean checkDate(int year, int month, int date)
    {
    	boolean dateCheck = true;
    	boolean isLeapYear = false;
    	
    	 if (year > 1000 && year < 2000){
             return dateCheck = false;
         }
         if (year < 1000){
             year+=2000;
         }
         if (year < 0){
             return dateCheck = false;
         }
         if (Isleap(year)) {
        	 isLeapYear = true; 
             }
         if (month < 1 || month > 12){
             return dateCheck = false;
         }
         if (month==1 || month ==3 || month== 5 || month==7 || month ==8|| month ==10 || month ==12)
         {
                 if (date > 31 || date < 1){
                     return dateCheck = false;}
         }
         if (month==4 || month ==6 || month== 9 || month== 11)
         {
                 if (date > 30 || date < 1){
                     return dateCheck = false;}
         }
         if (month == 2)
         {
            if(isLeapYear)
            {
            	if (date>29 || date <1)
            	{
            		dateCheck = false;
            	}
            }
            else
            {
            	if (date>28 || date <1)
            	{
            		dateCheck = false;
            	}
            }
         }
                
    	return dateCheck;
    }
    private static boolean Isleap(int year)
	{ 
    	boolean isLeap = true;
    	GregorianCalendar cal = new GregorianCalendar();
    	  if(cal.isLeapYear(year))
    	  {
    		  isLeap = true;
    	  }
    	  else 
    	  {
    		  isLeap = false;
    	  }
		return isLeap;
	}

    private static void PrintBestDate(int[] date)
    {
    	
     String year ="";
     String month ="";
     String day = "";
     if (date[2] < 1000){
         date[2]+=2000; 
     }
     year = Integer.toString(date[2]);
     if (date[1] < 10){ 
         month = "0" + date[1];}
     else{
         month = Integer.toString(date[1]);
     }
     if (date[0] < 10){
         day = "0" + date[0];
     }else{
         day = Integer.toString(date[0]);
     }
     
     System.out.println(year + "-" + month +"-" + day);
    }

    
    
}
