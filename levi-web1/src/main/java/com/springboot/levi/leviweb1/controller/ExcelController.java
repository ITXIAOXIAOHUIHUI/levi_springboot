package com.springboot.levi.leviweb1.controller;

import com.google.common.collect.*;
import com.springboot.levi.leviweb1.event.service.EventService;
import com.springboot.levi.leviweb1.model.ExcelModel;
import com.springboot.levi.leviweb1.model.Response;
import com.springboot.levi.leviweb1.service.ExcelService;
import com.springboot.levi.leviweb1.utils.ExcelUtils;
import io.swagger.annotations.Api;
import jxl.Workbook;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.json.JSONObject;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.LongStream;

import org.json.JSONObject;

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
        for (int i = 0; i < 2; i++) {
            List<ExcelModel> excelModels = ExcelUtils.sheetToList(workbook, i, ExcelModel.class, headMap, null);
            for (ExcelModel excelModel : excelModels) {
                sb.append("update evo_basic.basic_container set slot_code = " + "'" + excelModel.getSlotCode() + "'" + "where slot_code="
                        + "'" + excelModel.getSlotCode() + "';");
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

    public static void main(String[] args) throws IOException {
        /*String filePath = "D:\\test\\agvstatus.txt";
        StringBuilder jsonString = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                jsonString.append(line);
            }
            bufferedReader.close();
            JSONObject jsonObject = new JSONObject(jsonString.toString());
            String data = jsonObject.getString("data");
            List<Float> M5bListPower = new ArrayList<>();
            List<Float> C56List = new ArrayList<>();
            List<Float> M60List = new ArrayList<>();
            JSONArray jsonArray  = new JSONArray(data);
            StringBuffer M5bsb = new StringBuffer();
            StringBuffer C56sb = new StringBuffer();
            StringBuffer M60sb = new StringBuffer();
            int m5b =0;
            int c56 = 0;
            int m60 =0;
            for(int i=0 ; i < jsonArray .length() ; i++){
                JSONObject objectJson = jsonArray.getJSONObject(i);
                String onlineState = objectJson.getString("onlineState");
                if(Objects.equals(onlineState,"UNREGISTERED")){
                    //System.out.println("UNREGISTEREDUNREGISTEREDUNREGISTERED");
                    continue;
                }
                String power = objectJson.getString("power");
                String agvBasicVO = objectJson.getString("agvBasicVO");
                JSONObject agvBasicVOJson = new JSONObject(agvBasicVO);
                String agvTypeId = agvBasicVOJson.getString("agvTypeId");
                String agvCode = agvBasicVOJson.getString("agvCode");
                if(agvTypeId.equals("235")){
                    ++m5b;
                    M5bListPower.add(Float.parseFloat(power));
                    M5bsb.append("'"+agvCode+"',");
                }else if(agvTypeId.equals("240")){
                    ++c56;
                    C56List.add(Float.parseFloat(power));
                    C56sb.append("'"+agvCode+"',");
                }else if(agvTypeId.equals("239")){
                    ++m60;
                    M60List.add(Float.parseFloat(power));
                    M60sb.append("'"+agvCode+"',");
                }
            }
            System.out.println(M5bsb);
            System.out.println(C56sb);
            System.out.println(M60sb);
            System.out.println(m5b);
            System.out.println(c56);
            System.out.println(m60);

            double sum = 0;
            for(Float d : M5bListPower){
                sum += d;
                System.out.println(sum+"sumsumsumsum");
            }
            System.out.println("average+++"+M5bListPower.stream().mapToDouble(Float::doubleValue).average().orElse(Double.NaN));
            System.out.println(M5bListPower.size()+"2222"+sum);
            OptionalDouble average = M5bListPower.stream().mapToDouble(Float::doubleValue).average();
            if (average.isPresent()) {
                System.out.println("m5b Average of elements: " + average.getAsDouble());
            }
            OptionalDouble c56Average = C56List.stream().mapToDouble(Float::floatValue).average();
            if (c56Average.isPresent()) {
                System.out.println("c56 Average of elements: " + c56Average.getAsDouble());
            }
            OptionalDouble m60average = M60List.stream().mapToDouble(Float::floatValue).average();
            if (m60average.isPresent()) {
                System.out.println("m60 Average of elements: " + m60average.getAsDouble());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }*/

        /*List<String> test = Lists.newArrayList();
        test.add("11");
        test.add("22");
        test.add("33");
        test.forEach(job->{
            if(job.equals("11")){
                System.out.println("1111");
                return;
            }
            System.out.println(job);
        });*/
       List<Integer> result  = Lists.newArrayList();
       for(int i=0;i<= 1000000;i++){
           result.add(i);
       }

       result.stream().parallel().forEach( re->{
           System.out.println(re);
       });

    }
}



