package com.vlogplusplus.service.impl;

import com.vlogplusplus.dao.ISearchDao;
import com.vlogplusplus.entity.Search;
import com.vlogplusplus.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService implements ISearchService {
    @Autowired
    private ISearchDao iSearchDao;

    @Override
    public List<Search> list(int n) {
        return iSearchDao.list(n);
    }

    @Override
    public void add(String var, int count) {
        iSearchDao.add(var,count);
    }
}
