package ccd.computerscience;

public class Query {
    public Query() {
        System.out.println("Starting an instance of " + this.getClass().toString());
        int todo = 0;
        ++todo; //add something to this function
        ++todo; //test this functionality
        ++todo; //add support for this platform
        ++todo; //ship it!
        SheetsReporter.updateReadiness(this.getClass().toString().substring(6), todo);
    }
}
