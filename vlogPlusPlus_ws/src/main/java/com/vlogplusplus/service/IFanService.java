package com.vlogplusplus.service;

import com.vlogplusplus.entity.Fan;

import java.util.List;

public interface IFanService {
    List<Fan> list_fan(int up_id);
    List<Fan> list_fellow(int fan_id);
    void add_fan(int fan_id, int up_id);
    void del_fan(int fan_id, int up_id);
}
