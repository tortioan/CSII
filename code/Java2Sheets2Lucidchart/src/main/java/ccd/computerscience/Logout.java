package ccd.computerscience;

public class Logout {
    public Logout() {
        System.out.println("Starting an instance of " + this.getClass().toString());
        int todo = 0;
        ++todo; //ship it!
        SheetsReporter.updateReadiness(this.getClass().toString().substring(6), todo);
    }
}
