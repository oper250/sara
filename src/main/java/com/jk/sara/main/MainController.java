package com.jk.sara.main;

import com.jk.sara.common.CommonController;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping( value = "/main" )
public class MainController extends CommonController {

    @Autowired
    private MainService mainService;

    public MainController() {
        super(MainController.class);
    }

    @ResponseBody
    @SuppressWarnings("unchecked")
    @PostMapping( value = "/login" )
    public Map<String, Object> login(@RequestBody Map<String, Object> paramMap, HttpServletRequest request) throws Exception {
        Map<String, Object> rsMap = new HashMap<String, Object>();

        // 로그인 프로세스
        String userSeq = mainService.login(paramMap);       // 사용자 시퀀스

        // 세션 SET
        request.getSession().setAttribute("userSeq", userSeq);

        rsMap.put("userSeq", userSeq);
        System.out.println("~~~~~~~~~~~~~~");
        log.trace(">>>>> 로그인 성공");
        log.error(">>>>> 로그인 성공");
        log.debug(">>>>> 로그인 성공");
        log.info(">>>>> 로그인 성공");

        return super.getResponse(rsMap);
    }

    /* 로그인 */
    @ResponseBody
    @PostMapping( value = "/getBucketList" )
    public Map<String, Object> getBucketList(@RequestBody Map<String, Object> paramMap, HttpServletRequest request) throws Exception {
        Map<String, Object> rsMap;

        rsMap = mainService.getBucketList(paramMap);

        rsMap.put("rsList", rsMap.get("rsList"));
        rsMap.put("moreYn", rsMap.get("moreYn"));
        rsMap.put("nextStno", rsMap.get("nextStno"));

        return super.getResponse(rsMap);
    }

}
