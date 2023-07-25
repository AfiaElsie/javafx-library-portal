
package application.model;


public class Admin {
    private int id;
    private String email;
    private String password;
   
    public Admin(){
    }

    public Admin(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

     
    @Override
    public String toString() {
        return "adminInfo{" + "id=" + id + ", email=" + email + ", password=" + password + '}';
    }
    
}
