CREATE TABLE `sys_alphabet`
(
    `id` int          NOT NULL AUTO_INCREMENT,
    `b`  varchar(255) NULL DEFAULT NULL,
    `c`  varchar(255) NULL DEFAULT NULL,
    `d`  varchar(255) NULL DEFAULT NULL,
    `a`  varchar(255) NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
);

-- ----------------------------
-- Records of sys_alphabet
-- ----------------------------
INSERT INTO `sys_alphabet` VALUES (1, '2', '3', '4', NULL);
INSERT INTO `sys_alphabet` VALUES (2, '21', '31', '41', NULL);

