package com.vlogplusplus.service.impl;

import com.vlogplusplus.dao.IVersionDao;
import com.vlogplusplus.entity.Version;
import com.vlogplusplus.service.IVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VersionService implements IVersionService {
    @Autowired
    private IVersionDao iVersionDao;

    @Override
    public List<Version> list() {
        return iVersionDao.list();
    }

    @Override
    public Version get_latest() {
        return iVersionDao.get_latest();
    }
}
