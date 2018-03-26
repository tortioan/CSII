package ccd.computerscience;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Starting Program...");
        System.out.println("Instantiating modules...");
        new IdFun();
        new Query();
        new InvChForm();
        new ShowInventory();
        new Logout();

        SheetsReporter.printCurrentTable();
    }
}
