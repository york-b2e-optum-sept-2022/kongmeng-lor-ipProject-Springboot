package net.yorksolutions.ip_project;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

@Service
public class IpProjectService {

    InetAddress ip;
    String ipAddress;


    public IpProjectService() {
        try {
            ip = InetAddress.getLocalHost();
            ipAddress = ip.getHostAddress();
        } catch (UnknownHostException err) {
            err.printStackTrace();
            ;
        }
    }
    public HashMap ipGet() {
        HashMap ipReturn = new HashMap();
        ipReturn.put("id", ipAddress);
        return ipReturn;
    }


    public HashMap headers(HttpServletRequest request) {

        HashMap<String, String> map = new HashMap<String, String>();
        Enumeration headernNames = request.getHeaderNames();
        while(headernNames.hasMoreElements()) {
            String key = (String) headernNames.nextElement();
            String value = request.getHeader(key);
            map.put(key,value);
        }
        return map;
    }

    public HashMap dateTime() {
        HashMap map = new HashMap();
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        Date date1 = new Date();

        int hour = time.getHour();
        map.put("date",date);
        long mill = System.currentTimeMillis();
        map.put("milliseconds_since_epoch", mill);
        if (hour > 12) {

            hour = hour - 12;
            String timeReturn = String.valueOf(hour);

            timeReturn += ":";
            timeReturn += String.valueOf(time.getMinute());

            timeReturn += ":";
            timeReturn += String.valueOf(time.getSecond());

            timeReturn += " PM";
            map.put("time",timeReturn);

            return map;
        }
        String timeReturn = String.valueOf(hour);
        timeReturn += ":";
        timeReturn += String.valueOf(time.getMinute());
        timeReturn += ":";
        timeReturn += String.valueOf(time.getSecond());
        timeReturn += " AM";

        map.put("time",timeReturn);
        return map;
    }
    public HashMap echo(HttpServletRequest request) {
        String url = request.getRequestURI();
        String[] lists = url.split("/");

        HashMap map = new HashMap();
        int count = 2;
        while (count < lists.length) {
            String key = lists[count];

            String value = "";
            int valueIndex = count + 1;
            if (valueIndex < lists.length) {
                value = lists[valueIndex];
            }
            map.put(key, value);
        }
        return map;
    }
    public String code() {
        return "alert(\"Your IP address is: " + ipAddress + "\"):";
    }
    public String callback() {
        return "showIP({\"IP\": \"" +ipAddress+ "\"});";
    }
    public HashMap cookie(HttpServletResponse response, HttpServletRequest request) {
        HashMap map = new HashMap();
        long mil = System.currentTimeMillis();
        String value = String.valueOf(mil);
        Cookie cookie = new Cookie("jsontestdotcom",value);
        response.addCookie(cookie);

        Cookie cookies[] = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            String cookiesReturn = cookies[i].getName();
            map.put("cookie_status", cookiesReturn);
        }
        return map;
    }
    public HashMap md5(String id) {
        try {
            HashMap map = new HashMap();
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(id.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);
            String hashText = no.toString(16);
            while (hashText.length() < 32) {
                hashText = "0" + hashText;
            }
            map.put("original", id);
            map.put("md5", hashText);
            hashText = "";
            return map;
        } catch (Exception err) {
            throw new RuntimeException(err);
        }
    }

    public HashMap validate(String jsonString) {
        try {
            boolean isArray = jsonString.charAt(0) == '[';

            int length;
            var startTime = Instant.now().getNano();
            if (isArray) {
                JSONArray array = new JSONArray(jsonString);
                length = array.length();

            } else {
                JSONObject obj = new JSONObject(jsonString);
                length = obj.length();
            }
            var endTime = Instant.now().getNano();


            HashMap map = new HashMap();
            map.put(
                    "object_or_array",
                    isArray ? "array" : "object"
            );
            map.put(
                    "validate",
                    true
            );
            map.put(
                    "parse_time_nanoseconds",
                    endTime - startTime
            );
            map.put(
                    "size",
                    length
            );
            map.put(
                    "empty",
                    length > 0 ? false : true
            );
            return map;
        } catch (JSONException exception) {
            HashMap map = new HashMap();
            map.put(
                    "validate",
                    false
            );
            map.put(
                    "error",
                    exception.getMessage()
            );
            map.put(
                    "object_or_array",
                    jsonString.charAt(0) == '[' ? "array" : "object"
            );
            map.put(
                    "error_info",
                    "This error came from the " + exception.getClass()
            );
            return map;
        }
    }


}



