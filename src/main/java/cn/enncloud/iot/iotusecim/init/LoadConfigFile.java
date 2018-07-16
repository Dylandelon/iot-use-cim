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

    private Resource resource;

    private static Map<String,String> IOT_META_MAP=null;

    @Override
    public void run(String... args) throws Exception {

        loadCimConfigService.init();
        loadCimConfigService.load();

    }
}
