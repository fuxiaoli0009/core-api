package com.core.api.enums;

public enum FlagTypeEnum {
	
	INIT_CONCERNED("0", "初始化关注的数据"),
	
	TYPE_HS("0", "沪深"),
	TYPE_HK("1", "香港"),
	TYPE_STAR("2", "科创");

	private String code;
	
	private String msg;
	
	private FlagTypeEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public static String getValueByCode(String code){
        for(FlagTypeEnum flagTypeEnum:FlagTypeEnum.values()){
            if(code.equals(flagTypeEnum.getCode())){
                return flagTypeEnum.getMsg();
            }
        }
        return  null;
    }
	
	public static String getCodeByValue(String msg) {
		for(FlagTypeEnum flagTypeEnum:FlagTypeEnum.values()) {
			if(msg.equals(flagTypeEnum.getMsg())) {
				return flagTypeEnum.getCode();
			}
		}
		return null;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}