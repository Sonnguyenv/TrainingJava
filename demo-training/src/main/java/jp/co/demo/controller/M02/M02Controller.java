package jp.co.demo.controller.M02;

import jp.co.demo.common.RequestPathConst;
import jp.co.demo.common.ScreenPathConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class M02Controller {
    @GetMapping(RequestPathConst.M02)
    public String list() {
        return ScreenPathConst.M02_SCREEN;
    }
}
