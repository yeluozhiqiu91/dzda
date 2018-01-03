package com.igool.util;

public enum HpzlEnum {

	大型汽车("01","大型汽车", "黄"),
	小型汽车("02","小型汽车", "蓝"),
	轻便摩托车("08","轻便摩托车", "蓝"),
	两轮或三轮摩托车("07","两、三轮摩托车", "黄"),
	农用运输车("13","农用运输车", "黄"),
	外籍汽车("06","外籍汽车", "黑"),
	领馆汽车("04","领馆汽车", "黑"),
	使馆汽车("03","使馆汽车", "黑"),
	使馆摩托车("09","使馆摩托车", "黑"),
	领馆摩托车("10","领馆摩托车", "黑"),
	境外摩托车("11","境外摩托车", "黑"),
	外籍摩托车("12","外籍摩托车", "黑"),
	港澳入出境车("05","港澳入出境车", "黑"),
	拖拉机("14","拖拉机", "绿"),
	挂车("15","挂车", "黄"),
	教练汽车("16","教练汽车", "黄"),
	警用汽车("23","警用汽车", "白");
	
	private String code;
	private String view;
	private String color;
	
	private HpzlEnum(String code, String view, String color) {
		this.code = code;
		this.view = view;
		this.color = color;
	}
	
	public static String getHpzlView(String code){
		HpzlEnum[] values = HpzlEnum.values();
		for (HpzlEnum hpzl : values) {
			if(hpzl.getCode().equals(code))
				return hpzl.getView();
		}
		
		return code;
	}

	public static HpzlEnum getHpzlByCode(String code) {
		if(code == null || code.trim().equals("")) {
			return null;
		}
		for (HpzlEnum hpzl : HpzlEnum.values()) {
			if(hpzl.getCode().equals(code)) {
				return hpzl;
			}
		}
		return null;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getView() {
		return view;
	}

	public String getColor() {
		return color;
	}
}
