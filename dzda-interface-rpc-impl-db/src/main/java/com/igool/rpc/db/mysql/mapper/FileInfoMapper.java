package com.igool.rpc.db.mysql.mapper;

import com.igool.rpc.db.model.thrift.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.io.File;
import java.util.List;

/**
 * Created by wang on 2017/9/6.
 */
public interface FileInfoMapper {

    void addFileInfo(List<FileInfo> list);

    List<FileInfo> findFileInfos(@Param("fileInfos") List<FileInfo> list);

    FileInfo findFileInfoByFileCode(@Param("fileCode")String fileCode);

    void updateStatus(@Param("status")int status, @Param("fileId")String fileId);

    int addFile(@Param("fileInfo") FileInfo fileInfo);

    List<FileId> findAllFileIdsByCkStatus(@Param("ckStatus") int ckStatus);

    List<FileId> findAllForFileCode();
    List<FileId> findAllForBox();
    int findAllForFileCodeCount();
    List<FileId> findForFileCode(@Param("startIndex") int startIndex,@Param("pageSize") int pageSize);
}
