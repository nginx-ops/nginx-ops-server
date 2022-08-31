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

-- 正在导出表  nginx_ops.sys_dict 的数据：~30 rows (大约)
DELETE
FROM `sys_dict`;
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

/*!40103 SET TIME_ZONE = IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE = IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS = IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES = IFNULL(@OLD_SQL_NOTES, 1) */;
