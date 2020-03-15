package com.vlogplusplus.dao;

import com.vlogplusplus.entity.Template_info;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITemplate_infoDao {
    List<Template_info> list();
    void add(@Param("name") String name,@Param("summary") String summary,@Param("detail") String detail,
             @Param("u_id") int u_id);
    void update(@Param("name") String name,@Param("summary") String summary,@Param("detail") String detail,
             @Param("u_id") int u_id,@Param("id") int id);
    void del(@Param("id") int id);
}