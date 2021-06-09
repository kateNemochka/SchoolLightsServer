package com.katenemochka.schoollights.controller.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
public class WebErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Throwable exception = (Throwable)request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            model.addAttribute("error_code", statusCode);

            if (exception != null) {
                String message = exception.getMessage().isEmpty() ? "" : exception.getMessage();
                String cause = exception.getCause().getMessage();
                String stackTrace = Arrays.stream(exception.getStackTrace()).limit(5).collect(Collectors.toList()).toString();
                String uri = request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI).toString();
                model.addAttribute("error_message", message);
                model.addAttribute("cause", cause);
                model.addAttribute("error_stack_trace", stackTrace);
                model.addAttribute("error_uri", uri);
            }

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error-404";
            }
            else return "error";
        }
        return "error";
    }

    public String getErrorPath() {
        return null;
    }
}
