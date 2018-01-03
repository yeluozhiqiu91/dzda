package com.igool.util;

/**
 * 随手拍用户状态枚举
 * @author leave
 * */
public enum SSPUserStatusEnum {
	
    NORMAL(0,"正常"),
    INVALID(1,"无效"),
    DELETE(-1,"删除");
    
	private Integer value;
    private String text;

    SSPUserStatusEnum(Integer value, String text) {
        this.setValue(value);
        this.setText(text);
    }

    public static final String findText(Integer value){
        for (SSPUserStatusEnum fr : SSPUserStatusEnum.values()) {
            if (fr.getValue().equals(value))
                return fr.getText();
        }
        return null;
    }
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
