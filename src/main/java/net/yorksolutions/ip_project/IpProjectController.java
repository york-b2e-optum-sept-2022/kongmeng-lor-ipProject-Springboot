package net.yorksolutions.ip_project;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("headers")
    public HashMap headers() {
        return ipService.httpHeaders();
    }

}
