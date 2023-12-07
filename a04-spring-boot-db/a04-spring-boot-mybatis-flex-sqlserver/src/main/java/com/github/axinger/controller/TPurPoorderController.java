package com.github.axinger.controller;

import com.mybatisflex.core.paginate.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.axinger.service.TPurPoorderService;
import com.github.axinger.domain.TPurPoorder;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

/**
 * 控制层。
 *
 * @author mybatis-flex-helper automatic generation
 * @since 1.0
 */
@RestController
@RequestMapping("/tPurPoorder")
public class TPurPoorderController {

    @Autowired
    private TPurPoorderService tPurPoorderService;


    /**
     * 根据主键获取详细信息。
     *
     * @param id tPurPoorder主键
     * @return 详情
     */
    @GetMapping("/getInfo/{id}")
    public TPurPoorder getInfo(@PathVariable Serializable id) {
        return tPurPoorderService.getById(id);
    }


    /**
     * 分页查询
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("/page")
    public Page<TPurPoorder> page(Page<TPurPoorder> page) {
        return tPurPoorderService.page(page);
    }
}
