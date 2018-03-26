package edu.ccd;

public interface InventoryDatabaseInterface {
    Role ViewRole(int uid);
    boolean AddRole(Role addrole);
    boolean DeleteRole(int uid);
    boolean EditRole(int replaceme, Role replacewith);
    Role ReloadRole(int uid);
}
