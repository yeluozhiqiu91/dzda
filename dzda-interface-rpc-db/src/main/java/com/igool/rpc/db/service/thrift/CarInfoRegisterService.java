// Generated by swift-generator from dzda/carinfoservice.thrift

package com.igool.rpc.db.service.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.service.*;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.*;
import java.util.*;


@ThriftService("CarInfoRegisterService")
public interface CarInfoRegisterService extends Closeable
{
    @ThriftService("CarInfoRegisterService")
    public interface Async extends Closeable
    {
        void close();

        @ThriftMethod(value = "findCarInfoBySfzAndName")
        ListenableFuture<List<com.igool.rpc.db.model.thrift.Vehicle>> findCarInfoBySfzAndName(
            @ThriftField(value=1, name="singleSfz", requiredness=Requiredness.NONE) final String singleSfz,
            @ThriftField(value=2, name="name", requiredness=Requiredness.NONE) final String name,
            @ThriftField(value=3, name="registertType", requiredness=Requiredness.NONE) final String registertType
        );

        @ThriftMethod(value = "addAccredit")
        ListenableFuture<Void> addAccredit(
            @ThriftField(value=1, name="findRegister", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.FindRegister findRegister
        );

        @ThriftMethod(value = "findCarInfoByVehicles")
        ListenableFuture<List<com.igool.rpc.db.model.thrift.FindRegister>> findCarInfoByVehicles(
            @ThriftField(value=1, name="vehicles", requiredness=Requiredness.NONE) final List<com.igool.rpc.db.model.thrift.Vehicle> vehicles,
            @ThriftField(value=2, name="singleSfz", requiredness=Requiredness.NONE) final String singleSfz
        );

        @ThriftMethod(value = "findCarInfoByFindRegister")
        ListenableFuture<List<com.igool.rpc.db.model.thrift.FindRegister>> findCarInfoByFindRegister(
            @ThriftField(value=1, name="accreditSfz", requiredness=Requiredness.NONE) final String accreditSfz,
            @ThriftField(value=2, name="hphms", requiredness=Requiredness.NONE) final String hphms,
            @ThriftField(value=3, name="carSfzs", requiredness=Requiredness.NONE) final String carSfzs,
            @ThriftField(value=4, name="hpzls", requiredness=Requiredness.NONE) final String hpzls
        );
    }
    void close();


    @ThriftMethod(value = "findCarInfoBySfzAndName")
    List<com.igool.rpc.db.model.thrift.Vehicle> findCarInfoBySfzAndName(
        @ThriftField(value=1, name="singleSfz", requiredness=Requiredness.NONE) final String singleSfz,
        @ThriftField(value=2, name="name", requiredness=Requiredness.NONE) final String name,
        @ThriftField(value=3, name="registertType", requiredness=Requiredness.NONE) final String registertType
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "addAccredit")
    void addAccredit(
        @ThriftField(value=1, name="findRegister", requiredness=Requiredness.NONE) final com.igool.rpc.db.model.thrift.FindRegister findRegister
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "findCarInfoByVehicles")
    List<com.igool.rpc.db.model.thrift.FindRegister> findCarInfoByVehicles(
        @ThriftField(value=1, name="vehicles", requiredness=Requiredness.NONE) final List<com.igool.rpc.db.model.thrift.Vehicle> vehicles,
        @ThriftField(value=2, name="singleSfz", requiredness=Requiredness.NONE) final String singleSfz
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "findCarInfoByFindRegister")
    List<com.igool.rpc.db.model.thrift.FindRegister> findCarInfoByFindRegister(
        @ThriftField(value=1, name="accreditSfz", requiredness=Requiredness.NONE) final String accreditSfz,
        @ThriftField(value=2, name="hphms", requiredness=Requiredness.NONE) final String hphms,
        @ThriftField(value=3, name="carSfzs", requiredness=Requiredness.NONE) final String carSfzs,
        @ThriftField(value=4, name="hpzls", requiredness=Requiredness.NONE) final String hpzls
    ) throws org.apache.thrift.TException;
}