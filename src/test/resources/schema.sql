SET MODE MySQL;

DROP TABLE if EXISTS recommend_user;
CREATE TABLE `recommend_user` (
  `user_id` int(11) unsigned NOT NULL,
  `rank` int(11) NOT NULL,
  PRIMARY KEY (`user_id`)
);