package hr.atos.praksa.DijanaIvezic.zadatak06;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("Enter a name:");
		
		String name = "";
		
		try (Scanner scan = new Scanner(System.in)) {
			name = scan.nextLine();
		}
		
		if(name.isBlank()){
			name = "Dijana";
		}
		
		String separator = "-------------------------------";
		String title = ": : :  TABLICA  MNOZENJA  : : :";
		String header = "* |  1  2  3  4  5  6  7  8  9";
		
		int totalWidth = separator.length();
		String rightSide = "by " + name;
		int rightLength = rightSide.length();
		int difference = totalWidth-rightLength;
		String additional = (difference%2==0)? "" : ":"; //another ":" without " " if space left has odd number of characters
		String byName = ": ".repeat((difference)/2) + additional + rightSide;
		
		List<String> headerLines = Arrays.asList(separator, title, separator, header, separator);
		List<String> footerLines = Arrays.asList(separator, byName, separator);
				
		headerLines.stream().forEach(s -> System.out.println(s));
		
		for(int i=1; i < 10; i++) {
			System.out.print(String.format("%-2d|", i));
			for(int j=1; j < 10; j++) {
				System.out.print(String.format("%3d", i*j));
			}
			System.out.println();
		}
		
		footerLines.stream().forEach(s -> System.out.println(s));
	}	

}
