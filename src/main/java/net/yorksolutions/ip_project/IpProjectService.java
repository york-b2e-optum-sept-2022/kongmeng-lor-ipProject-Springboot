package net.yorksolutions.ip_project;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

        long mill = System.currentTimeMillis();
        map.put("milliseconds_since_epoch", mill);
        String timeReturn = String.valueOf(hour);
        timeReturn += ":";
        timeReturn += String.valueOf(time.getMinute());
        timeReturn += ":";
        timeReturn += String.valueOf(time.getSecond());
        timeReturn += " AM";
        map.put("time",timeReturn);
        return map;
    }

    public HashMap echo() {


    }


}



