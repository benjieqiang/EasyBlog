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

 Date: 13/12/2024 08:37:41
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
                            `articles_total` int DEFAULT NULL,
                            PRIMARY KEY (`id`) USING BTREE,
                            UNIQUE KEY `uk_name` (`name`) USING BTREE,
                            KEY `idx_create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='文章分类表';

-- ----------------------------
-- Records of category
-- ----------------------------
BEGIN;
INSERT INTO `category` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (2, '后端开发', '2024-03-21 17:32:04', '2024-03-21 17:32:04', 0, 3);
INSERT INTO `category` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (3, 'Flutter', '2024-03-21 17:54:51', '2024-03-21 17:54:51', 1, 0);
INSERT INTO `category` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (7, 'Nodejs', '2024-03-21 17:55:11', '2024-03-21 17:55:11', 0, 1);
INSERT INTO `category` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (9, '前端开发', '2024-03-21 19:39:38', '2024-03-21 19:39:38', 0, 5);
INSERT INTO `category` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (10, 'App开发', '2024-03-21 20:56:46', '2024-03-21 20:56:46', 0, 0);
INSERT INTO `category` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (11, '人工智能', '2024-03-21 20:56:58', '2024-03-21 20:56:58', 0, 0);
INSERT INTO `category` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (17, '生活知识', '2024-03-26 08:00:16', '2024-03-26 08:00:16', 0, 0);
INSERT INTO `category` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (18, '软件安装', '2024-03-26 08:01:19', '2024-03-26 08:01:19', 0, 4);
INSERT INTO `category` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (19, 'Git', '2024-04-17 11:16:04', '2024-04-17 11:16:04', 0, 0);
INSERT INTO `category` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (20, 'Java', '2024-12-12 14:29:36', '2024-12-12 14:29:36', 1, 0);
INSERT INTO `category` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (21, 'python', '2024-12-12 15:24:02', '2024-12-12 15:24:02', 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
                       `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '标签id',
                       `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '标签名称',
                       `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                       `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
                       `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '0：未删除 1：已删除',
                       `articles_total` int DEFAULT NULL COMMENT '文章总数',
                       PRIMARY KEY (`id`) USING BTREE,
                       UNIQUE KEY `uk_name` (`name`) USING BTREE,
                       KEY `idx_create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='文章标签表';

-- ----------------------------
-- Records of tag
-- ----------------------------
BEGIN;
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (1, 'java', '2024-03-21 23:44:27', '2024-03-21 23:44:27', 0, 6);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (2, 'javascript', '2024-03-21 23:44:44', '2024-03-21 23:44:44', 0, 3);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (3, 'Python', '2024-03-21 23:44:49', '2024-03-21 23:44:49', 0, 0);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (9, '工具', '2024-03-22 16:34:10', '2024-03-22 16:34:10', 0, 5);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (10, 'Flutter', '2024-03-22 16:36:30', '2024-03-22 16:36:30', 0, 2);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (11, 'Vue3', '2024-03-22 17:16:24', '2024-03-22 17:16:24', 0, 5);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (12, 'Vite', '2024-03-22 17:16:24', '2024-03-22 17:16:24', 0, 2);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (13, 'Element', '2024-03-22 17:16:24', '2024-03-22 17:16:24', 0, 0);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (14, '生活知识', '2024-03-26 16:02:48', '2024-03-26 16:02:48', 0, 0);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (15, '健康', '2024-03-26 16:02:48', '2024-03-26 16:02:48', 0, 0);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (16, '跑步', '2024-03-26 16:02:48', '2024-03-26 16:02:48', 0, 0);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (17, 'Mac', '2024-03-26 16:02:48', '2024-03-26 16:02:48', 0, 2);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (18, 'Windows', '2024-03-26 16:02:48', '2024-03-26 16:02:48', 0, 0);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (19, 'iPhone', '2024-03-26 16:02:48', '2024-03-26 16:02:48', 0, 0);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (20, '安卓', '2024-03-26 16:02:48', '2024-03-26 16:02:48', 0, 0);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (21, 'UniApp', '2024-03-26 16:02:48', '2024-03-26 16:02:48', 0, 1);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (22, 'Vue2', '2024-03-26 16:03:30', '2024-03-26 16:03:30', 0, 2);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (23, '微信小程序', '2024-03-26 16:03:30', '2024-03-26 16:03:30', 0, 1);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (24, 'SpringBoot', '2024-03-26 16:03:30', '2024-03-26 16:03:30', 0, 2);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (25, 'Redis', '2024-03-26 16:03:30', '2024-03-26 16:03:30', 0, 1);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (26, '心理学', '2024-03-26 16:04:21', '2024-03-26 16:04:21', 0, 0);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (27, '孩子教育', '2024-03-26 16:04:21', '2024-03-26 16:04:21', 0, 0);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (28, '自我提升', '2024-03-26 16:04:21', '2024-03-26 16:04:21', 0, 0);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (29, '经济管理', '2024-03-26 16:04:21', '2024-03-26 16:04:21', 0, 0);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (30, 'IDE', '2024-03-26 17:53:00', '2024-03-26 17:53:00', 0, 2);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (31, 'MySql', '2024-04-03 10:45:31', '2024-04-03 10:45:31', 0, 1);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (32, 'Docker', '2024-04-03 10:45:31', '2024-04-03 10:45:31', 0, 3);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (33, 'Jenkins', '2024-04-03 10:45:31', '2024-04-03 10:45:31', 0, 2);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (34, 'VueUse', '2024-04-17 00:33:03', '2024-04-17 00:33:03', 0, 2);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (35, 'Git', '2024-04-17 19:16:25', '2024-04-17 19:16:25', 0, 1);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (36, 'Npm', '2024-04-17 19:19:48', '2024-04-17 19:19:48', 0, 1);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (37, 'Vant', '2024-04-17 19:19:48', '2024-04-17 19:19:48', 0, 1);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (38, 'ElementUI', '2024-04-17 19:19:48', '2024-04-17 19:19:48', 0, 1);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (39, '字体图标', '2024-04-19 18:46:32', '2024-04-19 18:46:32', 0, 2);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`) VALUES (40, '面试题', '2024-04-23 18:58:17', '2024-04-23 18:58:17', 0, 1);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户角色表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
BEGIN;
INSERT INTO `user_role` (`id`, `username`, `role`, `create_time`, `update_time`) VALUES (1, 'ben', 'ROLE_ADMIN', '2024-12-03 08:46:55', '2024-12-03 08:46:55');
INSERT INTO `user_role` (`id`, `username`, `role`, `create_time`, `update_time`) VALUES (2, 'admin', 'ROLE_ADMIN', '2024-12-03 08:46:55', '2024-12-05 18:19:11');
INSERT INTO `user_role` (`id`, `username`, `role`, `create_time`, `update_time`) VALUES (3, 'test', 'ROLE_TEST', '2024-12-03 08:47:57', '2024-12-03 08:51:42');
INSERT INTO `user_role` (`id`, `username`, `role`, `create_time`, `update_time`) VALUES (4, 'ben', 'ROLE_TEST', '2024-12-03 08:47:57', '2024-12-03 08:47:57');
INSERT INTO `user_role` (`id`, `username`, `role`, `create_time`, `update_time`) VALUES (5, 'test2', 'ROLE_TEST', '2024-12-13 08:35:54', '2024-12-13 08:35:54');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
