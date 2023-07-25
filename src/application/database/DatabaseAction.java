package application.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.model.Admin;
import application.model.Book;
import application.model.IssueBook;
import application.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DatabaseAction {
    Connection getConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/library management","root","root");
    }
    
    public ObservableList<User> getAllUser() throws SQLException{
        
        ObservableList<User> userList =FXCollections.observableArrayList();
        
        Connection conn = getConnection();
        Statement statement=conn.createStatement();
        String query="select * from userinfo";
        
        ResultSet rs=statement.executeQuery(query);
        
        while(rs.next()){
            int id=rs.getInt("userID");
            String name=rs.getString("Name");
            String email=rs.getString("userEmail");
            String password=rs.getString("userPassword");
            String confirmpass=rs.getString("confirmPass");
            
            User userinfo=new User(id,name,email,password,confirmpass);
            userList.add(userinfo);
        }
        return userList;
        
    }
    public String addUser(User userinfo) throws SQLException{
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        
        String query="insert into userinfo "
                + "values("+userinfo.getId()+",'"+userinfo.getName()+"','"+userinfo.getEmail()+"','"+userinfo.getPassword()+"','"+userinfo.getConfirmpass()+"')";
        
        if(statement.executeUpdate(query)>0){
            return "(: User Added Successfully :)";
        }else{
            return "Sorry, Unexpected Error Occurred :(";
        }     
        
    }

  
  public ObservableList<Admin> getAllAdmins() throws SQLException{
        
        ObservableList<Admin> adminList =FXCollections.observableArrayList();
        
        Connection conn = getConnection();
        Statement statement=conn.createStatement();
        String query="select * from admininfo";
        
        ResultSet rs=statement.executeQuery(query);
        
        while(rs.next()){
            int id=rs.getInt("adminID");
            String email=rs.getString("adminEmail");
            String password=rs.getString("adminPassword");
            
            Admin admininfo=new Admin(id,email,password);
            adminList.add(admininfo);
        }
        return adminList;
        
    } 
  public ObservableList<Book> getAllBooks() throws SQLException{
        
        ObservableList<Book> bookList =FXCollections.observableArrayList();
        
        Connection conn = getConnection();
        Statement statement=conn.createStatement();
        String query="select * from bookinfo";
        
        ResultSet rs=statement.executeQuery(query);
        
        while(rs.next()){
            String title=rs.getString("bookTitle");
            String isbn=rs.getString("bookISBN");
            String author=rs.getString("bookAuthor");
            String publisher=rs.getString("bookPublisher");
            int numcopies =rs.getInt("NumCopies");
            Book bookinfo=new Book(title,isbn,author,publisher,numcopies);
            bookList.add(bookinfo);
        }
        return bookList;
        
    }
  
  public String addBook(Book bookinfo) throws SQLException{
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        
        String query="insert into bookinfo "
                + "values('"+bookinfo.getTitle()+"','"+bookinfo.getIsbn()+"','"+bookinfo.getAuthor()+"','"+bookinfo.getPublisher()+"',"+bookinfo.getNumcopies()+")";
        
        if(statement.executeUpdate(query)>0){
            return "(: Book Data Added :)";
        }else{
            return "Sorry, Unexpected Error Occurred :(";
        }     
        
    }
  
  public String deleteBooks(ObservableList<Book> selectedBooks) throws SQLException{
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        
        for(Book bookinfo:selectedBooks){
            String query="delete from bookinfo WHERE bookISBN = '"+bookinfo.getIsbn()+"'";
            statement.executeUpdate(query);
            }
        return null;
        }
  
  public String updateBook(Book bookinfo) throws SQLException{
      Connection conn=getConnection();
        Statement statement=conn.createStatement();
        
        String query= "UPDATE bookinfo "
                    + "SET bookTitle ='"+bookinfo.getTitle()
                    +"',bookAuthor='"+bookinfo.getAuthor()
                    +"',bookPublisher='"+bookinfo.getPublisher()
                    +"',NumCopies="+bookinfo.getNumcopies()
                    + " WHERE bookISBN ='"+bookinfo.getIsbn()+"'";
        System.out.println(query);
        if(statement.executeUpdate(query)>0){
            return "(: Book Data Updated :)";
        }else{
            return "Sorry, Unexpected Error Occurred :(";
        } 
    }
  
  public String reserveBook(Book bookinfo) throws SQLException{
      Connection conn=getConnection();
        Statement statement=conn.createStatement();
        
        String query= "UPDATE bookinfo "
                    + "SET NumCopies=("+bookinfo.getNumcopies()+"-1) "
                    + "WHERE bookISBN ='"+bookinfo.getIsbn()+"'";
        if(statement.executeUpdate(query)>0){
            return "(: Book Data Reserved :)";
        }else{
            return "Sorry, Unexpected Error Occurred :(";
        } 
    }
  
  public String returnBook(Book bookinfo) throws SQLException{
      Connection conn=getConnection();
        Statement statement=conn.createStatement();
        
        String query= "UPDATE bookinfo "
                        + "SET NumCopies=("+bookinfo.getNumcopies()+"+1)"
                        + "WHERE bookISBN ='"+bookinfo.getIsbn()+"'";
        if(statement.executeUpdate(query)>0){
            return "(: Book Data Returned :)";
        }else{
            return "Sorry, Unexpected Error Occurred :(";
        }
    }

    public ObservableList<IssueBook> getAllIssuedBooks() throws SQLException{
        
        ObservableList<IssueBook> issuedbookList=FXCollections.observableArrayList();
        
        Connection conn = getConnection();
        Statement statement=conn.createStatement();
        String query="select * from issuedBooks";
        
        ResultSet rs=statement.executeQuery(query);
        
        while(rs.next()){
            String title=rs.getString("bookTitle");
            String isbn=rs.getString("bookISBN");
            String author=rs.getString("bookAuthor");
            String publisher=rs.getString("bookPublisher");
            
            IssueBook bookinfo=new IssueBook(title,isbn,author,publisher);
            issuedbookList.add(bookinfo);
        }
        return issuedbookList;
        
    }
    public String addIssuedBook(IssueBook bookobj) throws SQLException{
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        
        String query="insert into issuedBooks "
                + "values('"+bookobj.getTitle()+"','"+bookobj.getIsbn()+"','"+bookobj.getAuthor()+"','"+bookobj.getPublisher()+"')";
        
        if(statement.executeUpdate(query)>0){
            return "(: Book Data Added :)";
        }else{
            return "Sorry, Unexpected Error Occurred :(";
        }     
    }
    public String returnBook(IssueBook bookobj) throws SQLException{
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        
        String query="delete from issuedBooks WHERE bookISBN = '"+bookobj.getIsbn()+"'";
        
        if(statement.executeUpdate(query)>0){
            return "(: Book Data Deleted :)";
        }else{
            return "Sorry, Unexpected Error Occurred :(";
        }     
    }
}
