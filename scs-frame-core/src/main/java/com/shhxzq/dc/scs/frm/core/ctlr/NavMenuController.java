package com.shhxzq.dc.scs.frm.core.ctlr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shhxzq.dc.scs.frm.core.domain.CommonResponse;
import com.shhxzq.dc.scs.frm.core.domain.NavNode;
import com.shhxzq.dc.scs.frm.core.jpa.EntityJpaService;
import com.shhxzq.dc.scs.frm.core.jpa.entity.common.MenuEntity;

/**
 * @author XiaoYi
 * Created on 2017-08-07 15:52:40
 */
@RestController
@RequestMapping("/home")
@CrossOrigin
public class NavMenuController {
    
    
    @Autowired
    private EntityJpaService entityJpaService;
    
    @RequestMapping(value = "/nav")
    @ResponseBody
    public CommonResponse<Map<String, Object>> getNav(@RequestParam String userId){
        CommonResponse<Map<String, Object>> response = new CommonResponse<>(); 
        Map<String, Object> navInfo = new LinkedHashMap<>();
        List<MenuEntity> list = getMenuList();
        if(list == null || list.size() == 0){
            return new CommonResponse<>(false, "The navegator is empty");
        }
        List<NavNode> navList = getNavList(list);
        navInfo.put("navList", navList);
        response.setContent(navInfo);
        Map<Integer, NavNode> subNavMap = this.getMenuNavNodeList(list);
        Map<Integer, List<NavNode>> menuMap = new LinkedHashMap<>();
        for(NavNode navNode : navList){
            menuMap.put(navNode.getId(), subNavMap.get(navNode.getId()).getChildren());
        }
        navInfo.put("menuMap", menuMap);
        return response;
    }
    
    private List<NavNode> getNavList(List<MenuEntity> list){
        List<NavNode> navNodes = new ArrayList<>();
        Iterator<MenuEntity> it = list.iterator();
        MenuEntity menu;
        while(it.hasNext()){
            menu = it.next();
            if(menu.getParent() == null){
                navNodes.add(getNavNode(menu));
            }
        }
        Collections.sort(navNodes);
        return navNodes;
    }
    
    private Map<Integer, NavNode> getMenuNavNodeList(List<MenuEntity> list){
        Map<Integer, NavNode> allNavNodeMap = new HashMap<>();
        NavNode item;
        NavNode parent;
        Map<Integer, MenuEntity> entityMap = new HashMap<>();
        for(MenuEntity entity : list){
            entityMap.put(entity.getId(), entity);
        }
        for(MenuEntity entity : list){
            item = getNavNode(entity);
            if(!allNavNodeMap.containsKey(item.getId())){
                allNavNodeMap.put(item.getId(), item);
            }
            if(entity.getParent() != null && entityMap.containsKey(entity.getParent().getId())){
                parent = allNavNodeMap.get(entity.getParent().getId());
                if(parent == null){
                    parent = getNavNode(entity.getParent());
                    allNavNodeMap.put(parent.getId(), parent);
                }
                parent.addNavNode(item);
            }
        }
        return allNavNodeMap;
    }
    
    private NavNode getNavNode(MenuEntity menu) {
        NavNode navNode = new NavNode();
        navNode.setId(menu.getId());
        navNode.setName(menu.getName());
        navNode.setTargetUrl(menu.getTargetUrl());
        navNode.setOrder(menu.getOrder());
        return navNode;
    }

    private List<MenuEntity> getMenuList(){
        List<MenuEntity> list = entityJpaService.getList(MenuEntity.class, null);
        return list;
    }
    
    
}
