package util;

import org.json.JSONObject;

import java.io.Serializable;

public class MJSONObject implements Serializable {

    private static final long serialVersionUID = 1L;

    private JSONObject jsonObject;

    public MJSONObject() {
    }

    public MJSONObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public void set(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public JSONObject get() {
        return jsonObject;
    }


    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

}