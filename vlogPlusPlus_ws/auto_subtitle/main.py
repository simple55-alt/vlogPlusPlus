# -*- coding: utf-8 -*-

import os
import sys
from pydub import AudioSegment
from pydub.silence import split_on_silence
from aip import AipSpeech
from moviepy.editor import *

# 百度验证部分
APP_ID = '18833499'
API_KEY = 'NeR9iAH1ZnNBdtrmvXD04Z9z'
SECRET_KEY = 'L4qDqZCnGkbwltsxM3VemGhIEn8dWZx2'
client = AipSpeech(APP_ID, API_KEY, SECRET_KEY)


# 将音频转换为wav
def gotwave(audio):
    new = AudioSegment.empty()
    for inx, val in enumerate(audio):
        new = val + silent
        new.export('./temp/%d.wav' % inx, format='wav')
    # 毫秒换算 根据需要只到分


def ms2s(ms):
    mspart = ms % 1000
    mspart = str(mspart).zfill(3)
    spart = (ms // 1000) % 60
    spart = str(spart).zfill(2)
    mpart = (ms // 1000) // 60
    mpart = str(mpart).zfill(2)
    # srt的时间格式
    stype = "00:" + mpart + ":" + spart + "," + mspart
    return stype


# 读取切割后的文件
def get_file_content(filePath):
    with open(filePath, 'rb') as fp:
        return fp.read()


# 语音识别
def audio2text(wavsample):
    rejson = client.asr(wavsample, 'wav', 16000, {'dev_pid': 1537, })  # 1537表示识别普通话, 1737为英语
    if (rejson['err_no'] == 0):
        result = rejson['result'][0]
    else:
        result = "erro" + str(rejson['err_no'])
    return result


# 输出字幕
def text2str(inx, text, starttime, endtime):
    strtext = str(inx) + '\n' + ms2s(starttime) + ' --> ' + ms2s(endtime) + '\n' + text + '\n' + '\n'
    return strtext


# 读写文件
def strtxt(text, path):
    with open(path, 'a') as fp:
        fp.write(text)
        fp.close()


# main
if __name__ == '__main__':  #传入2个参数，视频路径，目标srt路径
    # 提取音频
    video = VideoFileClip(sys.argv[1])  # 视频文件
    audio = video.audio
    audio.write_audiofile('./temp/test.wav')  # 提取视频中的音频
    # 读取音频预处理
    AudioSegment.ffmpeg = "C:/Program Files/ffmpeg/bin/ffmpeg.exe"
    sound = AudioSegment.from_wav('./temp/test.wav')
    sound = sound.set_frame_rate(16000)  # 百度API指定的采样频率16000
    sound = sound.set_channels(1)  # 单声道
    # 切割音频
    min_silence_len = 700
    silence_thresh = -32
    pieces, start_t, end_t = split_on_silence(sound, min_silence_len, silence_thresh)
    silent = AudioSegment.silent(duration=1000)
    print("切割音频已完成！")
    gotwave(pieces)  # 将切割后的音频转为wav
    for inx, val in enumerate(pieces):
        wav = get_file_content('./temp/%d.wav' % inx)
        text = audio2text(wav)
        text2 = text2str(inx, text, start_t[inx], end_t[inx])
        strtxt(text2, sys.argv[2])  # 写入srt文件
        print(str(round((inx / len(pieces)) * 100)) + '%')
        os.remove('./temp/%d.wav' % inx)  # 删除临时文件
    os.remove('./temp/test.wav')
    print("已完成！")
