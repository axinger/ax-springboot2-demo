CREATE TABLE `sys_employee`
(
    `id`      bigint NOT NULL AUTO_INCREMENT,
    `dept_id` int          DEFAULT NULL,
    `email`   varchar(255) DEFAULT NULL,
    `gender`  int          DEFAULT NULL,
    `name`    varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);
