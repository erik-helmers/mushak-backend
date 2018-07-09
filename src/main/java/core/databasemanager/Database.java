package core.databasemanager;

import core.Settings;
import logger.Log;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.*;

/**
 * USAGE HERE
 * By Erik Helmers, the 26/05/2018
 */
public abstract class Database {

    protected Connection connection;

    protected Settings settings;

    public Database(Settings settings){
        this.settings = settings;
    }
    protected void connect(String path,
                              String populate){

        //TODO : populate not working
        String url = "jdbc:sqlite:"+path;
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url);
            PreparedStatement stmt = conn.prepareStatement(populate);
            stmt.execute();
            Log.debug_line("Initialized table !");
        } catch (SQLException e) {
            throw new Error(e);
        }

        this.connection = conn;
    }

    public void connect(String url, File populate_file){

        String populate;
        if (populate_file == null)
            throw new Error("WTF DI");
        try{
            populate = new String(Files.readAllBytes(populate_file.toPath()));
        } catch (IOException e){
            throw new Error(e);
        }

        connect(url, populate);
    }

}
