package hr.atos.praksa.DijanaIvezic.zadatak15;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
	
	public static String init() {
		String sql;
		sql = "CREATE TABLE IF NOT EXISTS Employees(\r\n"
        		+ "  name TEXT,\r\n"
        		+ "  last_name TEXT,\r\n"
        		+ "  work_place TEXT,\r\n"
        		+ "  OIB INTEGER UNIQUE);";
        
        sql += "CREATE TABLE IF NOT EXISTS Tasks(\r\n"
        		+ "  id INTEGER UNIQUE,\r\n"
        		+ "  name TEXT,\r\n"
        		+ "  description TEXT,\r\n"
        		+ "  type TEXT,\r\n"
        		+ "  status TEXT,\r\n"
        		+ "  complexity INTEGER,\r\n"
        		+ "  time_spent INTEGER,\r\n"
        		+ "  date_start TEXT,\r\n"
        		+ "  date_end TEXT\r\n"
        		+ ");";
        
        sql += "INSERT OR IGNORE INTO Employees VALUES(\"Main\", \"Admin\", \"admin\", 0);"; //needed so we can start using our program and enter others
		return sql;
	}
	
	public static int getOib(Scanner scan) throws Exception {
		System.out.println("Welcome. Please enter your OIB:");
		int id;
		String input = scan.nextLine();
    	try {
    	id = Integer.parseInt(input);
    	if(id < 0) {
    		throw new Exception();
    	}
    	}catch(Exception e) {
    		throw new Exception("Enter an integer greater or equal to 0.");
    	}
		return id;
	}
	
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		
        try { 
        	String databaseName = "database.db";
        	String url = "jdbc:sqlite:databases/" + databaseName;
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();
            String sql;
            sql = init();
            
            stmt.executeUpdate(sql);
            
            int oib;
            try(Scanner scan = new Scanner(System.in)){
            	oib = getOib(scan);
            	
            	Employee employee = Employee.make(stmt, oib);
            	employee.greet();
            	employee.makeChoice(stmt, scan);
            }
   
        } catch (Exception e) {  
            System.out.println(e.getMessage());  
        } finally {
        	try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
    }

}


