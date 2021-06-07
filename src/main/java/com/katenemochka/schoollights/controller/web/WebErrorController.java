package com.katenemochka.schoollights.controller.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class WebErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            model.addAttribute("error_code", RequestDispatcher.ERROR_STATUS_CODE);
            model.addAttribute("error_message", RequestDispatcher.ERROR_MESSAGE);
            model.addAttribute("error_exception", RequestDispatcher.ERROR_EXCEPTION);
            model.addAttribute("error_uri", RequestDispatcher.ERROR_REQUEST_URI);

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error-404";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error-500";
            }
            else return "error";
        }
        return "error";
    }

    public String getErrorPath() {
        return null;
    }
}
