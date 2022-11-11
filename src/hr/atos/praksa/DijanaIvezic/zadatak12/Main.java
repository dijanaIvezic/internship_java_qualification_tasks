package hr.atos.praksa.DijanaIvezic.zadatak12;

import java.io.File;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		try {
			String filename;
			TreeMap<String, Integer> words = new TreeMap<String,Integer>();
			
			System.out.println("Enter the filename:");
			
			try(Scanner scan = new Scanner(System.in)){
				filename = scan.nextLine();
			}
			File file = new File(filename);
			if(!file.exists()) {
				throw new Exception("The file does not exist!");
			}
			try(Scanner scan = new Scanner(file)){
				String line;
				Pattern pattern = Pattern.compile("\\w+");
				Matcher matcher;
				Stream<String> stream;
				while(scan.hasNext()) {
					line = scan.nextLine();
					matcher = pattern.matcher(line);
					stream = matcher.results().map(MatchResult::group).map(String::toLowerCase);
					stream.forEach(s->words.put(s, words.getOrDefault(s, 0) + 1));
				}
			}
			
			System.out.println(String.format("U datoteci %s se nalaze sljedece rijeci:", file.getName()));
			System.out.println("------------------------");
			System.out.println("Rijec (broj ponavljanja)");
			System.out.println("------------------------");
			for(Map.Entry<String, Integer> entry: words.entrySet()) {
				System.out.println(String.format("%s (%d)", entry.getKey(), entry.getValue()));
			}
			System.out.println("------------------------");
					
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

	}

}
