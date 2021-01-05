package jp.co.demo.controller.M04;

import jp.co.demo.common.RequestPathConst;
import jp.co.demo.common.ScreenPathConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * M04Controller
 */
@Controller
@Slf4j
public class M04Controller {

    @GetMapping(RequestPathConst.M04)
    public String list() {
        return ScreenPathConst.M04_SCREEN;
    }
}
