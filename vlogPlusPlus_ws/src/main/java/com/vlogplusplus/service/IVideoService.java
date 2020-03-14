package com.vlogplusplus.service;

import com.vlogplusplus.entity.Video;

import java.util.List;

public interface IVideoService {
    List<Video> get_video(int id);
}
