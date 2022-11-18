package com.zp.controller;


import com.zp.RedisUtils;
import com.zp.controller.dto.LoginDTO;
import com.zp.controller.vo.LoginInfoVO;
import com.zp.controller.vo.UserVO;
import com.zp.response.R;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhengpanone
 * @Description:
 * @Date:Created in 2021/08/06 21:07.
 * @Email zhengpanone@hotmail.com
 * @Modified By:
 */
@RequestMapping("/user")
@RestController
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    RedisUtils redisUtils;

    @PostMapping("/login")
    public R<UserVO> login(@Validated @RequestBody LoginDTO loginDTO) {
        LOGGER.info("提交的用户数据为:" + loginDTO.toString());
        UserVO userVO = new UserVO();
        userVO.setUserId(1);
        userVO.setUserName(loginDTO.getUserName());
        userVO.setUserLevel(10);
        return R.success(userVO);
    }

    @PostMapping("/register")
    public R<?> register(@Validated LoginDTO loginDTO) {
        return R.success();
    }

    /**
     * 登录页 图片数据
     * @return
     */
    @GetMapping("/login/info")
    public R<LoginInfoVO> loginInfo() {
        LoginInfoVO infoVO = new LoginInfoVO();
        infoVO.setSlide(new String[]{"a","b","c"});
        infoVO.setLoginLogo("loginLogo");
        infoVO.setLogoRectangle("logo rectangle");
        infoVO.setLogoSquare("logo square");
        return R.success(infoVO);
    }
    @GetMapping("/redis")
    public R<?> testRedis(){
        redisUtils.set("test","test");
        return R.success();
    }

    @GetMapping("/redis/{key}")
    public R<?> testRedisKey(@PathVariable String key){
        Object value = redisUtils.get(key);
        Map<String,Object> data = new HashMap<String, Object>();
        data.put(key, value);
        return R.success(data);
    }
}
