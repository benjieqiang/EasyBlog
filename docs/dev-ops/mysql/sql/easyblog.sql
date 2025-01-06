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

 Date: 06/01/2025 18:11:11
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '文章id',
    `title`       varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '文章标题',
    `cover`       varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '文章封面',
    `summary`     varchar(160) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT '' COMMENT '文章摘要',
    `create_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
    `is_deleted`  tinyint                                                       NOT NULL DEFAULT '0' COMMENT '删除标志位：0：未删除 1：已删除',
    `read_num`    int unsigned NOT NULL DEFAULT '1' COMMENT '被阅读次数',
    `weight`      int unsigned NOT NULL DEFAULT '0' COMMENT '文章权重，用于是否置顶（0: 未置顶；>0: 参与置顶，权重值越高越靠前）',
    `type`        tinyint                                                       NOT NULL DEFAULT '1' COMMENT '文章类型 - 1：普通文章，2：收录于知识库',
    PRIMARY KEY (`id`) USING BTREE,
    KEY           `idx_create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='文章表';

-- ----------------------------
-- Records of article
-- ----------------------------
BEGIN;
INSERT INTO `article` (`id`, `title`, `cover`, `summary`, `create_time`, `update_time`, `is_deleted`, `read_num`,
                       `weight`, `type`)
VALUES (2, 'TypeScript文档', 'https://img.23233.jpg', 'TypeScript全套教程', '2024-03-22 16:02:00',
        '2024-03-25 23:13:19', 1, 23, 0, 2);
INSERT INTO `article` (`id`, `title`, `cover`, `summary`, `create_time`, `update_time`, `is_deleted`, `read_num`,
                       `weight`, `type`)
VALUES (7, '测试哈哈哈', 'https://img.23233.jpg', '测试摘33要', '2024-03-22 19:21:07', '2024-03-26 15:33:08', 0, 23, 1,
        2);
INSERT INTO `article` (`id`, `title`, `cover`, `summary`, `create_time`, `update_time`, `is_deleted`, `read_num`,
                       `weight`, `type`)
VALUES (10, '测试哈哈哈', 'https://img.23233.jpg', '测试摘33要', '2024-03-26 17:47:17', '2024-03-26 18:03:21', 0, 23, 0,
        2);
INSERT INTO `article` (`id`, `title`, `cover`, `summary`, `create_time`, `update_time`, `is_deleted`, `read_num`,
                       `weight`, `type`)
VALUES (11, '测试哈哈哈', 'https://img.23233.jpg', '测试摘33要', '2024-03-26 17:56:23', '2024-03-26 17:56:23', 0, 23,
        12, 1);
INSERT INTO `article` (`id`, `title`, `cover`, `summary`, `create_time`, `update_time`, `is_deleted`, `read_num`,
                       `weight`, `type`)
VALUES (12, '测试哈哈哈', 'https://img.23233.jpg', '测试摘12212要', '2024-04-03 10:44:38', '2024-04-03 11:10:41', 0, 23,
        0, 2);
INSERT INTO `article` (`id`, `title`, `cover`, `summary`, `create_time`, `update_time`, `is_deleted`, `read_num`,
                       `weight`, `type`)
VALUES (13, '测试哈哈哈', 'https://img.23233.jpg', '测试摘12212要', '2024-04-07 00:31:04', '2024-04-07 00:31:23', 0, 23,
        30, 1);
COMMIT;

-- ----------------------------
-- Table structure for article_category_rel
-- ----------------------------
DROP TABLE IF EXISTS `article_category_rel`;
CREATE TABLE `article_category_rel`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `article_id`  bigint unsigned NOT NULL COMMENT '文章id',
    `category_id` bigint unsigned NOT NULL COMMENT '分类id',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uni_article_id` (`article_id`) USING BTREE,
    KEY           `idx_category_id` (`category_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='文章所属分类关联表';

-- ----------------------------
-- Records of article_category_rel
-- ----------------------------
BEGIN;
INSERT INTO `article_category_rel` (`id`, `article_id`, `category_id`)
VALUES (43, 15, 7);
INSERT INTO `article_category_rel` (`id`, `article_id`, `category_id`)
VALUES (45, 16, 18);
INSERT INTO `article_category_rel` (`id`, `article_id`, `category_id`)
VALUES (47, 14, 18);
INSERT INTO `article_category_rel` (`id`, `article_id`, `category_id`)
VALUES (48, 17, 9);
INSERT INTO `article_category_rel` (`id`, `article_id`, `category_id`)
VALUES (49, 18, 2);
INSERT INTO `article_category_rel` (`id`, `article_id`, `category_id`)
VALUES (50, 19, 9);
INSERT INTO `article_category_rel` (`id`, `article_id`, `category_id`)
VALUES (55, 20, 9);
INSERT INTO `article_category_rel` (`id`, `article_id`, `category_id`)
VALUES (58, 21, 9);
INSERT INTO `article_category_rel` (`id`, `article_id`, `category_id`)
VALUES (61, 23, 16);
INSERT INTO `article_category_rel` (`id`, `article_id`, `category_id`)
VALUES (62, 25, 16);
INSERT INTO `article_category_rel` (`id`, `article_id`, `category_id`)
VALUES (63, 26, 18);
INSERT INTO `article_category_rel` (`id`, `article_id`, `category_id`)
VALUES (65, 28, 20);
INSERT INTO `article_category_rel` (`id`, `article_id`, `category_id`)
VALUES (66, 29, 20);
INSERT INTO `article_category_rel` (`id`, `article_id`, `category_id`)
VALUES (67, 30, 21);
INSERT INTO `article_category_rel` (`id`, `article_id`, `category_id`)
VALUES (68, 31, 21);
INSERT INTO `article_category_rel` (`id`, `article_id`, `category_id`)
VALUES (72, 33, 21);
INSERT INTO `article_category_rel` (`id`, `article_id`, `category_id`)
VALUES (73, 7, 21);
INSERT INTO `article_category_rel` (`id`, `article_id`, `category_id`)
VALUES (74, 10, 10);
INSERT INTO `article_category_rel` (`id`, `article_id`, `category_id`)
VALUES (75, 11, 11);
INSERT INTO `article_category_rel` (`id`, `article_id`, `category_id`)
VALUES (76, 12, 12);
INSERT INTO `article_category_rel` (`id`, `article_id`, `category_id`)
VALUES (77, 13, 13);
COMMIT;

