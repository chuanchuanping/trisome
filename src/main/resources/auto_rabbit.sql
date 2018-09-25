/*
Navicat MySQL Data Transfer

Source Server         : myMAX
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : auto_rabbit

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2018-09-25 14:36:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for command
-- ----------------------------
DROP TABLE IF EXISTS `command`;
CREATE TABLE `command` (
  `CID` int(16) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of command
-- ----------------------------
INSERT INTO `command` VALUES ('1', '查看', '精彩内容');
INSERT INTO `command` VALUES ('2', '刘昊然', '电影内容');

-- ----------------------------
-- Table structure for command_content
-- ----------------------------
DROP TABLE IF EXISTS `command_content`;
CREATE TABLE `command_content` (
  `ID` int(11) DEFAULT NULL,
  `CONTENT` varchar(2048) DEFAULT NULL,
  `COMMAND_ID` int(11) DEFAULT NULL,
  KEY `user` (`COMMAND_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of command_content
-- ----------------------------
INSERT INTO `command_content` VALUES ('1', '刘昊然，1997年10月10日出生于河南省平顶山市，中国内地男演员，就读于中央戏剧学院表演系本科。2014年，主演电影《北京爱情故事》，正式出道，凭借该片入围第21届北京大学生电影节最佳新人奖。2015年5月，加盟国防教育特别节目《真正男子汉》；7月，以专业和文化双料第一名的成绩被中央戏剧学院录取；12月，主演悬疑喜剧电影《唐人街探案》，凭借该片获得第20届华鼎奖中国最佳新人奖和2016中国电影指数盛典最佳银幕新锐演员奖，并入围第19届上海国际电影节亚洲新人奖最佳男演员奖，该片中国内地 ', '2');
INSERT INTO `command_content` VALUES ('2', 'Bootstrap\r\n简洁、直观、强悍的前端开发框架，让web开发更迅速、简单。', '2');
INSERT INTO `command_content` VALUES ('3', '2006年，刘昊然于春节期间登上中央电视台少儿频道春节晚会表演并获得奖项 [19]  。2009年，刘昊然小学毕业后考上北京舞蹈学院附中，11岁就开始了“北漂”的求学生活，年纪尚小的他曾因为不适应，在入校的第一天就写好了退学申请书，但最终还是咬牙坚持了下来 [20]  。2013年3月，还在读高一的刘昊然被陈思诚选中，得到了主演电影《北京爱情故事》的机会，并以此开始了他的演艺之路', '2');
INSERT INTO `command_content` VALUES ('4', '2014年2月14日，主演的浪漫爱情电影《北京爱情故事》上映 [22]  ，在片中饰演清纯初恋故事的单元男主“宋歌”，凭借自然真挚的表演获得第21届北京大学生电影节的最佳新人奖提名 [2]  ，该片在中国内地票房达4.06亿元 [23]  。5月，参与录制《快乐大本营》六一特别节目《少年，少年》', '2');
INSERT INTO `command_content` VALUES ('5', '刘昊然小学的时候文化成绩就很好，同时考取了河南省的重点初中和北京舞蹈学院附中，并最终选择就读北京舞蹈学院附中 [20]  。刘昊然爱独数，所有棋牌类游戏都会，会玩很多稀奇古怪的东西，比如：射箭。会口琴、萨克斯、钢琴、舞蹈，对什么都兴趣浓厚', '2');
INSERT INTO `command_content` VALUES ('6', '回肠荡气 引人入胜 精妙绝伦 扣人心弦 韵味无穷 精彩纷呈 百看不厌 爱不释手 身临其境 跌宕起伏 纷繁复杂 妙语连珠 书中自有颜如玉 书中自有黄金屋', '1');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `ID` int(20) NOT NULL AUTO_INCREMENT,
  `COMMAND` varchar(255) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `CONTENT` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', '查看', '精彩内容', '通过线上生产参数，真实支付一笔0.01元的订单，只要正确配置了webhook的回调地址（在管理平台配置或者通过API在代码中设置），都会收到webhook通知。有的同学说我自己的服务器上什么反应都没有啊，服务器上日志也没有任何记录，这种情况9成就是你的服务器没有正常响应webhook请求，返回500错误了，那怎么查看问题呢，只需要把订单号通过webhooks通知查询工具进行查询。');
INSERT INTO `message` VALUES ('3', '新闻', '今日头条', '通过线上生产参数，真实支付一笔0.01元的订单，只要正确配置了webhook的回调地址（在管理平台配置或者通过API在代码中设置），都会收到webhook通知。有的同学说我自己的服务器上什么反应都没有啊，服务器上日志也没有任何记录，这种情况9成就是你的服务器没有正常响应webhook请求，返回500错误了，那怎么查看问题呢，只需要把订单号通过webhooks通知查询工具进行查询。');
INSERT INTO `message` VALUES ('4', '娱乐', '娱乐新闻', '我的内涵段子啊');
INSERT INTO `message` VALUES ('5', '电影', '近日上映大片', '我不是药神');
INSERT INTO `message` VALUES ('6', '彩票', '中奖号码', '122');
INSERT INTO `message` VALUES ('7', '哈哈', '超超', '就看见');
INSERT INTO `message` VALUES ('8', '哈哈', '额度', '哦交换机');
INSERT INTO `message` VALUES ('16', '哈哈', '艰苦艰苦', '测的v');
INSERT INTO `message` VALUES ('20', '哈哈', '都是', '秘籍');
INSERT INTO `message` VALUES ('31', '哈哈', '方便v', '吉吉');
INSERT INTO `message` VALUES ('46', '耐瘠薄', '你们了可能', '才');
INSERT INTO `message` VALUES ('47', ' 交', '模块', '猜到没');


-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `pr_id` int(10) NOT NULL AUTO_INCREMENT,
  `pr_name` varchar(25) DEFAULT NULL,
  `pr_content` varchar(255) DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `stock_num` int(10) DEFAULT NULL,
  `sale_num` int(10) DEFAULT NULL,
  PRIMARY KEY (`pr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', 'iphone6s', '好肾好哦手机', '3788.00', '100', '0');
INSERT INTO `product` VALUES ('2', '小米8', '年轻人的第一次车祸', '2788.00', '100', '0');
INSERT INTO `product` VALUES ('3', 'vivo20', '高价低配的烂手机', '4788.00', '100', '0');
INSERT INTO `product` VALUES ('4', 'meizu6', '店长推荐', '3690.00', '100', '0');
INSERT INTO `product` VALUES ('5', '诺基亚200', '小时候的回忆', '188.00', '100', '0');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(64) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL COMMENT '用户昵称',
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱|登录帐号',
  `pswd` varchar(32) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `openid` varchar(255) DEFAULT NULL,
  `headimgurl` varchar(255) DEFAULT NULL,
  `nickname` varchar(15) DEFAULT NULL,
  `city` varchar(10) DEFAULT NULL,
  `province` varchar(10) DEFAULT NULL,
  `country` varchar(10) DEFAULT NULL,
  `sex` int(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '123456@qq.com', '123456', '2018-09-25 10:46:13', null, null, null, null, null, null, null);
