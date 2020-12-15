package com.example.demo.controller;

import com.example.demo.dao.LinkerRepository;
import com.example.demo.data.AddData;
import com.example.demo.data.LoginData;
import com.example.demo.data.TableData;
import com.example.demo.entity.Linker;
import com.example.demo.model.CheckLogin;
import com.example.demo.model.TableAlters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class WebController {
    private LinkerRepository linkerRepository;
    @Autowired
    public void setLinkerRepository(LinkerRepository linkerRepository){
        this.linkerRepository=linkerRepository;
    }

    @RequestMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @RequestMapping("/login")
    public String login(LoginData login, Model model, HttpServletRequest request) {
        model.addAttribute("login", login);
        if (CheckLogin.Check(login)) {
            request.getSession().setAttribute("login", "ok");
            return "redirect:/format";
        }
        else
            return "login";
    }

    @GetMapping("/format")
    public String showFormat(HttpServletRequest request) {
        Object flag = request.getSession().getAttribute("login");
        if (flag != null) {
            HttpSession session = request.getSession();
            session.removeAttribute("table");
            TableData tableData = new TableData();
            List<Linker> linkerList = (List<Linker>) linkerRepository.findAll();
            for(int i=linkerList.size()-1;i>=0;i--){
                linkerRepository.deleteById(linkerList.get(i).getId());
            }
            for(int i=0;i<linkerList.size();i++){
                AddData addData = new AddData(
                        linkerList.get(i).getUsername(),
                        linkerList.get(i).getTel(),
                        linkerList.get(i).getEmail(),
                        linkerList.get(i).getAddr(),
                        linkerList.get(i).getQq());
                tableData.getTabledata().addElement(addData);
                Linker linker = new Linker(
                        (long)(i+1),
                        linkerList.get(i).getUsername(),
                        linkerList.get(i).getTel(),
                        linkerList.get(i).getEmail(),
                        linkerList.get(i).getAddr(),
                        linkerList.get(i).getQq());
                linkerRepository.save(linker);
            }
            session.setAttribute("table",tableData);
            return "format";
        } else
            return "redirect:/login";
    }

    @RequestMapping("/add")
    public String add(AddData add, Model model) {
        model.addAttribute("add", add);
        return "add";
    }

    @PostMapping("/alter")
    public String showAlter(HttpServletRequest request, @ModelAttribute(value = "row") Integer row, LoginData m, Model model) {
        linkerRepository.deleteById(Long.valueOf(row+1));
        TableData t = (TableData) request.getSession().getAttribute("table");
        AddData info = t.getTabledata().elementAt(row);
        model.addAttribute("add", info);
        return "alter";
    }

    // 直接访问修改联系人 --- 直接弹回去
    @GetMapping("/alter")
    public String redirectAlter() {
        return "redirect:/format";
    }

    // 直接访问判断联系人修改 直接重定向回去
    @GetMapping("/checkalter")
    public String redirectCheckAlter() {
        return "redirect:/format";
    }

    // 修改联系人信息 （因为名字不能修改 所以一定可以修改成功）
    @PostMapping("/checkalter")
    public String checkAlter(AddData add, HttpServletRequest request) {
        TableData t = (TableData) request.getSession().getAttribute("table");
        t.getTabledata().addElement(add);
        int count = t.getTabledata().size()+1;
        Linker linker=new Linker(
                (long)count,
                add.getUsername(),
                add.getTel(),
                add.getEmail(),
                add.getAddr(),
                add.getQq());
        linkerRepository.save(linker);
        return "redirect:/format";
    }

    // 删除联系人，不需要返回view 直接使用model 进行处理 如果是直接请求，必然是逻辑错误 直接跳转回去
    @GetMapping("/del")
    public String redirectDel() {
        return "redirect:/format";
    }

    // 删除联系人
    @PostMapping("/del")    // ModelAttribute 可以理解成随请求一起发过来的参数Param
    public String DeleteContact(@ModelAttribute(value = "row") Integer row, HttpServletRequest request) {
        linkerRepository.deleteById(Long.valueOf(row+1));
        return "redirect:/format";
    }

    // 处理添加的URL 如果不是通过post请求的说明是手动请求的，将跳转回去，否则进行处理
    @GetMapping("/checkadd")
    public String redirectAdd() {
        return "add";
    }

    // 处理添加的URL 如果是通过POST提交的，则进行处理
    @PostMapping("/checkadd")
    public String checkAdd(AddData add, HttpServletRequest request, Model model) {
        TableData t = (TableData) request.getSession().getAttribute("table");
        boolean is_valid = TableAlters.checkValidAdd(t, add);
        if (true == is_valid) {
            int count=t.getTabledata().size()+1;
            t.getTabledata().addElement(add);
            Linker linker = new Linker(
                    (long)count,
                    add.getUsername(),
                    add.getTel(),
                    add.getEmail(),
                    add.getAddr(),
                    add.getQq());
            linkerRepository.save(linker);
            return "redirect:/format";
        } else {
            add.setUsername("");
            return add(add, model);
        }
    }
    @ResponseBody
    @PostMapping("/checkTel")
    public int checkTel(@RequestParam(name="tel")String tel, HttpServletRequest request) {
        TableData table = (TableData) request.getSession().getAttribute("table");
        boolean result = TableAlters.hasTel(linkerRepository.findAll(), tel);
        System.out.println("result*******************"+result);
        if (result)
            return 1;
        else
            return 0;
    }
}
