package hr.atos.praksa.DijanaIvezic.zadatak09;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		String input;
		
		try (Scanner scan = new Scanner(System.in)) {
			System.out.println("Luka, please enter your oven costs:");
			input = scan.nextLine();
		}
		try {	
			List<String> items = Arrays.asList(input.split(","));
			
			List<Integer> costs = items.stream().map(Integer::parseInt).collect(Collectors.toList());
			int n = costs.size();
			
			Predicate<Integer> withinRange = i -> (i < 0 || i > 4500);
			if(n != 12 || costs.stream().allMatch(withinRange)) {
				throw new Exception();
			}
			
			//put costs in their corresponding bins
			//if we would want, for an example, to have range of values from 1500 to 2500 show as 2000, we would write ((nn+500)/1000)*1000
			//since graph in the task shows a value at 4500, we opted for increments of 500
			final List<Integer> costsBin = costs.stream().map(nn->((nn)/500)*500).collect(Collectors.toList());
			
			Consumer<Integer> makeRow = nn->{
				String firstPart = String.format("%4dkn - |", nn);
				if(nn%1000 != 0) {
					firstPart = " ".repeat(firstPart.length()-1) + "|";
				}
				String secondPart = costsBin.stream().map(x-> (x.equals(nn)?" x":"  ")).collect(Collectors.joining(" "));
				System.out.print(firstPart);
				System.out.println(secondPart);
			};
			
			List<Integer> bins = Arrays.asList(5000,4500,4000,3500,3000,2500,2000,1500,1000,500,0);
			bins.stream().forEach(makeRow);
			System.out.println("          -- -- -- -- -- -- -- -- -- -- -- --");
			System.out.println("           1  2  3  4  5  6  7  8  9 10 11 12");
			
		}catch(Exception e) {
			System.out.println("Please enter costs as comma seperated values. There must be 12 values, one for each month. The values must be numbers greater than 0 and less than 4500.");
		}

	}

}
