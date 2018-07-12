package core.entities.groups;

import core.entities.Entity;
import easysqlite.annotations.declarations.Table;

@Table("groups")
public class Group extends Entity<Group.Id>{

    public Group(){}

    public static class Id extends Entity.IntegerId{
        public Id(Integer id) {
            super(id);
        }
    }
}
