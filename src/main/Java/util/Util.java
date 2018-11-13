/*
 * tiancheng copyrights reserved
 */

package util;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class Util {
    public static String StatusSuccess = "success";
    public static String StatusFail = "fail";

    public static ResponseEntity jsonify(boolean ret, String msg, Map<String, Object> data) {
        ResponseEntity re = jsonify(ret, msg);
        Map map = (Map) re.getBody();
        for(Map.Entry entry: data.entrySet()){
            map.put(entry.getKey(), entry.getValue());
        }
        return re;
    }

    public static ResponseEntity jsonify(boolean ret, String msg) {
        Map<String, String> map = new HashMap<String, String>();
        String sret = StatusFail;
        HttpStatus st = HttpStatus.BAD_REQUEST;
        if (ret) {
            sret = StatusSuccess;
            st = HttpStatus.OK;
        }
        map.put("code", sret);
        map.put("message", msg);

        return new ResponseEntity(map, st);
    }

}
