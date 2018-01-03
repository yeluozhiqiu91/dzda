package com.igool.util;

/**
 * 随手拍用户类型枚举
 * @author leave
 * */
public enum SSPUserCategoryEnum {
	/**
     * 警员
     */
	POLICE(0,"警员"),
	/**
     * 协警
     */
	AUXPOLICE(1,"协警"),
	/**
     * 志愿者
     */
	VOLUNTEER(2,"志愿者");
	
	private Integer value;
    private String text;

    SSPUserCategoryEnum(Integer value, String text) {
        this.setValue(value);
        this.setText(text);
    }

    public static final String findText(Integer value){
        for (SSPUserCategoryEnum fr : SSPUserCategoryEnum.values()) {
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
