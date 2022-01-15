package com.ax.shop;

import com.ax.shop.entity.IpLog;
import com.ax.shop.mapper.IpLogMapper;
import com.ax.shop.mapper.UserinfoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class MapperTests {

    @Autowired
    private IpLogMapper ipLogMapper;


    @Autowired
    private UserinfoMapper userinfoMapper;

    @Test
    void test_like() {
        System.out.println("userinfoMapper = " + userinfoMapper.likePrefixUserName("ji"));
    }

    @Test
    void test_like2() {
        System.out.println("userinfoMapper = " + userinfoMapper.likeUserName("ji%"));
        System.out.println("userinfoMapper2 = " + userinfoMapper.likeUserName("%im%"));
    }


    @Test
    void test1() {

        IpLog ipLog = new IpLog();
        ipLog.setLoginState(0);

        List<IpLog> ipLogs = ipLogMapper.selectIfParam(ipLog);
        System.out.println("ipLogs = " + ipLogs);
    }

    @Test
    void test2() {


        IpLog ipLog = ipLogMapper.selectByPrimaryKey(1L);
        System.out.println("ipLog = " + ipLog);
    }

    @Test
    void test3() {
        IpLog ipLog = new IpLog();
        ipLog.setId(1L);
        List<IpLog> ipLogs = ipLogMapper.selectChoose(ipLog);
        System.out.println("ipLog = " + ipLogs);
    }

    @Test
    void test3_2() {
        IpLog ipLog = new IpLog();
        ipLog.setUserName("jim");
        List<IpLog> ipLogs = ipLogMapper.selectChoose(ipLog);
        System.out.println("ipLog = " + ipLogs.size());
    }

    @Test
    void test3_3() {
        IpLog ipLog = new IpLog();
        List<IpLog> ipLogs = ipLogMapper.selectChoose(ipLog);
        System.out.println("ipLog = " + ipLogs.size());
    }

    @Test
    void test4_3() {
        IpLog ipLog = new IpLog();
        ipLog.setId(1L);
        ipLog.setUserinfoId(3);
        int re = ipLogMapper.updateByPrimaryKeySelective(ipLog);
        System.out.println("ipLog = " + re);
    }

    @Test
    void test5() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        List<IpLog> logs = ipLogMapper.selectByIdList(list);
        System.out.println("ipLog = " + logs);
    }

    @Test
    void test_insertList() {
		/*
		 insert into t_ip_log ( user_name, login_time, ip, login_state, user_type, user_info_id )
 VALUES ( 'jack', null, null, null, null, null ) , ( 'tom', null, null, null, null, null );
		* */
        List<IpLog> list = new ArrayList<>();
        list.add(IpLog.builder().userName("jack").build());
        list.add(IpLog.builder().userName("tom").build());
        System.out.println("ipLog = " + ipLogMapper.insertList(list));
    }


}
