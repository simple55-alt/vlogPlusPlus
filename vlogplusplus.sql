/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : vlogplusplus

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 29/03/2020 11:59:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  `var` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  `begin_time` datetime(0) NOT NULL,
  `end_time` datetime(0) NULL DEFAULT NULL,
  `method` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT NULL,
  `image` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT '',
  `c_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_german2_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES (1, '活动名称', '主题', '活动具体内容和要求', '2020-03-06 22:41:26', '2020-05-03 22:41:31', '参与方式：电话、邮件、APP在线报名', 'activity/1.jpg', '2020-03-06 22:42:16');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) NOT NULL,
  `target_id` int(11) NOT NULL,
  `c_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `var` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  `image` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT '',
  `count` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `u_id`(`u_id`) USING BTREE,
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_german2_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, 2, 1, '2020-03-06 23:23:19', '评论的内容：这个视频真好看啊！', 'comment/1584279995535.jpg', 0);
INSERT INTO `comment` VALUES (2, 2, -1, '2020-03-06 23:23:47', '这个模板不错', '', 0);
INSERT INTO `comment` VALUES (3, 2, 1, '2020-03-28 22:03:10', '太好看了', '', 0);
INSERT INTO `comment` VALUES (4, 1, 1, '2020-03-29 10:11:57', 'say something ok?!', '', 0);
INSERT INTO `comment` VALUES (5, 1, 1, '2020-03-29 10:21:14', 'I like this video bcz its my video!! hia hia~', '', 0);
INSERT INTO `comment` VALUES (6, 1, 1, '2020-03-29 10:23:33', 'If u like my video, plz give me a like! 3Q', '', 0);
INSERT INTO `comment` VALUES (7, 1, 1, '2020-03-29 10:29:56', 'plz follow me!', '', 0);
INSERT INTO `comment` VALUES (8, 1, 1, '2020-03-29 11:23:13', '@测试账号 Grazie!!', '', 0);
INSERT INTO `comment` VALUES (9, 1, 1, '2020-03-29 11:24:53', '@测试账号 xia xia', '', 0);

-- ----------------------------
-- Table structure for complaint
-- ----------------------------
DROP TABLE IF EXISTS `complaint`;
CREATE TABLE `complaint`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) NOT NULL,
  `c_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `var` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `u_id`(`u_id`) USING BTREE,
  CONSTRAINT `complaint_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_german2_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of complaint
-- ----------------------------
INSERT INTO `complaint` VALUES (1, 2, '2020-03-06 23:35:09', '我有意见要提');

