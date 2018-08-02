package cn.enncloud.iot.iotusecim.init;

import cn.enncloud.iot.iotusecim.constants.IotFile;
import cn.enncloud.iot.iotusecim.service.ILoadCimConfigService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class LoadConfigFile implements CommandLineRunner {
    private static final Logger logger = LogManager.getLogger(LoadConfigFile.class);

    @Autowired
    private IotFile iotFile;

    @Autowired
    private ILoadCimConfigService loadCimConfigService;


    @Override
    public void run(String... args) throws Exception {

        if(logger.isInfoEnabled()){
            logger.info("----------加载cim配置文件开始---------");
        }
        loadCimConfigService.init();
        loadCimConfigService.load();
        if(logger.isInfoEnabled()){
            logger.info("----------加载cim配置文件结束---------");
        }

    }
}
