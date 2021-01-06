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
    public String listUser() {
        return ScreenPathConst.M02_SCREEN;
    }

    @GetMapping(RequestPathConst.M02_01)
    public String createUser() {
        return ScreenPathConst.M02_01_SCREEN;
    }

    @GetMapping(RequestPathConst.M02_02)
    public String detailUser() {
        return ScreenPathConst.M02_02_SCREEN;
    }
}
