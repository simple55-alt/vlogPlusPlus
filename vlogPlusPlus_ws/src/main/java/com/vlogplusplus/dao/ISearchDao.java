package com.vlogplusplus.dao;

import com.vlogplusplus.entity.Search;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISearchDao {
    List<Search> list(@Param("n") int n);
    void add(@Param("var") String var);
    Search search(@Param("var") String var);
    void count(@Param("id") int id);
}
