package com.greenhouse.restfull;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Data
@Slf4j
@RestController
@RequestMapping("/account")
public class UserRestFullEndPoint {

    @GetMapping("/userInfo")
    public Principal userInfo(Principal principal){
    	System.out.println("userInfo" + principal);
    	System.out.println("userInfo" + SecurityContextHolder.getContext().getAuthentication());
        return principal;
    }
}
