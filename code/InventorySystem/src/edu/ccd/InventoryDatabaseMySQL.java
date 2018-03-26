package edu.ccd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryDatabaseMySQL implements InventoryDatabaseInterface{

    private Connection conn = null;

    private Connection getConnection () {
        if(conn == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                return DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/Inventory?useSSL=false", "root", "&APx.RApkf9f$2kHk56bljS3{"
                );
            } catch (Exception any) {
                any.printStackTrace();
            }
        }
        return conn;
    }

    public int getSize() {
        try {
            ResultSet results = getConnection().createStatement().executeQuery(
                    "SELECT COUNT(*) FROM Roles;"
            );
            while (results.next())
                return results.getInt(1);
        } catch (Exception any) {
            any.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean AddRole(Role addrole) {
        try {
            getConnection().createStatement().executeUpdate(
                    "INSERT INTO Roles (uid, rolename, targetname, `permissions-view`, `permissions-add`, `permissions-delete`, `permissions-edit`, `permissions-reload`)" +
                    " VALUES (" + addrole.getUid() +
                    ", '" + addrole.getRolename() +
                    "', '" + addrole.getTargetname() +
                    "', " + (addrole.canView()?"1":"0") +
                    ", " + (addrole.canAdd()?"1":"0") +
                    ", " + (addrole.canDelete()?"1":"0") +
                    ", " + (addrole.canEdit()?"1":"0") +
                    ", " + (addrole.canReload()?"1":"0") + ");"
            );
        } catch (SQLException any) {
            any.printStackTrace();
        }

        return true;
    }

    @Override
    public Role ViewRole(int uid) {
        Role returnme = null;
        try {
            ResultSet results = getConnection().createStatement().executeQuery(
                    "SELECT * FROM Roles WHERE uid=" + uid + ";"
            );
            while (results.next()) {
                returnme = Role.cloneRole(
                        results.getInt(1),
                        results.getString(2),
                        results.getString(3),
                        results.getBoolean(4),
                        results.getBoolean(5),
                        results.getBoolean(6),
                        results.getBoolean(7),
                        results.getBoolean(8)
                );
            }
        } catch (Exception any) {
            any.printStackTrace();
        }
        return returnme;
    }

    @Override
    public boolean DeleteRole(int uid) {
        try {
            getConnection().createStatement().executeUpdate(
                    "DELETE FROM Roles WHERE uid=" + uid + ";"
            );
        } catch (SQLException any) {
            any.printStackTrace();
        }
        return true;
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
