package core.entities.users;

import core.entities.Entity;
import easysqlite.annotations.declarations.Column;
import easysqlite.annotations.declarations.Table;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PUBLIC)
@Table("userSettings")
public class UserSettings extends Entity<MusicProfile.Id> {
    public UserSettings(){}

    @Column
    String useless = "foo";
    public static class Id extends IntegerId {

        public Id(Integer id) {
            super(id);
        }

    }




}
