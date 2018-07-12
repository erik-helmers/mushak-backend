package core.entities.users;

import core.entities.Entity;
import core.entities.mediatypes.MusicElement;
import easysqlite.annotations.declarations.Column;
import easysqlite.annotations.declarations.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level=AccessLevel.PUBLIC)
@Table("musicProfiles")
public class MusicProfile extends Entity<MusicProfile.Id> {

    public MusicProfile(){}
    @Builder
    public MusicProfile(Id id) {
        super(id);
    }

    public static class Id extends Entity.IntegerId {
        public Id(Integer id) {
            super(id);
        }
    }


    @Column
    String songs= "foo";



}

