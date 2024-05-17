import java.sql.*;
import java.util.Scanner;
public class InsertPreStatement {
     public static void main(String[] args) throws ClassNotFoundException  {
          String url="jdbc:mysql://Localhost:3306/mysql";
          String username="root";
          String password="root";
           String sql= "insert into employees (id,name,job_title,salary) values(? , ? , ?, ?);";    
            
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
                 
                 PreparedStatement preparedStatement=con.prepareStatement(sql);

                 Scanner sc=new Scanner(System.in);
                System.out.println("Enter employees Id");
                int id=sc.nextInt();
                sc.nextLine();
                System.out.println("Enter employee Name :");
                String name=sc.nextLine();
                System.out.println("Enter job Title :");
                String job=sc.nextLine();
                System.out.println("Enter employees salary :");
                int salary=sc.nextInt();
                
                 preparedStatement.setInt(1, id);
                 preparedStatement.setString(2, name);
                 preparedStatement.setString(3, job);
                 preparedStatement.setInt(4,salary) ;

                 int rowaffected=preparedStatement.executeUpdate();

                 if(rowaffected>0){
                    System.out.println("Data insert sucessfully.");
                 }
                 else{
                    System.out.println("Data not affect sucessfully.");
                 }
              
               //   ResultSet result=preparedStatement.executeQuery();
               //   while(result.next()){
               //      int id=result.getInt("id");
               //      String name=result.getString("name");
               //      String job=result.getString("job_title");
               //      int salary=result.getInt("salary");
               //      System.out.println("Id :" +id);
               //      System.out.println("Name : "+ name);
               //      System.out.println("job Title :"+ job);
               //      System.out.println("Salary : "+ salary);

               //   }

                
                preparedStatement.close();
                con.close();
                System.out.println(" ");
                System.out.println(" Connection closes Successfully!!");

    
               }catch(SQLException e){
                    System.out.println(e.getMessage());
            }
     }
}
