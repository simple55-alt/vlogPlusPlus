package com.vlogplusplus.service;

import com.vlogplusplus.entity.Version;

import java.util.List;

public interface IVersionService {
    List<Version> list();
    List<Version> get_latest();
}
