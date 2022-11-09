package com.systemk.Mapper;

import java.util.HashMap;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PageMapper {

    Integer pageRecord(HashMap item);

    Integer stkPageRecord(HashMap item);

    Integer stkExPageRecord(HashMap item);

    Integer stkRfidPageRecord(HashMap item);

    Integer inPageRecord(HashMap item);

    Integer invPageRecord(HashMap item);

    Integer outPageRecord(HashMap item);

}
