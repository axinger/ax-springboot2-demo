package com.ax.demo;


import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class A22DemoApplicationTest {

    @Test
    void test1() {


        String token = "ILutFAbWsGfZ0AD6TVm1gVQntZxxJY9XLk0DDe-SBiLy_T9ztgMMkSMXyhbdrl35bGejWweESsZP0oETivYI9g==";
        String bucket = "axing";
        String org = "axing";
        InfluxDBClient client = InfluxDBClientFactory.create("http://47.101.156.93:8086", token.toCharArray());


        WriteApiBlocking writeApi = client.getWriteApiBlocking();

//        String data = "mem,host=host1 used_percent=23.43234543";
//
//        writeApi.writeRecord(bucket, org, WritePrecision.NS, data);


        //Option 2: Use a Data Point to write data
        Point point = Point
                .measurement("mem_001")
                .addTag("定义tag", "tag_001")
                .addField("定义Field1", "Field_001")
                .addField("定义Field2", "Field_002")
                .time(Instant.now(), WritePrecision.NS);
        writeApi.writePoint(bucket, org, point);

//        @Measurement(name = "mem")
//        class Mem {
//            @Column(tag = true)
//            String host;
//            @Column
//            Double used_percent;
//            @Column(timestamp = true)
//            Instant time;
//        }
//
//
//        Mem mem = new Mem();
//        mem.host = "aaaaaaaaaaa";
//        mem.used_percent = 23.43234543;
//        mem.time = Instant.now();
//
//
//        writeApi.writeRecord(bucket, org, WritePrecision.NS,mem.toString());
//

    }

    @Autowired
    private InfluxDBConfig influxDBConfig;


    @Test
    void test2() {

        String measurement = "a_person_01";
        Map<String, String> tags = new HashMap<>();
        ;
        tags.put("name", "jim");
        Map<String, Object> fields = new HashMap<>();
        fields.put("age", "10");
        influxDBConfig.insert(measurement, tags, fields);
    }
}
