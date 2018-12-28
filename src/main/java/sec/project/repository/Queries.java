
package sec.project.repository;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.h2.tools.RunScript;
import sec.project.domain.Signup;


public class Queries {
    
    Connection connection = null;
    private String databaseAddress = "jdbc:h2:file:./database";
    
    public Queries() {
        try {
           this.connection = DriverManager.getConnection(databaseAddress, "sa", "");
            RunScript.execute(connection, new FileReader("src/main/resources/sql/schema.sql"));
            RunScript.execute(connection, new FileReader("src/main/resources/sql/userdata.sql"));
        } catch (Throwable t) {
            System.err.println(t.getMessage());
        }
    }
    

    public Signup getAccount(String username, String password) throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT id, username, password FROM accounts WHERE username='" +username+ "' AND password='" +password+ "'");
        while (resultSet.next()) {
            return new Signup(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getString("password"));
    }
        return null;
    }
}
   