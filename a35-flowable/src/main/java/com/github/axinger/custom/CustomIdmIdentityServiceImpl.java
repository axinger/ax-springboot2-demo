//package com.github.axinger.custom;
//
//import org.flowable.idm.api.UserQuery;
//import org.flowable.idm.engine.IdmEngineConfiguration;
//import org.flowable.idm.engine.impl.IdmIdentityServiceImpl;
//
//// 这里直接继承了它的默认实现类
//public class CustomIdmIdentityServiceImpl extends IdmIdentityServiceImpl {
//
//    public CustomIdmIdentityServiceImpl(IdmEngineConfiguration idmEngineConfiguration) {
//        super(idmEngineConfiguration);
//    }
//
//    @Override
//    public UserQuery createUserQuery() {
//        // 自定义的用户查询器实现
//        return new CustomUserQueryImpl();
//    }
//}
