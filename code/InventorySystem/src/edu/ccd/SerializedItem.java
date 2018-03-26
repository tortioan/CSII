package edu.ccd;

public abstract class SerializedItem extends InventoryItem {
    private String _serialnumber = "1701";

    public void setSerialnumber(String serialnumber) {
        _serialnumber = serialnumber;
    }

    public String getSerialnumber(){
        return _serialnumber;
    }
}
