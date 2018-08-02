package cn.enncloud.iot.iotusecim.service.imp;

import cn.enncloud.iot.iotusecim.constants.IotFile;
import cn.enncloud.iot.iotusecim.constants.StaticManager;
import cn.enncloud.iot.iotusecim.service.ILoadCimConfigService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class LoadCimConfigService implements ILoadCimConfigService {
    private static final Logger logger = LogManager.getLogger(LoadCimConfigService.class);

    @Autowired
    private IotFile iotFile;

    private Resource resource;

    @Override
    public void init() {
        resource = new FileSystemResource(iotFile.getPath());
    }

    @Override
    public void load() {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(resource.getInputStream(),"gbk"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stream<String> stringStream = bufferedReader.lines();

        Stream<Map<String,String>> stream = stringStream.map(s->s.split(",")).filter(o->o.length>1).map(o->{
            Map<String,String> map = new HashMap<>();

            map.put(o[0],o[1]);
            return map;
        });

        stream.forEach(StaticManager.IOT_META_MAP::putAll);
        StaticManager.IOT_META_MAP.entrySet().stream().filter(o->o.getKey()!=null).forEach(o->{
            logger.info("key:"+o.getKey()+"value:"+o.getValue());});

    }

    @Override
    public String refresh(String filePath ) {
        if(!StringUtils.isEmpty(filePath)){
            resource = new FileSystemResource(filePath);

        }
        load();

        return null;
    }
}
