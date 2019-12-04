package cn.lhz.sso.controller;

import cn.lhz.common.entity.User;
import cn.lhz.common.utils.*;
import cn.lhz.sso.service.LoginService;
import cn.lhz.sso.service.consumer.RedisService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.*;
import java.util.UUID;

/**
 * @author Neo
 * @date 2019/12/1 20:58
 */
@Controller
public class LoginController
{
    @Autowired
    private LoginService loginService;

    @Autowired
    private RedisService redisService;

    public LoginController()
    {
    }

    @RequestMapping("/login")
    public String login(@RequestParam(required = false) String url, HttpServletRequest request, Model model)
    {
        String token = CookieUtils.getCookieValue(request, "token");
        if (StringUtils.isNotBlank(token)){
            String loginCode = redisService.get(token);
            if (StringUtils.isNotBlank(loginCode)){
                String json = redisService.get(loginCode);
                if (StringUtils.isNotBlank(json)){
                    try
                    {
                        User user = MapperUtils.json2pojo(json, User.class);
                            if (StringUtils.isNotBlank(url))
                                return "redirect:"+url;
                            model.addAttribute("user",user);

                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(String loginCode, String password, @RequestParam(required = false) String url, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
    {
        User user = loginService.login(loginCode, password);
        if (user==null){
            redirectAttributes.addFlashAttribute("message","用户名或密码错误，请重新输入！");
        }else {
            String token = UUID.randomUUID().toString();
            String result = redisService.put(token, loginCode, 60 * 60 * 24);
            if ("ok".equals(result)){
                CookieUtils.setCookie(request,response,"token",token,60*60*24);
                if (StringUtils.isNotBlank(url))
                           return "redirect:"+url;
            }else {
                redirectAttributes.addFlashAttribute("message","服务器异常，请稍后再试！");
            }


        }


        return "redirect:/login";
    }
}
