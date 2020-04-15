package com.example.aq.Controller;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

import com.example.aq.Domain.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class RegAndLog {
    @Resource
    UserInfoMapper userInfoMapper;

    @Resource
    SubmissionsMapper submissionsMapper;

    @Resource
    ProblemMapper problemMapper;

    @Resource
    KnowledgeMapper knowledgeMapper;

    @RequestMapping("/reg")
    public String reg(UserInfo user_info) {
        userInfoMapper.add(user_info);
        return "/toLogin";
    }

    @RequestMapping("/login")
    public String log(UserInfo userInfo, HttpServletRequest request) {
        UserInfo userInfo1 = new UserInfo();
        userInfo1 = userInfoMapper.find(userInfo);
        if (!(userInfo1 == null)) {
            //System.out.println(userInfo1.toString());
            int account = userInfo1.getAccount();
            String name = userInfo1.getName();
            request.getSession().setAttribute("account", account);
            request.getSession().setAttribute("name", name);
            Integer i = userInfo1.getTea_account();
            System.out.println(i);
            if (i != 0)
                return "/toShuati";
            else
                return "/toindex2";
        } else
            return "/toLogin";
    }

    @RequestMapping("/tomain")
    public String tomain(HttpServletRequest request, HashMap<String, Object> map) {
        int a = (int) request.getSession().getAttribute("account");
        UserInfo ur = new UserInfo();
        ur.setAccount(a);
        List<Map<String, Object>> u = new ArrayList<Map<String, Object>>();
        Map<String, Object> m;
        List<UserInfo> ui = userInfoMapper.findbyid(ur);
        for (UserInfo uu : ui) {
            m = new HashMap<>();
            m.put("total_submit", uu.getTotal_submit());
            m.put("accepted", uu.getAccepted());
            m.put("score", uu.getScore());
            int rank = userInfoMapper.rank(ur);
            m.put("rank", rank);
            m.put("name", uu.getName());
            u.add(m);
        }
        map.put("u", u);

        ui = new ArrayList<>();
        ui = userInfoMapper.zongbang();
        u = new ArrayList<>();
        int tmp = 1;
        for (UserInfo uu : ui) {
            m = new HashMap<>();
            m.put("rank", tmp);
            tmp++;
            m.put("name", uu.getName());
            m.put("score", uu.getScore());
            m.put("total_submit", uu.getTotal_submit());
            u.add(m);
            if (tmp >= 11)
                break;
        }
        map.put("uu", u);

        int rank = userInfoMapper.rank(ur);
        if (rank <= 10) {
            m = new HashMap<>();
            m.put("tip", "加油！点此开始刷题保持前十！");
            map.put("uuu", m);
        } else {
            m = new HashMap<>();
            m.put("tip", "还没上榜？点此开始刷题冲向前十!");
            map.put("uuu", m);
        }

        return "index";
    }

    @RequestMapping("/toJiucuo")
    public String toJiucuo(HttpServletRequest request, HashMap<String, Object> map) {

        Map<String, Object> m = new HashMap<>();
        String name = request.getSession().getAttribute("name").toString();
        int account = (int) request.getSession().getAttribute("account");
        m.put("name", name);
        m.put("account", account);
        map.put("u", m);
        return "jiucuo";
    }

    @RequestMapping("/toShuati")
    public String toShuati(HttpServletRequest request, HashMap<String, Object> map) {

        Map<String, Object> m = new HashMap<>();
        String name = request.getSession().getAttribute("name").toString();
        int account = (int) request.getSession().getAttribute("account");
        m.put("name", name);
        m.put("account", account);
        map.put("u", m);

        m = new HashMap<>();
        List<Problem> l = problemMapper.all();
        List<Map<String, Object>> ll = new ArrayList<>();

        for (Problem p : l) {
            m = new HashMap<>();
            m.put("problem_id", p.getProblem_id());
            m.put("difficulty_level", p.getDifficulty_level());
            int knowledge_id = (int) p.getKnowledge_level();
            String kname = knowledgeMapper.name(knowledge_id);
            m.put("kname", kname);
            m.put("title", p.getTitle());
            ll.add(m);
        }
        map.put("uu", ll);
        return "shuati";
    }

    @RequestMapping("/kanti")
    public String kanti(HttpServletRequest request, HashMap<String, Object> map, Problem problem) throws IOException {
        Map<String, Object> m = new HashMap<>();
        String name = request.getSession().getAttribute("name").toString();
        int account = (int) request.getSession().getAttribute("account");
        m.put("name", name);
        m.put("account", account);
        map.put("u", m);

        m = new HashMap<>();
        List<Problem> l = problemMapper.all();
        List<Map<String, Object>> ll = new ArrayList<>();

        for (Problem p : l) {
            m = new HashMap<>();
            m.put("problem_id", p.getProblem_id());
            m.put("difficulty_level", p.getDifficulty_level());
            int knowledge_id = (int) p.getKnowledge_level();
            String kname = knowledgeMapper.name(knowledge_id);
            m.put("kname", kname);
            m.put("title", p.getTitle());
            ll.add(m);
        }
        map.put("uu", ll);

        String loc = "D:\\Project\\Exam\\Test\\";
        String tihao = String.valueOf(problem.getProblem_id());
        loc += tihao;
        loc += "\\Description.txt";
        File ff = new File(loc);
        InputStreamReader isr = new InputStreamReader(new FileInputStream(ff), "GBK");
        BufferedReader reader = new BufferedReader(isr);
        String s;
        StringBuffer sb = new StringBuffer();
        while ((s = reader.readLine()) != null) {
            sb.append(s + "\n");
        }
        reader.close();
        String title = problem.getTitle();
        m = new HashMap<>();
        m.put("biaoti", title);
        m.put("miaoshu", sb);
        map.put("kanti", m);

        return "shuati";
    }

    @RequestMapping("/toPaihangbang")
    public String toPaihangbang(HttpServletRequest request, HashMap<String, Object> map) {
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
        return "paihangbang";
    }

    @RequestMapping("/toLishijilu")
    public String toLishi(HashMap<String, Object> map, HttpServletRequest request) {

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
        return "lishijilu";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("account");
        request.getSession().removeAttribute("name");
        //System.out.println(request.getSession().getAttribute("id"));
        return "/toLogin";
    }

    @RequestMapping("toGerenzhongxin")
    public String geren(HttpServletRequest request, HashMap<String, Object> map) {
        int a = (int) request.getSession().getAttribute("account");
        UserInfo ur = new UserInfo();
        ur.setAccount(a);
        List<Map<String, Object>> u = new ArrayList<Map<String, Object>>();
        Map<String, Object> m;
        List<UserInfo> ui = userInfoMapper.findbyid(ur);
        for (UserInfo uu : ui) {
            m = new HashMap<>();
            m.put("name", uu.getName());
            m.put("zhanghao", uu.getUsername());
            u.add(m);
        }
        map.put("u", u);
        return "/profile";
    }

    @RequestMapping("toindex2")
    public String to2(HttpServletRequest request, HashMap<String, Object> map) {
        int a = (int) request.getSession().getAttribute("account");
        UserInfo ur = new UserInfo();
        ur.setAccount(a);
        List<Map<String, Object>> u = new ArrayList<Map<String, Object>>();
        Map<String, Object> m;
        List<UserInfo> ui = userInfoMapper.findbyid(ur);
        for (UserInfo uu : ui) {
            m = new HashMap<>();
            m.put("total_submit", uu.getTotal_submit());
            m.put("accepted", uu.getAccepted());
            m.put("score", uu.getScore());
            int rank = userInfoMapper.rank(ur);
            m.put("rank", rank);
            m.put("name", uu.getName());
            u.add(m);
        }
        map.put("u", u);

        ui = new ArrayList<>();
        ui = userInfoMapper.zongbang();
        u = new ArrayList<>();
        int tmp = 1;
        for (UserInfo uu : ui) {
            m = new HashMap<>();
            m.put("rank", tmp);
            tmp++;
            m.put("name", uu.getName());
            m.put("score", uu.getScore());
            m.put("total_submit", uu.getTotal_submit());
            u.add(m);
            if (tmp >= 11)
                break;
        }
        map.put("uu", u);

        int rank = userInfoMapper.rank(ur);
        if (rank <= 10) {
            m = new HashMap<>();
            m.put("tip", "加油！点此开始刷题保持前十！");
            map.put("uuu", m);
        } else {
            m = new HashMap<>();
            m.put("tip", "还没上榜？点此开始刷题冲向前十!");
            map.put("uuu", m);
        }
        return "index2";
    }

    @RequestMapping("/toBianji")
    public String toBianji(HttpServletRequest request, HashMap<String, Object> map) {

        Map<String, Object> m = new HashMap<>();
        String name = request.getSession().getAttribute("name").toString();
        int account = (int) request.getSession().getAttribute("account");
        m.put("name", name);
        m.put("account", account);
        map.put("u", m);

        m = new HashMap<>();
        List<Problem> l = problemMapper.all();
        List<Map<String, Object>> ll = new ArrayList<>();

        for (Problem p : l) {
            m = new HashMap<>();
            m.put("problem_id", p.getProblem_id());
            m.put("difficulty_level", p.getDifficulty_level());
            int knowledge_id = (int) p.getKnowledge_level();
            String kname = knowledgeMapper.name(knowledge_id);
            m.put("kname", kname);
            m.put("title", p.getTitle());
            ll.add(m);
        }
        map.put("uu", ll);
        return "bianji";
    }

}
