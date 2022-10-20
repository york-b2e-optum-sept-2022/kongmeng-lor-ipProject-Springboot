package net.yorksolutions.ip_project;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
public class IpProjectController {

    private IpProjectService ipService;


    public IpProjectController(IpProjectService ipService) {
        this.ipService = ipService;
    }

    @GetMapping("/ip")
    public HashMap ipGet() {
        return ipService.ipGet();
    }

    @GetMapping("/headers")
    public HashMap headers (HttpServletRequest request) {
        return ipService.headers(request);
    }
    @GetMapping("/dateTime")
    public HashMap dateTime() {
        return ipService.dateTime();
    }
    @GetMapping("/echo")
    public HashMap echo() {

    }
















}
