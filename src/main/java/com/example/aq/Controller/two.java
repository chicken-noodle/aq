package com.example.aq.Controller;

import com.example.aq.Domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class two {

    @Resource
    UserInfoMapper userInfoMapper;

    @Resource
    SubmissionsMapper submissionsMapper;

    @Resource
    ProblemMapper problemMapper;

    @Resource
    KnowledgeMapper knowledgeMapper;


    @RequestMapping("toPaihangbang2")
    public String p2(HttpServletRequest request, HashMap<String, Object> map) {
        Map<String, Object> m = new HashMap<>();
        String name = request.getSession().getAttribute("name").toString();
        int account = (int) request.getSession().getAttribute("account");
        m.put("name", name);
        m.put("account", account);
        map.put("u", m);

        List<Map<String, Object>> ll = new ArrayList<>();
        List<UserInfo> l = userInfoMapper.zongbang();
        int num = 1;
        for (UserInfo uu : l) {
            m = new HashMap<>();
            m.put("rank", num);
            num++;
            m.put("name", uu.getName());
            m.put("score", uu.getScore());
            m.put("total_submit", uu.getTotal_submit());
            ll.add(m);
        }
        map.put("uu", ll);
        return "paihangbang2";
    }

    @RequestMapping("/toLishijilu2")
    public String toLishi2(HashMap<String, Object> map, HttpServletRequest request) {
        Map<String, Object> m = new HashMap<>();
        String name = request.getSession().getAttribute("name").toString();
        int account = (int) request.getSession().getAttribute("account");
        m.put("name", name);
        m.put("account", account);
        map.put("u", m);
        List<Map<String, Object>> ll = new ArrayList<>();
        List<Submissions> l = submissionsMapper.all();
        int num = 1;
        for (Submissions s : l) {
            m = new HashMap<>();
            m.put("num", num++);
            UserInfo u = new UserInfo();
            int tmp = s.getUid();
            u.setAccount(tmp);
            name = userInfoMapper.name(u);
            m.put("name", name);
            m.put("pid", s.getPid());
            tmp = s.getStatus();
            if (tmp == 1) {
                m.put("status", "accepted");
            } else {
                m.put("status", "rejected");
            }
            ll.add(m);
        }
        map.put("uu", ll);
        return "lishijilu2";
    }

    @RequestMapping("/toJiucuo2")
    public String toJiucuo(HttpServletRequest request, HashMap<String, Object> map) {

        Map<String, Object> m = new HashMap<>();
        String name = request.getSession().getAttribute("name").toString();
        int account = (int) request.getSession().getAttribute("account");
        m.put("name", name);
        m.put("account", account);
        map.put("u", m);
        return "jiucuo2";
    }


}
