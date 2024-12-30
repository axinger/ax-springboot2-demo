package com.github.axinger.controller;

import com.github.axinger.domain.SysAnimalEntity;
import com.github.axinger.mapper.SysAnimalMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Slf4j
@Service
@AllArgsConstructor
public class TestService2 {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private Executor executor;

    @SneakyThrows
    public void test() {
    }
}
