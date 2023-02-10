package com.lihm.gatewaydemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    @Autowired
    private Environment env;

    @RequestMapping("/getIp")
    public Map getIp() throws UnknownHostException {
        Map map = new HashMap();
        map.put("host", InetAddress.getLocalHost().getHostAddress());
        map.put("port", env.getProperty("server.port"));
        return map;
    }

}
