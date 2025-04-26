CREATE TABLE `sys_alphabet`
(
    `id` int       NOT NULL AUTO_INCREMENT,
    `b`  decimal(10, 2) DEFAULT NULL,
    `c`  datetime       DEFAULT NULL,
    `d`  timestamp NULL DEFAULT NULL,
    `a`  varchar(255)   DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
);

INSERT INTO `sys_alphabet` (`id`, `b`, `c`, `d`, `a`) VALUES (1, 2.13, '2025-04-24 20:30:26', '2025-04-23 20:30:29', NULL);
INSERT INTO `sys_alphabet` (`id`, `b`, `c`, `d`, `a`) VALUES (2, 2.00, NULL, '2025-04-23 20:30:29', 'jim');


