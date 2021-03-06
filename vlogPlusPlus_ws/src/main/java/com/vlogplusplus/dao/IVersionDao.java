package com.vlogplusplus.dao;

import com.vlogplusplus.entity.Version;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVersionDao {
    List<Version> list();
    Version get_latest();
}
