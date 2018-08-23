package com.basicauth.mapper;

import com.basicauth.data.CustomerCare;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Jabito on 10/6/2016.
 */
public interface HistoryMapper {

    void addToLoaHistory(@Param("c") CustomerCare loa);

    void addToLoaLTBLHistory(@Param("c")CustomerCare loa);

    void addToLoaPFHistory(@Param("c")CustomerCare loa);
}
