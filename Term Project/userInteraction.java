import java.util.*;
import java.io.*;

public class userInteraction{
	ArrayList<File> dataFiles = new ArrayList<File>;

	public static void main(String[] args){
		boolean moreFiles = true;
		Scanner scanner = new Scanner(System.in);
		while(moreFiles){
			System.out.print("What file would you like to use as data?   ");
			File file = new File(scanner.nextLine());
			if(file.exists()){
				dataFiles.append(file);
				System.out.print("\nWould you like to input another file?(y/n)   ");
				String response = scanner.nextLine();
				if(response == "n") moreFiles = false; 
			} else {
				System.out.println("That file does not exist.")
				continue;
			}
		}

		//Create the inverted indices for the files uploaded by the user
		loadInvertedIndices();

		boolean validSelection = false;
		while(!validSelection){
			System.out.println("\nPlease select one of the following:");
			System.out.println("1. Search for term");
			System.out.println("2. Top-N Results");
			System.out.print("Selection: ");
			String selection = scanner.nextLine();

			if(selection == "1"){
				searchTerm();
				validSelection = true;
			} else if(selection == "2") {
				topN();
				validSelection = true;
			} else {
				System.out.println("You entered an invalid input. Please enter 1 or 2.");
			}
		}

	}

	public static void loadInvertedIndices(){
		return;
	}

	public static void searchTerm(){
		boolean validSelection = false;
		while(!validSelection){
			System.out.print("\nWhat term would you like to search for? ");
			String term = scanner.nextLine();
			if(term == "" || term.contains(" ")){
				System.out.println("\nYou entered an invalid term.");
			} else {
				//execute search term
			}
		}
		return;
	}

	public static void topN(){
		boolean validSelection = false;
		while(!validSelection){
			System.out.print("\nWhat N values terms would you like? ");
			String n = scanner.nextLine();
			if(n <=0 ){
				System.out.println("\nYou entered an invalid N.");
			} else {
				//execute search term
			}
		}
		return;
	}


}