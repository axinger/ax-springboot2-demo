CREATE TABLE `sys_person`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `age`         int          DEFAULT NULL COMMENT '年龄',
    `birthday`    datetime(6)  DEFAULT NULL COMMENT '生日',
    `create_time` datetime(6)  DEFAULT NULL COMMENT '创建时间',
    `deleted`     int          DEFAULT NULL COMMENT '逻辑删除',
    `gender`      int          DEFAULT NULL COMMENT '性别',
    `height` double DEFAULT NULL COMMENT '身高',
    `name`        varchar(255) DEFAULT NULL COMMENT '姓名',
    `tenant_id`   varchar(255) DEFAULT NULL COMMENT '多租户id',
    `update_time` datetime(6)  DEFAULT NULL COMMENT '更新时间',
    `version`     bigint       DEFAULT NULL COMMENT '版本号',
    `weight` double DEFAULT NULL COMMENT '体重',
    PRIMARY KEY (`id`)
)
