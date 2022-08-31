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

-- 正在导出表  nginx_ops.conf_info 的数据：~0 rows (大约)

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

-- 正在导出表  nginx_ops.conf_info_certificate 的数据：~0 rows (大约)

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

-- 正在导出表  nginx_ops.conf_info_comm 的数据：~0 rows (大约)

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

-- 正在导出表  nginx_ops.conf_info_his 的数据：~0 rows (大约)

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

-- 正在导出表  nginx_ops.conf_info_his_item 的数据：~0 rows (大约)

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

-- 正在导出表  nginx_ops.conf_info_item 的数据：~0 rows (大约)

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

-- 正在导出表  nginx_ops.conf_info_server 的数据：~0 rows (大约)

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

-- 正在导出表  nginx_ops.conf_info_server_item 的数据：~0 rows (大约)

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

-- 正在导出表  nginx_ops.conf_info_template 的数据：~0 rows (大约)

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

-- 正在导出表  nginx_ops.conf_info_template_item 的数据：~0 rows (大约)

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

-- 正在导出表  nginx_ops.conf_info_upstream 的数据：~0 rows (大约)

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

-- 正在导出表  nginx_ops.conf_info_upstream_item 的数据：~0 rows (大约)

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

-- 正在导出表  nginx_ops.sys_dict 的数据：~30 rows (大约)
INSERT INTO `sys_dict` (`id`, `type`, `code`, `value`, `order`, `remark`, `create_by`, `create_time`, `update_by`,
                        `update_time`, `delete_flag`)
