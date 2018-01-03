package com.igool.ssp.web.util;

public class BizException extends Exception {

	private static final long serialVersionUID = -341081171520068462L;
	
	private String code;

	public String getCode() {
		return code;
	}

	public BizException(String code) {
		super();
		this.code = code;
	}

	public BizException(String code, String msg, Throwable e, boolean arg2, boolean arg3) {
		super(msg, e, arg2, arg3);
		this.code = code;
	}

	public BizException(String code, String msg, Throwable e) {
		super(msg, e);
		this.code = code;
	}

	public BizException(String code, String msg) {
		super(msg);
		this.code = code;
	}

	public BizException(String code, Throwable e) {
		super(e);
		this.code = code;
	}
}
