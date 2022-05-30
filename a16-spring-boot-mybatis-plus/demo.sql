CREATE DATABASE
    IF
    NOT EXISTS `ax_test` CHARACTER
    SET utf8;
USE `ax_test`;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`
(
    `id`   bigint NOT NULL AUTO_INCREMENT,
    `name` varchar(20) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of department
-- ----------------------------
BEGIN;
INSERT INTO `department`
VALUES (1, '财务部');
INSERT INTO `department`
VALUES (2, '销售部');
COMMIT;

-- ----------------------------
-- Table structure for emps
-- ----------------------------
DROP TABLE IF EXISTS `emps`;
CREATE TABLE `emps`
(
    `id`       bigint NOT NULL AUTO_INCREMENT,
    `lastName` varchar(20)     DEFAULT NULL,
    `email`    varchar(20)     DEFAULT NULL,
    `gender`   int    NOT NULL DEFAULT '0',
    `deptid`   int             DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of emps
-- ----------------------------
BEGIN;
INSERT INTO `emps`
VALUES (1, 'jim', NULL, 0, 1);
INSERT INTO `emps`
VALUES (2, 'tom', NULL, 0, 1);
INSERT INTO `emps`
VALUES (3, 'jack', NULL, 0, 2);
COMMIT;

-- ----------------------------
-- Table structure for t_person
-- ----------------------------
DROP TABLE IF EXISTS `t_person`;
CREATE TABLE `t_person`
(
    `id`   bigint NOT NULL AUTO_INCREMENT,
    `name` varchar(20) DEFAULT NULL,
    `age`  int         DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_person
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student`
(
    `id`      bigint NOT NULL AUTO_INCREMENT,
    `name`    varchar(20) DEFAULT NULL,
    `age`     int         DEFAULT NULL,
    `sex`     int         DEFAULT NULL,
    `address` varchar(20) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_student
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`     bigint NOT NULL AUTO_INCREMENT,
    `name`   varchar(20)     DEFAULT NULL,
    `age`    int             DEFAULT NULL,
    `gender` int    NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user`
VALUES (1, 'jim', 20, 0);
INSERT INTO `user`
VALUES (2, 'tom', 21, 2);
INSERT INTO `user`
VALUES (3, 'jack', 20, 1);
INSERT INTO `user`
VALUES (4, 'lili', 23, 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
