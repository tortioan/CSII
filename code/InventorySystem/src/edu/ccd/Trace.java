package edu.ccd;

public class Trace implements Notifier {

    public static Trace the = null;

    private Trace() {}

    public static Trace the() {
        if (the == null) {
            the = new Trace();
        }
        return the;
    }

    @Override
    public void displayNotification(String note) {
        System.out.println("Mobile: " + note);
    }
}
