package hr.atos.praksa.DijanaIvezic.zadatak11;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
	
	public static String getTranslatedLanguages(List<ResourceBundle> languages, String language_name) {
		String translated = languages.stream().map(l->l.getString(language_name)).collect(Collectors.joining("/"));
		return translated;
	}

	public static void main(String[] args) {
		try (Scanner scan = new Scanner(System.in)) {
			String separator = "-".repeat(100);
			
			ResourceBundle english = ResourceBundle.getBundle("bundle");
			ResourceBundle german = ResourceBundle.getBundle("bundle", new Locale("de", "DE"));
			ResourceBundle french = ResourceBundle.getBundle("bundle", new Locale("fr", "FR"));
			ResourceBundle italian = ResourceBundle.getBundle("bundle", new Locale("it", "IT"));
			
			Map<String,ResourceBundle> choiceLanguage = new HashMap<String,ResourceBundle>();
			choiceLanguage.put("a", english);
			choiceLanguage.put("b", german);
			choiceLanguage.put("c", french);
			choiceLanguage.put("d", italian);
			
			List<ResourceBundle> languages = Arrays.asList(english, german, french, italian);
			languages.stream().forEach(l->System.out.println(l.getString("language_input")));
			System.out.println(separator);
			
			List<String> languageNames = Arrays.asList("a)English", "b)German", "c)French", "d)Italian");
			languageNames.stream().forEach(l->System.out.println(l.substring(0,2) + getTranslatedLanguages(languages,l.substring(2))));
						
			String choice = scan.nextLine().toLowerCase();
			if(!Arrays.asList("a", "b", "c", "d").contains(choice)) {
				throw new Exception("a,b,c or d");
			}
			System.out.println(separator);
			
			ResourceBundle language = choiceLanguage.get(choice);
			System.out.println(language.getString("introduction"));
			System.out.println(language.getString("name_input"));
			
			String name = scan.nextLine();
			System.out.println(separator);
			
			System.out.println(language.getString("input"));
			System.out.println("a)"+language.getString("feelings"));
			System.out.println("b)"+language.getString("facts"));
			
			choice = scan.nextLine().toLowerCase();
			if(!Arrays.asList("a", "b").contains(choice)) {
				throw new Exception("a or b");
			}
			System.out.println(separator);
			
			if(choice.equals("a")) {
				System.out.println(language.getString("feeling_input").replace("+", name));
				List<String> feelings = Arrays.asList("a)great", "b)good", "c)okay");
				feelings.stream().forEach(l->System.out.println(l.substring(0,2) + language.getString(l.substring(2))));
				choice = scan.nextLine().toLowerCase();
				if(!Arrays.asList("a", "b", "c", "d").contains(choice)) {
					throw new Exception("a,b,c or d");
				}
				System.out.println(language.getString("glad_feelings"));
				
			}else {
				System.out.println(language.getString("facts_fact"));
				System.out.println(language.getString("number_pick"));
				choice = scan.nextLine();
				System.out.println(language.getString("thank_you_pick"));
				System.out.println(language.getString("the_fact"));
			}
			System.out.println(separator);
			System.out.println(language.getString("farewell"));
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

}
