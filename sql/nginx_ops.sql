-- --------------------------------------------------------
-- 主机:                           sh-cynosdbmysql-grp-nd1blxru.sql.tencentcdb.com
-- 服务器版本:                        5.7.18-cynos-log - 20200531
-- 服务器操作系统:                      Linux
-- HeidiSQL 版本:                  12.0.0.6468
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;


-- 导出 nginx_ops 的数据库结构
DROP DATABASE IF EXISTS `nginx_ops`;
CREATE DATABASE IF NOT EXISTS `nginx_ops` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `nginx_ops`;

-- 导出  表 nginx_ops.conf_info 结构
DROP TABLE IF EXISTS `conf_info`;
CREATE TABLE IF NOT EXISTS `conf_info`
(
    `id`          char(20)    NOT NULL COMMENT '唯一ID',
    `tenant_id`   char(20)    NOT NULL COMMENT '租户ID',
    `status`      varchar(10) NOT NULL COMMENT '状态',
    `content`     longtext    NOT NULL COMMENT '配置文件',
    `create_by`   varchar(50) DEFAULT 'SYS' COMMENT '创建人',
    `create_time` datetime    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   varchar(50) DEFAULT 'SYS' COMMENT '修改人',
    `update_time` datetime    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_tenant_id` (`tenant_id`),
    KEY `idx_status` (`status`),
    KEY `idx_update_time` (`update_time`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='nginx配置表';

-- 数据导出被取消选择。

-- 导出  表 nginx_ops.conf_info_certificate 结构
DROP TABLE IF EXISTS `conf_info_certificate`;
CREATE TABLE IF NOT EXISTS `conf_info_certificate`
(
    `id`          char(20)     NOT NULL COMMENT '唯一ID',
    `tenant_id`   char(20)     NOT NULL COMMENT '租户ID',
    `domain`      varchar(255) NOT NULL COMMENT '域名',
    `pem`         text COMMENT 'ssl证书的pem文件路径',
    `key`         text COMMENT 'ssl证书的key文件路径',
    `create_by`   varchar(50) DEFAULT NULL COMMENT '创建人',
    `create_time` datetime    DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(50) DEFAULT NULL COMMENT '修改人',
    `update_time` datetime    DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_domain` (`domain`),
    KEY `idx_tenant_id` (`tenant_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='证书表';

-- 数据导出被取消选择。

-- 导出  表 nginx_ops.conf_info_comm 结构
DROP TABLE IF EXISTS `conf_info_comm`;
CREATE TABLE IF NOT EXISTS `conf_info_comm`
(
    `id`          char(20)     NOT NULL COMMENT '唯一ID',
    `tenant_id`   char(20)     NOT NULL COMMENT '租户ID',
    `other_id`    char(20)    DEFAULT NULL COMMENT '关联表ID',
    `type`        varchar(25)  NOT NULL COMMENT '配置类型',
    `name`        varchar(255) NOT NULL COMMENT '字段标题',
    `value`       text         NOT NULL COMMENT '字段内容',
    `order`       int(2)       NOT NULL COMMENT '顺序',
    `remark`      text COMMENT '备注',
    `create_by`   varchar(50) DEFAULT 'SYS' COMMENT '创建人',
    `create_time` datetime    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   varchar(50) DEFAULT 'SYS' COMMENT '修改人',
    `update_time` datetime    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `delete_flag` bit(1)      DEFAULT b'0' COMMENT '逻辑删除',
    PRIMARY KEY (`id`),
    KEY `idx_tenant_id` (`tenant_id`),
    KEY `idx_name` (`name`),
    KEY `idx_delete_flag` (`delete_flag`),
    KEY `idx_other_id` (`other_id`),
    KEY `idx_type` (`type`),
    KEY `idx_order` (`order`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='nginx通用配置表';

-- 数据导出被取消选择。

-- 导出  表 nginx_ops.conf_info_his 结构
DROP TABLE IF EXISTS `conf_info_his`;
CREATE TABLE IF NOT EXISTS `conf_info_his`
(
    `id`          char(20) NOT NULL COMMENT '唯一ID',
    `tenant_id`   char(20) NOT NULL COMMENT '租户ID',
    `version`     int(5)   NOT NULL DEFAULT '0' COMMENT '版本',
    `content`     longtext NOT NULL COMMENT '配置文件',
    `create_by`   varchar(50)       DEFAULT 'SYS' COMMENT '创建人',
    `create_time` datetime          DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   varchar(50)       DEFAULT 'SYS' COMMENT '修改人',
    `update_time` datetime          DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_tenant_id` (`tenant_id`),
    KEY `idx_version` (`version`),
    KEY `idx_update_time` (`update_time`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='nginx配置历史表';

-- 数据导出被取消选择。

-- 导出  表 nginx_ops.conf_info_his_item 结构
DROP TABLE IF EXISTS `conf_info_his_item`;
CREATE TABLE IF NOT EXISTS `conf_info_his_item`
(
    `id`          char(20)     NOT NULL COMMENT '唯一ID',
    `tenant_id`   char(20)     NOT NULL COMMENT '租户ID',
    `conf_id`     char(20)     NOT NULL COMMENT '文件ID',
    `name`        varchar(255) NOT NULL COMMENT '文件名称',
    `content`     longtext     NOT NULL COMMENT '配置文件',
    `create_by`   varchar(50) DEFAULT 'SYS' COMMENT '创建人',
    `create_time` datetime    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   varchar(50) DEFAULT 'SYS' COMMENT '修改人',
    `update_time` datetime    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_tenant_id` (`tenant_id`) USING BTREE,
    KEY `idx_conf_id` (`conf_id`),
    KEY `idx_name` (`name`),
    KEY `idx_update_time` (`update_time`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='nginx明细配置记录表';

-- 数据导出被取消选择。

-- 导出  表 nginx_ops.conf_info_item 结构
DROP TABLE IF EXISTS `conf_info_item`;
CREATE TABLE IF NOT EXISTS `conf_info_item`
(
    `id`          char(20)     NOT NULL COMMENT '唯一ID',
    `tenant_id`   char(20)     NOT NULL COMMENT '租户ID',
    `conf_id`     char(20)     NOT NULL COMMENT '文件ID',
    `status`      varchar(10)  NOT NULL COMMENT '状态',
    `name`        varchar(255) NOT NULL COMMENT '文件名称',
    `content`     longtext     NOT NULL COMMENT '配置文件',
    `create_by`   varchar(50) DEFAULT 'SYS' COMMENT '创建人',
    `create_time` datetime    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   varchar(50) DEFAULT 'SYS' COMMENT '修改人',
    `update_time` datetime    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_tenant_id` (`tenant_id`) USING BTREE,
    KEY `idx_conf_id` (`conf_id`),
    KEY `idx_status` (`status`),
    KEY `idx_name` (`name`),
    KEY `idx_update_time` (`update_time`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='nginx明细配置表';

-- 数据导出被取消选择。

-- 导出  表 nginx_ops.conf_info_server 结构
DROP TABLE IF EXISTS `conf_info_server`;
CREATE TABLE IF NOT EXISTS `conf_info_server`
(
    `id`             char(20)    NOT NULL COMMENT '唯一ID',
    `tenant_id`      char(20)    NOT NULL COMMENT '租户ID',
    `server_name`    varchar(50) NOT NULL COMMENT '监听域名',
    `ip`             char(18)    NOT NULL COMMENT '监听IP',
    `port`           int(5)      NOT NULL COMMENT '监听端口',
    `type`           varchar(25) NOT NULL COMMENT '服务类型',
    `certificate_id` char(20)             DEFAULT NULL COMMENT '证书表ID',
    `is_ssl`         bit(1)               DEFAULT b'0' COMMENT '是否开启证书',
    `is_http2`       bit(1)               DEFAULT b'1' COMMENT '是否开启http2',
    `http_to_https`  bit(1)               DEFAULT b'1' COMMENT '是否开启http跳转https',
    `http_ip`        char(18)             DEFAULT NULL COMMENT 'http跳转https的ip',
    `http_port`      int(5)               DEFAULT NULL COMMENT 'http跳转https的端口',
    `is_enable`      bit(1)      NOT NULL DEFAULT b'1' COMMENT '是否启用',
    `order`          int(2)      NOT NULL DEFAULT '0' COMMENT '顺序',
    `remark`         text COMMENT '备注',
    `create_by`      varchar(50)          DEFAULT 'SYS' COMMENT '创建人',
    `create_time`    datetime             DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`      varchar(50)          DEFAULT 'SYS' COMMENT '修改人',
    `update_time`    datetime             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `delete_flag`    bit(1)               DEFAULT b'0' COMMENT '逻辑删除',
    PRIMARY KEY (`id`),
    KEY `idx_server_name` (`server_name`),
    KEY `idx_tenact_id` (`tenant_id`) USING BTREE,
    KEY `is_enable` (`is_enable`),
    KEY `idx_order` (`order`) USING BTREE,
    KEY `idx_delete_flag` (`delete_flag`) USING BTREE,
    KEY `idx_type` (`type`) USING BTREE,
    KEY `idx_certificate_id` (`certificate_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='nginx服务配置表';

-- 数据导出被取消选择。

-- 导出  表 nginx_ops.conf_info_server_item 结构
DROP TABLE IF EXISTS `conf_info_server_item`;
CREATE TABLE IF NOT EXISTS `conf_info_server_item`
(
    `id`           char(20)     NOT NULL COMMENT '唯一ID',
    `tenant_id`    char(20)     NOT NULL COMMENT '租户ID',
    `server_id`    char(20)     NOT NULL COMMENT '服务头表ID',
    `order`        int(2)      DEFAULT '0' COMMENT '顺序',
    `path`         varchar(255) NOT NULL COMMENT '监控路径',
    `type`         varchar(50)  NOT NULL COMMENT '监控类型',
    `value`        varchar(255) NOT NULL COMMENT '监控地址',
    `is_header`    bit(1)      DEFAULT b'1' COMMENT '是否携带HOST参数',
    `is_websocket` bit(1)      DEFAULT b'1' COMMENT '是否开启websocket',
    `rootType`     varchar(10) DEFAULT NULL COMMENT '路由模式',
    `rootPath`     text COMMENT '路由路径',
    `rootIndex`    varchar(50) DEFAULT NULL COMMENT '指向页',
    `remark`       text COMMENT '备注',
    `create_by`    varchar(50) DEFAULT 'SYS' COMMENT '创建人',
    `create_time`  datetime    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`    varchar(50) DEFAULT 'SYS' COMMENT '修改人',
    `update_time`  datetime    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `delete_flag`  bit(1)      DEFAULT b'0' COMMENT '逻辑删除',
    PRIMARY KEY (`id`),
    KEY `idx_tenant_id` (`tenant_id`) USING BTREE,
    KEY `idx_server_id` (`server_id`) USING BTREE,
    KEY `idx_order` (`order`) USING BTREE,
    KEY `idx_type` (`type`) USING BTREE,
    KEY `idx_delete_flag` (`delete_flag`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='nginx服务明细配置表';

-- 数据导出被取消选择。

-- 导出  表 nginx_ops.conf_info_template 结构
DROP TABLE IF EXISTS `conf_info_template`;
CREATE TABLE IF NOT EXISTS `conf_info_template`
(
    `id`          char(20)    NOT NULL COMMENT '唯一ID',
    `tenant_id`   char(20)    NOT NULL COMMENT '租户ID',
    `type`        varchar(25) NOT NULL COMMENT '类型',
    `name`        varchar(25) NOT NULL COMMENT '名称',
    `remark`      text        NOT NULL COMMENT '备注',
    `order`       int(2)      NOT NULL DEFAULT '0' COMMENT '排序',
    `create_by`   varchar(50)          DEFAULT 'SYS' COMMENT '创建人',
    `create_time` datetime             DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   varchar(50)          DEFAULT 'SYS' COMMENT '修改人',
    `update_time` datetime             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `delete_flag` bit(1)               DEFAULT b'0' COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_tenant_id` (`tenant_id`) USING BTREE,
    KEY `idx_type` (`type`),
    KEY `idx_name` (`name`),
    KEY `idx_order` (`order`),
    KEY `idx_delete_flag` (`delete_flag`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='nginx配置模板表';

-- 数据导出被取消选择。

-- 导出  表 nginx_ops.conf_info_template_item 结构
DROP TABLE IF EXISTS `conf_info_template_item`;
CREATE TABLE IF NOT EXISTS `conf_info_template_item`
(
    `id`          char(20)    NOT NULL COMMENT '唯一ID',
    `tenant_id`   char(20)    NOT NULL COMMENT '租户ID',
    `template_id` char(20)    NOT NULL COMMENT '模板头表ID',
    `type`        varchar(25) NOT NULL COMMENT '类型',
    `name`        varchar(25) NOT NULL COMMENT '名称',
    `value`       text        NOT NULL COMMENT '内容',
    `remark`      text        NOT NULL COMMENT '备注',
    `order`       int(2)      NOT NULL DEFAULT '0' COMMENT '排序',
    `create_by`   varchar(50)          DEFAULT 'SYS' COMMENT '创建人',
    `create_time` datetime             DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   varchar(50)          DEFAULT 'SYS' COMMENT '修改人',
    `update_time` datetime             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `delete_flag` bit(1)               DEFAULT b'0' COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_tenant_id` (`tenant_id`) USING BTREE,
    KEY `idx_template_id` (`template_id`),
    KEY `idx_type` (`type`),
    KEY `idx_name` (`name`),
    KEY `idx_order` (`order`),
    KEY `idx_delete_flag` (`delete_flag`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='nginx配置模板表';

-- 数据导出被取消选择。

-- 导出  表 nginx_ops.conf_info_upstream 结构
DROP TABLE IF EXISTS `conf_info_upstream`;
CREATE TABLE IF NOT EXISTS `conf_info_upstream`
(
    `id`          char(20)    NOT NULL COMMENT '唯一ID',
    `tenant_id`   char(20)    NOT NULL COMMENT '租户ID',
    `name`        varchar(50) NOT NULL COMMENT '名称',
    `tactics`     varchar(25) NOT NULL COMMENT '负载策略',
    `proxy_type`  varchar(25) NOT NULL COMMENT '转发类型(HTTP or UDP)',
    `order`       int(2)      DEFAULT '0' COMMENT '顺序',
    `remark`      text COMMENT '备注',
    `create_by`   varchar(50) DEFAULT 'SYS' COMMENT '创建人',
    `create_time` datetime    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   varchar(50) DEFAULT 'SYS' COMMENT '修改人',
    `update_time` datetime    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `delete_flag` bit(1)      DEFAULT b'0' COMMENT '逻辑删除',
    PRIMARY KEY (`id`),
    KEY `idx_tenant_id` (`tenant_id`),
    KEY `idx_name` (`name`),
    KEY `idx_tactics` (`tactics`),
    KEY `idx_proxy_type` (`proxy_type`),
    KEY `idx_order` (`order`),
    KEY `idx_delete_flag` (`delete_flag`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='nginx 负载均衡头表配置表';

-- 数据导出被取消选择。

-- 导出  表 nginx_ops.conf_info_upstream_item 结构
DROP TABLE IF EXISTS `conf_info_upstream_item`;
CREATE TABLE IF NOT EXISTS `conf_info_upstream_item`
(
    `id`           char(20) NOT NULL COMMENT '唯一ID',
    `tenant_id`    char(20) NOT NULL COMMENT '租户ID',
    `upstream_id`  char(20) NOT NULL COMMENT '负载均衡头表ID',
    `ip`           char(16) NOT NULL COMMENT 'ip',
    `port`         int(5)   NOT NULL COMMENT '端口',
    `weight`       int(2)      DEFAULT NULL COMMENT '权重',
    `fail_timeout` int(5)      DEFAULT NULL COMMENT '失败等待时间(s)',
    `max_fails`    int(5)      DEFAULT NULL COMMENT '最大失败次数',
    `max_conns`    int(5)      DEFAULT NULL COMMENT '最大连接数',
    `status`       varchar(50) DEFAULT NULL COMMENT '状态策略',
    `order`        int(2)      DEFAULT '0' COMMENT '顺序',
    `remark`       text COMMENT '备注',
    `create_by`    varchar(50) DEFAULT 'SYS' COMMENT '创建人',
    `create_time`  datetime    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`    varchar(50) DEFAULT 'SYS' COMMENT '修改人',
    `update_time`  datetime    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `delete_flag`  bit(1)      DEFAULT b'0' COMMENT '逻辑删除',
    PRIMARY KEY (`id`),
    KEY `idx_tenant_id` (`tenant_id`),
    KEY `idx_upstream_id` (`upstream_id`),
    KEY `idx_status` (`status`),
    KEY `idx_order` (`order`),
    KEY `idx_delete_flag` (`delete_flag`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='nginx 负载均衡明细配置表';

-- 数据导出被取消选择。

-- 导出  表 nginx_ops.sys_dict 结构
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE IF NOT EXISTS `sys_dict`
(
    `id`          bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `type`        varchar(50) NOT NULL COMMENT '字典类型',
    `code`        varchar(50) NOT NULL COMMENT '字典编码',
    `value`       text        NOT NULL COMMENT '字典内容',
    `order`       int(2)      DEFAULT '0' COMMENT '顺序',
    `remark`      text COMMENT '备注',
    `create_by`   varchar(50) DEFAULT 'SYS' COMMENT '创建人',
    `create_time` datetime    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   varchar(50) DEFAULT 'SYS' COMMENT '修改人',
    `update_time` datetime    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `delete_flag` bit(1)      DEFAULT b'0' COMMENT '逻辑删除',
    PRIMARY KEY (`id`),
    KEY `idx_type` (`type`),
    KEY `idx_code` (`code`),
    KEY `idx_delete_flag` (`delete_flag`),
    KEY `idx_order` (`order`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 31
  DEFAULT CHARSET = utf8mb4 COMMENT ='系统设置-字典表';

-- 数据导出被取消选择。

-- 导出  表 nginx_ops.sys_file 结构
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE IF NOT EXISTS `sys_file`
(
    `id`          varchar(255) NOT NULL COMMENT '标识',
    `file_name`   varchar(255) DEFAULT NULL COMMENT '文件名称',
    `file_desc`   varchar(255) DEFAULT NULL COMMENT '文件描述',
    `file_path`   text COMMENT '文件路径',
    `file_type`   varchar(255) DEFAULT NULL COMMENT '文件类型',
    `file_size`   bigint(20)   DEFAULT NULL COMMENT '文件大小',
    `create_by`   varchar(255) DEFAULT NULL COMMENT '创建人',
    `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(255) DEFAULT NULL COMMENT '修改人',
    `update_time` datetime     DEFAULT NULL COMMENT '修改时间',
    `remark`      varchar(255) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;

-- 数据导出被取消选择。

-- 导出  表 nginx_ops.sys_operation_log 结构
DROP TABLE IF EXISTS `sys_operation_log`;
CREATE TABLE IF NOT EXISTS `sys_operation_log`
(
    `id`             char(20)     NOT NULL COMMENT '分布式ID',
    `title`          varchar(255) NOT NULL COMMENT '模块标题',
    `business_type`  varchar(10)  NOT NULL COMMENT '业务类型',
    `method`         varchar(255) NOT NULL COMMENT '方法名称',
    `request_method` varchar(10)  NOT NULL COMMENT '请求方式',
    `ip`             char(16)     NOT NULL COMMENT '请求IP',
    `url`            varchar(255) NOT NULL COMMENT '请求URL',
    `browser`        varchar(50)  NOT NULL COMMENT '浏览器类型',
    `os`             varchar(50)  NOT NULL COMMENT '操作系统',
    `param`          json         NOT NULL COMMENT '请求参数',
    `result`         json        DEFAULT NULL COMMENT '返回参数',
    `status`         bit(1)       NOT NULL COMMENT '操作状态',
    `error_msg`      text COMMENT '错误消息',
    `create_by`      varchar(50) DEFAULT NULL COMMENT '操作人',
    `create_time`    datetime    DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
    `update_by`      varchar(50) DEFAULT NULL COMMENT '修改人',
    `update_time`    datetime    DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_create_time` (`create_time`) USING BTREE,
    KEY `idx_title` (`title`) USING BTREE,
    KEY `idx_business_type` (`business_type`) USING BTREE,
    KEY `idx_method` (`method`) USING BTREE,
    KEY `idx_status` (`status`) USING BTREE,
    KEY `idx_create_by` (`create_by`) USING BTREE,
    KEY `idx_request_method` (`request_method`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='操作日志表';

-- 数据导出被取消选择。

-- 导出  表 nginx_ops.sys_role 结构
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE IF NOT EXISTS `sys_role`
(
    `id`          char(20)     NOT NULL COMMENT '唯一ID',
    `role_code`   varchar(50)  NOT NULL COMMENT '角色编码',
    `role_name`   varchar(255) NOT NULL COMMENT '角色名称',
    `order`       int(5)      DEFAULT '0' COMMENT '排序',
    `is_enable`   bit(1)      DEFAULT b'1' COMMENT '是否开启',
    `remark`      text COMMENT '备注',
    `create_by`   varchar(50) DEFAULT NULL COMMENT '创建人',
    `create_time` datetime    DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(50) DEFAULT NULL COMMENT '修改人',
    `update_time` datetime    DEFAULT NULL COMMENT '修改时间',
    `delete_flag` bit(1)      DEFAULT b'0' COMMENT '逻辑删除',
    PRIMARY KEY (`id`),
    KEY `idx_role_code` (`role_code`) USING BTREE,
    KEY `idx_role_name` (`role_name`) USING BTREE,
    KEY `idx_is_enable` (`is_enable`) USING BTREE,
    KEY `idx_update_time` (`update_time`) USING BTREE,
    KEY `idx_delete_flag` (`delete_flag`) USING BTREE,
    KEY `idx_order` (`order`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='角色表';

-- 数据导出被取消选择。

-- 导出  表 nginx_ops.sys_setting 结构
DROP TABLE IF EXISTS `sys_setting`;
CREATE TABLE IF NOT EXISTS `sys_setting`
(
    `id`               char(20)     NOT NULL COMMENT '唯一ID',
    `group_id`         char(20)              DEFAULT NULL COMMENT '分组ID',
    `name`             varchar(255)          DEFAULT NULL COMMENT '名称',
    `url`              text COMMENT '地址',
    `is_localhost`     bit(1)       NOT NULL DEFAULT b'1' COMMENT '是否为本地服务',
    `username`         varchar(255) NOT NULL COMMENT '账号',
    `password`         varchar(255) NOT NULL COMMENT '密码',
    `order`            int(2)                DEFAULT '0' COMMENT '顺序',
    `nginx_exe`        text         NOT NULL COMMENT 'nginx执行文件',
    `nginx_path`       text COMMENT 'nginx路径',
    `nginx_start_cmd`  text         NOT NULL COMMENT 'nginx启动命令',
    `nginx_reload_cmd` text         NOT NULL COMMENT 'nginx重启命令',
    `nginx_stop_cmd`   text         NOT NULL COMMENT 'nginx停止命令',
    `nginx_conf_path`  text         NOT NULL COMMENT 'nginx配置文件夹路径',
    `remark`           text COMMENT '备注',
    `create_by`        varchar(50)  NOT NULL COMMENT '创建人',
    `create_time`      datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`        varchar(50)  NOT NULL COMMENT '修改人',
    `update_time`      datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `delete_flag`      bit(1)       NOT NULL DEFAULT b'0' COMMENT '逻辑删除',
    PRIMARY KEY (`id`),
    KEY `idx_group_id` (`group_id`),
    KEY `idx_name` (`name`),
    KEY `idx_order` (`order`),
    KEY `idx_delete_flag` (`delete_flag`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='系统设置';

-- 数据导出被取消选择。

-- 导出  表 nginx_ops.sys_user 结构
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE IF NOT EXISTS `sys_user`
(
    `id`          char(20)    NOT NULL COMMENT '唯一ID',
    `login_name`  varchar(50) NOT NULL COMMENT '登陆账号',
    `password`    varchar(50) NOT NULL COMMENT '密码',
    `nick_name`   varchar(255) DEFAULT NULL COMMENT '昵称',
    `email`       varchar(255) DEFAULT NULL COMMENT '邮箱',
    `avatar`      varchar(255) DEFAULT NULL COMMENT '头像',
    `is_enable`   bit(1)       DEFAULT b'1' COMMENT '是否启用',
    `login_ip`    char(16)     DEFAULT NULL COMMENT '最后登录IP',
    `login_date`  datetime     DEFAULT NULL COMMENT '最后登录时间',
    `create_by`   varchar(50)  DEFAULT 'SYS' COMMENT '创建人',
    `create_time` datetime     DEFAULT CURRENT_TIMESTAMP,
    `update_by`   varchar(50)  DEFAULT 'SYS' COMMENT '修改人',
    `update_time` datetime     DEFAULT CURRENT_TIMESTAMP,
    `delete_flag` bit(1)       DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户表';

-- 数据导出被取消选择。

-- 导出  表 nginx_ops.sys_user_role 结构
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE IF NOT EXISTS `sys_user_role`
(
    `id`          char(20) NOT NULL COMMENT '唯一ID',
    `user_id`     char(20) NOT NULL COMMENT '用户ID',
    `role_id`     char(20) NOT NULL COMMENT '角色ID',
    `remark`      text COMMENT '备注',
    `create_by`   varchar(50) DEFAULT NULL COMMENT '创建人',
    `create_time` datetime    DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(50) DEFAULT NULL COMMENT '修改人',
    `update_time` datetime    DEFAULT NULL COMMENT '修改时间',
    `delete_flag` bit(1)      DEFAULT NULL COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_user_id` (`user_id`) USING BTREE,
    KEY `idx_role_id` (`role_id`) USING BTREE,
    KEY `idx_delete_flag` (`delete_flag`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户角色关联表';

-- 数据导出被取消选择。

-- 导出  表 nginx_ops.sys_user_setting 结构
DROP TABLE IF EXISTS `sys_user_setting`;
CREATE TABLE IF NOT EXISTS `sys_user_setting`
(
    `id`          char(20) NOT NULL COMMENT '唯一ID',
    `user_id`     char(20) NOT NULL COMMENT '用户ID',
    `setting_id`  char(20) NOT NULL COMMENT 'nginx管理ID',
    `remark`      text COMMENT '备注',
    `create_by`   varchar(50) DEFAULT NULL COMMENT '创建人',
    `create_time` datetime    DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(50) DEFAULT NULL COMMENT '修改人',
    `update_time` datetime    DEFAULT NULL COMMENT '修改时间',
    `delete_flag` bit(1)      DEFAULT NULL COMMENT '逻辑删除',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_setting_id` (`setting_id`),
    KEY `idx_delete_flag` (`delete_flag`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户和设置的中间表';

-- 数据导出被取消选择。

/*!40103 SET TIME_ZONE = IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE = IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS = IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES = IFNULL(@OLD_SQL_NOTES, 1) */;
