package edu.ccd;

public class Keyboard extends InventoryItem{
    public Keyboard() {

    }

    public Keyboard(String name, float value) {
        _name = name;
        _value = value;
    }

    public void displayDetail() {
        System.out.println(0 + "\t\t\t\t\t" + _name + "\t\t\t\t" + _value);
    }
}
