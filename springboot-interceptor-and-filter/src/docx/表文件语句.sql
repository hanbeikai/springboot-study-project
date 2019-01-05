CREATE TABLE `request_logger` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `requestIp` varchar(32) DEFAULT NULL COMMENT '请求ip',
  `requestTime` datetime DEFAULT NULL COMMENT '请求时间',
  `requestMethod` varchar(128) DEFAULT NULL COMMENT '请求方法',
  `requestParams` varchar(512) DEFAULT NULL COMMENT '请求参数',
  `methodDescription` varchar(64) DEFAULT NULL COMMENT '请求方法描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;