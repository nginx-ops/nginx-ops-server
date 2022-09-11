/*
 Navicat Premium Data Transfer

 Source Server         : 腾讯云
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : 49.232.174.49:3306
 Source Schema         : nginx_ops

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 10/09/2022 23:19:39
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for conf_info
-- ----------------------------
DROP TABLE IF EXISTS `conf_info`;
CREATE TABLE `conf_info`
(
    `id`          char(20) CHARACTER SET utf8 COLLATE utf8_general_ci    NOT NULL COMMENT '唯一ID',
    `tenant_id`   char(20) CHARACTER SET utf8 COLLATE utf8_general_ci    NOT NULL COMMENT '租户ID',
    `status`      varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态',
    `content`     longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '配置文件',
    `create_by`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'SYS' COMMENT '创建人',
    `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'SYS' COMMENT '修改人',
    `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX         `idx_tenant_id`(`tenant_id`) USING BTREE,
    INDEX         `idx_status`(`status`) USING BTREE,
    INDEX         `idx_update_time`(`update_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'nginx配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of conf_info
-- ----------------------------

-- ----------------------------
-- Table structure for conf_info_certificate
-- ----------------------------
DROP TABLE IF EXISTS `conf_info_certificate`;
CREATE TABLE `conf_info_certificate`
(
    `id`          char(20) CHARACTER SET utf8 COLLATE utf8_general_ci     NOT NULL COMMENT '唯一ID',
    `tenant_id`   char(20) CHARACTER SET utf8 COLLATE utf8_general_ci     NOT NULL COMMENT '租户ID',
    `domain`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '域名',
    `pem`         text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'ssl证书的pem文件路径',
    `key`         text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'ssl证书的key文件路径',
    `create_by`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
    `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
    `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX         `idx_domain`(`domain`) USING BTREE,
    INDEX         `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '证书表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of conf_info_certificate
-- ----------------------------

-- ----------------------------
-- Table structure for conf_info_comm
-- ----------------------------
DROP TABLE IF EXISTS `conf_info_comm`;
CREATE TABLE `conf_info_comm`
(
    `id`          char(20) CHARACTER SET utf8 COLLATE utf8_general_ci     NOT NULL COMMENT '唯一ID',
    `tenant_id`   char(20) CHARACTER SET utf8 COLLATE utf8_general_ci     NOT NULL COMMENT '租户ID',
    `other_id`    char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联表ID',
    `type`        varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '配置类型',
    `name`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字段标题',
    `value`       text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字段内容',
    `order`       int                                                     NOT NULL COMMENT '顺序',
    `remark`      text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
    `create_by`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'SYS' COMMENT '创建人',
    `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'SYS' COMMENT '修改人',
    `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `delete_flag` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX         `idx_tenant_id`(`tenant_id`) USING BTREE,
    INDEX         `idx_name`(`name`) USING BTREE,
    INDEX         `idx_delete_flag`(`delete_flag`) USING BTREE,
    INDEX         `idx_other_id`(`other_id`) USING BTREE,
    INDEX         `idx_type`(`type`) USING BTREE,
    INDEX         `idx_order`(`order`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'nginx通用配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of conf_info_comm
-- ----------------------------

-- ----------------------------
-- Table structure for conf_info_his
-- ----------------------------
DROP TABLE IF EXISTS `conf_info_his`;
CREATE TABLE `conf_info_his`
(
    `id`          char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '唯一ID',
    `tenant_id`   char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '租户ID',
    `version`     int                                                 NOT NULL DEFAULT 0 COMMENT '版本',
    `content`     longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '配置文件',
    `create_by`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'SYS' COMMENT '创建人',
    `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'SYS' COMMENT '修改人',
    `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX         `idx_tenant_id`(`tenant_id`) USING BTREE,
    INDEX         `idx_version`(`version`) USING BTREE,
    INDEX         `idx_update_time`(`update_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'nginx配置历史表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of conf_info_his
-- ----------------------------

-- ----------------------------
-- Table structure for conf_info_his_item
-- ----------------------------
DROP TABLE IF EXISTS `conf_info_his_item`;
CREATE TABLE `conf_info_his_item`
(
    `id`          char(20) CHARACTER SET utf8 COLLATE utf8_general_ci     NOT NULL COMMENT '唯一ID',
    `tenant_id`   char(20) CHARACTER SET utf8 COLLATE utf8_general_ci     NOT NULL COMMENT '租户ID',
    `conf_id`     char(20) CHARACTER SET utf8 COLLATE utf8_general_ci     NOT NULL COMMENT '文件ID',
    `name`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件名称',
    `content`     longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '配置文件',
    `create_by`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'SYS' COMMENT '创建人',
    `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'SYS' COMMENT '修改人',
    `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX         `idx_tenant_id`(`tenant_id`) USING BTREE,
    INDEX         `idx_conf_id`(`conf_id`) USING BTREE,
    INDEX         `idx_name`(`name`) USING BTREE,
    INDEX         `idx_update_time`(`update_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'nginx明细配置记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of conf_info_his_item
-- ----------------------------

-- ----------------------------
-- Table structure for conf_info_item
-- ----------------------------
DROP TABLE IF EXISTS `conf_info_item`;
CREATE TABLE `conf_info_item`
(
    `id`          char(20) CHARACTER SET utf8 COLLATE utf8_general_ci     NOT NULL COMMENT '唯一ID',
    `tenant_id`   char(20) CHARACTER SET utf8 COLLATE utf8_general_ci     NOT NULL COMMENT '租户ID',
    `conf_id`     char(20) CHARACTER SET utf8 COLLATE utf8_general_ci     NOT NULL COMMENT '文件ID',
    `status`      varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '状态',
    `name`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件名称',
    `content`     longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '配置文件',
    `create_by`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'SYS' COMMENT '创建人',
    `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'SYS' COMMENT '修改人',
    `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX         `idx_tenant_id`(`tenant_id`) USING BTREE,
    INDEX         `idx_conf_id`(`conf_id`) USING BTREE,
    INDEX         `idx_status`(`status`) USING BTREE,
    INDEX         `idx_name`(`name`) USING BTREE,
    INDEX         `idx_update_time`(`update_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'nginx明细配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of conf_info_item
-- ----------------------------

-- ----------------------------
-- Table structure for conf_info_server
-- ----------------------------
DROP TABLE IF EXISTS `conf_info_server`;
CREATE TABLE `conf_info_server`
(
    `id`             char(20) CHARACTER SET utf8 COLLATE utf8_general_ci    NOT NULL COMMENT '唯一ID',
    `tenant_id`      char(20) CHARACTER SET utf8 COLLATE utf8_general_ci    NOT NULL COMMENT '租户ID',
    `server_name`    varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '监听域名',
    `ip`             char(18) CHARACTER SET utf8 COLLATE utf8_general_ci    NOT NULL COMMENT '监听IP',
    `port`           int                                                    NOT NULL COMMENT '监听端口',
    `type`           varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '服务类型',
    `certificate_id` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证书表ID',
    `is_ssl`         bit(1) NULL DEFAULT b'0' COMMENT '是否开启证书',
    `is_http2`       bit(1) NULL DEFAULT b'1' COMMENT '是否开启http2',
    `http_to_https`  bit(1) NULL DEFAULT b'1' COMMENT '是否开启http跳转https',
    `http_ip`        char(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'http跳转https的ip',
    `http_port`      int NULL DEFAULT NULL COMMENT 'http跳转https的端口',
    `is_enable`      bit(1)                                                 NOT NULL DEFAULT b'1' COMMENT '是否启用',
    `order`          int                                                    NOT NULL DEFAULT 0 COMMENT '顺序',
    `remark`         text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
    `create_by`      varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'SYS' COMMENT '创建人',
    `create_time`    datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`      varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'SYS' COMMENT '修改人',
    `update_time`    datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `delete_flag`    bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX            `idx_server_name`(`server_name`) USING BTREE,
    INDEX            `idx_tenact_id`(`tenant_id`) USING BTREE,
    INDEX            `is_enable`(`is_enable`) USING BTREE,
    INDEX            `idx_order`(`order`) USING BTREE,
    INDEX            `idx_delete_flag`(`delete_flag`) USING BTREE,
    INDEX            `idx_type`(`type`) USING BTREE,
    INDEX            `idx_certificate_id`(`certificate_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'nginx服务配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of conf_info_server
-- ----------------------------

-- ----------------------------
-- Table structure for conf_info_server_item
-- ----------------------------
DROP TABLE IF EXISTS `conf_info_server_item`;
CREATE TABLE `conf_info_server_item`
(
    `id`           char(20) CHARACTER SET utf8 COLLATE utf8_general_ci     NOT NULL COMMENT '唯一ID',
    `tenant_id`    char(20) CHARACTER SET utf8 COLLATE utf8_general_ci     NOT NULL COMMENT '租户ID',
    `server_id`    char(20) CHARACTER SET utf8 COLLATE utf8_general_ci     NOT NULL COMMENT '服务头表ID',
    `order`        int NULL DEFAULT 0 COMMENT '顺序',
    `path`         varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '监控路径',
    `type`         varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '监控类型',
    `value`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '监控地址',
    `is_header`    bit(1) NULL DEFAULT b'1' COMMENT '是否携带HOST参数',
    `is_websocket` bit(1) NULL DEFAULT b'1' COMMENT '是否开启websocket',
    `rootType`     varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路由模式',
    `rootPath`     text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '路由路径',
    `rootIndex`    varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '指向页',
    `remark`       text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
    `create_by`    varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'SYS' COMMENT '创建人',
    `create_time`  datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`    varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'SYS' COMMENT '修改人',
    `update_time`  datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `delete_flag`  bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX          `idx_tenant_id`(`tenant_id`) USING BTREE,
    INDEX          `idx_server_id`(`server_id`) USING BTREE,
    INDEX          `idx_order`(`order`) USING BTREE,
    INDEX          `idx_type`(`type`) USING BTREE,
    INDEX          `idx_delete_flag`(`delete_flag`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'nginx服务明细配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of conf_info_server_item
-- ----------------------------

-- ----------------------------
-- Table structure for conf_info_template
-- ----------------------------
DROP TABLE IF EXISTS `conf_info_template`;
CREATE TABLE `conf_info_template`
(
    `id`          char(20) CHARACTER SET utf8 COLLATE utf8_general_ci    NOT NULL COMMENT '唯一ID',
    `tenant_id`   char(20) CHARACTER SET utf8 COLLATE utf8_general_ci    NOT NULL COMMENT '租户ID',
    `type`        varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型',
    `name`        varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
    `remark`      text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '备注',
    `order`       int                                                    NOT NULL DEFAULT 0 COMMENT '排序',
    `create_by`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'SYS' COMMENT '创建人',
    `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'SYS' COMMENT '修改人',
    `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `delete_flag` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX         `idx_tenant_id`(`tenant_id`) USING BTREE,
    INDEX         `idx_type`(`type`) USING BTREE,
    INDEX         `idx_name`(`name`) USING BTREE,
    INDEX         `idx_order`(`order`) USING BTREE,
    INDEX         `idx_delete_flag`(`delete_flag`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'nginx配置模板表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of conf_info_template
-- ----------------------------

-- ----------------------------
-- Table structure for conf_info_template_item
-- ----------------------------
DROP TABLE IF EXISTS `conf_info_template_item`;
CREATE TABLE `conf_info_template_item`
(
    `id`          char(20) CHARACTER SET utf8 COLLATE utf8_general_ci    NOT NULL COMMENT '唯一ID',
    `tenant_id`   char(20) CHARACTER SET utf8 COLLATE utf8_general_ci    NOT NULL COMMENT '租户ID',
    `template_id` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci    NOT NULL COMMENT '模板头表ID',
    `type`        varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型',
    `name`        varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
    `value`       text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
    `remark`      text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '备注',
    `order`       int                                                    NOT NULL DEFAULT 0 COMMENT '排序',
    `create_by`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'SYS' COMMENT '创建人',
    `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'SYS' COMMENT '修改人',
    `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `delete_flag` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX         `idx_tenant_id`(`tenant_id`) USING BTREE,
    INDEX         `idx_template_id`(`template_id`) USING BTREE,
    INDEX         `idx_type`(`type`) USING BTREE,
    INDEX         `idx_name`(`name`) USING BTREE,
    INDEX         `idx_order`(`order`) USING BTREE,
    INDEX         `idx_delete_flag`(`delete_flag`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'nginx配置模板表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of conf_info_template_item
-- ----------------------------

-- ----------------------------
-- Table structure for conf_info_upstream
-- ----------------------------
DROP TABLE IF EXISTS `conf_info_upstream`;
CREATE TABLE `conf_info_upstream`
(
    `id`          char(20) CHARACTER SET utf8 COLLATE utf8_general_ci    NOT NULL COMMENT '唯一ID',
    `tenant_id`   char(20) CHARACTER SET utf8 COLLATE utf8_general_ci    NOT NULL COMMENT '租户ID',
    `name`        varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
    `tactics`     varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '负载策略',
    `proxy_type`  varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '转发类型(HTTP or UDP)',
    `order`       int NULL DEFAULT 0 COMMENT '顺序',
    `remark`      text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
    `create_by`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'SYS' COMMENT '创建人',
    `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'SYS' COMMENT '修改人',
    `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `delete_flag` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX         `idx_tenant_id`(`tenant_id`) USING BTREE,
    INDEX         `idx_name`(`name`) USING BTREE,
    INDEX         `idx_tactics`(`tactics`) USING BTREE,
    INDEX         `idx_proxy_type`(`proxy_type`) USING BTREE,
    INDEX         `idx_order`(`order`) USING BTREE,
    INDEX         `idx_delete_flag`(`delete_flag`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'nginx 负载均衡头表配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of conf_info_upstream
-- ----------------------------

-- ----------------------------
-- Table structure for conf_info_upstream_item
-- ----------------------------
DROP TABLE IF EXISTS `conf_info_upstream_item`;
CREATE TABLE `conf_info_upstream_item`
(
    `id`           char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '唯一ID',
    `tenant_id`    char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '租户ID',
    `upstream_id`  char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '负载均衡头表ID',
    `ip`           char(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ip',
    `port`         int                                                 NOT NULL COMMENT '端口',
    `weight`       int NULL DEFAULT NULL COMMENT '权重',
    `fail_timeout` int NULL DEFAULT NULL COMMENT '失败等待时间(s)',
    `max_fails`    int NULL DEFAULT NULL COMMENT '最大失败次数',
    `max_conns`    int NULL DEFAULT NULL COMMENT '最大连接数',
    `status`       varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态策略',
    `order`        int NULL DEFAULT 0 COMMENT '顺序',
    `remark`       text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
    `create_by`    varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'SYS' COMMENT '创建人',
    `create_time`  datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`    varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'SYS' COMMENT '修改人',
    `update_time`  datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `delete_flag`  bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX          `idx_tenant_id`(`tenant_id`) USING BTREE,
    INDEX          `idx_upstream_id`(`upstream_id`) USING BTREE,
    INDEX          `idx_status`(`status`) USING BTREE,
    INDEX          `idx_order`(`order`) USING BTREE,
    INDEX          `idx_delete_flag`(`delete_flag`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'nginx 负载均衡明细配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of conf_info_upstream_item
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`
(
    `id`          bigint                                                 NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `type`        varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典类型',
    `code`        varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典编码',
    `value`       text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典内容',
    `order`       int NULL DEFAULT 0 COMMENT '顺序',
    `remark`      text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
    `create_by`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'SYS' COMMENT '创建人',
    `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'SYS' COMMENT '修改人',
    `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `delete_flag` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX         `idx_type`(`type`) USING BTREE,
    INDEX         `idx_code`(`code`) USING BTREE,
    INDEX         `idx_delete_flag`(`delete_flag`) USING BTREE,
    INDEX         `idx_order`(`order`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统设置-字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict`
VALUES (1, 'nginxHttpDefaultConf', 'keepalive_timeout', '75', 0, '连接超时时间', 'SYS', '2022-08-16 23:26:41', 'SYS',
        '2022-08-16 23:26:47', b'0');
INSERT INTO `sys_dict`
VALUES (2, 'nginxHttpDefaultConf', 'gzip', 'on', 0, '是否开启压缩传输', 'SYS', '2022-08-16 23:26:44', 'SYS',
        '2022-08-17 08:11:37', b'0');
INSERT INTO `sys_dict`
VALUES (3, 'nginxHttpDefaultConf', 'gzip_min_length', '4k', 0, '最小压缩文件', 'SYS', '2022-08-16 23:26:45', 'SYS',
        '2022-08-17 08:10:35', b'0');
INSERT INTO `sys_dict`
VALUES (4, 'nginxHttpDefaultConf', 'gzip_comp_level', '4', 0, '压缩率', 'SYS', '2022-08-16 23:26:45', 'SYS',
        '2022-08-16 23:27:15', b'0');
INSERT INTO `sys_dict`
VALUES (5, 'gzip_types', 'text/html', 'html', 0, '压缩类型', 'SYS', '2022-08-17 07:51:49', 'SYS', '2022-08-17 07:51:49',
        b'0');
INSERT INTO `sys_dict`
VALUES (6, 'gzip_types', 'text/plain', 'plain', 0, '压缩类型', 'SYS', '2022-08-17 07:51:49', 'SYS', '2022-08-17 07:51:49',
        b'0');
INSERT INTO `sys_dict`
VALUES (7, 'gzip_types', 'text/css', 'css', 0, '压缩类型', 'SYS', '2022-08-17 07:51:49', 'SYS', '2022-08-17 07:51:49',
        b'0');
INSERT INTO `sys_dict`
VALUES (8, 'gzip_types', 'application/x-javascript', 'x-javascript', 0, '压缩类型', 'SYS', '2022-08-17 07:51:49', 'SYS',
        '2022-08-17 07:51:49', b'0');
INSERT INTO `sys_dict`
VALUES (9, 'gzip_types', 'text/javascript', 'javascript', 0, '压缩类型', 'SYS', '2022-08-17 07:51:49', 'SYS',
        '2022-08-17 07:51:49', b'0');
INSERT INTO `sys_dict`
VALUES (10, 'gzip_types', 'application/xml', 'xml', 0, '压缩类型', 'SYS', '2022-08-17 07:51:49', 'SYS',
        '2022-08-17 07:51:49', b'0');
INSERT INTO `sys_dict`
VALUES (11, 'gzip_types', 'application/json', 'json', 0, '压缩类型', 'SYS', '2022-08-17 07:51:49', 'SYS',
        '2022-08-17 07:51:49', b'0');
INSERT INTO `sys_dict`
VALUES (12, 'nginxBasisDefaultConf', 'worker_processes', 'auto', 0, '工作进程', 'SYS', '2022-08-17 08:02:09', 'SYS',
        '2022-08-17 08:02:09', b'0');
INSERT INTO `sys_dict`
VALUES (13, 'nginxBasisDefaultConf', 'events', '{\naccept_mutex on;\nmulti_accept on;\nuse epoll;\n}', 0, '全局块', 'SYS',
        '2022-08-17 08:02:09', 'SYS', '2022-08-17 08:02:09', b'0');
INSERT INTO `sys_dict`
VALUES (14, 'nginxHttpDefaultConf', 'server_tokens', 'off', 0, '是否展示NGINX版本号', 'SYS', '2022-08-17 08:10:35', 'SYS',
        '2022-08-17 08:10:35', b'0');
INSERT INTO `sys_dict`
VALUES (15, 'nginxHttpDefaultConf', 'sendfile', 'on', 0,
        'sendfile = off 时，应用程序读取磁盘中的文件以字节流的形式从磁盘中加载文件，然后再将文件以字节流的形式复制到内核中。内核在把文件推送到NC。\nsendfile = on 时，应用程序直接向内核发送指令，让内核去读文件。读完文件内核直接推送给NC。只有一次复制操作，实现异步网络IO形式。因此，性能会有很大的提升。',
        'SYS', '2022-08-17 08:10:35', 'SYS', '2022-08-17 08:11:37', b'0');
INSERT INTO `sys_dict`
VALUES (16, 'nginxHttpDefaultConf', 'charset', 'UTF-8', 0, '默认字符编码', 'SYS', '2022-08-17 08:10:35', 'SYS',
        '2022-08-17 08:10:35', b'0');
INSERT INTO `sys_dict`
VALUES (17, 'nginxHttpDefaultConf', 'default_type', 'application/octet-stream', 0, '默认返回类型', 'SYS',
        '2022-08-17 08:10:35', 'SYS', '2022-08-17 08:10:35', b'0');
INSERT INTO `sys_dict`
VALUES (18, 'nginxHttpDefaultConf', 'client_max_body_size', '1024m', 0, '最大上传限制', 'SYS', '2022-08-17 08:10:35', 'SYS',
        '2022-08-17 08:10:35', b'0');
INSERT INTO `sys_dict`
VALUES (19, 'nginxHttpDefaultConf', 'client_header_buffer_size', '32k', 0, '客户端请求头buffer大小', 'SYS',
        '2022-08-17 08:10:35', 'SYS', '2022-08-17 08:10:35', b'0');
INSERT INTO `sys_dict`
VALUES (20, 'nginxHttpDefaultConf', 'client_body_buffer_size', '8m', 0, '请求主体缓冲区', 'SYS', '2022-08-17 08:10:35', 'SYS',
        '2022-08-17 08:10:35', b'0');
INSERT INTO `sys_dict`
VALUES (21, 'nginxHttpDefaultConf', 'server_names_hash_bucket_size', '512', 0, '服务器名字的哈希表大小', 'SYS',
        '2022-08-17 08:10:35', 'SYS', '2022-08-17 08:10:35', b'0');
INSERT INTO `sys_dict`
VALUES (22, 'nginxHttpDefaultConf', 'proxy_headers_hash_max_size', '51200', 0, '头部哈希表的最大值', 'SYS',
        '2022-08-17 08:10:35', 'SYS', '2022-08-17 08:10:35', b'0');
INSERT INTO `sys_dict`
VALUES (23, 'nginxHttpDefaultConf', 'proxy_headers_hash_bucket_size', '6400', 0, '头部哈希表大小', 'SYS',
        '2022-08-17 08:10:35', 'SYS', '2022-08-17 08:10:35', b'0');
INSERT INTO `sys_dict`
VALUES (24, 'nginxHttpDefaultConf', 'include', 'mime.types', 0, '包含类型', 'SYS', '2022-08-17 08:13:05', 'SYS',
        '2022-08-17 08:14:37', b'0');
INSERT INTO `sys_dict`
VALUES (25, 'nginxHttpDefaultConf', 'log_format', 'json \'{\"@timestamp\":\"$time_iso8601\",\'\n                 \'\"host\":\"$server_addr\",\'\n                 \'\"clientIp\":\"$remote_addr\",\'\n                 \'\"size\":$body_bytes_sent,\'\n                 \'\"responseTime\":$request_time,\'\n                 \'\"upstreamTime\":\"$upstream_response_time\",\'\n                 \'\"upstreamHost\":\"$upstream_addr\",\'\n                 \'\"http_host\":\"$host\",\'\n                 \'\"url\":\"$uri\",\'\n                 \'\"referer\":\"$http_referer\",\'\n                 \'\"agent\":\"$http_user_agent\",\'\n                 \'\"status\":\"$status\"}\'', 0, '日志json格式', 'SYS', '2022-08-17 08:18:08', 'SYS', '2022-08-17 08:18:08', b'0');
INSERT INTO `sys_dict` VALUES (26, 'nginxTemplateType', 'server_http', '反向代理(server)(http)', 0, '模板配置类型', 'SYS', '2022-08-17 12:45:09', 'SYS', '2022-08-17 12:45:09', b'0');
INSERT INTO `sys_dict`
VALUES (27, 'nginxTemplateType', 'server_tcp', '反向代理(server)(tcp)', 0, '模板配置类型', 'SYS', '2022-08-17 12:45:09', 'SYS',
        '2022-08-17 12:45:09', b'0');
INSERT INTO `sys_dict`
VALUES (28, 'nginxTemplateType', 'server_udp', '反向代理(server)(udp)', 0, '模板配置类型', 'SYS', '2022-08-17 12:45:09', 'SYS',
        '2022-08-17 12:45:09', b'0');
INSERT INTO `sys_dict`
VALUES (29, 'nginxTemplateType', 'location', '代理目标(location)', 0, '模板配置类型', 'SYS', '2022-08-17 12:45:10', 'SYS',
        '2022-08-17 12:45:10', b'0');
INSERT INTO `sys_dict`
VALUES (30, 'nginxTemplateType', 'upstream', '负载均衡(upstream)', 0, '模板配置类型', 'SYS', '2022-08-17 12:45:10', 'SYS',
        '2022-08-17 12:45:10', b'0');

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file`
(
    `id`          varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标识',
    `file_name`   varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名称',
    `salt`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '唯一盐值',
    `file_desc`   varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件描述',
    `file_path`   text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '文件路径',
    `file_type`   varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件类型',
    `file_size`   bigint NULL DEFAULT NULL COMMENT '文件大小',
    `create_by`   varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
    `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
    `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
    `remark`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file`
VALUES ('1568619326909972482', '作息时间.jpg', 'n89iv', NULL, 'd://img', 'image/jpeg', 240622, NULL, NULL, NULL, NULL,
        NULL);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`
(
    `id`          char(20) CHARACTER SET utf8 COLLATE utf8_general_ci    NOT NULL COMMENT '菜单ID',
    `menu_name`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
    `parent_id`   char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父菜单ID',
    `order`       int NULL DEFAULT 0 COMMENT '显示顺序',
    `path`        varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路由地址',
    `component`   varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件路径',
    `query`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路由参数',
    `is_frame`    bit(1) NULL DEFAULT b'0' COMMENT '是否为外链（0是 1否）',
    `is_cache`    bit(1) NULL DEFAULT b'0' COMMENT '是否缓存（0缓存 1不缓存）',
    `menu_type`   char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单类型（M目录 C菜单 F按钮）',
    `is_enable`   bit(1) NULL DEFAULT b'1' COMMENT '菜单状态（0正常 1停用）',
    `perms`       varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限标识',
    `icon`        varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
    `create_by`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'SYS' COMMENT '创建者',
    `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'SYS' COMMENT '更新者',
    `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_flag` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除',
    `remark`      text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX         `idx_parent_id`(`parent_id`) USING BTREE,
    INDEX         `idx_order`(`order`) USING BTREE,
    INDEX         `idx_is_enable`(`is_enable`) USING BTREE,
    INDEX         `idx_menu_name`(`menu_name`) USING BTREE,
    INDEX         `idx_delete_flag`(`delete_flag`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation_log`;
CREATE TABLE `sys_operation_log`
(
    `id`             char(20) CHARACTER SET utf8 COLLATE utf8_general_ci     NOT NULL COMMENT '分布式ID',
    `title`          varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模块标题',
    `business_type`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '业务类型',
    `method`         varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '方法名称',
    `request_method` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '请求方式',
    `ip`             char(16) CHARACTER SET utf8 COLLATE utf8_general_ci     NOT NULL COMMENT '请求IP',
    `url`            varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '请求URL',
    `browser`        varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '浏览器类型',
    `os`             varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '操作系统',
    `param`          json                                                    NOT NULL COMMENT '请求参数',
    `result`         json NULL COMMENT '返回参数',
    `status`         bit(1)                                                  NOT NULL COMMENT '操作状态',
    `error_msg`      text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '错误消息',
    `create_by`      varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
    `create_time`    datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
    `update_by`      varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
    `update_time`    datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX            `idx_create_time`(`create_time`) USING BTREE,
    INDEX            `idx_title`(`title`) USING BTREE,
    INDEX            `idx_business_type`(`business_type`) USING BTREE,
    INDEX            `idx_method`(`method`) USING BTREE,
    INDEX            `idx_status`(`status`) USING BTREE,
    INDEX            `idx_create_by`(`create_by`) USING BTREE,
    INDEX            `idx_request_method`(`request_method`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_operation_log
-- ----------------------------
INSERT INTO `sys_operation_log`
VALUES ('1568398148400320513', '登录', 'login', 'io.github.nginx.ops.server.admin.controller.AdminController.login()',
        'POST', '127.0.0.1', 'http://localhost:9988/login', 'Chrome 103.0.0.0', 'Windows 10.0', '{
    \"dto\": {\"id\": \"\"}}', '{
    \"code\": \"200\", \"time\": 1662770232410, \"count\": 0, \"message\": \"登录成功!\"}', b'1', NULL, NULL, NULL, NULL,
        NULL);
INSERT INTO `sys_operation_log`
VALUES ('1568548078687457281', '上传文件', 'upload',
        'io.github.nginx.ops.server.system.controller.SysFileController.upload()', 'POST', '0:0:0:0:0:0:0:1',
        'http://localhost:9988/sys/file/upload', 'Chrome 103.0.0.0', 'Windows 10.0', '{}', '{
    \"code\": \"200\", \"data\": {\"id\": \"1568547965197979649\", \"fileName\": \"作息时间.jpg\", \"filePath\": \"d:\", \"fileSize\": 240622, \"fileType\": \"image/jpeg\"}, \"time\": 1662805952094, \"count\": 1, \"message\": \"上传成功!\"}',
        b'1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation_log`
VALUES ('1568593675592671233', '上传文件', 'upload',
        'io.github.nginx.ops.server.system.controller.SysFileController.upload()', 'POST', '0:0:0:0:0:0:0:1',
        'http://localhost:9988/sys/file/upload', 'Chrome 105.0.0.0', 'Windows 10.0', '{
    \"dto\": {\"path\": \"F:\\\\\", \"fileType\": \"1\"}}', '{
    \"code\": \"200\", \"data\": {\"id\": \"1568593661172654081\", \"fileName\": \"sha1sum.txt\", \"filePath\": \"F:\\\\\", \"fileSize\": 410, \"fileType\": \"text/plain\"}, \"time\": 1662816846739, \"count\": 1, \"message\": \"上传成功!\"}',
        b'1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation_log`
VALUES ('1568596737663787010', '上传文件', 'upload',
        'io.github.nginx.ops.server.system.controller.SysFileController.upload()', 'POST', '0:0:0:0:0:0:0:1',
        'http://localhost:9988/sys/file/upload', 'Chrome 103.0.0.0', 'Windows 10.0', '{
    \"dto\": {\"path\": \"d:\", \"fileType\": \"pdf\"}}', '{
    \"code\": \"200\", \"data\": {\"id\": \"1568596735851847681\", \"fileName\": \"test.pdf\", \"filePath\": \"d:\", \"fileSize\": 774697, \"fileType\": \"application/pdf\"}, \"time\": 1662817579889, \"count\": 1, \"message\": \"上传成功!\"}',
        b'1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation_log`
VALUES ('1568604425009336322', '上传文件', 'upload',
        'io.github.nginx.ops.server.system.controller.SysFileController.upload()', 'POST', '0:0:0:0:0:0:0:1',
        'http://localhost:9988/sys/file/upload', 'Chrome 103.0.0.0', 'Windows 10.0', '{
    \"dto\": {\"path\": \"d://img\", \"fileType\": \"txt\"}}', '{
    \"code\": \"200\", \"data\": {\"id\": \"1568604423222562817\", \"fileName\": \"阿里云key.txt\", \"filePath\": \"d://img\", \"fileSize\": 148, \"fileType\": \"text/plain\"}, \"time\": 1662819412702, \"count\": 1, \"message\": \"上传成功!\"}',
        b'1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation_log`
VALUES ('1568612787479912450', '上传文件', 'upload',
        'io.github.nginx.ops.server.system.controller.SysFileController.upload()', 'POST', '0:0:0:0:0:0:0:1',
        'http://localhost:9988/sys/file/upload', 'Chrome 105.0.0.0', 'Windows 10.0', '{
    \"dto\": {\"path\": \"F:\\\\\", \"fileType\": \"1\"}}', '{
    \"code\": \"200\", \"data\": {\"id\": \"1568612743053844481\", \"fileName\": \"sha1sum.txt\", \"filePath\": \"F:\\\\\", \"fileSize\": 410, \"fileType\": \"text/plain\"}, \"time\": 1662821396095, \"count\": 1, \"message\": \"上传成功!\"}',
        b'1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation_log`
VALUES ('1568619328885489665', '上传文件', 'upload',
        'io.github.nginx.ops.server.system.controller.SysFileController.upload()', 'POST', '0:0:0:0:0:0:0:1',
        'http://localhost:9988/sys/file/upload', 'Chrome 103.0.0.0', 'Windows 10.0', '{
    \"dto\": {\"path\": \"d://img\", \"fileType\": \"jpg\"}}', '{
    \"code\": \"200\", \"data\": {\"id\": \"1568619326909972482\", \"salt\": \"n89iv\", \"fileName\": \"作息时间.jpg\", \"filePath\": \"d://img\", \"fileSize\": 240622, \"fileType\": \"image/jpeg\"}, \"time\": 1662822966050, \"count\": 1, \"message\": \"上传成功!\"}',
        b'1', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`          char(20) CHARACTER SET utf8 COLLATE utf8_general_ci     NOT NULL COMMENT '唯一ID',
    `role_code`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '角色编码',
    `role_name`   varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
    `order`       int NULL DEFAULT 0 COMMENT '排序',
    `is_enable`   bit(1) NULL DEFAULT b'1' COMMENT '是否开启',
    `remark`      text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
    `create_by`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
    `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
    `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
    `delete_flag` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX         `idx_role_code`(`role_code`) USING BTREE,
    INDEX         `idx_role_name`(`role_name`) USING BTREE,
    INDEX         `idx_is_enable`(`is_enable`) USING BTREE,
    INDEX         `idx_update_time`(`update_time`) USING BTREE,
    INDEX         `idx_delete_flag`(`delete_flag`) USING BTREE,
    INDEX         `idx_order`(`order`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`
(
    `id`          char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '唯一ID',
    `role_id`     char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色ID',
    `menu_id`     char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单ID',
    `create_by`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
    `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
    `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
    `delete_flag` bit(1) NULL DEFAULT NULL COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX         `idx_menu_id`(`menu_id`) USING BTREE,
    INDEX         `idx_role_id`(`role_id`) USING BTREE,
    INDEX         `idx_delete_flag`(`delete_flag`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_setting
-- ----------------------------
DROP TABLE IF EXISTS `sys_setting`;
CREATE TABLE `sys_setting`
(
    `id`               char(20) CHARACTER SET utf8 COLLATE utf8_general_ci     NOT NULL COMMENT '唯一ID',
    `group_id`         char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分组ID',
    `name`             varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
    `url`              text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '地址',
    `is_localhost`     bit(1)                                                  NOT NULL DEFAULT b'1' COMMENT '是否为本地服务',
    `username`         varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
    `password`         varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
    `order`            int NULL DEFAULT 0 COMMENT '顺序',
    `nginx_exe`        text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'nginx执行文件',
    `nginx_path`       text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'nginx路径',
    `nginx_start_cmd`  text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'nginx启动命令',
    `nginx_reload_cmd` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'nginx重启命令',
    `nginx_stop_cmd`   text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'nginx停止命令',
    `nginx_conf_path`  text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'nginx配置文件夹路径',
    `remark`           text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
    `create_by`        varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '创建人',
    `create_time`      datetime                                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`        varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '修改人',
    `update_time`      datetime                                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `delete_flag`      bit(1)                                                  NOT NULL DEFAULT b'0' COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX              `idx_group_id`(`group_id`) USING BTREE,
    INDEX              `idx_name`(`name`) USING BTREE,
    INDEX              `idx_order`(`order`) USING BTREE,
    INDEX              `idx_delete_flag`(`delete_flag`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统设置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_setting
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`          char(20) CHARACTER SET utf8 COLLATE utf8_general_ci    NOT NULL COMMENT '唯一ID',
    `login_name`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登陆账号',
    `password`    varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
    `nick_name`   varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
    `email`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
    `avatar`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
    `is_enable`   bit(1) NULL DEFAULT b'1' COMMENT '是否启用',
    `login_ip`    char(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后登录IP',
    `login_date`  datetime NULL DEFAULT NULL COMMENT '最后登录时间',
    `create_by`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'SYS' COMMENT '创建人',
    `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
    `update_by`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'SYS' COMMENT '修改人',
    `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
    `delete_flag` bit(1) NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `id`          char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '唯一ID',
    `user_id`     char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
    `role_id`     char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色ID',
    `create_by`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
    `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
    `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
    `delete_flag` bit(1) NULL DEFAULT NULL COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX         `idx_user_id`(`user_id`) USING BTREE,
    INDEX         `idx_role_id`(`role_id`) USING BTREE,
    INDEX         `idx_delete_flag`(`delete_flag`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_setting
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_setting`;
CREATE TABLE `sys_user_setting`
(
    `id`          char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '唯一ID',
    `user_id`     char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
    `setting_id`  char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'nginx管理ID',
    `remark`      text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
    `create_by`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
    `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
    `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
    `delete_flag` bit(1) NULL DEFAULT NULL COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX         `idx_user_id`(`user_id`) USING BTREE,
    INDEX         `idx_setting_id`(`setting_id`) USING BTREE,
    INDEX         `idx_delete_flag`(`delete_flag`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户和设置的中间表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_setting
-- ----------------------------

SET
FOREIGN_KEY_CHECKS = 1;
