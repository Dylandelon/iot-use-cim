package cn.enncloud.iot.iotusecim.constants;

import java.util.Map;

public enum CimFileEnum {

    INSTANCE;
    private Map<String,String> IOT_META_MAP=null;

    CimFileEnum() {
        IOT_META_MAP =null;
    }
    public Map<String,String> getCimData(){

        return  IOT_META_MAP;

    }
}
