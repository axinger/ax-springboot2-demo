package com.ax.mall.service.impl;

import com.ax.mall.entity.IpLog;
import com.ax.mall.mapper.IpLogMapper;
import com.ax.mall.query.IpLogQueryObject;
import com.ax.mall.util.axUtil.AxPageResultEntity;
import com.ax.mall.util.axUtil.ResponseEntity;
import com.ax.mall.util.axUtil.AxResultStateEnum;
import com.ax.mall.service.IIpLogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author axing
 */
@Service
@CacheConfig(cacheNames = "ipLog")
public class IpLogServiceImpl implements IIpLogService {

//    @Autowired
//    RedisService redisService;

//    public addIpLog(Userinfo userinfo)
//    IpLog ipLog = new IpLog();
//        ipLog.setIp(request.getRemoteAddr());
//        ipLog.setLoginTime(new Date());
//        ipLog.setUsername(username);
//
//
//    Userinfo userinfo = this.userinfoMapper.getModelByusernameAndpassword(username, password);
//        if (userinfo != null) {
//            /*
//            登陆成功,保存当前登陆的userinfo
//             */
////            UserinfoContext.putUserinfo(userinfo);
//
//        ipLog.setUserType(userinfo.getUserType());
//        ipLog.setUserinfoId(userinfo.getId());
//        ipLog.setLoginState(IpLog.LOGINSTATE_SUCCESS);
//
//        ipLogMapper.insert(ipLog);
//    } else {
//        ipLog.setLoginState(IpLog.LOGINSTATE_FAILD);
//
//    }
//
    @Autowired
    private IpLogMapper ipLogMapper;

    @Override
    public AxPageResultEntity query(IpLogQueryObject queryObject) {

        int count = ipLogMapper.queryForCount(queryObject);

        if (count > 0) {

            List<IpLog> list = ipLogMapper.query(queryObject);


            return new AxPageResultEntity(count, queryObject.getPageSize(), queryObject.getCurrentPage(), list);

        }
        return AxPageResultEntity.empty(queryObject.getPageSize());
    }

    @Override
    public void insert(IpLog ipLog) {
        ipLogMapper.insert(ipLog);
    }

    @Override
    public int queryForCount(IpLogQueryObject queryObject) {
        return 0;
    }

    //        @Cacheable(value = RedisService.REDIS_VALUE_IPLOG)
    @Override
    public PageInfo<IpLog> findByPageInfo(int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);

        List<IpLog> list = ipLogMapper.findByPage();

        PageInfo<IpLog> pageInfo = new PageInfo(list);

        return pageInfo;
    }

    //    @Cacheable(value = RedisService.REDIS_VALUE_IPLOG)
    @Override
    public Page<IpLog> findByPage(int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        return ipLogMapper.findByPage();
    }

    @Cacheable(key = "'all'")
    @Override
    public Object findAll() {
        List<IpLog> list = ipLogMapper.findByPage();
        return list;
    }

    @Override
    public IpLog getByKey(Long id) {
        IpLog ipLog = ipLogMapper.selectByPrimaryKey(id);
        return ipLog;
    }


    //    @CacheEvict(value = RedisService.REDIS_VALUE_IPLOG,key = "#ipLog.id")
//    @CacheEvict(value = RedisService.REDIS_VALUE_IPLOG, key = "#p0.id")
    @Override
    public int updateByEntity(IpLog ipLog) {
        return ipLogMapper.updateByPrimaryKeySelective(ipLog);
    }

    @Override
    public Object updateByListWhen(List<IpLog> list) {

        int count = ipLogMapper.updateByListWhen(list);
        ResponseEntity responseEntity = new ResponseEntity();
        if (count > 0) {
            responseEntity.setStateEnum(AxResultStateEnum.SUCCESS);
        } else {
            responseEntity.setStateEnum(AxResultStateEnum.INVALID);
        }
        return responseEntity;
    }
/**
 * 缓存注解的使用
 *
 * @Cacheable
 * Spring 在执行 @Cacheable 标注的方法前先查看缓存中是否有数据，如果有数据，则直接返回缓存数据；若没有数据，执行该方法并将方法返回值放进缓存。
 * 参数： value缓存名、 key缓存键值、 condition满足缓存条件、unless否决缓存条件
 *
 * @CachePut
 * 和 @Cacheable 类似，但会把方法的返回值放入缓存中, 主要用于数据新增和修改方法
 *
 * @CacheEvict
 * 方法执行成功后会从缓存中移除相应数据。
 * 参数： value缓存名、 key缓存键值、 condition满足缓存条件、 unless否决缓存条件、 allEntries是否移除所有数据（设置为true时会移除所有缓存）
 * */

}
