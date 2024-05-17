import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
//import java.io.FileInputStream;
import java.io.FileInputStream;
//import java.io.OutputStream;
import java.io.*; 

public class ImageJdbc {  


public static void main(String[] args) throws FileNotFoundException{
          String url="jdbc:mysql://Localhost:3306/mysql";
          String username="root";
          String password="root";
          //It use image insert into database;
          //String image_path="C:\\Users\\Arvind\\Desktop\\Java code\\Telusko48 java\\JDBC\\Prepared_Statement\\download.jpg";
          //String sql="insert into image_table (image_data) values(?);";

          //It use retrive the image 
          String folderpath="C:\\Users\\Arvind\\Desktop\\Java code\\Telusko48 java\\JDBC\\New folder\\";
          String sql="Select image_data from image_table where image_id=?;";
          

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
                    
                    PreparedStatement preparedStatement = con.prepareStatement(sql);
                    preparedStatement.setInt(1,1);
                     ResultSet result=preparedStatement.executeQuery(); 
                     if(result.next()){
                        byte[] image_data=result.getBytes("image_data");

                        String imagepath=folderpath+"Buddha";
                       FileOutputStream outputStream = new FileOutputStream(imagepath); // Use FileOutputStream for writing
                       outputStream.write(image_data);
                       outputStream.close(); 
                   }else{
                        System.out.println("Image not found.");
                     }




                    //It use insert the image in database;
                    // FileInputStream fileInputStream=new FileInputStream(image_path);
                    // byte[] imagedata=new byte[fileInputStream.available()];
                    // fileInputStream.read(imagedata);
                    // PreparedStatement preparedStatement = con.prepareStatement(sql);
                    // preparedStatement.setBytes(1, imagedata);
                    // int affect=preparedStatement.executeUpdate();
                    //  if(affect>0){
                    //      System.out.println("Affected !!");
                    //  }else{
                    //      System.out.println("not Affected!!");
                    //  }
        
                    
                   
                    con.close();
                System.out.println(" ");
                System.out.println(" Connection closes Successfully!!");

    
               }
               catch(SQLException e){
                    System.out.println(e.getMessage());
            }catch( RuntimeException e){
               System.out.println(e.getMessage());

            }catch(IOException e){
               System.out.println(e.getMessage());
            }
     }

} 