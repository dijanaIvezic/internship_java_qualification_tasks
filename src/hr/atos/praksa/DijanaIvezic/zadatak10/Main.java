package hr.atos.praksa.DijanaIvezic.zadatak10;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
	

	public static boolean fileContains(String file, String expression) {
		try(Scanner scan = new Scanner(new File(file))){
			String line;
			Pattern pattern = Pattern.compile(expression);
			Matcher matcher;
			while(scan.hasNext()) {
				line = scan.nextLine();
				matcher = pattern.matcher(line);
				if(matcher.find()) {
					return true;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
	

	public static void main(String[] args) {
		String directory;
		String expression;
		
		try {
			try (Scanner scan = new Scanner(System.in)) {
				System.out.println("Please enter a directory:");
				directory = scan.nextLine();
				System.out.println("Please enter an expression:");
				expression = scan.nextLine();
			}
			
			Path path = Paths.get(directory);
			if(!Files.exists(path)) {
				throw new Exception("Directory does not exist!");
			}
			
			List<String> files;
			
			try (Stream<Path> walk = Files.walk(path)) {
	            files = walk .filter(p -> !Files.isDirectory(p))
	                    .map(p -> p.toString().toLowerCase())
	                    .filter(f -> f.endsWith("txt") || f.endsWith("csv"))
	                    .collect(Collectors.toList());
	        }
											
			List<Boolean> containsPhrase = files.stream().map(file->fileContains(file, expression)).collect(Collectors.toList());
			
			if(containsPhrase.contains(true)) {
				System.out.println("Files that contain the expression:");
				for(int i = 0; i < files.size(); i++) {
					if(containsPhrase.get(i)) {
						System.out.println(files.get(i));
					}
				}
			}else {
				System.out.println("No files contain the expression.");
			}
			
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
