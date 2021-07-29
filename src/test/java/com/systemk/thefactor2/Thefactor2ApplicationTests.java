package com.systemk.thefactor2;

import com.systemk.thefactor2.Config.ConstansConfig;
import com.systemk.thefactor2.Mapper.TfCommCodeMapper;
import com.systemk.thefactor2.Service.BrandService;
import com.systemk.thefactor2.VO.TfCommCodeVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest(classes={SystemkAmsApplication.class})
public class Thefactor2ApplicationTests {


//	@Test
//	public void contextLoads() throws Exception {
//
//		System.out.println(bCryptPasswordEncoder.encode("admin"));
//	}

    @Autowired
    private TfCommCodeMapper tfCommCodeMapper;

    @Autowired
    private BrandService brandService;

    @Test
    public void contextLoads() throws Exception {

        Map brandInfo = brandService.detailSearch("010101");
        System.out.println(brandInfo);
//        System.out.println(ConstansConfig.getNameMap());
//        List<TfCommCodeVO> abs = tfCommCodeMapper.commList();
//
//        Object[] as = abs.stream().filter(x->x.getCommCd().equals("060101")).toArray();
//        System.out.println(as);
    }


}
