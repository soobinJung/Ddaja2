package com.bng.ddaja.passRate.service;

import com.bng.ddaja.passRate.dto.PassRateDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

import org.json.JSONObject;
import org.json.XML;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.net.URL;

import lombok.AllArgsConstructor;
import java.io.BufferedReader; 

import com.fasterxml.jackson.databind.SerializationFeature;

/*
** 응시자격별 국가기술자격 원서접수 및 합격 현황 조회
*/
@Service
@AllArgsConstructor
public class PassRateService {
    
    public Object getPassRate ( PassRateDTO passRateDTO ) throws Exception {

        StringBuilder sb = new StringBuilder();
        String line;

        /** URL **/
        StringBuilder urlBuilder = new StringBuilder("http://openapi.q-net.or.kr/api/service/rest/InquiryEmqualPassSVC/getList");

        final String ServiceKey = "X4XLTRWw4t4a+VaeVigQCCvZAR6QPkeH6BuBnmasH8CJUgXNnyYFOlCb6w/IICASjBzGwfju/sKqEL9cENui6w==";

        /*Service Key*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + "" ); 

        /*발급받은 인증키*/
        urlBuilder.append("&" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + URLEncoder.encode(ServiceKey, "UTF-8")); 

        /*등급코드*/
        urlBuilder.append("&" + URLEncoder.encode("grdCd","UTF-8")      + "=" + URLEncoder.encode( passRateDTO.getGrdCd(), "UTF-8")); 

        /*통계연도*/
        urlBuilder.append("&" + URLEncoder.encode("baseYY","UTF-8")     + "=" + URLEncoder.encode( passRateDTO.getBaseYY() , "UTF-8")); 

        /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8")     + "=" + URLEncoder.encode( passRateDTO.getPageNo() , "UTF-8")); 

        /*페이지당 데이터 수*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8")  + "=" + URLEncoder.encode( passRateDTO.getNumOfRows() , "UTF-8")); 

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        System.out.println(sb.toString());

        System.out.println("BINSOO - XML 문서 보냄");
        

        return xmlToJson(sb.toString());
    }

    public static Object xmlToJson(String xml) throws Exception {
        System.out.println("BINSOO - XML 문서 받음 변환시도");

        JSONObject jObject = XML.toJSONObject(xml);

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        
        Object json = mapper.readValue(jObject.toString(), Object.class);


        String output = mapper.writeValueAsString(json);
        System.out.println(" == xmlToJson");
        System.out.println(output);


        return json;
    }

}