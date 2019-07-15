package com.core.api.po;

public class ApiNotifyMsg {
    public final String uuid;
    public final String name;
    public final String businessId;
    public final String args;

    public ApiNotifyMsg(String uuid, String name, String businessId, String args) {
        this.uuid = uuid;
        this.name = name;
        this.businessId = businessId;
        this.args = args;
    }

}