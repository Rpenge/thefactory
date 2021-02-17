package com.systemk.ams;

import com.systemk.ams.mapper.PageMapper;
import com.systemk.ams.Util.BoardPager;
import com.systemk.ams.Util.MybatisUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SystemkAmsApplication.class)
@Slf4j
public class MybatisTests {




    @Autowired
    private PageMapper mapper;

    @Test
    public void mapperTest() {
//        List<AssetChangeVO> list = mapper.assetChangeList("sample");
//
//        log.info("list : {}", list);

        MybatisUtil mu = new MybatisUtil();
        mu.setTable("asset_change");
        mu.addLike("ast_nm", "애자일 마스터" );
        mu.addEqual("ast_dp", "LAB");

//        HashMap<String, Object> map = new HashMap<>();
//        map.put("table", "asset_change");

//        List searchList = new ArrayList();
//        searchList.add("ast_nm like '%애자일 마스터%'");
//        searchList.add("ast_dp like '%LAB%'");
//        map.put("list", searchList);



        int a = mapper.pageRecord(mu.getTableSearch());
        log.info("data : {}", a);

        BoardPager bp = new BoardPager(a, 1);
        log.info("data : {}, {}", bp.getTotBlock(), bp.getTotPage());


    }

    @Test
    public void password() {
        BCryptPasswordEncoder ec = new BCryptPasswordEncoder();
        log.info("data : {}", ec.encode("1234"));
    }

}



