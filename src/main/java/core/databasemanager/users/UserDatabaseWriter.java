package core.databasemanager.users;

import core.databasemanager.DatabaseModule;
import core.entities.users.User;
import core.exceptions.UsernameExistsException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * USAGE HERE
 * By Erik Helmers, the 26/05/2018
 */
public class UserDatabaseWriter extends DatabaseModule<UserDatabase> {


    public UserDatabaseWriter(UserDatabase database) {
        super(database);
    }

    public void register(User account) throws UsernameExistsException {
        String sql = "INSERT INTO users(username, password) VALUES(?,?)";

        if (parent.read.read_from(account.username).isPresent()){
            throw new UsernameExistsException(account.username);
        }

        try(PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, account.username);
            pstmt.setString(2, account.password);
            pstmt.execute();
        } catch (SQLException e) {
            throw new Error(e);
        }
    }

}
