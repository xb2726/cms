package com.serendipity.cms.service;

import com.serendipity.cms.entity.vo.HotVo;

import java.io.IOException;
import java.util.List;

public interface IHomeService {

    List<HotVo> baiduHot() throws IOException;
}