VALUES (1, 'nginxHttpDefaultConf', 'keepalive_timeout', '75', 0, '连接超时时间', 'SYS', '2022-08-16 23:26:41', 'SYS',
        '2022-08-16 23:26:47', b'0'),
       (2, 'nginxHttpDefaultConf', 'gzip', 'on', 0, '是否开启压缩传输', 'SYS', '2022-08-16 23:26:44', 'SYS',
        '2022-08-17 08:11:37', b'0'),
       (3, 'nginxHttpDefaultConf', 'gzip_min_length', '4k', 0, '最小压缩文件', 'SYS', '2022-08-16 23:26:45', 'SYS',
        '2022-08-17 08:10:35', b'0'),
       (4, 'nginxHttpDefaultConf', 'gzip_comp_level', '4', 0, '压缩率', 'SYS', '2022-08-16 23:26:45', 'SYS',
        '2022-08-16 23:27:15', b'0'),
       (5, 'gzip_types', 'text/html', 'html', 0, '压缩类型', 'SYS', '2022-08-17 07:51:49', 'SYS', '2022-08-17 07:51:49',
        b'0'),
       (6, 'gzip_types', 'text/plain', 'plain', 0, '压缩类型', 'SYS', '2022-08-17 07:51:49', 'SYS', '2022-08-17 07:51:49',
        b'0'),
       (7, 'gzip_types', 'text/css', 'css', 0, '压缩类型', 'SYS', '2022-08-17 07:51:49', 'SYS', '2022-08-17 07:51:49',
        b'0'),
       (8, 'gzip_types', 'application/x-javascript', 'x-javascript', 0, '压缩类型', 'SYS', '2022-08-17 07:51:49', 'SYS',
        '2022-08-17 07:51:49', b'0'),
       (9, 'gzip_types', 'text/javascript', 'javascript', 0, '压缩类型', 'SYS', '2022-08-17 07:51:49', 'SYS',
        '2022-08-17 07:51:49', b'0'),
       (10, 'gzip_types', 'application/xml', 'xml', 0, '压缩类型', 'SYS', '2022-08-17 07:51:49', 'SYS',
        '2022-08-17 07:51:49', b'0'),
       (11, 'gzip_types', 'application/json', 'json', 0, '压缩类型', 'SYS', '2022-08-17 07:51:49', 'SYS',
        '2022-08-17 07:51:49', b'0'),
       (12, 'nginxBasisDefaultConf', 'worker_processes', 'auto', 0, '工作进程', 'SYS', '2022-08-17 08:02:09', 'SYS',
        '2022-08-17 08:02:09', b'0'),
       (13, 'nginxBasisDefaultConf', 'events', '{\naccept_mutex on;\nmulti_accept on;\nuse epoll;\n}', 0, '全局块', 'SYS',
        '2022-08-17 08:02:09', 'SYS', '2022-08-17 08:02:09', b'0'),
       (14, 'nginxHttpDefaultConf', 'server_tokens', 'off', 0, '是否展示NGINX版本号', 'SYS', '2022-08-17 08:10:35', 'SYS',
        '2022-08-17 08:10:35', b'0'),
       (15, 'nginxHttpDefaultConf', 'sendfile', 'on', 0,
        'sendfile = off 时，应用程序读取磁盘中的文件以字节流的形式从磁盘中加载文件，然后再将文件以字节流的形式复制到内核中。内核在把文件推送到NC。\nsendfile = on 时，应用程序直接向内核发送指令，让内核去读文件。读完文件内核直接推送给NC。只有一次复制操作，实现异步网络IO形式。因此，性能会有很大的提升。',
        'SYS', '2022-08-17 08:10:35', 'SYS', '2022-08-17 08:11:37', b'0'),
       (16, 'nginxHttpDefaultConf', 'charset', 'UTF-8', 0, '默认字符编码', 'SYS', '2022-08-17 08:10:35', 'SYS',
        '2022-08-17 08:10:35', b'0'),
       (17, 'nginxHttpDefaultConf', 'default_type', 'application/octet-stream', 0, '默认返回类型', 'SYS',
        '2022-08-17 08:10:35', 'SYS', '2022-08-17 08:10:35', b'0'),
       (18, 'nginxHttpDefaultConf', 'client_max_body_size', '1024m', 0, '最大上传限制', 'SYS', '2022-08-17 08:10:35', 'SYS',
        '2022-08-17 08:10:35', b'0'),
       (19, 'nginxHttpDefaultConf', 'client_header_buffer_size', '32k', 0, '客户端请求头buffer大小', 'SYS',
        '2022-08-17 08:10:35', 'SYS', '2022-08-17 08:10:35', b'0'),
       (20, 'nginxHttpDefaultConf', 'client_body_buffer_size', '8m', 0, '请求主体缓冲区', 'SYS', '2022-08-17 08:10:35', 'SYS',
        '2022-08-17 08:10:35', b'0'),
       (21, 'nginxHttpDefaultConf', 'server_names_hash_bucket_size', '512', 0, '服务器名字的哈希表大小', 'SYS',
        '2022-08-17 08:10:35', 'SYS', '2022-08-17 08:10:35', b'0'),
       (22, 'nginxHttpDefaultConf', 'proxy_headers_hash_max_size', '51200', 0, '头部哈希表的最大值', 'SYS',
        '2022-08-17 08:10:35', 'SYS', '2022-08-17 08:10:35', b'0'),
       (23, 'nginxHttpDefaultConf', 'proxy_headers_hash_bucket_size', '6400', 0, '头部哈希表大小', 'SYS',
        '2022-08-17 08:10:35', 'SYS', '2022-08-17 08:10:35', b'0'),
       (24, 'nginxHttpDefaultConf', 'include', 'mime.types', 0, '包含类型', 'SYS', '2022-08-17 08:13:05', 'SYS',
        '2022-08-17 08:14:37', b'0'),
       (25, 'nginxHttpDefaultConf', 'log_format',
        'json \'{"@timestamp":"$time_iso8601",\'\n                 \'"host":"$server_addr",\'\n                 \'"clientIp":"$remote_addr",\'\n                 \'"size":$body_bytes_sent,\'\n                 \'"responseTime":$request_time,\'\n                 \'"upstreamTime":"$upstream_response_time",\'\n                 \'"upstreamHost":"$upstream_addr",\'\n                 \'"http_host":"$host",\'\n                 \'"url":"$uri",\'\n                 \'"referer":"$http_referer",\'\n                 \'"agent":"$http_user_agent",\'\n                 \'"status":"$status"}\'',
        0, '日志json格式', 'SYS', '2022-08-17 08:18:08', 'SYS', '2022-08-17 08:18:08', b'0'),
       (26, 'nginxTemplateType', 'server_http', '反向代理(server)(http)', 0, '模板配置类型', 'SYS', '2022-08-17 12:45:09', 'SYS',
        '2022-08-17 12:45:09', b'0'),
       (27, 'nginxTemplateType', 'server_tcp', '反向代理(server)(tcp)', 0, '模板配置类型', 'SYS', '2022-08-17 12:45:09', 'SYS',
        '2022-08-17 12:45:09', b'0'),
       (28, 'nginxTemplateType', 'server_udp', '反向代理(server)(udp)', 0, '模板配置类型', 'SYS', '2022-08-17 12:45:09', 'SYS',
        '2022-08-17 12:45:09', b'0'),
       (29, 'nginxTemplateType', 'location', '代理目标(location)', 0, '模板配置类型', 'SYS', '2022-08-17 12:45:10', 'SYS',
        '2022-08-17 12:45:10', b'0'),
       (30, 'nginxTemplateType', 'upstream', '负载均衡(upstream)', 0, '模板配置类型', 'SYS', '2022-08-17 12:45:10', 'SYS',
        '2022-08-17 12:45:10', b'0');

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

