package com.shhxzq.dc.scs.frame.core.ctlr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shhxzq.dc.scs.frame.core.adapter.JpaApadterService;

/**
 * @author XiaoYi
 * Created on 2017-07-26 16:08:50
 */
@RestController
@RequestMapping("/jpa")
@CrossOrigin
public class CommonJpaControllor {
    
    @Autowired
    private JpaApadterService jpaApadterService;
    
    
    
}
