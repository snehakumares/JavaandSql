package net.sqlitetutorial;

import java.sql.*;
import java.util.Scanner;
public class Movies{
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:D:/Users/sneha_jol0o1o/Downloads/sqlite/db/Sneha_trial.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    

    public void selectAll(){
        String sql = "SELECT * FROM Movies";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            while (rs.next()) {
                System.out.println(rs.getString("Movie") +  "\t" + 
                                   rs.getString("Leadactor") + "\t" +
				   rs.getString("Leadactress") + "\t" +
				   rs.getString("Year") + "\t" +
                                   rs.getString("Director"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void selectYear(){
        String sql = "SELECT movie,year FROM Movies";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            while (rs.next()) {
                System.out.println(rs.getString("Movie") +  "\t" + 
                                   rs.getString("Year"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

      public void insert() {
        String movie,actor,actress,year,director;
	Scanner sc = new Scanner(System.in); 
	System.out.println("\nEnter the details below");
	System.out.print("Movie : ");
        movie = sc.nextLine();
	System.out.print("Lead Actor : ");
        actor = sc.nextLine();
	System.out.print("Lead actress : ");
        actress = sc.nextLine();
	System.out.print("Year : ");
        year= sc.nextLine();
	System.out.print("Director : ");
        director= sc.nextLine();
        String sql = "INSERT INTO movies(movie,leadactor,leadactress,year,director) VALUES(?,?,?,?,?)";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, movie);
            pstmt.setString(2, actor);
            pstmt.setString(3, actress);
            pstmt.setString(4, year);
            pstmt.setString(5, director);	      
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) {
        Movies data = new Movies();
        int  choice = 0;
	Scanner sc= new Scanner(System.in); 
	while(choice!=4){
	    System.out.println("\n1.View data \n2.View Movie and Year \n3.Insert new \n4.Exit");
	    choice = sc.nextInt();
	    switch(choice){
		case 1: data.selectAll();break;
	        case 2: data.selectYear();break;
		case 3: data.insert();break;
	        case 4: System.out.println("Exiting");break;
	    }
	}
    }

}