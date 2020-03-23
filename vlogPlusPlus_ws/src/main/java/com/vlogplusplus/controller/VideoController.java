package com.vlogplusplus.controller;

import com.vlogplusplus.entity.Resp;
import com.vlogplusplus.entity.Video;
import com.vlogplusplus.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@RestController
@RequestMapping(value = "/video")
public class VideoController {
    @Autowired
    private IVideoService iVideoService;

    @RequestMapping(value = "/get_video", method = RequestMethod.POST)
    private Video get_video(@RequestParam int id){
        return iVideoService.get_video(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    private void add(@RequestBody Video video){
        iVideoService.add(video.getTitle(),video.getType(), video.getVar(),video.getSubtitle(),
                video.getContent(),video.getU_id(),video.getT_id(),video.getCount_likes(),
                video.getCount_share(),video.getCount_favorite(),video.getCount_watch(),
                video.getState());
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    private void update(@RequestBody Video video){
        iVideoService.update(video.getTitle(),video.getType(), video.getVar(),video.getSubtitle(),
                video.getContent(),video.getU_id(),video.getT_id(),video.getCount_likes(),
                video.getCount_share(),video.getCount_favorite(),video.getCount_watch(),
                video.getState(),video.getId());
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    private void del(@RequestParam int id){
        iVideoService.del(id);
    }

    @RequestMapping(value = "/upload_video/{subtitle}", method = RequestMethod.POST)
    private Resp<String> upload_video(@PathVariable("subtitle") boolean subtitle, @RequestParam("file") MultipartFile file){
        Resp<String> result = iVideoService.upload_video(file); //上传文件

        class AutoSubtitle implements Runnable{
            @Override
            public void run() {
                System.out.println("生成字幕的线程启动！");
                String fileName = result.getBody();
                try {
                    Process process;
                    String cmd = System.getProperty("user.dir") + "\\auto_subtitle\\venv\\Scripts\\python.exe ";
                    cmd += System.getProperty("user.dir") + "\\auto_subtitle\\main.py ";
                    cmd += System.getProperty("user.dir") + "\\upload\\video\\"+fileName+" "; //视频文件的路径
                    cmd += System.getProperty("user.dir") + "\\upload\\subtitle\\"+fileName.substring(0,fileName.lastIndexOf("."))+".txt"; //字幕结果文件的路径
                    System.out.println(cmd);
                    process = Runtime.getRuntime().exec(cmd);
                    //用输入输出流来截取结果
                    BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String line = null;
                    while ((line = in.readLine()) != null) {
                        System.out.println("Python程序:" + line);
                    }
                    in.close();
                    System.out.println(process.waitFor());
                    System.out.println("生成字幕文件成功！");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        if(subtitle && result.getCode().equals("200")) {
            System.out.println("调用python生成字幕程序...");
            new Thread(new AutoSubtitle()).start();
        }
        return result;
    }
}
