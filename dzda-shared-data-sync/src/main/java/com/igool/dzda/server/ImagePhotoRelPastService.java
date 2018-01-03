package com.igool.dzda.server;

import com.igool.rpc.db.model.thrift.ImagePhotoRel;

import java.util.List;

/**
 * @author H J .
 * @date 2017/8/30.
 */
public interface ImagePhotoRelPastService {

    List<ImagePhotoRel> getImagePhotoRel(String i, int indexStart, int indexEnd);

}
