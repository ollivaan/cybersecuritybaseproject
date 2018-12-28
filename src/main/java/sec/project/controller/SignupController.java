package sec.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Signup;

import sec.project.repository.Queries;

@Controller
public class SignupController {


    private List<String> list;
    public SignupController() {
        this.list = new ArrayList<>();
    }
    
    @RequestMapping("*")
    public String defaultMapping(Model model, HttpSession httpSession) {
        return "redirect:/form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String loadForm(Model model, HttpSession httpSession) {
        return "form";
    }
    @RequestMapping(value = "/secretforum", method = RequestMethod.GET)
    public String loadSecret(@RequestParam String url) throws SQLException {
        return "redirect:" + url;

    }
    @RequestMapping("/secretforum")
    public String home(Model model, @RequestParam(required = false) String content) {
        if (content != null && !content.trim().isEmpty()) {
                        if(list.size()>=10) {
                        list.remove(0);
            }
                        this.list.add(content.trim());
        }
            
        

        model.addAttribute("list", list);

        return "secretforum";
    }    

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String submitForm(@RequestParam String username, @RequestParam String password) throws SQLException {
        Queries db = new Queries();
        try {
            Signup login = db.getAccount(username, password);
            if (login != null) {
                return "secretforum";
            }
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}