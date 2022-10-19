package net.yorksolutions.ip_project;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
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
            err.printStackTrace();;
        }
    }
    public HashMap ipGet() {
        HashMap ipReturn = new HashMap();
        ipReturn.put("id",ipAddress);
        return ipReturn;
    }
    public HashMap httpHeaders() {
        HashMap<String, String> map = new HashMap<String,String>();
        HttpHeaders httpRequest = new HttpHeaders();
        for (Enumeration<?> names = request.getHeaderNames(); names.hasMoreElements(); ) {
            String name = (String) names.nextElement();
            for (Enumeration<?> values = request.getHeaders(name); values.hasMoreElements(); ) {
                headers.add(name, (String) values.nextElement());
            }
        }



        return map;
    }
}