-- ----------------------------
-- Table structure for article_content
-- ----------------------------
DROP TABLE IF EXISTS `article_content`;
CREATE TABLE `article_content`
(
    `id`         bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '文章内容id',
    `article_id` bigint NOT NULL COMMENT '文章id',
    `content`    longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '教程正文',
    PRIMARY KEY (`id`) USING BTREE,
    KEY          `idx_article_id` (`article_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='文章内容表';

-- ----------------------------
-- Records of article_content
-- ----------------------------
BEGIN;
INSERT INTO `article_content` (`id`, `article_id`, `content`)
VALUES (7, 7, '内容33');
INSERT INTO `article_content` (`id`, `article_id`, `content`)
VALUES (10, 10, '内容10');
INSERT INTO `article_content` (`id`, `article_id`, `content`)
VALUES (11, 11, '内容11');
INSERT INTO `article_content` (`id`, `article_id`, `content`)
VALUES (12, 12, '内容12');
INSERT INTO `article_content` (`id`, `article_id`, `content`)
VALUES (13, 13, '内容13');
COMMIT;

-- ----------------------------
-- Table structure for article_tag_rel
-- ----------------------------
DROP TABLE IF EXISTS `article_tag_rel`;
CREATE TABLE `article_tag_rel`
(
    `id`         bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `article_id` bigint unsigned NOT NULL COMMENT '文章id',
    `tag_id`     bigint unsigned NOT NULL COMMENT '标签id',
    PRIMARY KEY (`id`) USING BTREE,
    KEY          `idx_article_id` (`article_id`) USING BTREE,
    KEY          `idx_tag_id` (`tag_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=215 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='文章对应标签关联表';

-- ----------------------------
-- Records of article_tag_rel
-- ----------------------------
BEGIN;
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (121, 15, 17);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (122, 15, 9);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (126, 16, 1);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (127, 16, 25);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (128, 16, 32);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (130, 14, 9);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (131, 17, 2);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (132, 17, 11);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (133, 17, 12);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (134, 17, 22);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (135, 18, 1);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (136, 18, 9);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (137, 18, 24);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (138, 18, 30);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (139, 19, 11);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (140, 19, 34);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (153, 20, 2);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (154, 20, 9);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (155, 20, 39);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (158, 21, 40);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (159, 21, 2);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (160, 21, 11);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (161, 21, 12);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (162, 21, 21);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (163, 21, 22);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (164, 21, 23);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (165, 21, 34);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (166, 21, 35);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (167, 21, 36);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (168, 21, 37);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (169, 21, 38);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (170, 21, 39);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (171, 1, 20);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (173, 26, 1);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (174, 26, 51);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (175, 26, 52);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (179, 28, 1);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (180, 28, 51);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (181, 28, 52);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (182, 29, 1);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (183, 29, 51);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (184, 29, 52);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (185, 30, 1);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (186, 30, 51);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (187, 30, 52);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (188, 31, 1);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (189, 31, 51);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (190, 31, 52);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (199, 33, 1);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (200, 33, 2);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (201, 33, 53);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (202, 33, 52);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (203, 7, 1);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (204, 7, 2);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (205, 7, 53);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (206, 7, 52);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (207, 10, 1);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (208, 10, 2);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (209, 11, 1);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (210, 11, 2);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (211, 12, 1);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (212, 12, 2);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (213, 13, 1);
INSERT INTO `article_tag_rel` (`id`, `article_id`, `tag_id`)
VALUES (214, 13, 2);
COMMIT;

-- ----------------------------
-- Table structure for blog_setting
-- ----------------------------
DROP TABLE IF EXISTS `blog_setting`;
CREATE TABLE `blog_setting`
(
    `id`                         bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `logo`                       varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '博客Logo',
    `name`                       varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '博客名称',
    `author`                     varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '作者名',
    `introduction`               varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '介绍语',
    `avatar`                     varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '作者头像',
    `github_homepage`            varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'GitHub 主页访问地址',
    `csdn_homepage`              varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'CSDN 主页访问地址',
    `gitee_homepage`             varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'Gitee 主页访问地址',
    `zhihu_homepage`             varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '知乎主页访问地址',
    `mail`                       varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci           DEFAULT '' COMMENT '博主邮箱地址',
    `is_comment_sensi_word_open` tinyint                                                       NOT NULL DEFAULT '1' COMMENT '是否开启评论敏感词过滤, 0:不开启；1：开启',
    `is_comment_examine_open`    tinyint                                                       NOT NULL DEFAULT '0' COMMENT '是否开启评论审核, 0: 未开启；1：开启',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='博客设置表';

-- ----------------------------
-- Records of blog_setting
-- ----------------------------
BEGIN;
INSERT INTO `blog_setting` (`id`, `logo`, `name`, `author`, `introduction`, `avatar`, `github_homepage`,
                            `csdn_homepage`, `gitee_homepage`, `zhihu_homepage`, `mail`, `is_comment_sensi_word_open`,
                            `is_comment_examine_open`)
VALUES (1, 'https://avatars.githubusercontent.com/u/26585993', 'Ben', 'Ben', 'Less is More',
        'https://avatars.githubusercontent.com/u/26585993', 'http://github.benjieqiang.com',
        'http://github.benjieqiang.com', 'http://github.benjieqiang.com', 'http://github.benjieqiang.com',
        'benjieqiang@gmail.com', 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`
(
    `id`             bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '分类id',
    `name`           varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '分类名称',
    `create_time`    datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
    `is_deleted`     tinyint                                                      NOT NULL DEFAULT '0' COMMENT '逻辑删除标志位：0：未删除 1：已删除',
    `articles_total` int                                                                   DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_name` (`name`) USING BTREE,
    KEY              `idx_create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='文章分类表';

-- ----------------------------
-- Records of category
-- ----------------------------
BEGIN;
INSERT INTO `category` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (2, '后端开发', '2024-03-21 17:32:04', '2025-01-04 18:15:44', 0, 3);
INSERT INTO `category` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (3, 'Flutter', '2024-03-21 17:54:51', '2024-03-21 17:54:51', 1, 0);
INSERT INTO `category` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (7, 'Nodejs', '2024-03-21 17:55:11', '2025-01-04 18:15:44', 0, 1);
INSERT INTO `category` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (9, '前端开发', '2024-03-21 19:39:38', '2025-01-04 18:15:44', 0, 5);
INSERT INTO `category` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (10, 'App开发', '2024-03-21 20:56:46', '2025-01-04 18:15:44', 0, 0);
INSERT INTO `category` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (11, '人工智能', '2024-03-21 20:56:58', '2025-01-04 18:15:44', 0, 0);
INSERT INTO `category` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (17, '生活知识', '2024-03-26 08:00:16', '2025-01-04 18:15:44', 0, 0);
INSERT INTO `category` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (18, '软件安装', '2024-03-26 08:01:19', '2025-01-04 18:15:44', 0, 5);
INSERT INTO `category` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (19, 'Git', '2024-04-17 11:16:04', '2025-01-04 18:15:44', 0, 0);
INSERT INTO `category` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (20, 'Java', '2024-12-12 14:29:36', '2025-01-04 18:15:44', 0, 2);
INSERT INTO `category` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (21, 'python', '2024-12-12 15:24:02', '2025-01-04 18:15:44', 0, 3);
COMMIT;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`
(
    `id`                bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `content`           varchar(120) NOT NULL DEFAULT '' COMMENT '评论内容',
    `avatar`            varchar(160)          DEFAULT NULL COMMENT '头像',
    `nickname`          varchar(60)  NOT NULL DEFAULT '' COMMENT '昵称',
    `mail`              varchar(60)  NOT NULL DEFAULT '' COMMENT '邮箱',
    `website`           varchar(60)           DEFAULT NULL COMMENT '网站地址',
    `router_url`        varchar(60)  NOT NULL DEFAULT '' COMMENT '评论所属的路由',
    `create_time`       datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`       datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
    `is_deleted`        tinyint      NOT NULL DEFAULT '0' COMMENT '删除标志位：0：未删除 1：已删除',
    `reply_comment_id`  bigint unsigned DEFAULT NULL COMMENT '回复的评论 ID',
    `parent_comment_id` bigint unsigned DEFAULT NULL COMMENT '父评论 ID',
    `reason`            varchar(300)          DEFAULT '' COMMENT '原因描述',
    `status`            tinyint      NOT NULL DEFAULT '1' COMMENT '1: 待审核；2：正常；3：审核未通过;',
    PRIMARY KEY (`id`) USING BTREE,
    KEY                 `idx_router_url` (`router_url`) USING BTREE,
    KEY                 `idx_create_time` (`create_time`) USING BTREE,
    KEY                 `idx_reply_comment_id` (`reply_comment_id`) USING BTREE,
    KEY                 `idx_parent_comment_id` (`parent_comment_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='评论表';

-- ----------------------------
-- Records of comment
-- ----------------------------
BEGIN;
INSERT INTO `comment` (`id`, `content`, `avatar`, `nickname`, `mail`, `website`, `router_url`, `create_time`,
                       `update_time`, `is_deleted`, `reply_comment_id`, `parent_comment_id`, `reason`, `status`)
VALUES (1, '测试哈哈', 'http://23.img', '哈哈', '2324@mail.com', 'http://23.com', 'http://23.com',
        '2025-01-06 09:56:17', '2025-01-06 09:56:33', 0, 1, NULL, '审核不通过，敏感词', 3);
INSERT INTO `comment` (`id`, `content`, `avatar`, `nickname`, `mail`, `website`, `router_url`, `create_time`,
                       `update_time`, `is_deleted`, `reply_comment_id`, `parent_comment_id`, `reason`, `status`)
VALUES (2, '测试评一级回复', 'http://htet.img', 'weha', '2342@mail.com', '', '/article/9', '2025-01-06 16:57:00',
        '2025-01-06 16:57:00', 0, 0, NULL, '', 2);
INSERT INTO `comment` (`id`, `content`, `avatar`, `nickname`, `mail`, `website`, `router_url`, `create_time`,
                       `update_time`, `is_deleted`, `reply_comment_id`, `parent_comment_id`, `reason`, `status`)
VALUES (3, 'alex测试评论', 'http://htet.img', 'alex', '2342@mail.com', '', '/article/9', '2025-01-06 16:58:47',
        '2025-01-06 16:58:47', 0, 6, 2, '', 2);
INSERT INTO `comment` (`id`, `content`, `avatar`, `nickname`, `mail`, `website`, `router_url`, `create_time`,
                       `update_time`, `is_deleted`, `reply_comment_id`, `parent_comment_id`, `reason`, `status`)
VALUES (4, 'ben测试评论', '', 'ben', '2342@mail.com', '', '/article/9', '2025-01-06 16:59:05', '2025-01-06 16:59:05', 0,
        4, 2, '', 2);
INSERT INTO `comment` (`id`, `content`, `avatar`, `nickname`, `mail`, `website`, `router_url`, `create_time`,
                       `update_time`, `is_deleted`, `reply_comment_id`, `parent_comment_id`, `reason`, `status`)
VALUES (5, 'carl测试评论', '23232', 'carl', '2342@mail.com', '', '/article/9', '2025-01-06 16:59:29',
        '2025-01-06 16:59:29', 0, 6, 2, '', 2);
INSERT INTO `comment` (`id`, `content`, `avatar`, `nickname`, `mail`, `website`, `router_url`, `create_time`,
                       `update_time`, `is_deleted`, `reply_comment_id`, `parent_comment_id`, `reason`, `status`)
VALUES (6, 'edde测试评论', '23232', 'edde', '2342@mail.com', '', '/article/9', '2025-01-06 16:59:45',
        '2025-01-06 16:59:45', 0, 1, 2, '', 2);
INSERT INTO `comment` (`id`, `content`, `avatar`, `nickname`, `mail`, `website`, `router_url`, `create_time`,
                       `update_time`, `is_deleted`, `reply_comment_id`, `parent_comment_id`, `reason`, `status`)
VALUES (7, 'fun测试评论', '23232', 'fun', '2342@mail.com', '', '/article/9', '2025-01-06 16:59:52',
        '2025-01-06 16:59:52', 0, 2, 2, '', 2);
INSERT INTO `comment` (`id`, `content`, `avatar`, `nickname`, `mail`, `website`, `router_url`, `create_time`,
                       `update_time`, `is_deleted`, `reply_comment_id`, `parent_comment_id`, `reason`, `status`)
VALUES (8, '测试评论', '23232', 'weew', '2342@mail.com', '', '/article/9', '2025-01-06 17:07:25', '2025-01-06 17:07:25',
        0, 2, 2, '', 1);
INSERT INTO `comment` (`id`, `content`, `avatar`, `nickname`, `mail`, `website`, `router_url`, `create_time`,
                       `update_time`, `is_deleted`, `reply_comment_id`, `parent_comment_id`, `reason`, `status`)
VALUES (9, '测试评论', '23232', 'weew', '2342@mail.com', '', '/article/9', '2025-01-06 17:07:31', '2025-01-06 17:07:31',
        0, NULL, 2, '', 1);
INSERT INTO `comment` (`id`, `content`, `avatar`, `nickname`, `mail`, `website`, `router_url`, `create_time`,
                       `update_time`, `is_deleted`, `reply_comment_id`, `parent_comment_id`, `reason`, `status`)
VALUES (10, '测试评论', '23232', 'weew', '2342@mail.com', '', '/article/9', '2025-01-06 17:07:35',
        '2025-01-06 17:07:35', 0, NULL, NULL, '', 1);
COMMIT;

-- ----------------------------
-- Table structure for statistics_article_pv
-- ----------------------------
DROP TABLE IF EXISTS `statistics_article_pv`;
CREATE TABLE `statistics_article_pv`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `pv_date`     date     NOT NULL COMMENT '被统计的日期',
    `pv_count`    bigint unsigned NOT NULL COMMENT 'pv访问量',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_pv_date` (`pv_date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='统计表 - 文章 PV (访问量)';

-- ----------------------------
-- Records of statistics_article_pv
-- ----------------------------
BEGIN;
INSERT INTO `statistics_article_pv` (`id`, `pv_date`, `pv_count`, `create_time`, `update_time`)
VALUES (1, '2024-03-27', 13, '2024-03-26 23:00:00', '2024-03-26 23:00:00');
INSERT INTO `statistics_article_pv` (`id`, `pv_date`, `pv_count`, `create_time`, `update_time`)
VALUES (2, '2024-03-28', 64, '2024-03-27 23:00:00', '2024-03-27 23:00:00');
INSERT INTO `statistics_article_pv` (`id`, `pv_date`, `pv_count`, `create_time`, `update_time`)
VALUES (3, '2024-04-04', 0, '2024-04-03 23:00:00', '2024-04-03 23:00:00');
INSERT INTO `statistics_article_pv` (`id`, `pv_date`, `pv_count`, `create_time`, `update_time`)
VALUES (4, '2024-04-05', 3, '2024-04-04 23:00:00', '2024-04-04 23:00:00');
INSERT INTO `statistics_article_pv` (`id`, `pv_date`, `pv_count`, `create_time`, `update_time`)
VALUES (5, '2024-04-06', 15, '2024-04-05 23:00:00', '2024-04-05 23:00:00');
INSERT INTO `statistics_article_pv` (`id`, `pv_date`, `pv_count`, `create_time`, `update_time`)
VALUES (6, '2024-04-07', 5, '2024-04-06 23:00:00', '2024-04-06 23:00:00');
INSERT INTO `statistics_article_pv` (`id`, `pv_date`, `pv_count`, `create_time`, `update_time`)
VALUES (8, '2024-04-08', 17, '2024-04-07 23:00:00', '2024-04-07 23:00:00');
INSERT INTO `statistics_article_pv` (`id`, `pv_date`, `pv_count`, `create_time`, `update_time`)
VALUES (9, '2024-04-09', 1, '2024-04-08 23:00:00', '2024-04-08 23:00:00');
INSERT INTO `statistics_article_pv` (`id`, `pv_date`, `pv_count`, `create_time`, `update_time`)
VALUES (10, '2024-04-10', 4, '2024-04-09 23:00:00', '2024-04-09 23:00:00');
INSERT INTO `statistics_article_pv` (`id`, `pv_date`, `pv_count`, `create_time`, `update_time`)
VALUES (11, '2024-04-11', 7, '2024-04-10 23:00:00', '2024-04-10 23:00:00');
INSERT INTO `statistics_article_pv` (`id`, `pv_date`, `pv_count`, `create_time`, `update_time`)
VALUES (12, '2024-04-12', 3, '2024-04-11 23:00:00', '2024-04-11 23:00:00');
INSERT INTO `statistics_article_pv` (`id`, `pv_date`, `pv_count`, `create_time`, `update_time`)
VALUES (13, '2024-04-13', 1, '2024-04-12 23:00:00', '2024-04-12 23:00:00');
INSERT INTO `statistics_article_pv` (`id`, `pv_date`, `pv_count`, `create_time`, `update_time`)
VALUES (14, '2024-04-14', 2, '2024-04-13 23:00:00', '2024-04-13 23:00:00');
INSERT INTO `statistics_article_pv` (`id`, `pv_date`, `pv_count`, `create_time`, `update_time`)
VALUES (15, '2024-04-15', 11, '2024-04-14 23:00:00', '2024-04-14 23:00:00');
INSERT INTO `statistics_article_pv` (`id`, `pv_date`, `pv_count`, `create_time`, `update_time`)
VALUES (16, '2024-04-16', 0, '2024-04-15 23:00:00', '2024-04-15 23:00:00');
INSERT INTO `statistics_article_pv` (`id`, `pv_date`, `pv_count`, `create_time`, `update_time`)
VALUES (17, '2024-04-17', 64, '2024-04-16 23:00:00', '2024-04-16 23:00:00');
INSERT INTO `statistics_article_pv` (`id`, `pv_date`, `pv_count`, `create_time`, `update_time`)
VALUES (18, '2024-04-18', 10, '2024-04-17 23:00:00', '2024-04-17 23:00:00');
INSERT INTO `statistics_article_pv` (`id`, `pv_date`, `pv_count`, `create_time`, `update_time`)
VALUES (19, '2024-04-19', 2, '2024-04-18 23:00:00', '2024-04-18 23:00:00');
INSERT INTO `statistics_article_pv` (`id`, `pv_date`, `pv_count`, `create_time`, `update_time`)
VALUES (20, '2024-04-20', 1, '2024-04-19 23:00:00', '2024-04-19 23:00:00');
INSERT INTO `statistics_article_pv` (`id`, `pv_date`, `pv_count`, `create_time`, `update_time`)
VALUES (21, '2024-04-21', 0, '2024-04-20 23:00:00', '2024-04-20 23:00:00');
INSERT INTO `statistics_article_pv` (`id`, `pv_date`, `pv_count`, `create_time`, `update_time`)
VALUES (22, '2024-04-22', 0, '2024-04-21 23:00:00', '2024-04-21 23:00:00');
INSERT INTO `statistics_article_pv` (`id`, `pv_date`, `pv_count`, `create_time`, `update_time`)
VALUES (23, '2024-04-23', 6, '2024-04-22 23:00:00', '2024-04-22 23:00:00');
INSERT INTO `statistics_article_pv` (`id`, `pv_date`, `pv_count`, `create_time`, `update_time`)
VALUES (24, '2024-04-24', 16, '2024-04-23 23:00:00', '2024-04-23 23:00:00');
INSERT INTO `statistics_article_pv` (`id`, `pv_date`, `pv_count`, `create_time`, `update_time`)
VALUES (25, '2025-01-04', 2, '2025-01-04 11:04:00', '2025-01-04 11:48:41');
COMMIT;

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`
(
    `id`             bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '标签id',
    `name`           varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '标签名称',
    `create_time`    datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
    `is_deleted`     tinyint                                                      NOT NULL DEFAULT '0' COMMENT '0：未删除 1：已删除',
    `articles_total` int                                                                   DEFAULT NULL COMMENT '文章总数',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_name` (`name`) USING BTREE,
    KEY              `idx_create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='文章标签表';

-- ----------------------------
-- Records of tag
-- ----------------------------
BEGIN;
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (1, 'java', '2024-03-21 23:44:27', '2025-01-04 18:15:44', 0, 12);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (2, 'javascript', '2024-03-21 23:44:44', '2025-01-04 18:15:44', 0, 4);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (3, 'Python', '2024-03-21 23:44:49', '2025-01-04 18:15:45', 0, 0);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (9, '工具', '2024-03-22 16:34:10', '2025-01-04 18:15:44', 0, 5);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (10, 'Flutter', '2024-03-22 16:36:30', '2025-01-04 18:15:44', 0, 1);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (11, 'Vue3', '2024-03-22 17:16:24', '2025-01-04 18:15:44', 0, 4);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (12, 'Vite', '2024-03-22 17:16:24', '2025-01-04 18:15:44', 0, 2);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (13, 'Element', '2024-03-22 17:16:24', '2025-01-04 18:15:45', 0, 0);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (14, '生活知识', '2024-03-26 16:02:48', '2025-01-04 18:15:44', 0, 0);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (15, '健康', '2024-03-26 16:02:48', '2025-01-04 18:15:44', 0, 0);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (16, '跑步', '2024-03-26 16:02:48', '2025-01-04 18:15:44', 0, 0);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (17, 'Mac', '2024-03-26 16:02:48', '2025-01-04 18:15:44', 0, 2);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (18, 'Windows', '2024-03-26 16:02:48', '2025-01-04 18:15:44', 0, 0);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (19, 'iPhone', '2024-03-26 16:02:48', '2025-01-04 18:15:44', 0, 0);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (20, '安卓', '2024-03-26 16:02:48', '2025-01-04 18:15:44', 0, 1);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (21, 'UniApp', '2024-03-26 16:02:48', '2025-01-04 18:15:44', 0, 1);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (22, 'Vue2', '2024-03-26 16:03:30', '2025-01-04 18:15:44', 0, 2);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (23, '微信小程序', '2024-03-26 16:03:30', '2025-01-04 18:15:44', 0, 1);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (24, 'SpringBoot', '2024-03-26 16:03:30', '2025-01-04 18:15:44', 0, 2);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (25, 'Redis', '2024-03-26 16:03:30', '2025-01-04 18:15:44', 0, 1);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (26, '心理学', '2024-03-26 16:04:21', '2025-01-04 18:15:44', 0, 0);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (27, '孩子教育', '2024-03-26 16:04:21', '2025-01-04 18:15:44', 0, 0);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (28, '自我提升', '2024-03-26 16:04:21', '2025-01-04 18:15:44', 0, 0);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (29, '经济管理', '2024-03-26 16:04:21', '2025-01-04 18:15:44', 0, 0);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (30, 'IDE', '2024-03-26 17:53:00', '2025-01-04 18:15:44', 0, 2);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (31, 'MySql', '2024-04-03 10:45:31', '2025-01-04 18:15:44', 0, 1);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (32, 'Docker', '2024-04-03 10:45:31', '2025-01-04 18:15:44', 0, 3);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (33, 'Jenkins', '2024-04-03 10:45:31', '2025-01-04 18:15:44', 0, 2);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (34, 'VueUse', '2024-04-17 00:33:03', '2025-01-04 18:15:44', 0, 2);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (35, 'Git', '2024-04-17 19:16:25', '2025-01-04 18:15:44', 0, 1);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (36, 'Npm', '2024-04-17 19:19:48', '2025-01-04 18:15:44', 0, 1);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (37, 'Vant', '2024-04-17 19:19:48', '2025-01-04 18:15:44', 0, 1);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (38, 'ElementUI', '2024-04-17 19:19:48', '2025-01-04 18:15:44', 0, 1);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (39, '字体图标', '2024-04-19 18:46:32', '2025-01-04 18:15:44', 0, 2);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (40, '面试题', '2024-04-23 18:58:17', '2024-04-23 18:58:17', 1, 1);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (44, 'test1', '2024-12-13 10:37:29', '2025-01-04 18:15:44', 0, 0);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (45, 'test2', '2024-12-13 10:37:29', '2025-01-04 18:15:44', 0, 0);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (46, 'test3', '2024-12-13 10:37:29', '2024-12-13 10:37:29', 1, 0);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (49, '123', '2024-12-13 11:18:00', '2025-01-04 18:15:44', 0, 0);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (50, '12334', '2024-12-13 11:18:00', '2025-01-04 18:15:44', 0, 0);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (51, '新标签', '2024-12-30 18:00:36', '2025-01-04 18:15:44', 0, 5);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (52, '新标签2', '2024-12-30 18:00:36', '2025-01-04 18:15:44', 0, 6);
INSERT INTO `tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`, `articles_total`)
VALUES (53, '新标签3', '2025-01-02 15:14:29', '2025-01-04 18:15:44', 0, 1);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `username`    varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
    `password`    varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
    `create_time` datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  tinyint                                                      NOT NULL DEFAULT '0' COMMENT '0：未删除 1：已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`id`, `username`, `password`, `create_time`, `update_time`, `is_deleted`)
VALUES (5, 'ben', '$2a$10$eVkOaNbsBMvhKCAKBDRwWeVYycUL.wj5L2c6gYk23UY/D2qh42mu2', '2024-12-03 17:04:24',
        '2024-12-03 17:04:24', 0);
INSERT INTO `user` (`id`, `username`, `password`, `create_time`, `update_time`, `is_deleted`)
VALUES (6, 'admin', '$2a$10$TWNYR0ztTCSCs2SD4KZ0yu6QcXM0nAdMQrE7xvuZfj5OjYJ81vAO6', '2024-12-05 18:14:00',
        '2024-12-05 18:14:00', 0);
INSERT INTO `user` (`id`, `username`, `password`, `create_time`, `update_time`, `is_deleted`)
VALUES (7, 'test', '$2a$10$CJFHBjdUr4h0TbN9mn5PXOmAQSUz3Kd3oDDVMqIWGyWBXsUbwRoiy', '2024-12-05 23:54:12',
        '2024-12-05 23:54:12', 0);
COMMIT;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `username`    varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
    `role`        varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色',
    `create_time` datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY           `idx_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户角色表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
BEGIN;
INSERT INTO `user_role` (`id`, `username`, `role`, `create_time`, `update_time`)
VALUES (1, 'ben', 'ROLE_ADMIN', '2024-12-03 08:46:55', '2024-12-03 08:46:55');
INSERT INTO `user_role` (`id`, `username`, `role`, `create_time`, `update_time`)
VALUES (2, 'admin', 'ROLE_ADMIN', '2024-12-03 08:46:55', '2024-12-05 18:19:11');
INSERT INTO `user_role` (`id`, `username`, `role`, `create_time`, `update_time`)
VALUES (3, 'test', 'ROLE_TEST', '2024-12-03 08:47:57', '2024-12-03 08:51:42');
INSERT INTO `user_role` (`id`, `username`, `role`, `create_time`, `update_time`)
VALUES (4, 'ben', 'ROLE_TEST', '2024-12-03 08:47:57', '2024-12-03 08:47:57');
INSERT INTO `user_role` (`id`, `username`, `role`, `create_time`, `update_time`)
VALUES (5, 'test2', 'ROLE_TEST', '2024-12-13 08:35:54', '2024-12-13 08:35:54');
COMMIT;

-- ----------------------------
-- Table structure for wiki
-- ----------------------------
DROP TABLE IF EXISTS `wiki`;
CREATE TABLE `wiki`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `title`       varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '标题',
    `cover`       varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '封面',
    `summary`     varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT '' COMMENT '摘要',
    `create_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
    `is_deleted`  tinyint                                                       NOT NULL DEFAULT '0' COMMENT '删除标志位：0：未删除 1：已删除',
    `weight`      int unsigned NOT NULL DEFAULT '0' COMMENT '权重，用于是否置顶（0: 未置顶；>0: 参与置顶，权重值越高越靠前）',
    `is_publish`  tinyint                                                       NOT NULL DEFAULT '1' COMMENT '是否发布：0：未发布 1：已发布',
    PRIMARY KEY (`id`) USING BTREE,
    KEY           `idx_create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='知识库表';

-- ----------------------------
-- Records of wiki
-- ----------------------------
BEGIN;
INSERT INTO `wiki` (`id`, `title`, `cover`, `summary`, `create_time`, `update_time`, `is_deleted`, `weight`,
                    `is_publish`)
VALUES (2, '测试更新2title', 'http://12mds.png', '测试更新2', '2024-04-01 12:21:28', '2025-01-05 16:21:32', 0, 20, 1);
INSERT INTO `wiki` (`id`, `title`, `cover`, `summary`, `create_time`, `update_time`, `is_deleted`, `weight`,
                    `is_publish`)
VALUES (4, '前端知识库', 'http://22332.img', 'HTML / CSS / JavaScript / Vue2 / Vue3 / React / Bootstrap',
        '2024-04-01 16:38:53', '2024-04-01 16:38:53', 0, 0, 0);
INSERT INTO `wiki` (`id`, `title`, `cover`, `summary`, `create_time`, `update_time`, `is_deleted`, `weight`,
                    `is_publish`)
VALUES (6, '微信小程序开发', 'http://22332.img', '原生开发 / UniApp', '2024-04-17 11:44:51', '2024-04-17 11:44:51', 0,
        0, 1);
INSERT INTO `wiki` (`id`, `title`, `cover`, `summary`, `create_time`, `update_time`, `is_deleted`, `weight`,
                    `is_publish`)
VALUES (7, '桌面端开发（Electron）', 'http://22332.img', 'Electron 是一个基于 Chrominum 和 Node.js 的跨平台桌面应用框架',
        '2024-04-17 11:51:38', '2024-04-17 11:51:38', 0, 0, 1);
INSERT INTO `wiki` (`id`, `title`, `cover`, `summary`, `create_time`, `update_time`, `is_deleted`, `weight`,
                    `is_publish`)
VALUES (9, '测试title', 'http://22332.img', '测试哈行a', '2025-01-05 15:58:14', '2025-01-05 15:58:14', 0, 0, 1);
INSERT INTO `wiki` (`id`, `title`, `cover`, `summary`, `create_time`, `update_time`, `is_deleted`, `weight`,
                    `is_publish`)
VALUES (10, '测试title1', 'http://22332.img', '测试哈行a', '2025-01-05 16:00:38', '2025-01-05 16:00:38', 1, 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for wiki_catalog
-- ----------------------------
DROP TABLE IF EXISTS `wiki_catalog`;
CREATE TABLE `wiki_catalog`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `wiki_id`     bigint unsigned NOT NULL COMMENT '知识库id',
    `article_id`  bigint unsigned DEFAULT NULL COMMENT '文章id',
    `title`       text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
    `level`       tinyint  NOT NULL DEFAULT '1' COMMENT '目录层级',
    `parent_id`   bigint unsigned DEFAULT NULL COMMENT '父目录id',
    `sort`        tinyint unsigned NOT NULL DEFAULT '1' COMMENT '排序',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
    `is_deleted`  tinyint  NOT NULL DEFAULT '0' COMMENT '删除标志位：0：未删除 1：已删除',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_article_id` (`article_id`) USING BTREE,
    KEY           `idx_sort` (`sort`) USING BTREE,
    KEY           `idx_wiki_id` (`wiki_id`) USING BTREE,
    KEY           `idx_parent_id` (`parent_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=221 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='知识库目录表';

-- ----------------------------
-- Records of wiki_catalog
-- ----------------------------
BEGIN;
INSERT INTO `wiki_catalog` (`id`, `wiki_id`, `article_id`, `title`, `level`, `parent_id`, `sort`, `create_time`,
                            `update_time`, `is_deleted`)
VALUES (210, 2, NULL, '概述', 1, NULL, 1, '2025-01-05 15:52:17', '2025-01-05 15:52:17', 0);
INSERT INTO `wiki_catalog` (`id`, `wiki_id`, `article_id`, `title`, `level`, `parent_id`, `sort`, `create_time`,
                            `update_time`, `is_deleted`)
VALUES (211, 2, NULL, '基础', 1, NULL, 2, '2025-01-05 15:52:17', '2025-01-05 15:52:17', 0);
INSERT INTO `wiki_catalog` (`id`, `wiki_id`, `article_id`, `title`, `level`, `parent_id`, `sort`, `create_time`,
                            `update_time`, `is_deleted`)
VALUES (213, 10, NULL, '测试wiki标题', 1, NULL, 2, '2025-01-05 15:58:14', '2025-01-05 15:58:14', 0);
INSERT INTO `wiki_catalog` (`id`, `wiki_id`, `article_id`, `title`, `level`, `parent_id`, `sort`, `create_time`,
                            `update_time`, `is_deleted`)
VALUES (214, 10, 7, '基础', 2, 213, 1, '2025-01-05 16:00:38', '2025-01-05 16:00:38', 0);
INSERT INTO `wiki_catalog` (`id`, `wiki_id`, `article_id`, `title`, `level`, `parent_id`, `sort`, `create_time`,
                            `update_time`, `is_deleted`)
VALUES (215, 10, 10, '概述', 2, 213, 2, '2025-01-05 16:00:38', '2025-01-05 16:00:38', 0);
INSERT INTO `wiki_catalog` (`id`, `wiki_id`, `article_id`, `title`, `level`, `parent_id`, `sort`, `create_time`,
                            `update_time`, `is_deleted`)
VALUES (218, 9, NULL, '测试wiki9简介', 1, NULL, 1, '2025-01-05 18:23:01', '2025-01-05 18:23:01', 0);
INSERT INTO `wiki_catalog` (`id`, `wiki_id`, `article_id`, `title`, `level`, `parent_id`, `sort`, `create_time`,
                            `update_time`, `is_deleted`)
VALUES (219, 9, 2, 'Java如何入门', 2, 218, 2, '2025-01-05 18:23:01', '2025-01-05 18:23:01', 0);
INSERT INTO `wiki_catalog` (`id`, `wiki_id`, `article_id`, `title`, `level`, `parent_id`, `sort`, `create_time`,
                            `update_time`, `is_deleted`)
VALUES (220, 9, 9, '哈哈', 2, 218, 1, '2025-01-05 20:18:05', '2025-01-05 20:18:05', 0);
COMMIT;

SET
FOREIGN_KEY_CHECKS = 1;
