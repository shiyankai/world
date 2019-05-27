package controller;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("service")
public class ProvideServices {
    @RequestMapping("/find/name.do")
    public ModelAndView testLogin2(String username, String password, int age){
        if (!"admin".equals(username) || !"admin".equals(password) || age < 5) {
            return new ModelAndView("error"); // 手动实例化ModelAndView完成跳转页面（转发），效果等同于上面的方法返回字符串
        }
        return new ModelAndView(new RedirectView("../index.jsp"));  // 采用重定向方式跳转页面
        // 重定向还有一种简单写法
        // return new ModelAndView("redirect:../index.jsp");
    }
   @RequestMapping("name.do")
   @ResponseBody
    public String hello(HttpServletResponse response) throws IOException {
        Map<String,String> map=new HashMap<String, String>();
        map.put("001", "222");
        map.put("002", "22233");
        String jsonString = JSON.toJSONString(map);
        return jsonString;
    }
}
