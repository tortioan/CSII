package edu.ccd;

import java.util.ArrayList;

public class Main {

    private static void depositMoney(String routingnumber, float deposit_amount) {
        //Pre-conditions:
        assert deposit_amount > 0.0f : "Cannot deposit a debit";
        assert deposit_amount != 0.0f : "Non operation";
        assert !routingnumber.isEmpty() : "No routing number provided";

        Notifications.the().displayNotification("I am depositing at the bank: $" + deposit_amount);

        //Post-conditions:
        assert deposit_amount > 0.0f : "No operating funds. Close the shop.  Everyone file for unemployment!";

    }

    public static void main(String[] args) {

        /*Notifications.the().displayNotification("Starting program...");
        Notifications.the().toggleQuietMode();

        depositMoney("Operationsfunds", 3000f);
        float total_of_inventory_value = 0.0f;

        ArrayList<InventoryItem> mystuff = new ArrayList<>();
        try {
            if( InventoryItem.canCreate("myCPU") )
                mystuff.add(new CPU("myCPU", 3000f));
        } catch (ComponentHasNoNameException e) {
            Notifications.the().displayNotification(e.getMessage());
            mystuff.add(new Monitor("Unknown CPU", 1f));
        }
        try {
            if( InventoryItem.canCreate("") )
                mystuff.add(new Monitor("", 300f));
        } catch (ComponentHasNoNameException e) {
            Notifications.the().displayNotification(e.getMessage());
            mystuff.add(new Monitor("Unknown Monitor", 1f));
        }
        try {
            if( InventoryItem.canCreate("DasKeyboard") )
                mystuff.add(new Keyboard("DasKeyboard", 150f));
        } catch (ComponentHasNoNameException e) {
            Notifications.the().displayNotification(e.getMessage());
            mystuff.add(new Monitor("Unknown Keyboard", 1f));
        }
	    Notifications.the().displayNotification("Inventory system started.");

	    InventoryItem.displayReportHeader();

	    int x = 5;
	    Trace.the().displayNotification("Is x really 5? " + (x==5?"Yes":"No"));

        for (InventoryItem aMystuff : mystuff) {
            aMystuff.displayDetail();
            total_of_inventory_value += aMystuff.getValue();
            Notifications.the().displayNotification("Inventory number " + aMystuff.getInventoryNumber() + " is " + aMystuff.getName());
        }
        Notifications.the().displayNotification("My inventory value is $" + total_of_inventory_value);
        InventoryItem.displayTotalItemsInInventory();

        CPU concreteItem = concreteItem = new CPU("Something", 300f);


        InventoryItem abstractItem = ((InventoryItem)concreteItem);

        Notifications.the().displayNotification("This is concreteItem as a String " + concreteItem.toString());
        Notifications.the().displayNotification("This is abstractItem as a String " + abstractItem.toString());

        concreteItem.iAmACPU();
        //but I cannnot say testme.iAmACPU, because I am a InventoryItem...but am I?

        for (InventoryItem aMystuff : mystuff) {
            if (aMystuff instanceof InventoryItem)
                System.out.print(((InventoryItem) aMystuff).getName() + ", serial number: ");
            else
                System.out.print("Item not an inventory item.");

            if (aMystuff instanceof SerializedItem)
                Notifications.the().displayNotification(((SerializedItem) aMystuff).getSerialnumber());
            else
                Notifications.the().displayNotification("Item not serialized.");
        }

        if (abstractItem instanceof CPU)
            Notifications.the().displayNotification("Yes!");
        else
            Notifications.the().displayNotification("No!");

        //-- Workstation class testing space
        Workstation myWorkstation = new Workstation(new CPU("Lab Computer", 1500f), new Monitor("Dell ElCheapo", 1f), new Keyboard("DasKeyboard", 120f), new Mouse());

        try {
            if (InventoryItem.canCreate("Just bought this at Micro Center"))
                myWorkstation.addInventoryItem(new CPU("Just bought this at Micro Center", 3900f));
        }
        catch (Exception e) {
            Notifications.the().displayNotification(e.getMessage());
        }

        Workstation newWorkstation = new Workstation();

        try {
            newWorkstation.addInventoryItem(new Monitor("Monitor1", 100f));
        }
        catch (DuplicateItemException e) {
            Notifications.the().displayNotification(e.getMessage());
        }

        try {
            newWorkstation.addInventoryItem(new Monitor("Monitor2", 100f));
        }
        catch (DuplicateItemException e) {
            Notifications.the().displayNotification(e.getMessage());
        }
        try {
            newWorkstation.addInventoryItem(new Monitor("Monitor3", 100f));
        }
        catch (DuplicateItemException e) {
            Notifications.the().displayNotification(e.getMessage());
        }*/

        InventoryDatabaseMySQL idb = new InventoryDatabaseMySQL();

        idb.AddRole(new Role("admin", "InventoryTable", true, true, true, true, true));
        idb.AddRole(new Role("super", "InventoryTable", true, true, true, true, true));
        idb.AddRole(new Role("TheDude", "InventoryTable", true, true, true, true, true));

        if(idb.getSize() > 2) {
            Role.print(idb.ViewRole(3));
            //idb.DeleteRole(3);
            Role.print(idb.ViewRole(3));
        }
        System.out.println("End program.");
    }
}
