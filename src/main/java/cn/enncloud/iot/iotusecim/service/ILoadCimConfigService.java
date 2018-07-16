package cn.enncloud.iot.iotusecim.service;

public interface ILoadCimConfigService {

    void init();
    void load();
    String refresh(String filePath);
    /**
     * 预留接口，获取网络资源配置信息
     */
//    String refreshByUrl(String fileUrlPath);
}
