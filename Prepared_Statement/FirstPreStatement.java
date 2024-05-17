import java.sql.*;


public class FirstPreStatement {
     public static void main(String[] args) throws ClassNotFoundException  {
          String url="jdbc:mysql://Localhost:3306/mysql";
          String username="root";
          String password="root";
          String sql="select * from employees where name=? ;";    
            
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
                 preparedStatement.setString(1,"Anuj Goad");  
              
                 ResultSet result=preparedStatement.executeQuery();
                 while(result.next()){
                    int id=result.getInt("id");
                    String name=result.getString("name");
                    String job=result.getString("job_title");
                    int salary=result.getInt("salary");
                    System.out.println("Id :" +id);
                    System.out.println("Name : "+ name);
                    System.out.println("job Title :"+ job);
                    System.out.println("Salary : "+ salary);

                 }

                result.close();
                preparedStatement.close();
                con.close();
                System.out.println(" ");
                System.out.println(" Connection closes Successfully!!");

    
               }catch(SQLException e){
                    System.out.println(e.getMessage());
            }
     }
}

