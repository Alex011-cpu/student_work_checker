package ru.mirea.student_work_checker.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AuthController {

    /**
     * GET-запрос для страницы входа
     * @param model
     * @return наименование html страницы String
     */
    @GetMapping("/auth/login")
    public String getLoginPage(Model model) {
        return "login";
    }

    /**
     * GET-запрос для страницы входа с ошибкой
     * @param model
     * @return наименование html страницы String
     */
    @GetMapping("/auth/loginError")
    public String getLoginPageWithError(Model model) {
        model.addAttribute("errorMessage","Неправильная почта или пароль");
        return "login";
    }

    /**
     * GET-запрос для страницы после выхода
     * @param request
     * @param response
     * @return наименование html страницы String
     */
    @GetMapping("/auth/logout")
    public String logoutFromAc(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            Cookie cookie = null;
            for (Cookie c:request.getCookies()) {
                /*Удаление куки*/
                if (c.getName().equals("remember-me")) {
                    c.setMaxAge(0);
                    c.setPath("/");
                    response.addCookie(c);
                }
                if (c.getName().equals("JSESSIONID")) {
                    c.setMaxAge(0);
                    c.setPath("/");
                    response.addCookie(c);
                }
            }
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/";
    }
}
