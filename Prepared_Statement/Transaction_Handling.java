import java.sql.*;
public class Transaction_Handling {
     public static void main(String[] args) throws SQLException {
          String url="jdbc:mysql://Localhost:3306/mysql";
          String username="root";
          String password="root";
          String withdroquery ="update accounts set balance =balance -? where account_number= ?;";
          String depositQuery ="update accounts set balance =balance+ ? where account_number=?;";

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
                   try{
                  PreparedStatement withdrowStatement=con.prepareStatement(withdroquery);
                  PreparedStatement deposiStatement=con.prepareStatement(depositQuery);        
                  withdrowStatement.setDouble(1,500.00);
                  withdrowStatement.setString(2,"account231");
                  deposiStatement.setDouble(1,500.00);
                  deposiStatement.setString(2,"account421");
                  int affectwithdrow=withdrowStatement.executeUpdate();
                  int affectdeposit=deposiStatement.executeUpdate();
                 if(affectdeposit>0 && affectwithdrow>0){
                  con.commit();
                 System.out.println("Transation successfully!!!.");
                 }
                 else{
                    con.rollback();
                    System.out.println("Transation falses!!!.");
                 }
                   }catch(SQLException e){
                    System.out.println(e.getMessage());
                   }

                    con.close();
                    System.out.println(" ");
                    System.out.println(" Connection closes Successfully!!");
    
        
                   }
                   catch(SQLException e){
                        System.out.println(e.getMessage());
                }
          
     }
     
}