-- 正在导出表  nginx_ops.sys_file 的数据：~0 rows (大约)

-- 导出  表 nginx_ops.sys_menu 结构
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE IF NOT EXISTS `sys_menu`
(
    `id`          char(20)    NOT NULL COMMENT '菜单ID',
    `menu_name`   varchar(50) NOT NULL COMMENT '菜单名称',
    `parent_id`   char(20)     DEFAULT NULL COMMENT '父菜单ID',
    `order`       int(4)       DEFAULT '0' COMMENT '显示顺序',
    `path`        varchar(200) DEFAULT NULL COMMENT '路由地址',
    `component`   varchar(255) DEFAULT NULL COMMENT '组件路径',
    `query`       varchar(255) DEFAULT NULL COMMENT '路由参数',
    `is_frame`    bit(1)       DEFAULT b'0' COMMENT '是否为外链（0是 1否）',
    `is_cache`    bit(1)       DEFAULT b'0' COMMENT '是否缓存（0缓存 1不缓存）',
    `menu_type`   char(1)      DEFAULT NULL COMMENT '菜单类型（M目录 C菜单 F按钮）',
    `is_enable`   bit(1)       DEFAULT b'1' COMMENT '菜单状态（0正常 1停用）',
    `perms`       varchar(100) DEFAULT NULL COMMENT '权限标识',
    `icon`        varchar(100) DEFAULT '#' COMMENT '菜单图标',
    `create_by`   varchar(50)  DEFAULT 'SYS' COMMENT '创建者',
    `create_time` datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   varchar(50)  DEFAULT 'SYS' COMMENT '更新者',
    `update_time` datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_flag` bit(1)       DEFAULT b'0' COMMENT '逻辑删除',
    `remark`      text COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_parent_id` (`parent_id`),
    KEY `idx_order` (`order`),
    KEY `idx_is_enable` (`is_enable`),
    KEY `idx_menu_name` (`menu_name`),
    KEY `idx_delete_flag` (`delete_flag`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='菜单权限表';

-- 正在导出表  nginx_ops.sys_menu 的数据：~0 rows (大约)

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

-- 正在导出表  nginx_ops.sys_operation_log 的数据：~0 rows (大约)

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

-- 正在导出表  nginx_ops.sys_role 的数据：~0 rows (大约)

-- 导出  表 nginx_ops.sys_role_menu 结构
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE IF NOT EXISTS `sys_role_menu`
(
    `id`          char(20) NOT NULL COMMENT '唯一ID',
    `role_id`     char(20) NOT NULL COMMENT '角色ID',
    `menu_id`     char(20) NOT NULL COMMENT '菜单ID',
    `create_by`   varchar(50) DEFAULT NULL COMMENT '创建人',
    `create_time` datetime    DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(50) DEFAULT NULL COMMENT '修改人',
    `update_time` datetime    DEFAULT NULL COMMENT '修改时间',
    `delete_flag` bit(1)      DEFAULT NULL COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_menu_id` (`menu_id`) USING BTREE,
    KEY `idx_role_id` (`role_id`) USING BTREE,
    KEY `idx_delete_flag` (`delete_flag`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='角色菜单关联表';

-- 正在导出表  nginx_ops.sys_role_menu 的数据：~0 rows (大约)

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

-- 正在导出表  nginx_ops.sys_setting 的数据：~0 rows (大约)

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

-- 正在导出表  nginx_ops.sys_user 的数据：~0 rows (大约)

-- 导出  表 nginx_ops.sys_user_role 结构
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE IF NOT EXISTS `sys_user_role`
(
    `id`          char(20) NOT NULL COMMENT '唯一ID',
    `user_id`     char(20) NOT NULL COMMENT '用户ID',
    `role_id`     char(20) NOT NULL COMMENT '角色ID',
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

-- 正在导出表  nginx_ops.sys_user_role 的数据：~0 rows (大约)

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

-- 正在导出表  nginx_ops.sys_user_setting 的数据：~0 rows (大约)

/*!40103 SET TIME_ZONE = IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE = IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS = IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES = IFNULL(@OLD_SQL_NOTES, 1) */;
