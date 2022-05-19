package Model.Utils;

import java.util.Date;

public class GameObject {
    private String _name;
    private Integer _timestamp;

    public GameObject(String name, Integer timestamp) {
        this._name = name;
        this._timestamp = timestamp;
    }

    public String getName() {
        return _name;
    }

    public Integer getTimestamp() {
        return _timestamp;
    }

    @Override
    public String toString() {
        Date time=new java.util.Date((long)_timestamp*1000);
        return time.toString() + ":" + _name;
    }
}
