package jp.co.demo.controller.M03;

import jp.co.demo.common.RequestPathConst;
import jp.co.demo.common.ScreenPathConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class M03Controller {
    @GetMapping(RequestPathConst.M03)
    public String listUser() {
        return ScreenPathConst.M03_SCREEN;
    }
}
