package com.serendipity.cms.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.serendipity.cms.entity.dto.IndustryDTO;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther bin
 */
public class FetchUtils {

    // http://reportapi.eastmoney.com/report/list
    // ?cb=datatable5450805&industryCode=*&pageSize=50&industry=*&rating=&ratingChange=&
    // beginTime=2019-01-01&endTime=2021-01-01&pageNo=2&fields=&qType=0&orgCode=&code=*
    // &rcode=&p=2&pageNum=2&_=1609486623372


    //http://reportapi.eastmoney.com/report/list
    // ?cb=datatable6767533&industryCode=*&pageSize=50&industry=*&rating=*&ratingChange=*
    // &beginTime=2019-01-01&endTime=2021-01-01&pageNo=1&fields=&qType=1&orgCode=&rcode=&_=1609486236280
    private static final Map<String, String> headerMap = new HashMap<>();
    private static final String DATA_URL = "http://reportapi.eastmoney.com/report/list";
    private static final String DATA_BODY = "cb=datatable${datatableNO}&industryCode=*&pageSize=50&industry=*&rating=&ratingChange=&beginTime=2020-11-01" +
            "&endTime=${now}" +
            "&pageNo=${pageNo}" +
            "&fields=" +
            "&qType=${type}" +
            "&orgCode=" +
            "&code=*" +
            "&rcode=" +
            "&p=${pageNo}" +
            "&pageNum=${pageNo}" +
            "&_=1609486623372";

    static {
        headerMap.put("accept", "application/json");
        headerMap.put("accept-encoding", "gzip, deflate");
        headerMap.put("accept-language", "zh-CN,zh;q=0.9");
        headerMap.put("Cache-Control", "no-cache");
        headerMap.put("Connection", "keep-alive");
        // headerMap.put("Cookie", "st_si=20139653101145; intellpositionL=1217.91px; cowminicookie=true; st_asi=delete; qgqp_b_id=4bb3c5f764f9fd4856f9d39fd24f5c8a; cowCookie=true; st_pvi=34914904745611; st_sp=2020-12-15%2022%3A33%3A48; st_inirUrl=https%3A%2F%2Fwww.baidu.com%2Flink; st_sn=28; st_psi=20210101153703649-113300303752-4142832436; intellpositionT=2695px\n");
        headerMap.put("Host", "reportapi.eastmoney.com");
        headerMap.put("Pragma", "no-cache");
        headerMap.put("Referer", "http://data.eastmoney.com/");
        headerMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36");
    }

    public static Object fetch(int pageNo,String datatableNO,boolean isStocks) throws IOException {

        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        String date = sd.format(System.currentTimeMillis());
        Request request = Request.Get(DATA_URL+"?"+DATA_BODY.replace("${pageNo}", pageNo + "")
                .replace("${now}",date ).replace("${datatableNO}",datatableNO)
                .replace("${type}",isStocks?"0":"1"))  //0  个股    1  行业
                ;
        headerMap.forEach(request::setHeader);

        Response response = request.execute();
        String content = response.returnContent().asString(StandardCharsets.UTF_8);
        String substring = content.substring(content.indexOf("{")).replace(")","");
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        if(isStocks){

            return null;
        }
        IndustryDTO dto=mapper.readValue(substring, IndustryDTO.class);
        return dto;
    }

   /* public static void main(String[] args) throws IOException {
        StocksWrapper fetch = (StocksWrapper) FetchUtils.fetch(1,"5450805",true);
        IndustryWrapper fetch2 = (IndustryWrapper)FetchUtils.fetch(1,"6767533",false);
    }*/
}
