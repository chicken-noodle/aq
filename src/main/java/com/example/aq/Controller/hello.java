package com.example.aq.Controller;

import com.example.aq.Domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class hello {

    @Resource
    codeInfoMapper c;

    @Resource
    UserInfoMapper userInfoMapper;

    @Resource
    SubmissionsMapper submissionsMapper;

    @Resource
    ProblemMapper problemMapper;

    @Resource
    KnowledgeMapper knowledgeMapper;

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/toRegister")
    public String toRegister() {
        return "register";
    }

    @RequestMapping("/ceshi")
    public String test() throws IOException, InterruptedException {
        String id = "1";
        String exe = "python";
        String command = "D:\\user_info.py";
        String[] cmdArr = new String[]{exe, command, id};
        Process process = Runtime.getRuntime().exec(cmdArr);
        process.waitFor();
        return "hello";
    }

    @RequestMapping("shanchu")
    public String shanchu() {
        File fss = new File("G:\\IDEA\\aq\\cod.exe");
        if (fss.exists()) {
            fss.delete();
        }
        System.out.println(fss.exists());
        return "/toLogin";
    }

    @RequestMapping("/tijiao")
    public String tijiao(String code, HttpServletRequest request, HashMap<String, Object> map) throws IOException, InterruptedException {
        /*String exe = "python";
        String command = "D:\\test.py";
        String[] cmdArr = new String[] {exe,command,code};
        Process process = Runtime.getRuntime().exec(cmdArr);
        InputStream is = process.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        String str = dis.readLine();
        process.waitFor();*/

        /*String filename = "";
        filename +=request.getSession().getAttribute("account").toString();
        filename +="-";
        File ff = new File("D:\\read.txt");
        InputStreamReader isr = new InputStreamReader(new FileInputStream(ff),"GBK");
        BufferedReader reader = new BufferedReader(isr);
        String a = reader.readLine();
        filename += a;
        filename +="-";

        Submissions submissions = new Submissions();
        submissions.setPid(Integer.valueOf(a));
        submissions.setUid((int)request.getSession().getAttribute("account"));
        submissionsMapper.add(submissions);
        int b = submissionsMapper.tot(submissions);
        submissions.setTimes(b);
        submissionsMapper.times(submissions);
        filename+=String.valueOf(b);*/

        UserInfo userInfo = new UserInfo();
        int account = (int) request.getSession().getAttribute("account");
        userInfo.setAccount(account);
        int total_submit = userInfoMapper.zongtijiao(userInfo);
        total_submit += 1;
        userInfo.setTotal_submit(total_submit);
        userInfoMapper.gengxin(userInfo);

        File f = new File("D:\\Project\\Exam\\Temp\\test.txt");
        FileOutputStream fos1 = new FileOutputStream(f);
        OutputStreamWriter dos1 = new OutputStreamWriter(fos1, "utf-8");
        dos1.write(code);
        dos1.close();


        String exe = "python";
        String command = "D:\\py\\CodeCheck.py";
        String[] cmdArr = new String[]{exe, command};
        Process process = Runtime.getRuntime().exec(cmdArr);
        fos1.close();
        dos1.close();
        process.waitFor();


        File ff = new File("D:\\Project\\Exam\\Temp\\answer.txt");
        InputStreamReader isr = new InputStreamReader(new FileInputStream(ff), "GBK");
        BufferedReader reader = new BufferedReader(isr);
        int a = Integer.valueOf(reader.readLine());
        int b = Integer.valueOf(reader.readLine());
        int c = Integer.valueOf(reader.readLine());
        int d = Integer.valueOf(reader.readLine());
        String s = reader.readLine();
        System.out.println(s);
        Submissions submissions = new Submissions();
        submissions.setUid(account);
        submissions.setPid(a);
        submissionsMapper.add(submissions);
        int tot = submissionsMapper.tot(submissions);
        submissions.setTimes(tot);
        submissionsMapper.times(submissions);

        int flg = -1;
        String tihao = String.valueOf(a);
        String cuowuleixing = "";
        if (b == 1) {
            cuowuleixing = "出现语法错误！";
            flg = 0;
        } else if (d == 1) {
            if (s.equals("语法正确")) {
                cuowuleixing = "代码正确！";
                flg = 1;
            } else {
                cuowuleixing = "出现逻辑错误！";
                flg = 2;
            }

        }
        String jianyi;
        if (flg == 0) {
            jianyi = "仔细检查语法是否正确！";
            submissions.setStatus(0);
        } else if (flg == 1) {
            jianyi = "再接再厉，多刷题，更上一层楼！";
            submissions.setStatus(1);
            submissionsMapper.status(submissions);
            int accept = userInfoMapper.accept(userInfo);
            accept += 1;
            userInfo.setAccepted(accept);
            userInfoMapper.accepted(userInfo);

            int score = userInfoMapper.sco(userInfo);
            score += 5;
            int tmp = userInfoMapper.sco(userInfo);
            userInfo.setScore(score);
            userInfoMapper.score(userInfo);
        } else {
            jianyi = s;
        }
        isr.close();
        reader.close();
        Map<String, Object> m = new HashMap<>();
        String name = request.getSession().getAttribute("name").toString();
        account = (int) request.getSession().getAttribute("account");
        m.put("name", name);
        m.put("account", account);
        m.put("daima", code);
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
        m.put("cuowuleixing", cuowuleixing);
        m.put("jianyi", jianyi);
        map.put("tip", m);
        return "Shuati";
    }

    @RequestMapping("/findmima")
    public String findmima(UserInfo userInfo) {
        userInfoMapper.gaimima(userInfo);
        return "/toLogin";
    }

    @RequestMapping("/gaimima")
    public String gaimima(UserInfo userInfo, HttpServletRequest request) {
        userInfo.setAccount((Integer) request.getSession().getAttribute("account"));
        userInfoMapper.gmima(userInfo);
        return "/logout";
    }


    @RequestMapping("/tijiao1")
    public String tijiao1(String code, HttpServletRequest request, HashMap<String, Object> map) throws IOException, InterruptedException {

        /*String exe = "python";
        String command = "D:\\test.py";
        String[] cmdArr = new String[] {exe,command,code};
        Process process = Runtime.getRuntime().exec(cmdArr);
        InputStream is = process.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        String str = dis.readLine();
        process.waitFor();*/

        /*String filename = "";
        filename +=request.getSession().getAttribute("account").toString();
        filename +="-";
        File ff = new File("D:\\read.txt");
        InputStreamReader isr = new InputStreamReader(new FileInputStream(ff),"GBK");
        BufferedReader reader = new BufferedReader(isr);
        String a = reader.readLine();
        filename += a;
        filename +="-";

        Submissions submissions = new Submissions();
        submissions.setPid(Integer.valueOf(a));
        submissions.setUid((int)request.getSession().getAttribute("account"));
        submissionsMapper.add(submissions);
        int b = submissionsMapper.tot(submissions);
        submissions.setTimes(b);
        submissionsMapper.times(submissions);
        filename+=String.valueOf(b);*/

        UserInfo userInfo = new UserInfo();
        int account = (int) request.getSession().getAttribute("account");
        userInfo.setAccount(account);
        int total_submit = userInfoMapper.zongtijiao(userInfo);
        total_submit += 1;
        userInfo.setTotal_submit(total_submit);
        userInfoMapper.gengxin(userInfo);

        File f = new File("D:\\Project\\Exam\\Temp\\test.txt");
        f.delete();
        FileOutputStream fos1 = new FileOutputStream(f);
        OutputStreamWriter dos1 = new OutputStreamWriter(fos1, "utf-8");
        dos1.write(code);
        dos1.close();
        String exe = "python";
        String command = "D:\\py\\CodeCheck.py";
        String[] cmdArr = new String[]{exe, command};
        Process process = Runtime.getRuntime().exec(cmdArr);
        fos1.close();
        dos1.close();
        process.waitFor();
        //Thread.sleep(1000);

        File ff = new File("D:\\Project\\Exam\\Temp\\answer.txt");
        InputStreamReader isr = new InputStreamReader(new FileInputStream(ff), "GBK");
        BufferedReader reader = new BufferedReader(isr);
        int a = Integer.valueOf(reader.readLine());
        int b = Integer.valueOf(reader.readLine());
        int c = Integer.valueOf(reader.readLine());
        int d = Integer.valueOf(reader.readLine());
        String s = reader.readLine();
        System.out.println(s);
        Submissions submissions = new Submissions();
        submissions.setUid(account);
        submissions.setPid(a);
        submissionsMapper.add(submissions);
        int tot = submissionsMapper.tot(submissions);
        submissions.setTimes(tot);
        submissionsMapper.times(submissions);

        int flg = -1;
        String tihao = String.valueOf(a);
        String cuowuleixing = "";
        if (b == 1) {
            cuowuleixing = "出现语法错误！";
            flg = 0;
        } else if (d == 1) {
            if (s.equals("语法正确")) {
                cuowuleixing = "代码正确！";
                flg = 1;
            } else {
                cuowuleixing = "出现逻辑错误！";
                flg = 2;
            }

        }
        String jianyi;
        if (flg == 0) {
            jianyi = "仔细检查语法是否正确！";
        } else if (flg == 1) {
            jianyi = "再接再厉，多刷题，更上一层楼！";
            submissions.setStatus(1);
            submissionsMapper.status(submissions);
            int accept = userInfoMapper.accept(userInfo);
            accept += 1;
            userInfo.setAccepted(accept);
            userInfoMapper.accepted(userInfo);

            int score = userInfoMapper.sco(userInfo);
            score += 5;
            int tmp = userInfoMapper.sco(userInfo);
            userInfo.setScore(score);
            userInfoMapper.score(userInfo);
        } else {
            jianyi = s;
        }
        isr.close();
        reader.close();
        Map<String, Object> m = new HashMap<>();
        String name = request.getSession().getAttribute("name").toString();
        account = (int) request.getSession().getAttribute("account");
        m.put("name", name);
        m.put("account", account);
        m.put("daima", code);
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
        m.put("cuowuleixing", cuowuleixing);
        m.put("jianyi", jianyi);
        map.put("tip", m);

        File ffs = new File("G:\\IDEA\\aq\\code.exe");
        ffs.delete();

        return "jiucuo";
    }

    @RequestMapping("kankan")
    public String kankan(HttpServletRequest request, Problem problem, HashMap<String, Object> map) throws IOException {

        Integer ii = problem.getProblem_id();
        request.getSession().setAttribute("pid", ii);

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

        /*String loc = "D:\\Project\\Exam\\Test\\";
        String tihao = String.valueOf(problem.getProblem_id());
        loc+=tihao;
        loc+="\\Description.txt";
        File ff = new File(loc);
        InputStreamReader isr = new InputStreamReader(new FileInputStream(ff),"GBK");
        BufferedReader reader = new BufferedReader(isr);
        String s;
        StringBuffer sb = new StringBuffer();
        while((s=reader.readLine())!= null){
            sb.append(s+"\n");
        }
        reader.close();*/
        String title = problem.getTitle();
        String sb = problemMapper.Byid(problem);
        m = new HashMap<>();
        m.put("biaoti", title);
        m.put("miaoshu", sb);
        map.put("kanti", m);
        return "/toBianji";
    }

    @RequestMapping("bian")
    public String bian(HttpServletRequest request, Problem problem) {
        Integer ii = (Integer) request.getSession().getAttribute("pid");
        problem.setProblem_id(ii);
        problemMapper.geng(problem);
        return "/toBianji";
    }

    @RequestMapping("jiati")
    public String jia(Problem problem) {
        problemMapper.ins(problem);
        return "/toBianji";
    }
}
