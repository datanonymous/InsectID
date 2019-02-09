package ko.alex.insectid;

public class Device {

    private String deviceID;
    private String descriptionID;
    private String lastServicedID;

    public Device(String deviceID, String descriptionID, String lastServicedID){
        this.deviceID = deviceID;
        this.descriptionID = descriptionID;
        this.lastServicedID = lastServicedID;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getDescriptionID() {
        return descriptionID;
    }

    public void setDescriptionID(String descriptionID) {
        this.descriptionID = descriptionID;
    }

    public String getLastServicedID() {
        return lastServicedID;
    }

    public void setLastServicedID(String lastServicedID) {
        this.lastServicedID = lastServicedID;
    }
}
