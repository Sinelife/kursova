package main;

import java.util.Scanner;

public class Tse 
{
	public static void main(String args[])
	{
		//String s = "diod, resistor, indicator";
		//s = s.replace("resistorj, ", "");
		//System.out.println(s);
		
		
		
		String S = "diod, resistor, indicator";
		String s = "dio, ";
		S.toLowerCase().contains(s.toLowerCase());
		System.out.println(S.toLowerCase().contains(s.toLowerCase()));
    }
	
	
	public static String deleteWord(String text, String word)
	{
		String regex = "\\b" + word + "\\b";
		text = text.replaceAll(regex, "");
		
		return text;
	}
}

