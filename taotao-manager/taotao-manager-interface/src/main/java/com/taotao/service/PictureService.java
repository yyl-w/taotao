package com.taotao.service;

import pojo.PictureResult;

public interface PictureService {
    public PictureResult uploadFile(byte[] bytes, String name);
}
