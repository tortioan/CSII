package ccd.computerscience;

public class InvChForm {
    public InvChForm() {
        System.out.println("Starting an instance of " + this.getClass().toString());
        int todo = 0;
        ++todo; //add something to this function
        ++todo; //test this functionality
        ++todo; //ship it!
        SheetsReporter.updateReadiness(this.getClass().toString().substring(6), todo);
    }
}