-- ----------------------------
-- Table structure for draft
-- ----------------------------
DROP TABLE IF EXISTS `draft`;
CREATE TABLE `draft`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `video_id` int(11) NULL DEFAULT NULL,
  `u_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `draft_image` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `video_id`(`video_id`) USING BTREE,
  CONSTRAINT `draft_ibfk_1` FOREIGN KEY (`video_id`) REFERENCES `video` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_german2_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of draft
-- ----------------------------
INSERT INTO `draft` VALUES (1, 1, '2020-03-06 23:36:25', 'draft/1.jpg');

-- ----------------------------
-- Table structure for dynamic
-- ----------------------------
DROP TABLE IF EXISTS `dynamic`;
CREATE TABLE `dynamic`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) NOT NULL,
  `c_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `var` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT '',
  `video` int(11) NULL DEFAULT 0,
  `dynamic_img` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT '',
  `at_uid` int(11) NULL DEFAULT 0,
  `public` tinyint(4) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `u_id`(`u_id`) USING BTREE,
  CONSTRAINT `dynamic_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_german2_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dynamic
-- ----------------------------
INSERT INTO `dynamic` VALUES (1, 2, '2020-03-29 11:48:35', '今天好开心，@想放松i', 0, '', 1, 0);
INSERT INTO `dynamic` VALUES (2, 1, '2020-03-29 11:49:18', '看看自己以前拍的视频', 1, '', 0, 0);

-- ----------------------------
-- Table structure for fan
-- ----------------------------
DROP TABLE IF EXISTS `fan`;
CREATE TABLE `fan`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fan_id` int(11) NOT NULL,
  `up_id` int(11) NOT NULL,
  `c_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fan_id`(`fan_id`) USING BTREE,
  INDEX `up_id`(`up_id`) USING BTREE,
  CONSTRAINT `fan_ibfk_1` FOREIGN KEY (`fan_id`) REFERENCES `user` (`u_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fan_ibfk_2` FOREIGN KEY (`up_id`) REFERENCES `user` (`u_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_german2_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fan
-- ----------------------------
INSERT INTO `fan` VALUES (2, 2, 1, '2020-03-06 23:18:33');

-- ----------------------------
-- Table structure for favorite
-- ----------------------------
DROP TABLE IF EXISTS `favorite`;
CREATE TABLE `favorite`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) NOT NULL,
  `video_id` int(11) NOT NULL,
  `c_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `u_id`(`u_id`) USING BTREE,
  INDEX `video_id`(`video_id`) USING BTREE,
  CONSTRAINT `favorite_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `favorite_ibfk_2` FOREIGN KEY (`video_id`) REFERENCES `video` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_german2_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of favorite
-- ----------------------------
INSERT INTO `favorite` VALUES (1, 1, 1, '2020-03-06 23:27:55');

-- ----------------------------
-- Table structure for letter
-- ----------------------------
DROP TABLE IF EXISTS `letter`;
CREATE TABLE `letter`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sender_id` int(11) NOT NULL,
  `receiver_id` int(11) NOT NULL,
  `c_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `var` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  `state` tinyint(4) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sender_id`(`sender_id`) USING BTREE,
  INDEX `receiver_id`(`receiver_id`) USING BTREE,
  CONSTRAINT `letter_ibfk_1` FOREIGN KEY (`sender_id`) REFERENCES `user` (`u_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `letter_ibfk_2` FOREIGN KEY (`receiver_id`) REFERENCES `user` (`u_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_german2_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of letter
-- ----------------------------
INSERT INTO `letter` VALUES (1, 1, 2, '2020-03-06 23:32:39', '私信的内容', 1);

-- ----------------------------
-- Table structure for likes
-- ----------------------------
DROP TABLE IF EXISTS `likes`;
CREATE TABLE `likes`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) NOT NULL,
  `video_id` int(11) NOT NULL,
  `c_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `u_id`(`u_id`) USING BTREE,
  INDEX `video_id`(`video_id`) USING BTREE,
  CONSTRAINT `likes_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `likes_ibfk_2` FOREIGN KEY (`video_id`) REFERENCES `video` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_german2_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of likes
-- ----------------------------
INSERT INTO `likes` VALUES (1, 1, 1, '2020-03-06 23:30:13');

-- ----------------------------
-- Table structure for search
-- ----------------------------
DROP TABLE IF EXISTS `search`;
CREATE TABLE `search`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `var` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  `c_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `count` int(11) NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_german2_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of search
-- ----------------------------
INSERT INTO `search` VALUES (1, '珊珊姐姐', '2020-03-06 23:36:50', 5);
INSERT INTO `search` VALUES (2, '草草', '2020-03-06 23:36:58', 5);
INSERT INTO `search` VALUES (3, '志远帅哥', '2020-03-06 23:37:13', 100);
INSERT INTO `search` VALUES (4, 'test', '2020-03-15 22:14:08', 1);

-- ----------------------------
-- Table structure for template_info
-- ----------------------------
DROP TABLE IF EXISTS `template_info`;
CREATE TABLE `template_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  `summary` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT '',
  `detail` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT '',
  `u_id` int(11) NOT NULL,
  `c_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `u_id`(`u_id`) USING BTREE,
  CONSTRAINT `template_info_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_german2_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of template_info
-- ----------------------------
INSERT INTO `template_info` VALUES (1, '模板名字', '目标概要', '模板内容', 1, '2020-03-06 23:21:09');
INSERT INTO `template_info` VALUES (2, '模板2', '概要', '内容', 2, '2020-03-15 22:31:43');

-- ----------------------------
-- Table structure for topic
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  `summary` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  `video` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT '',
  `c_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `count` int(11) NULL DEFAULT 0,
  `u_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `u_id`(`u_id`) USING BTREE,
  CONSTRAINT `topic_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_german2_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO `topic` VALUES (1, '话题名称', '话题简介', 'topic/1.mp4', '2020-03-06 22:42:57', 1, 1);

-- ----------------------------
-- Table structure for topic_join
-- ----------------------------
DROP TABLE IF EXISTS `topic_join`;
CREATE TABLE `topic_join`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) NOT NULL,
  `c_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `topic_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `u_id`(`u_id`) USING BTREE,
  INDEX `topic_id`(`topic_id`) USING BTREE,
  CONSTRAINT `topic_join_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `topic_join_ibfk_2` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_german2_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of topic_join
-- ----------------------------
INSERT INTO `topic_join` VALUES (1, 1, '2020-03-13 16:10:53', 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  `nickname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT '',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT '',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT '',
  `image` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT '',
  `level` int(11) NULL DEFAULT 0,
  `count_fan` int(11) NULL DEFAULT 0,
  `count_follow` int(11) NULL DEFAULT 0,
  `sex` tinyint(4) NULL DEFAULT 1,
  `birthday` datetime(0) NULL DEFAULT NULL,
  `fashion` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT '',
  `medal` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT '',
  PRIMARY KEY (`u_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_german2_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'xfs', 'e10adc3949ba59abbe56e057f20f883e', '想放松', 'cnxfs@qq.com', '13082808309', 'head_img/1584279995535.jpg', 0, 1, 0, 1, '1999-01-06 22:39:43', '只有想不到，没有做不到', '小萌星');
INSERT INTO `user` VALUES (2, 'test', 'e10adc3949ba59abbe56e057f20f883e', '测试账号', '', '', 'head_img/1584279252715.png', 0, 0, 1, 0, '2020-03-10 22:22:13', '', '');
INSERT INTO `user` VALUES (3, 'admin', 'e10adc3949ba59abbe56e057f20f883e', '管理员', '', '', '', 0, 0, 0, 0, '1999-05-12 20:20:20', '', '');
INSERT INTO `user` VALUES (4, 'xfsfs', 'e10adc3949ba59abbe56e057f20f883e', 'xfsfs', '', '18857714822', '', 0, 0, 0, 0, '1998-01-01 08:00:00', '', '');

-- ----------------------------
-- Table structure for version
-- ----------------------------
DROP TABLE IF EXISTS `version`;
CREATE TABLE `version`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  `c_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  `detail` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_german2_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of version
-- ----------------------------
INSERT INTO `version` VALUES (1, 'v1.0', '2020-03-06 23:35:41', 'https://cnxfs.com.cn', '第一个版本');

-- ----------------------------
-- Table structure for video
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  `type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  `var` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NOT NULL,
  `subtitle` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT '',
  `content` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT '',
  `u_id` int(11) NOT NULL,
  `t_id` int(11) NULL DEFAULT NULL,
  `c_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `count_likes` int(11) NULL DEFAULT 0,
  `count_share` int(11) NULL DEFAULT 0,
  `count_favorite` int(11) NULL DEFAULT 0,
  `count_watch` int(11) NULL DEFAULT 0,
  `state` tinyint(4) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `u_id`(`u_id`) USING BTREE,
  INDEX `t_id`(`t_id`) USING BTREE,
  CONSTRAINT `video_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `video_ibfk_2` FOREIGN KEY (`t_id`) REFERENCES `template_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_german2_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of video
-- ----------------------------
INSERT INTO `video` VALUES (1, '视频标题', '视频类别', 'video/1584265151782.mp4', 'subtitle/1584265151782.txt', '视频内容介绍文字', 1, 1, '2020-03-06 23:21:31', 1, 0, 1, 0, 1);
INSERT INTO `video` VALUES (2, '再见韩国', '类别2', 'video/1584445454970.mp4', 'subtitle/1584445454970.txt', '离开汉阳大', 1, 1, '2020-03-28 22:00:32', 0, 0, 0, 0, 1);

-- ----------------------------
-- Table structure for visitor
-- ----------------------------
DROP TABLE IF EXISTS `visitor`;
CREATE TABLE `visitor`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) NOT NULL,
  `c_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `up_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `u_id`(`u_id`) USING BTREE,
  INDEX `up_id`(`up_id`) USING BTREE,
  CONSTRAINT `visitor_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `visitor_ibfk_2` FOREIGN KEY (`up_id`) REFERENCES `user` (`u_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_german2_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of visitor
-- ----------------------------
INSERT INTO `visitor` VALUES (1, 2, '2020-03-06 23:34:46', 1);

-- ----------------------------
-- Triggers structure for table fan
-- ----------------------------
DROP TRIGGER IF EXISTS `fan_insert`;
delimiter ;;
CREATE TRIGGER `fan_insert` AFTER INSERT ON `fan` FOR EACH ROW BEGIN
	UPDATE `user` SET count_fan=count_fan+1 WHERE u_id=new.up_id;
	UPDATE `user` SET count_follow=count_follow+1 WHERE u_id=new.fan_id;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table fan
-- ----------------------------
DROP TRIGGER IF EXISTS `fan_delete`;
delimiter ;;
CREATE TRIGGER `fan_delete` AFTER DELETE ON `fan` FOR EACH ROW BEGIN
	UPDATE `user` SET count_fan=count_fan-1 WHERE u_id=old.up_id;
	UPDATE `user` SET count_follow=count_follow-1 WHERE u_id=old.fan_id;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table favorite
-- ----------------------------
DROP TRIGGER IF EXISTS `favorite_insert`;
delimiter ;;
CREATE TRIGGER `favorite_insert` AFTER INSERT ON `favorite` FOR EACH ROW BEGIN
	UPDATE video SET count_favorite=count_favorite+1 WHERE id=new.video_id;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table favorite
-- ----------------------------
DROP TRIGGER IF EXISTS `favorite_delete`;
delimiter ;;
CREATE TRIGGER `favorite_delete` AFTER DELETE ON `favorite` FOR EACH ROW BEGIN
	UPDATE video SET count_favorite=count_favorite-1 WHERE id=old.video_id;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table likes
-- ----------------------------
DROP TRIGGER IF EXISTS `likes_insert`;
delimiter ;;
CREATE TRIGGER `likes_insert` AFTER INSERT ON `likes` FOR EACH ROW BEGIN
	UPDATE video SET count_likes=count_likes+1 WHERE id=new.video_id;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table likes
-- ----------------------------
DROP TRIGGER IF EXISTS `likes_delete`;
delimiter ;;
CREATE TRIGGER `likes_delete` AFTER DELETE ON `likes` FOR EACH ROW BEGIN
	UPDATE video SET count_likes=count_likes-1 WHERE id=old.video_id;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table topic_join
-- ----------------------------
DROP TRIGGER IF EXISTS `topic_join_insert`;
delimiter ;;
CREATE TRIGGER `topic_join_insert` AFTER INSERT ON `topic_join` FOR EACH ROW BEGIN
	UPDATE topic SET count=count+1 WHERE id=new.topic_id;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table topic_join
-- ----------------------------
DROP TRIGGER IF EXISTS `topic_join_delete`;
delimiter ;;
CREATE TRIGGER `topic_join_delete` AFTER DELETE ON `topic_join` FOR EACH ROW BEGIN
	UPDATE topic SET count=count-1 WHERE id=old.topic_id;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
