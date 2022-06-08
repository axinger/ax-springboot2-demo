#
创建ip登录信息
CREATE TABLE t_ip_log
(
    id           BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键id',
    user_name    VARCHAR(200) COMMENT '姓名',
    login_time   TIMESTAMP COMMENT '登录时间',
    ip           VARCHAR(22) COMMENT 'ip地址',
    login_state  TINYINT COMMENT '登录状态 0 1 2',
    user_type    TINYINT COMMENT '用户类型 0 1 2',
    user_info_id BIGINT COMMENT '关联User表id'
) COMMENT ='ip登录信息';


#
创建用户信息
CREATE TABLE t_user_info
(
    id        BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键id',
    user_name VARCHAR(20) COMMENT '姓名',
    password  VARCHAR(20) COMMENT '密码MD5加密的',
    user_type TINYINT COMMENT '用户类型 0 1 2'
) COMMENT ='用户信息';


#
创建用户角色
CREATE TABLE t_user_role
(
    id        BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键id',
    role_name VARCHAR(200) COMMENT '角色说明',
    user_id   TINYINT COMMENT '关联User表id'
) COMMENT ='角色信息';

#
创建学生信息
CREATE TABLE t_student
(
    id      BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键id',
    name    VARCHAR(11) COMMENT '姓名',
    age     INT COMMENT '年龄',
    sex     INT COMMENT '0保密，1男，2女',
    address VARCHAR(11) COMMENT '地址'
) COMMENT ='学生信息';
