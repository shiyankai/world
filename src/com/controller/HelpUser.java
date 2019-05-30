package com.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mybatis.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HelpUser {
	@RequestMapping("/getMsage.do")  // 请求url地址映射，类似Struts的action-mapping
    public ModelAndView getMsage(String name,String phoneNum,String wxNum,String contactMessage,HttpServletRequest request) throws IOException {
		if(phoneNum == null &&wxNum == null){
			return new ModelAndView(new RedirectView("index.html")); 
		}
		File file = new File("/home/ftptest/custMsg");
		FileWriter fw = new FileWriter(file, true);
		PrintWriter pw = new PrintWriter(fw);
		Calendar c = Calendar.getInstance();
    	pw.println(c.getTime());
		pw.println("姓名："+name+"电话号码："+phoneNum+"微信号码:"+wxNum+"留言"+contactMessage);
		try {
			pw.flush();
			pw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ModelAndView(new RedirectView("index.jsp")); 
    }
	
    @RequestMapping("/help_user.do")  // 请求url地址映射，类似Struts的action-mapping
    public String testLogin(@RequestParam(value="username")String username, @RequestParam("password")String password, HttpServletRequest request) {
        // @RequestParam是指请求url地址映射中必须含有的参数(除非属性required=false)
        // @RequestParam可简写为：@RequestParam("username")
    	if (!"admin".equals(username) || !"admin".equals(password)) {
            return "loginError"; // 跳转页面路径（默认为转发），该路径不需要包含spring-servlet配置文件中配置的前缀和后缀
        }
        return "loginSuccess";
    }

    @RequestMapping("/test/login2.do")
    public ModelAndView testLogin2(String username, String password, int age){
        // request和response不必非要出现在方法中，如果用不上的话可以去掉
        // 参数的名称是与页面控件的name相匹配，参数类型会自动被转换

        if (!"admin".equals(username) || !"admin".equals(password) || age < 5) {
            return new ModelAndView("loginError"); // 手动实例化ModelAndView完成跳转页面（转发），效果等同于上面的方法返回字符串
        }
        return new ModelAndView(new RedirectView("../index.jsp"));  // 采用重定向方式跳转页面
        // 重定向还有一种简单写法
        // return new ModelAndView("redirect:../index.jsp");
    }

    @RequestMapping("/test/login3.do")
    public ModelAndView testLogin3(User user) {
        // 同样支持参数为表单对象，类似于Struts的ActionForm，User不需要任何配置，直接写即可
        String username = user.getName();
        String password = user.getPassWord();
        int age = user.getAge();

        if (!"admin".equals(username) || !"admin".equals(password) || age < 5) {
            return new ModelAndView("loginError");
        }
        return new ModelAndView("loginSuccess");
    }

/*@Resource(name = "loginService")  // 获取applicationContext.xml中bean的id为loginService的，并注入
    private LoginService loginService;  //等价于spring传统注入方式写get和set方法，这样的好处是简洁工整，省去了不必要得代码

    @RequestMapping("/test/login4.do")
    public String testLogin4(User user) {
        if (loginService.login(user) == false) {
            return "loginError";
        }
        return "loginSuccess";
    }*/

    
    @RequestMapping("/count.do")  // 请求url地址映射，类似Struts的action-mapping
    public void count(HttpServletResponse response) {
    	try {
			response.getWriter().print("{\"abc\":123}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    public static void main(String[] args) throws IOException {
    	Calendar c = Calendar.getInstance();
    	System.out.println(c.getTime());
	}
}
