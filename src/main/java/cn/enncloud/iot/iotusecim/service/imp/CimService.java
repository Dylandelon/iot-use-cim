package cn.enncloud.iot.iotusecim.service.imp;

import cn.enncloud.iot.iotusecim.constants.StaticManager;
import cn.enncloud.iot.iotusecim.service.ICimService;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class CimService implements ICimService {

    @Override
    public void doPoceess(String messgage) {
        Stream<String> stream = Stream.of(messgage);
        Stream<String> stream2= stream.map(s -> {
            StaticManager.IOT_META_MAP.entrySet().stream().forEach(
                    t->{
                        Stream<String> streamStr = Stream.of(t.getValue()).map(u->u.substring(u.indexOf("\\"))).map(u->u.replace("\\","_"));
                        s.replace(t.getKey(),streamStr.toString());

                    }

            );
            return s;
            });
        stream2.toString();

    }
}
