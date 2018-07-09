package core.databasemanager.users;

import core.Settings;
import core.databasemanager.Database;
import logger.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

/**
 * User Database
 * By Erik Helmers, the 26/05/2018
 */

@Repository
public class UserDatabase extends Database {

    private static Resource populate_file = new ClassPathResource("/database/users_create.sql");


    public final UserDatabaseReader read;
    public final UserDatabaseWriter write;

    @Autowired
    public UserDatabase(Settings settings) throws Exception{
        super(settings);
        Log.debug_value("user-db-path", (settings.get(Settings.USERS_DB_PATH)));
        connect(settings.get(Settings.USERS_DB_PATH), populate_file.getFile());

        this.read = new UserDatabaseReader(this);
        this.write = new UserDatabaseWriter(this);
    }



}
