package net.yorksolutions.ip_project;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    @GetMapping({"/date","/time"})
    public HashMap dateTime() {
        return ipService.dateTime();
    }
    @GetMapping("/echo/**")
    public HashMap echo(HttpServletRequest request) {
        return this.ipService.echo(request);
    }
    @GetMapping("/code")
    public String code() {
        return this.ipService.code();
    }
    @GetMapping("/callbacks")
    public String callback() {
        return this.ipService.callback();
    }
    @GetMapping("/cookie")
    public HashMap cookie(HttpServletResponse response, HttpServletRequest request) {
        return this.ipService.cookie(response,request);
    }
    @GetMapping("/md5/text={id}")
    public HashMap md5(@PathVariable String id) {
        return this.ipService.md5(id);
    }
    @GetMapping("/validate")
    public HashMap validate(RequestParam String input) {
        return this.ipService.validate(input);
    }














}
