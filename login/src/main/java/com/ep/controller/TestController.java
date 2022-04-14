package com.ep.controller;

import com.ep.domain.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * 这是一个测试接口类
 * 用于测试接口是否能用
 * */
@Controller
public class TestController {

    @RequestMapping("/test")
    @ResponseBody
    public ResultInfo test(){
        ResultInfo info = new ResultInfo();
        info.setStatus(200);
        info.setMsg("请求成功");
        return info;
    }
}