package com.vlogplusplus.dao;


import com.vlogplusplus.entity.Fan;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFanDao {
    List<Fan> list_fan(@Param("up_id") int up_id);
    List<Fan> list_fellow(@Param("fan_id") int fan_id);
    void add_fan(@Param("fan_id") int fan_id,@Param("up_id") int up_id);
    void del_fan(@Param("fan_id") int fan_id,@Param("up_id") int up_id);
}
