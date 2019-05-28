import java.sql.*;


public class JDBC

{

  public void displayData(String urlStr,String username,String password)
  {

		  try
		  {

			  Class.forName ("com.mysql.jdbc.Driver").newInstance ();
			  
			  Connection conn = DriverManager.getConnection(urlStr,username,password);
			  System.out.println ("Connected to the MySQL database\n");



          
          
          // problem 1
          
         /////////////////////////////////////////////// 
      			  System.out.println ("Problem 1\n\n");



           //for problem 1, the view "Sculpture_view" works best to get info wanted, except for attribute "material" as we are only searching for "stone"
			  Statement stmt1 = conn.createStatement(); 
			  ResultSet rs1=null;
			  rs1=stmt1.executeQuery("select id_no, year, title, description, artist_name, weight, height from Sculpture_view where material = \"stone\"");
           System.out.println("Printing from Sculpture_view where material made from stone \n");
 
			  while (rs1.next())
			  {
				  System.out.println("id_no: "+rs1.getString(1));
              
				  System.out.println("year: "+rs1.getString(2));

              System.out.println("title: "+rs1.getString(3));

				  System.out.println("description: "+rs1.getString(4));
              				  
              System.out.println("artist_name: "+rs1.getString(5));
              				 
              System.out.println("weight: "+rs1.getString(6));
              
              System.out.println("height: "+rs1.getString(7)+"\n\n");



				
			  }
			  stmt1.close();
           
           
           
           //problem 2
////////////////////////////////////////// 

//this problem is exactly like problem 1, but all tuples are wanted
// search criteria artist_no_of_sculpture

			  System.out.println ("Problem 2\n\n");




 Statement stmt2 = conn.createStatement(); 
			  ResultSet rs2=null;
			  rs2=stmt2.executeQuery("select * from artist_no_of_sculpture");
           System.out.println("Printing from artist_no_of_sculpture \n");
 
			  while (rs2.next())
			  {
				  System.out.println("artist_name: "+rs2.getString(1));
              
              
              System.out.println("no_of_sculptures: "+rs2.getString(2)+"\n\n");



				
			  }
			  stmt2.close();







//problem 3
///////////////////////////

//first parameter is out (must register), 2nd parameter is in (E1)
			  System.out.println ("Problem 3\n\n\n");

CallableStatement cstmt = conn.prepareCall("{? = call No_of_painting_exhibited(?)}"); //call a function
			            
           cstmt.registerOutParameter(1,Types.INTEGER);

           cstmt.setString(2,"E1");

			  cstmt.execute();
			  System.out.println ("exhibited paintings of E1:   "+ cstmt.getInt(1));
			  cstmt.close();
			 
			 /* System.out.println("call procedure");
			  CallableStatement cstmt2 = conn.prepareCall("call useloop()"); //call a procedure
			  cstmt2.execute();
			  cstmt2.close();

			  System.out.println("run preparedStatement");
			  PreparedStatement pstmt= 
			  conn.prepareStatement("INSERT INTO Project Values(?, ?, ?, ?)");
			  pstmt.setString(1,"sac"); 
			  pstmt.setInt(2,10);
			  pstmt.setString(3,"RVR");
			  pstmt.setInt(4,2);
			  pstmt.executeUpdate();
			  pstmt.close();*/



//ending connection to jdbc
			  conn.close();

			  System.out.println("Disconnected");


		  }
		  catch (Exception e)
		  {
			  e.printStackTrace();
		  }

  }
  
  
  //main file run
  
////////////////////////////////////////////////////////////////////////////////////
  public static void main (String args [])
  {
		String url= "jdbc:mysql://athena.ecs.csus.edu/cs17411";
		String username= "cs17411";
		String password = "rxkfrpce";

		JDBC example = new JDBC();
		example.displayData(url,username,password);

  }

}
