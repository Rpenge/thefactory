//package com.systemk.thefactor2;
//
//
////import com.systemk.thefactor2.Util.jwtToken;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.UnsupportedEncodingException;
//import java.util.Map;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = SystemkAmsApplication.class)
//@Slf4j
//public class MybatisTests {
//
//
//
//
////    @Autowired
////    private PageMapper mapper;
//
////    @Test
////    public void mapperTest() {
//////        List<AssetChangeVO> list = mapper.assetChangeList("sample");
//////
//////        log.info("list : {}", list);
////
////        MybatisUtil mu = new MybatisUtil();
////        mu.setTable("asset_change");
////        mu.addLike("ast_nm", "애자일 마스터" );
////        mu.addEqual("ast_dp", "LAB");
////
//////        HashMap<String, Object> map = new HashMap<>();
//////        map.put("table", "asset_change");
////
//////        List searchList = new ArrayList();
//////        searchList.add("ast_nm like '%애자일 마스터%'");
//////        searchList.add("ast_dp like '%LAB%'");
//////        map.put("list", searchList);
////
////
////
////        int a = mapper.pageRecord(mu.getTableSearch());
////        log.info("data : {}", a);
////
////        BoardPager bp = new BoardPager(a, 1);
////        log.info("data : {}, {}", bp.getTotBlock(), bp.getTotPage());
////
////
////    }
//
////    @Test
////    public void password() {
////        BCryptPasswordEncoder ec = new BCryptPasswordEncoder();
////        log.info("data : {}", ec.encode("1234"));
////    }
////    @Test
////    public void password() throws UnsupportedEncodingException {
////        jwtToken testJWT = new jwtToken();
////
////        String jwt = testJWT.createToken();
////        System.out.println(jwt);
////
////        Map<String, Object> claimMap = testJWT.verifyJWT(jwt);
////        System.out.println(claimMap); // 토큰이 만료되었거나 문제가있으면 null
////    }
//
//
//}
//
//
//
