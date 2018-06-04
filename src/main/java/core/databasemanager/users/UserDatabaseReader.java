package core.databasemanager.users;

import core.databasemanager.DatabaseModule;
import core.entities.users.User;
import lombok.extern.log4j.Log4j2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Reader for User db
 * By Erik Helmers, the 26/05/2018
 */


@Log4j2
public class UserDatabaseReader extends DatabaseModule<UserDatabase> {

    private static final String read_all = "SELECT id, username, password FROM users";
    private static final String select_from_id = "SELECT id, username, password FROM users WHERE id=?";
    private static final String select_from_username = "SELECT id, username, password FROM users WHERE username=?";

    public UserDatabaseReader(UserDatabase database) {
        super(database);
    }


    private PreparedStatement getPstmt(String sql){
        try {
            return connection.prepareStatement(sql);
        } catch (SQLException e){
            throw new Error(e);
        }
    }

    private ResultSet exec(PreparedStatement pstmt){
        try {
            return pstmt.executeQuery();
        } catch (SQLException e){
            throw new Error(e);
        }
    }

    public Optional<User> read_from(User.Id id){
        PreparedStatement pstmt = getPstmt(select_from_id);
        try {
            pstmt.setString(1, id.toString());
            ResultSet rs = exec(pstmt);
            return rs.next() ? Optional.of(User.fromResultSet(rs)) : Optional.empty();
        } catch (SQLException e) {throw new Error(e); }
    }

    public Optional<User> read_from(String username){
        PreparedStatement pstmt = getPstmt(select_from_username);
        try {
            pstmt.setString(1, username);
            ResultSet rs = exec(pstmt);
            return rs.next() ? Optional.of(User.fromResultSet(rs)) : Optional.empty();
        } catch (SQLException e) {throw new Error(e); }
    }


    public List<User> all_users(){
        List<User> output = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(read_all);
             ResultSet rs = pstmt.executeQuery()){
            while (rs.next()){
                output.add(User.fromResultSet(rs));
            }

        } catch (SQLException e){
            log.error(e);
        }
        return output;
    }

}
