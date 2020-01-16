package com.levi.springboot.email.controller;

import com.levi.springboot.email.entity.CmsPageResult;
import com.levi.springboot.email.entity.CommonCode;
import com.levi.springboot.email.mapper.EmailInfo;
import com.levi.springboot.email.mapper.EmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author jianghaihui
 * @date 2020/1/10 15:18
 */
@RestController
@RequestMapping("/levi/email")
@Api(tags = "邮件接收")
public class EmailController {

    private Logger logger = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    private EmailService emailService;
    @PostMapping("/add")
    @ApiOperation(value = "收件人添加", notes = "收件人添加")
    @ResponseBody
    public CmsPageResult addEmail(@RequestBody EmailInfo emailInfo){
        try{
            int result = emailService.insertServer(emailInfo);
            if(result >= 1){
                return new CmsPageResult(CommonCode.SUCCESS);
            }else{
                return new CmsPageResult(CommonCode.FAIL);
            }
        }catch (Exception e){
            return new CmsPageResult(CommonCode.FAIL);
        }
    }
}
