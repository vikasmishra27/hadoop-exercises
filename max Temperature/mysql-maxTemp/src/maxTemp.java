import java.sql.*;

/*
 *  Class which connects to mysql and executes the query 
*/

public class maxTemp{
        public static void main(String args[]){
        try{
                //Including jdbc driver for mySql.
        	Class.forName("com.mysql.jdbc.Driver");
        	//Connection String for Database.
        	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/521?autoReconnect=true&useSSL=false","root","itmd521");
                Statement stmt=con.createStatement();
                // Execute select query to pull maximum temperature
                ResultSet rs=stmt.executeQuery("select distinct(OBSE_DT),air_temp from records where air_temp = "
                		+ "(select max(air_temp) from records where air_temp < 9999)");
                //Print the output on screen
                while(rs.next())
                	System.out.println(rs.getInt(1)+" " + rs.getInt(2));
                con.close();
           }
        catch(Exception e){
        	   System.out.println(e);
        	   }
        }
}
