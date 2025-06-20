package github.com.fanjh.amap_mcp_119.alarm.pojo;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ResponseData {
    private int code;
    private String msg;
    private Map<String, Object> data = new HashMap<>();

    public static ResponseData success() {
        ResponseData result = new ResponseData();
        result.setCode(200);
        result.setMsg("success");
        return result;
    }

    public ResponseData put(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    // getters and setters...
}
