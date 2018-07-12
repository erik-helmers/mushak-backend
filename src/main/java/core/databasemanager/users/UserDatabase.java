package core.databasemanager.users;

import core.Settings;
import core.entities.groups.GroupList;
import core.entities.users.*;
import easysqlite.core.Repository;
import logger.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.sql.Connection;


/**
 * User Database
 * By Erik Helmers, the 26/05/2018
 */

@org.springframework.stereotype.Repository
public class UserDatabase {

    private static Resource populate_file = new ClassPathResource("/database/users_create.sql");


    public final Repository<User> users;
    public final Repository<GroupList> groupLists;
    public final Repository<Token> tokens;
    public final Repository<MusicProfile> musicProfiles;
    public final Repository<UserSettings> usersSettings;


    private final Repository<Role> roles;


    @Autowired
    public UserDatabase(Settings settings) throws Exception{

        Connection connection = Repository.create_connection(settings.get(Settings.Key.MUSHAK_DB), populate_file.getFile());

        this.users = new Repository<>(User.class, connection);
        this.tokens = new Repository<>(Token.class, connection);
        this.musicProfiles = new Repository<>(MusicProfile.class, connection);
        this.usersSettings = new Repository<>(UserSettings.class, connection);
        this.groupLists = new Repository<>(GroupList.class, connection);

        this.roles = new Repository<>(Role.class, connection);

    }

    public void register(User user){

        user.groupList = (GroupList.Id)groupLists.save(new GroupList());
        user.userSettings = (UserSettings.Id)usersSettings.save(new UserSettings());
        user.musicProfile = (MusicProfile.Id) musicProfiles.save(new MusicProfile());


        user.role = roles.search("name", "NOOB").get(0).id;

        users.save(user);


    }

}
