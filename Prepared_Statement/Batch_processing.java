import java.sql.*;
public class Batch_processing {

     public static void main(String[] args) {
          String url="jdbc:mysql://Localhost:3306/mysql";
          String username="root";
          String password="root";
          

          try{
               // Class.forName("com.mysql.jdbc.Diver");
               Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("Driver loaded successfully");
                }
                catch(ClassNotFoundException e){       
                    System.out.println(e.getMessage());
                        } 
        
                try{
                    //@SuppressWarnings("unused")
                    Connection con=DriverManager.getConnection(url,username,password);
                    System.out.println("connection estavlished Successfully !!");

                    con.setAutoCommit(false);

                    Statement stmt=con.createStatement();
                    
                    stmt.addBatch("insert into employees (id,name, job_title, salary) values('001','Vishal', 'Software tester',7500.00)");
                    stmt.addBatch("insert into employees (id,name, job_title, salary)values('003','vrun','HR requter',56000);");
                    stmt.addBatch("insert into employees (id,name, job_title, salary) values('002','Neelam', 'HR Manager',65000.00)");
  
                   int [] batchResult=stmt.executeBatch();
                    con.commit();
                    System.out.println("Batch Executed Successfully!!!");    

                    con.close();
                    System.out.println(" ");
                    System.out.println(" Connection closes Successfully!!");
    
        
                   }
                   catch(SQLException e){
                        System.out.println(e.getMessage());
                }
          
     }
     


}
