package edu.ccd;

import java.util.ArrayList;

public class InventoryDatabase implements InventoryDatabaseInterface{
    private ArrayList<Role> roles = new ArrayList();

    public int getSize() {
        return roles.size();
    }

    @Override
    public boolean AddRole(Role addrole) {
        return roles.add(addrole);
    }

    @Override
    public Role ViewRole(int uid) {
        for( Role lookin : roles) {
            if( lookin.amI(uid) )
                return lookin;
        }
        return null;
    }

    @Override
    public boolean DeleteRole(int uid) {
        return roles.remove(ViewRole(uid));
    }

    @Override
    public boolean EditRole(int replaceme, Role replacewith) {
        return DeleteRole(replaceme) && AddRole(replacewith);
    }

    @Override
    public Role ReloadRole(int uid) {
        return ViewRole(uid);
    }
}
