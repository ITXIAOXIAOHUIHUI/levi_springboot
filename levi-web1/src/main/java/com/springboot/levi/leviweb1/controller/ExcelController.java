package com.springboot.levi.leviweb1.controller;

import com.springboot.levi.leviweb1.event.EventDto;
import com.springboot.levi.leviweb1.event.service.EventService;
import com.springboot.levi.leviweb1.model.ExcelModel;
import com.springboot.levi.leviweb1.model.Response;
import com.springboot.levi.leviweb1.service.ExcelService;
import com.springboot.levi.leviweb1.utils.ExcelUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jxl.Workbook;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-07-22 11:47
 */
@RestController
@RequestMapping(value = "/levi/excel")
@Api(tags = "excel工具")
@Slf4j
public class ExcelController {

    @Resource
    private ExcelService excelService;
    @Resource
    private EventService eventService;

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public Response importRoadWayData(MultipartFile file,
                                      HttpServletRequest request) throws IOException {
        Workbook workbook = ExcelUtils.getWorkbook(file.getInputStream());
        String lang = getLanguageFromCookie(request);
        LinkedHashMap<String, String> headMap = excelService.getFiledMapping(lang, ExcelModel.class);
        StringBuilder sb = new StringBuilder();
        for(int i= 0;i < 2;i++){
            List<ExcelModel> excelModels = ExcelUtils.sheetToList(workbook, i, ExcelModel.class, headMap, null);
            for(ExcelModel excelModel : excelModels){
                sb.append("update evo_basic.basic_container set slot_code = "+"'"+excelModel.getSlotCode()+"'"+ "where slot_code="
                        +"'"+excelModel.getSlotCode()+"';");
                sb.append("\n");
            }
        }
        FileUtils.write(new File("D:\\update.txt"), sb.toString(), "utf8", false);
        return new Response();
    }


    private String getLanguageFromCookie(HttpServletRequest request) {
        /*for (Cookie cookie : request.getCookies()) {
            if (Objects.equals(cookie.getName(), "local")) {
                return cookie.getValue();
            }
        }*/
        return "zh-cn";
    }

}
