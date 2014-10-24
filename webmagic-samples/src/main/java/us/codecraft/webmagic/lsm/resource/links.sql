CREATE TABLE `links` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `link` varchar(255) NOT NULL DEFAULT '',
  `domains` varchar(100) NOT NULL DEFAULT '',
  `reply` int(11) unsigned NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `extra` varchar(255) NOT NULL DEFAULT '',
  `visit_num` int(11) unsigned NOT NULL DEFAULT '1',
  `author` varchar(255) NOT NULL DEFAULT '',
  `content` text,
  `img` text,
  `title` varchar(255) NOT NULL DEFAULT '',
  `authorlink` varchar(255) NOT NULL DEFAULT '',
  `posttime` varchar(255) NOT NULL DEFAULT '',
  `pageno` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_link` (`link`)
) ENGINE=InnoDB AUTO_INCREMENT=5268 DEFAULT CHARSET=utf8;