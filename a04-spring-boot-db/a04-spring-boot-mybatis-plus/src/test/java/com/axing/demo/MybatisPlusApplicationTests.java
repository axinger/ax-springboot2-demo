package com.axing.demo;

import com.axing.demo.domain.Room;
import com.axing.demo.domain.School;
import com.axing.demo.service.RoomService;
import com.axing.demo.service.SchoolService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    private SchoolService schoolService;
    @Autowired
    private RoomService roomService;

    @Test
    void test_School_schoolList_可以() {

        List<School> list = schoolService.list();
        System.out.println("list = " + list);

        List<School> list1 = schoolService.schoolList(Wrappers.<School>query()
                .eq("s.id", 1));
        System.out.println("list1 = " + list1);

        // 这个不可以
        // List<School> List2 = schoolService.schoolList(Wrappers.<School>lambdaQuery()
        //         .eq(School::getId, 1));
        // System.out.println("List2 = " + List2);
    }

    @Test
    void test_Room() {
        List<Room> list = roomService.list();
        System.out.println("list = " + list);

        // 5.7 可以* group by
        // 8.0 查询的字段要和group by字段保持一致
        List<Room> list1 = roomService.lambdaQuery()
                .select(Room::getRoomName)
                .groupBy(Room::getRoomName)
                .list();
        System.out.println("list1 = " + list1);
    }
}
