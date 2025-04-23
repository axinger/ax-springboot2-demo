CREATE TABLE `sys_employee`
(
    `id`      bigint NOT NULL AUTO_INCREMENT,
    `dept_id` int          DEFAULT NULL,
    `email`   varchar(255) DEFAULT NULL,
    `gender`  int          DEFAULT NULL,
    `name`    varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO `sys_employee` (`id`, `dept_id`, `email`, `gender`, `name`) VALUES (1, 1, NULL, 1, '张三');
INSERT INTO `sys_employee` (`id`, `dept_id`, `email`, `gender`, `name`) VALUES (2, 1, NULL, 1, '李四');
INSERT INTO `sys_employee` (`id`, `dept_id`, `email`, `gender`, `name`) VALUES (3, 2, NULL, 1, '王五');
