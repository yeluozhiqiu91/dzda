package com.igool.rpc.db.model.thrift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;

import static com.google.common.base.MoreObjects.toStringHelper;

@ThriftStruct("TRegion")
public final class TRegion
{
    public TRegion() {
    }

    private long id;

    @ThriftField(value=1, name="id", requiredness=Requiredness.NONE)
    public long getId() { return id; }

    @ThriftField
    public void setId(final long id) { this.id = id; }

    private int parentId;

    @ThriftField(value=2, name="parentId", requiredness=Requiredness.NONE)
    public int getParentId() { return parentId; }

    @ThriftField
    public void setParentId(final int parentId) { this.parentId = parentId; }

    private String name;

    @ThriftField(value=3, name="name", requiredness=Requiredness.NONE)
    public String getName() { return name; }

    @ThriftField
    public void setName(final String name) { this.name = name; }

    private String code;

    @ThriftField(value=4, name="code", requiredness=Requiredness.NONE)
    public String getCode() { return code; }

    @ThriftField
    public void setCode(final String code) { this.code = code; }

    private String codePath;

    @ThriftField(value=5, name="codePath", requiredness=Requiredness.NONE)
    public String getCodePath() { return codePath; }

    @ThriftField
    public void setCodePath(final String codePath) { this.codePath = codePath; }

    private int grade;

    @ThriftField(value=6, name="grade", requiredness=Requiredness.NONE)
    public int getGrade() { return grade; }

    @ThriftField
    public void setGrade(final int grade) { this.grade = grade; }

    @Override
    public String toString()
    {
        return toStringHelper(this)
            .add("id", id)
            .add("parentId", parentId)
            .add("name", name)
            .add("code", code)
            .add("codePath", codePath)
            .add("grade", grade)
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

        TRegion other = (TRegion)o;

        return
            Objects.equals(id, other.id) &&
            Objects.equals(parentId, other.parentId) &&
            Objects.equals(name, other.name) &&
            Objects.equals(code, other.code) &&
            Objects.equals(codePath, other.codePath) &&
            Objects.equals(grade, other.grade);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[] {
            id,
            parentId,
            name,
            code,
            codePath,
            grade
        });
    }
}
