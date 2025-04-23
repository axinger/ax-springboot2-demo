CREATE TABLE `sys_department`
(
    `id`   bigint NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO `sys_department` (`id`, `name`) VALUES (1, '行政部');
INSERT INTO `sys_department` (`id`, `name`) VALUES (2, '采购部');
