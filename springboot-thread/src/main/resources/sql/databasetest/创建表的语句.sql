CREATE TABLE `course` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=401 DEFAULT CHARSET=utf8;


CREATE TABLE `sc` (
  `sc_id` int(11) NOT NULL AUTO_INCREMENT,
  `s_id` int(11) DEFAULT NULL,
  `c_id` int(11) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  PRIMARY KEY (`sc_id`),
  KEY `index_sc_cid_score` (`c_id`,`score`)
) ENGINE=InnoDB AUTO_INCREMENT=770001 DEFAULT CHARSET=utf8;


CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=70002 DEFAULT CHARSET=utf8;