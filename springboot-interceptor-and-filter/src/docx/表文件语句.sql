CREATE TABLE `request_logger` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `requestIp` varchar(32) DEFAULT NULL COMMENT '请求ip',
  `requestTime` datetime DEFAULT NULL COMMENT '请求时间',
  `requestMethod` varchar(128) DEFAULT NULL COMMENT '请求方法',
  `requestParams` varchar(512) DEFAULT NULL COMMENT '请求参数',
  `methodDescription` varchar(64) DEFAULT NULL COMMENT '请求方法描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;


ALTER TABLE request_logger ADD responseCode varchar(16) comment '响应代码';
ALTER TABLE request_logger ADD responseParams text comment '响应参数';
ALTER TABLE request_logger ADD responseMessage text comment '响应消息';
ALTER TABLE request_logger ADD responseTime datetime comment '响应时间';
ALTER TABLE request_logger MODIFY requestParems text COMMENT '请求参数';
ALTER TABLE request_logger ADD requestMessage text comment '请求消息';