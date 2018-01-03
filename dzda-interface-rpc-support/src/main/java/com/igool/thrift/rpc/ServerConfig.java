package com.igool.thrift.rpc;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by igool on 2017/9/11.
 */
public class ServerConfig {
    //框架块大小
   private  int maxFrameSize;

    //缓存区大小
    private int receiveBufferSize;

    public int getMaxFrameSize() {
        return maxFrameSize;
    }

    public void setMaxFrameSize(int maxFrameSize) {
        this.maxFrameSize = maxFrameSize;
    }

    public int getReceiveBufferSize() {
        return receiveBufferSize;
    }

    public void setReceiveBufferSize(int receiveBufferSize) {
        this.receiveBufferSize = receiveBufferSize;
    }

    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }
}
