package com.serendipity.cms.service.impl;

import com.serendipity.cms.entity.vo.HotVo;
import com.serendipity.cms.service.IHomeService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther bin
 */
@Service
public class IHomeServiceImpl implements IHomeService {

    @Override
    public List<HotVo> baiduHot() throws IOException {
        return this.parseBaiduHot();
    }


    /**
     * 政府网最新政策
     * @return
     * @throws IOException
     */
    private  List<HotVo> parseGwyHot() throws IOException {
        ArrayList<HotVo> vos = new ArrayList<>();
            Document doc = Jsoup.parse(new URL("http://www.gov.cn/zhengce/zuixin.htm"), 10000);
            Elements elements = doc.getElementsByClass("news_box");
            Elements li = elements.get(0).select("li");
            li.stream().limit(10).forEach(k->{
                HotVo hotVo = new HotVo();
                hotVo.setWord(k.select("a").text());
                hotVo.setDesc(k.select("span").text());
                vos.add(hotVo);
            });
        return vos;
    }

// 百度热搜
    private List<HotVo> parseBaiduHot() throws IOException {
        ArrayList<HotVo> vos = new ArrayList<>();
            Document doc = Jsoup.parse(new URL("https://top.baidu.com/board?tab=realtime"), 10000);
            Elements elements = doc.select("div");
            Elements elementsByClass = elements.get(0).getElementsByClass("c-single-text-ellipsis");

            elementsByClass.stream().limit(10).forEach(k->{
                HotVo vo = new HotVo();
                vo.setWord(k.text());
                vos.add(vo);
            });
        return vos;
    }

    public static void main(String[] args) throws IOException {
      //  parseGwyHot();
    }
}



