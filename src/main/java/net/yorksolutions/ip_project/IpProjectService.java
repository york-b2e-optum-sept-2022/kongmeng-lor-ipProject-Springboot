package net.yorksolutions.ip_project;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;

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




}



