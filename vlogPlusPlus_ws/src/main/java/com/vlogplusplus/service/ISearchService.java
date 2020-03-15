package com.vlogplusplus.service;

import com.vlogplusplus.entity.Search;

import java.util.List;

public interface ISearchService {
    List<Search> list(int n);
    void add(String var);
}
