/*
 Navicat Premium Data Transfer

 Source Server         : mysql8-blog
 Source Server Type    : MySQL
 Source Server Version : 80032 (8.0.32)
 Source Host           : 127.0.0.1:3306
 Source Schema         : easyblog

 Target Server Type    : MySQL
 Target Server Version : 80032 (8.0.32)
 File Encoding         : 65001

 Date: 12/12/2024 10:03:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
                            `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '分类id',
                            `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '分类名称',
                            `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
                            `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除标志位：0：未删除 1：已删除',
                            `articles_total` int DEFAULT NULL COMMENT '文章总数',
                            PRIMARY KEY (`id`) USING BTREE,
                            UNIQUE KEY `uk_name` (`name`) USING BTREE,
                            KEY `idx_create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='文章分类表';

-- ----------------------------
-- Records of category
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
                        `username` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
                        `password` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
                        `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                        `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                        `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '0：未删除 1：已删除',
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`id`, `username`, `password`, `create_time`, `update_time`, `is_deleted`) VALUES (5, 'ben', '$2a$10$eVkOaNbsBMvhKCAKBDRwWeVYycUL.wj5L2c6gYk23UY/D2qh42mu2', '2024-12-03 17:04:24', '2024-12-03 17:04:24', 0);
INSERT INTO `user` (`id`, `username`, `password`, `create_time`, `update_time`, `is_deleted`) VALUES (6, 'admin', '$2a$10$TWNYR0ztTCSCs2SD4KZ0yu6QcXM0nAdMQrE7xvuZfj5OjYJ81vAO6', '2024-12-05 18:14:00', '2024-12-05 18:14:00', 0);
INSERT INTO `user` (`id`, `username`, `password`, `create_time`, `update_time`, `is_deleted`) VALUES (7, 'test', '$2a$10$CJFHBjdUr4h0TbN9mn5PXOmAQSUz3Kd3oDDVMqIWGyWBXsUbwRoiy', '2024-12-05 23:54:12', '2024-12-05 23:54:12', 0);
COMMIT;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
                             `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
                             `username` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
                             `role` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色',
                             `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             PRIMARY KEY (`id`),
                             KEY `idx_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户角色表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
BEGIN;
INSERT INTO `user_role` (`id`, `username`, `role`, `create_time`, `update_time`) VALUES (1, 'ben', 'ROLE_ADMIN', '2024-12-03 08:46:55', '2024-12-03 08:46:55');
INSERT INTO `user_role` (`id`, `username`, `role`, `create_time`, `update_time`) VALUES (2, 'admin', 'ROLE_ADMIN', '2024-12-03 08:46:55', '2024-12-05 18:19:11');
INSERT INTO `user_role` (`id`, `username`, `role`, `create_time`, `update_time`) VALUES (3, 'test', 'ROLE_TEST', '2024-12-03 08:47:57', '2024-12-03 08:51:42');
INSERT INTO `user_role` (`id`, `username`, `role`, `create_time`, `update_time`) VALUES (4, 'ben', 'ROLE_TEST', '2024-12-03 08:47:57', '2024-12-03 08:47:57');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
