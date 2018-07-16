package cn.enncloud.iot.iotusecim;

import cn.enncloud.iot.iotusecim.rabbitmq.consumer.RabbitReceiver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IotUseCimApplicationTests {

    private static final Logger logger = LogManager.getLogger(RabbitReceiver.class);

    @Test
    public void contextLoads() {
        Resource resource = new ClassPathResource("xcm(1).csv");
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

//        Stream<Map<String,String>> stream =stringStream.map(
//                s->{String[] datas = s.split(",");
//
//                    Map<String,String> map = new HashMap<>();
//                    if(datas.){
//
//                    }
//                    map.put(datas[0],datas[1]);
//                    return map;
//
//        });
        Map<String,String> map = new HashMap<>();
        stream.forEach(map::putAll);
        map.entrySet().stream().filter(o->o.getKey()!=null).forEach(o->{
            System.out.println("key:"+o.getKey()+"value:"+o.getValue());});


//        Stream<String[]> stream = stringStream.map(s -> s.split(","));
//        Stream<Stream<String>> streamStream =stream.map(o-> Arrays.stream(o));


//        long count = bufferedReader.lines().count();
//        logger.info("count:"+count);
    }

    @Test
    public void test1(){
        String ss = "Hello";

        String[] aa = ss.split("");

        String[] bb = {"H", "e", "l", "l", "o"};


        String[] strings = {"Hello", "World"};

        //Arrays.stream接收一个数组返回一个流
        List<Stream<String>> streamList = Arrays.asList(strings).stream().
                map(str -> str.split("")).
                map(str -> Arrays.stream(str)).
                collect(Collectors.toList());

        //分步写(map)

        Stream<String[]> stream = Arrays.asList(strings).stream().
                map(str -> str.split(""));

        Stream<Stream<String>> streamStream = stream.map(strings1 -> Arrays.stream(strings1));
        List<Stream<String>> streamList1 = streamStream.collect(Collectors.toList());


        List<String> stringList = Arrays.asList(strings).stream().
                map(str -> str.split("")).
                flatMap(str -> Arrays.stream(str))
                .collect(Collectors.toList());


        //分步写(流只能消费一次)(flatMap)
        Stream<String[]> stream1 = Arrays.asList(strings).stream().
                map(str -> str.split(""));

        Stream<String> stringStream = stream1.flatMap(strings1 -> Arrays.stream(strings1));

        List<String> stringList1 = stringStream.collect(Collectors.toList());

    }

}
