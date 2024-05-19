import java.sql.*;
import java.util.Scanner;
public interface Batch_proPrepared {
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
                      
      
                    String sql= "insert into employees (id,name,job_title,salary) values(?,?,?,?);";  
                    PreparedStatement preparedStatement=con.prepareStatement(sql);
                    Scanner sc=new Scanner(System.in);
                    while (true) {
                         System.out.println("Employees Id:");
                         String id=sc.nextLine();
                         System.out.println("Employee name:");
                         String name=sc.nextLine();
                         System.out.println("Employee job title:");
                         String job_title=sc.nextLine();
                         System.out.println("Employees salary:");
                         int salary=sc.nextInt();
                         preparedStatement.setString(1,id);
                         preparedStatement.setString(2,name);
                         preparedStatement.setString(3,job_title);
                         preparedStatement.setInt(4,salary);
                         sc.nextLine();
                         preparedStatement.addBatch();
                          System.out.println("Add more values Y/N:");
                         String choice=sc.nextLine();
                         if(choice.toUpperCase().equals("N")){
                              break;
                         }
                    }
                       
                    int [] batch=preparedStatement.executeBatch();
                    con.commit();
                    System.out.println("Batch executed successfully!!!");

                    con.close();
                    System.out.println(" ");
                    System.out.println(" Connection closes Successfully!!");
    
        
                   }
                   catch(SQLException e){
                        System.out.println(e.getMessage());
                }
          
     }
}
