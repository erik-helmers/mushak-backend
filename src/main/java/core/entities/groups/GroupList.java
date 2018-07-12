package core.entities.groups;

import core.entities.Entity;
import easysqlite.annotations.declarations.Column;
import easysqlite.annotations.declarations.Table;

import java.util.List;

@Table("group_list")
public class GroupList extends Entity<GroupList.Id>{

    @Column
    public String groupID = "foo";

    public static class Id extends Entity.IntegerId{
        public Id(Integer id) {
            super(id);
        }
    }
}
