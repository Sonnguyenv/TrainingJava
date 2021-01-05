package jp.co.demo.controller;

import jp.co.demo.common.RequestPathConst;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
public class CustomErrorController implements ErrorController {

    @GetMapping(value = RequestPathConst.PAGE_ERROR)
    public ModelAndView handleAllError(HttpServletResponse response) {
        switch (response.getStatus()) {
            case HttpStatus.SC_NOT_FOUND:
                return new ModelAndView(RequestPathConst.PAGE_NOT_FOUND);
            case HttpStatus.SC_FORBIDDEN:
                return new ModelAndView(RequestPathConst.PAGE_FORBIDDEN);
            default:
                return new ModelAndView(RequestPathConst.ALL_ERROR);
        }
    }

    @Override
    public String getErrorPath() {
        return RequestPathConst.PAGE_ERROR;
    }
}
