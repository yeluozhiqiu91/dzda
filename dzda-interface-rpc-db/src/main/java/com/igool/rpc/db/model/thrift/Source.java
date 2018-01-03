package com.igool.rpc.db.model.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;

import static com.google.common.base.MoreObjects.toStringHelper;

@ThriftStruct("Source")
public final class Source
{
    public Source() {
    }

    private int id;

    @ThriftField(value=1, name="id", requiredness=Requiredness.NONE)
    public int getId() { return id; }

    @ThriftField
    public void setId(final int id) { this.id = id; }

    private int pid;

    @ThriftField(value=2, name="pid", requiredness=Requiredness.NONE)
    public int getPid() { return pid; }

    @ThriftField
    public void setPid(final int pid) { this.pid = pid; }

    private String name;

    @ThriftField(value=3, name="name", requiredness=Requiredness.NONE)
    public String getName() { return name; }

    @ThriftField
    public void setName(final String name) { this.name = name; }

    private int type;

    @ThriftField(value=4, name="type", requiredness=Requiredness.NONE)
    public int getType() { return type; }

    @ThriftField
    public void setType(final int type) { this.type = type; }

    private String url;

    @ThriftField(value=5, name="url", requiredness=Requiredness.NONE)
    public String getUrl() { return url; }

    @ThriftField
    public void setUrl(final String url) { this.url = url; }

    private String param;

    @ThriftField(value=6, name="param", requiredness=Requiredness.NONE)
    public String getParam() { return param; }

    @ThriftField
    public void setParam(final String param) { this.param = param; }

    private int seq;

    @ThriftField(value=7, name="seq", requiredness=Requiredness.NONE)
    public int getSeq() { return seq; }

    @ThriftField
    public void setSeq(final int seq) { this.seq = seq; }

    private int isvalid;

    @ThriftField(value=8, name="isvalid", requiredness=Requiredness.NONE)
    public int getIsvalid() { return isvalid; }

    @ThriftField
    public void setIsvalid(final int isvalid) { this.isvalid = isvalid; }

    private boolean isDelete;

    @ThriftField(value=9, name="isDelete", requiredness=Requiredness.NONE)
    public boolean isIsDelete() { return isDelete; }

    @ThriftField
    public void setIsDelete(final boolean isDelete) { this.isDelete = isDelete; }

    private int isSelect;

    @ThriftField(value=10, name="isSelect", requiredness=Requiredness.NONE)
    public int getIsSelect() { return isSelect; }

    @ThriftField
    public void setIsSelect(final int isSelect) { this.isSelect = isSelect; }

    private String iconSkin;

    @ThriftField(value=11, name="iconSkin", requiredness=Requiredness.NONE)
    public String getIconSkin() { return iconSkin; }

    @ThriftField
    public void setIconSkin(final String iconSkin) { this.iconSkin = iconSkin; }

    private int display;

    @ThriftField(value=12, name="display", requiredness=Requiredness.NONE)
    public int getDisplay() { return display; }

    @ThriftField
    public void setDisplay(final int display) { this.display = display; }

    @Override
    public String toString()
    {
        return toStringHelper(this)
            .add("id", id)
            .add("pid", pid)
            .add("name", name)
            .add("type", type)
            .add("url", url)
            .add("param", param)
            .add("seq", seq)
            .add("isvalid", isvalid)
            .add("isDelete", isDelete)
            .add("isSelect", isSelect)
            .add("iconSkin", iconSkin)
            .add("display", display)
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

        Source other = (Source)o;

        return
            Objects.equals(id, other.id) &&
            Objects.equals(pid, other.pid) &&
            Objects.equals(name, other.name) &&
            Objects.equals(type, other.type) &&
            Objects.equals(url, other.url) &&
            Objects.equals(param, other.param) &&
            Objects.equals(seq, other.seq) &&
            Objects.equals(isvalid, other.isvalid) &&
            Objects.equals(isDelete, other.isDelete) &&
            Objects.equals(isSelect, other.isSelect) &&
            Objects.equals(iconSkin, other.iconSkin) &&
            Objects.equals(display, other.display);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[] {
            id,
            pid,
            name,
            type,
            url,
            param,
            seq,
            isvalid,
            isDelete,
            isSelect,
            iconSkin,
            display
        });
    }
}
