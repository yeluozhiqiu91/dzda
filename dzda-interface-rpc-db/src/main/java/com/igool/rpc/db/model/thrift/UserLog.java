package com.igool.rpc.db.model.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;

import static com.google.common.base.MoreObjects.toStringHelper;

@ThriftStruct("UserLog")
public final class UserLog
{
    public UserLog() {
    }

    private int id;

    @ThriftField(value=1, name="id", requiredness=Requiredness.NONE)
    public int getId() { return id; }

    @ThriftField
    public void setId(final int id) { this.id = id; }

    private int userId;

    @ThriftField(value=2, name="user_id", requiredness=Requiredness.NONE)
    public int getUserId() { return userId; }

    @ThriftField
    public void setUserId(final int userId) { this.userId = userId; }

    private int type;

    @ThriftField(value=3, name="type", requiredness=Requiredness.NONE)
    public int getType() { return type; }

    @ThriftField
    public void setType(final int type) { this.type = type; }

    private String loginIp;

    @ThriftField(value=4, name="login_ip", requiredness=Requiredness.NONE)
    public String getLoginIp() { return loginIp; }

    @ThriftField
    public void setLoginIp(final String loginIp) { this.loginIp = loginIp; }

    private String agent;

    @ThriftField(value=5, name="agent", requiredness=Requiredness.NONE)
    public String getAgent() { return agent; }

    @ThriftField
    public void setAgent(final String agent) { this.agent = agent; }

    private String version;

    @ThriftField(value=6, name="version", requiredness=Requiredness.NONE)
    public String getVersion() { return version; }

    @ThriftField
    public void setVersion(final String version) { this.version = version; }

    private long loginTime;

    @ThriftField(value=7, name="login_time", requiredness=Requiredness.NONE)
    public long getLoginTime() { return loginTime; }

    @ThriftField
    public void setLoginTime(final long loginTime) { this.loginTime = loginTime; }

    private String description;

    @ThriftField(value=8, name="description", requiredness=Requiredness.NONE)
    public String getDescription() { return description; }

    @ThriftField
    public void setDescription(final String description) { this.description = description; }

    private String deviceType;

    @ThriftField(value=9, name="device_type", requiredness=Requiredness.NONE)
    public String getDeviceType() { return deviceType; }

    @ThriftField
    public void setDeviceType(final String deviceType) { this.deviceType = deviceType; }

    private String operationSystem;

    @ThriftField(value=10, name="operation_system", requiredness=Requiredness.NONE)
    public String getOperationSystem() { return operationSystem; }

    @ThriftField
    public void setOperationSystem(final String operationSystem) { this.operationSystem = operationSystem; }

    private String requestUrl;

    @ThriftField(value=11, name="request_url", requiredness=Requiredness.NONE)
    public String getRequestUrl() { return requestUrl; }

    @ThriftField
    public void setRequestUrl(final String requestUrl) { this.requestUrl = requestUrl; }

    @Override
    public String toString()
    {
        return toStringHelper(this)
            .add("id", id)
            .add("userId", userId)
            .add("type", type)
            .add("loginIp", loginIp)
            .add("agent", agent)
            .add("version", version)
            .add("loginTime", loginTime)
            .add("description", description)
            .add("deviceType", deviceType)
            .add("operationSystem", operationSystem)
            .add("requestUrl", requestUrl)
            .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserLog other = (UserLog)o;

        return
            Objects.equals(id, other.id) &&
            Objects.equals(userId, other.userId) &&
            Objects.equals(type, other.type) &&
            Objects.equals(loginIp, other.loginIp) &&
            Objects.equals(agent, other.agent) &&
            Objects.equals(version, other.version) &&
            Objects.equals(loginTime, other.loginTime) &&
            Objects.equals(description, other.description) &&
            Objects.equals(deviceType, other.deviceType) &&
            Objects.equals(operationSystem, other.operationSystem) &&
            Objects.equals(requestUrl, other.requestUrl);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[] {
            id,
            userId,
            type,
            loginIp,
            agent,
            version,
            loginTime,
            description,
            deviceType,
            operationSystem,
            requestUrl
        });
    }
}
