package core.databasemanager;

import core.Settings;

import java.sql.Connection;

/**
 * USAGE HERE
 * By Erik Helmers, the 23/05/2018
 */
public abstract class DatabaseModule<T extends Database> {

    protected Connection connection;
    protected Settings settings;
    protected T parent;

    public DatabaseModule(T database){
        this.parent = database;
        this.settings = database.settings;
        this.connection = database.connection;
    }

}
