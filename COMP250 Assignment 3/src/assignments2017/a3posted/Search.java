package assignments2017.a3posted;

import java.util.ArrayList;
import java.util.Scanner;

public class Search {

	public static void document(ArrayList<String> words){
 		
    	WordTree fileWordTree = new WordTree(); 
    	fileWordTree.loadWords(words);
 
    	System.out.println("The document contains " + words.size() + " words.");
    	
    	Scanner scanInput = new Scanner(System.in);
    	System.out.print("Search Document: ");

    	String[] search = scanInput.nextLine().split(" ");
    	
    	System.out.println();
    	System.out.println(" --- Contains: ---");
    	
    	for (String word: search){
        	boolean hasWord = fileWordTree.contains(word);
        	
    		System.out.println("Document contains '" + word + "': " + hasWord);		
    	}
    	
    	System.out.println();
    	System.out.println(" --- Prefix: ---");
    	
    	for (String word: search){
    		boolean hasWord = fileWordTree.contains(word);
    		
    		if (!hasWord){
    			System.out.println("Longest prefix of '" + word + "': " + fileWordTree.getPrefix(word));
    		} else {
    			System.out.println("Longest prefix is '" + word + "'.");
    		}
    	}
    	
    	System.out.println();
    	System.out.println(" --- Auto-complete: ---");
    	
    	for (String word: search){
    		System.out.println("Auto-complete '" + word + "': " + fileWordTree.getListPrefixMatches(word)); 
    	}
    			
		scanInput.close();

		System.out.println();
		System.out.println();
	}
	
}
