package hr.atos.praksa.DijanaIvezic.zadatak15;

import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;

public class Superuser extends User {
	public Superuser(String name, String lastname) {
		super(name, lastname);
		this.name = name;
		this.lastname = lastname;
		}
	
	protected class TaskInput {
		int id;
		String name;
		String description;
		String type;
		String status;
		int complexity;
		int timeSpent;
		String dateStart;
		String dateEnd;
		
		protected TaskInput(int id, String name, String description, String type, String status, int complexity, int timeSpent, String dateStart, String dateEnd) {
			this.id = id;
			this.name = name;
			this.description = description;
			this.type = type;
			this.status = status;
			this.complexity = complexity;
			this.timeSpent = timeSpent;
			this.dateStart = dateStart;
			this.dateEnd = dateEnd;
			
		}
	}
	
	protected class EmployeeInput{
		int oib;
		String name;
		String lastname;
		String workplace;
		
		protected EmployeeInput(int oib, String name, String lastname, String workplace) {
			this.oib = oib;
			this.name = name;
			this.lastname = lastname;
			this.workplace = workplace;
		}
		
	}
	
	protected int getId(Statement stmt, Scanner scan, Boolean unique) throws Exception{
		System.out.println("\nEnter task ID:");
		String input = scan.nextLine();
		String sql;
		int id;
		try {
			id = Integer.parseInt(input);
			sql = String.format("SELECT * FROM Tasks WHERE id=%d", id);
			ResultSet result = stmt.executeQuery(sql);
			if(id<=0) {
				throw new Exception("ID must be an integer greater than 0.");
			}
			if(unique && result.next()) {
				throw new Exception("ID must be unique.");
			}
			if(!unique && !result.next()) {
				throw new Exception("That task does not exist");
			}
			
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		return id;
	}
	
	protected TaskInput getTaskInput(Statement stmt, Scanner scan, Boolean unique) throws Exception {
		int id = getId(stmt, scan, unique);
		if(!unique) {
			System.out.println("You are now editing values for the existing id.");
		}
		
		System.out.println("Enter task name:");
		String name = scan.nextLine();
		
		System.out.println("Enter task description:");
		String desc = scan.nextLine();
		
		System.out.println("Enter task type (bug or task):");
		String type = scan.nextLine().toLowerCase();
		if(!Arrays.asList("bug", "task").contains(type)) {
			throw new Exception("Task type must be \"bug\" or \"task\".");
		}
		
		System.out.println("Enter task status (open, closed, pending):");
		String status = scan.nextLine().toLowerCase();
		if(!Arrays.asList("open", "closed", "pending").contains(status)) {
			throw new Exception("Task type must be \"open\",  \"closed\" or \"pending\".");
		}
		
		System.out.println("Enter task complexity:");
		 String input = scan.nextLine();
		int complexity;
		try {
			complexity = Integer.parseInt(input);
			if(complexity <= 0) {
				throw new Exception();
			}
			
		}catch(Exception e) {
			throw new Exception("Complexity must be an integer greater than 0.");
		}
		
		System.out.println("Enter time spent on task:");
		input = scan.nextLine();
		int timeSpent;
		try {
			timeSpent = Integer.parseInt(input);
			if(timeSpent <= 0) {
				throw new Exception();
			}
			
		}catch(Exception e) {
			throw new Exception("Time spent must be an integer greater than 0.");
		}
		
		String startDate, endDate;
		
		try {
		System.out.println("Enter starting date:");
		input = scan.nextLine();
		LocalDate startingDate = LocalDate.parse(input);
		
		System.out.println("Enter ending date:");
		input = scan.nextLine();
		LocalDate endingDate = LocalDate.parse(input);
		
		if(endingDate.isBefore(startingDate)) {
			throw new Exception();
		}
		
		startDate = startingDate.toString();
		endDate = endingDate.toString();
		
		}catch(Exception e) {
			throw new Exception("Date must be in YYYY-MM-DD format. Ending date must come after starting date.");
		}
		return new TaskInput(id, name, desc, type, status, complexity, timeSpent, startDate, endDate);
	}
	
	protected int getOib(Statement stmt, Scanner scan, Boolean unique) throws Exception {
		System.out.println("\nEnter employee's OIB:");
		String input = scan.nextLine();
		String sql;
		int oib;
		try {
			oib = Integer.parseInt(input);
			sql = String.format("SELECT * FROM Employees WHERE oib=%d", oib);
			ResultSet result = stmt.executeQuery(sql);
			if(oib<=0) {
				throw new Exception("OIB must be an integer greater than 0.");
			}
			if(unique && result.next()) {
				throw new Exception("OIB must be unique.");
			}
			if(!unique && !result.next()) {
				throw new Exception("That employee does not exist.");
			}
			
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		return oib;
	}
	
	protected EmployeeInput getEmployeeInput(Statement stmt, Scanner scan, Boolean unique) throws Exception{
		int oib = getOib(stmt, scan, unique);
		
		if(!unique) {
			System.out.println("You are now editing values for the existing OIB.");
		}
		
		System.out.println("Enter employee's name:");
		String name = scan.nextLine();
		
		System.out.println("Enter employee's last name:");
		String lastName = scan.nextLine();
		
		System.out.println("Enter employee's work place (user,superuser or admin):");
		String workplace = scan.nextLine().toLowerCase();
		if(!Arrays.asList("user", "superuser", "admin").contains(workplace)) {
			throw new Exception("Work place must be \"user\",  \"superuser\" or \"admin\".");
		}
		return new EmployeeInput(oib, name, lastName, workplace);
	}
	
	@Override
	void createTask(Statement stmt, Scanner scan) throws Exception {
		Boolean unique = true; //we need task ID to be unique upon creation
		TaskInput taskInput = getTaskInput(stmt, scan, unique);
		
		int id = taskInput.id;
		String name = taskInput.name;
		String desc = taskInput.description;
		String type = taskInput.type;
		String status = taskInput.status;
		int complexity = taskInput.complexity;
		int timeSpent = taskInput.timeSpent;
		String startDate = taskInput.dateStart;
		String endDate = taskInput.dateEnd;
		
		
		String sql = String.format("INSERT INTO Tasks VALUES(%d, \"%s\", \"%s\", \"%s\", \"%s\", %d, %d, \"%s\", \"%s\");",
				id, name, desc, type, status, complexity, timeSpent, startDate, endDate);
		
		stmt.execute(sql);
		System.out.println("Task added!");		
	}

	@Override
	void createEmployee(Statement stmt, Scanner scan) throws Exception{
		Boolean unique = true; //we need OIB to be unique upon creation
		EmployeeInput employeeInput = getEmployeeInput(stmt, scan, unique);
		int oib = employeeInput.oib;
		String name = employeeInput.name;
		String lastname = employeeInput.lastname;
		String workplace = employeeInput.workplace;
		
		String sql = String.format("INSERT INTO Employees VALUES(\"%s\",\"%s\",\"%s\",%d)",
				name, lastname, workplace, oib);
		
		stmt.execute(sql);
		System.out.println("Employee added!");
	}
	
	@Override
	void createReport(Statement stmt, Scanner scan) throws Exception{
		System.out.println("\nPick what type of report you would like to create:");
		System.out.println("1) How many workers are there at each workplace\n"
				+ "2) Time spent per task\n"
				+ "3) The task which is open the longest");
		
		String input = scan.nextLine();
		String sql;
		int id, timeSpent, choice;
		Long mostMonths;
		ResultSet result;
		LocalDate start, end;
		Period period;
		TreeMap<Long, Integer> timeTask = new TreeMap<Long, Integer>();
		Boolean hasNone = true;
		
		try {
			choice = Integer.parseInt(input);
			if(choice < 1 || choice > 3) {
				throw new Exception();
			}
		}catch(Exception e) {
			throw new Exception("Input must be integer between 1 and 3.");
		}
		
		
		switch(choice){
			case 1:
				sql = "SELECT COUNT(work_place)\r\n"
						+ "FROM Employees\r\n"
						+ "where work_place=\"%s\";";
				result = stmt.executeQuery(String.format(sql, "user"));
				System.out.println(String.format("Number of user employees = %d", result.getInt(1)));
				result = stmt.executeQuery(String.format(sql, "superuser"));
				System.out.println(String.format("Number of superuser employees = %d", result.getInt(1)));
				result = stmt.executeQuery(String.format(sql, "admin"));
				System.out.println(String.format("Number of admin employees = %d", result.getInt(1)));
				break;
			case 2:
				sql = "SELECT id, time_spent FROM Tasks;";
				result = stmt.executeQuery(sql);
				while(result.next()) {
					hasNone = false;
					id = result.getInt(1);
					timeSpent = result.getInt(2);
					System.out.println(String.format("On task %d, %d units of time have been spent.", id, timeSpent));
				}
				if(hasNone) {
					throw new Exception("There are no tasks.");
				}
				break;
			case 3:
				sql = "SELECT id, date_start, date_end FROM Tasks WHERE status=\"open\";";
				result = stmt.executeQuery(sql);
				while(result.next()) {
					hasNone = false;
					id = result.getInt(1);
					start = LocalDate.parse(result.getString(2));
					end = LocalDate.parse(result.getString(3));
					period = Period.between(start, end);
					timeTask.put(period.toTotalMonths(), id);
				}
				if(hasNone) {
					throw new Exception("There are no open tasks.");
				}
				
				mostMonths = timeTask.lastKey();
				if(mostMonths > 0) {
					System.out.println(String.format("Task %d is open the longest. %d months.", timeTask.get(mostMonths), mostMonths));
				}else {
					System.out.println("None of the open tasks lasted more than a month.");
				}
				break;
		}
		
	}
}
