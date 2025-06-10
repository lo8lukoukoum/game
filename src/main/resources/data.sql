-- Disable foreign key checks to allow inserting data more freely, especially if script is re-runnable
SET FOREIGN_KEY_CHECKS=0;

-- Clear existing data (optional, use with caution, good for development resets)
-- DELETE FROM `用户角色`;
-- DELETE FROM `角色`;
-- DELETE FROM `用户`;
-- DELETE FROM `游戏分类`;
-- DELETE FROM `游戏`;
-- DELETE FROM `评论`;
-- DELETE FROM `评分`;
-- DELETE FROM `收藏`;

-- Reset auto-increment counters (optional, use with caution)
-- ALTER TABLE `角色` AUTO_INCREMENT = 1;
-- ALTER TABLE `用户` AUTO_INCREMENT = 1;
-- ALTER TABLE `游戏分类` AUTO_INCREMENT = 1;
-- ALTER TABLE `游戏` AUTO_INCREMENT = 1;
-- ALTER TABLE `评论` AUTO_INCREMENT = 1;
-- ALTER TABLE `评分` AUTO_INCREMENT = 1;
-- ALTER TABLE `收藏` AUTO_INCREMENT = 1;


-- 角色 (Roles)
INSERT INTO `角色` (`id`, `角色名称`) VALUES (1, '普通用户') ON DUPLICATE KEY UPDATE 角色名称='普通用户';
INSERT INTO `角色` (`id`, `角色名称`) VALUES (2, '管理员') ON DUPLICATE KEY UPDATE 角色名称='管理员';

-- 用户 (Users)
-- Assuming ID 1 for admin, ID 2 for user.
-- Passwords are 'admin' and 'user' respectively (plain text)
INSERT INTO `用户` (`id`, `用户名`, `密码`, `邮箱`, `创建时间`, `更新时间`) VALUES
(1, 'admin', 'admin', 'admin@example.com', NOW(), NOW()) ON DUPLICATE KEY UPDATE 用户名='admin', 密码='admin', 邮箱='admin@example.com', 更新时间=NOW();
INSERT INTO `用户` (`id`, `用户名`, `密码`, `邮箱`, `创建时间`, `更新时间`) VALUES
(2, 'user', 'user', 'user@example.com', NOW(), NOW()) ON DUPLICATE KEY UPDATE 用户名='user', 密码='user', 邮箱='user@example.com', 更新时间=NOW();
INSERT INTO `用户` (`id`, `用户名`, `密码`, `邮箱`, `创建时间`, `更新时间`) VALUES
(3, 'testuser', 'password', 'test@example.com', NOW(), NOW()) ON DUPLICATE KEY UPDATE 用户名='testuser', 密码='password', 邮箱='test@example.com', 更新时间=NOW();


-- 用户角色 (UserRoles)
-- Assign '管理员' (ID 2) to 'admin' user (ID 1)
INSERT INTO `用户角色` (`用户id`, `角色id`) VALUES (1, 2) ON DUPLICATE KEY UPDATE `用户id`=`用户id`; -- ON DUPLICATE KEY UPDATE to prevent error if re-run
-- Assign '普通用户' (ID 1) to 'user' user (ID 2)
INSERT INTO `用户角色` (`用户id`, `角色id`) VALUES (2, 1) ON DUPLICATE KEY UPDATE `用户id`=`用户id`;
INSERT INTO `用户角色` (`用户id`, `角色id`) VALUES (3, 1) ON DUPLICATE KEY UPDATE `用户id`=`用户id`;


-- 游戏分类 (Categories)
INSERT INTO `游戏分类` (`id`, `分类名称`, `描述`, `创建时间`, `更新时间`) VALUES
(1, '动作游戏', '充满紧张刺激的动作场面，考验玩家的反应和技巧。', NOW(), NOW()) ON DUPLICATE KEY UPDATE 分类名称='动作游戏', 描述='充满紧张刺激的动作场面，考验玩家的反应和技巧。', 更新时间=NOW();
INSERT INTO `游戏分类` (`id`, `分类名称`, `描述`, `创建时间`, `更新时间`) VALUES
(2, '角色扮演', '玩家扮演虚拟世界中的角色，体验史诗般的故事情节和角色成长。', NOW(), NOW()) ON DUPLICATE KEY UPDATE 分类名称='角色扮演', 描述='玩家扮演虚拟世界中的角色，体验史诗般的故事情节和角色成长。', 更新时间=NOW();
INSERT INTO `游戏分类` (`id`, `分类名称`, `描述`, `创建时间`, `更新时间`) VALUES
(3, '策略游戏', '运筹帷幄，决胜千里。考验玩家战略思维和决策能力。', NOW(), NOW()) ON DUPLICATE KEY UPDATE 分类名称='策略游戏', 描述='运筹帷幄，决胜千里。考验玩家战略思维和决策能力。', 更新时间=NOW();
INSERT INTO `游戏分类` (`id`, `分类名称`, `描述`, `创建时间`, `更新时间`) VALUES
(4, '冒险游戏', '探索广阔的世界，解开谜题，经历一段难忘的旅程。', NOW(), NOW()) ON DUPLICATE KEY UPDATE 分类名称='冒险游戏', 描述='探索广阔的世界，解开谜题，经历一段难忘的旅程。', 更新时间=NOW();


-- 游戏 (Games)
-- Using placeholder image '/images/game_placeholder.png' as discussed
INSERT INTO `游戏` (`id`, `游戏名称`, `描述`, `发行日期`, `图片链接`, `分类id`, `创建时间`, `更新时间`) VALUES
(1, '烈火英雄传', '一款国产武侠动作大作，体验快意恩仇的江湖。江湖风雨，英雄辈出，剑指天下，谁与争锋？', '2023-01-15', '/images/game_placeholder.png', 1, NOW(), NOW()) ON DUPLICATE KEY UPDATE 游戏名称='烈火英雄传', 描述='一款国产武侠动作大作，体验快意恩仇的江湖。江湖风雨，英雄辈出，剑指天下，谁与争锋？', 发行日期='2023-01-15', 图片链接='/images/game_placeholder.png', 分类id=1, 更新时间=NOW();

INSERT INTO `游戏` (`id`, `游戏名称`, `描述`, `发行日期`, `图片链接`, `分类id`, `创建时间`, `更新时间`) VALUES
(2, '幻想史诗录', '一部宏大的西方奇幻角色扮演游戏，探索神秘的魔法大陆，与巨龙搏斗，揭开古老的秘密。', '2022-11-20', '/images/game_placeholder.png', 2, NOW(), NOW()) ON DUPLICATE KEY UPDATE 游戏名称='幻想史诗录', 描述='一部宏大的西方奇幻角色扮演游戏，探索神秘的魔法大陆，与巨龙搏斗，揭开古老的秘密。', 发行日期='2022-11-20', 图片链接='/images/game_placeholder.png', 分类id=2, 更新时间=NOW();

INSERT INTO `游戏` (`id`, `游戏名称`, `描述`, `发行日期`, `图片链接`, `分类id`, `创建时间`, `更新时间`) VALUES
(3, '三国智谋', '回到三国时代，扮演一方诸侯，招募名将，发展内政，与其他玩家斗智斗勇，统一天下。', '2023-03-10', '/images/game_placeholder.png', 3, NOW(), NOW()) ON DUPLICATE KEY UPDATE 游戏名称='三国智谋', 描述='回到三国时代，扮演一方诸侯，招募名将，发展内政，与其他玩家斗智斗勇，统一天下。', 发行日期='2023-03-10', 图片链接='/images/game_placeholder.png', 分类id=3, 更新时间=NOW();

INSERT INTO `游戏` (`id`, `游戏名称`, `描述`, `发行日期`, `图片链接`, `分类id`, `创建时间`, `更新时间`) VALUES
(4, '星际远征', '驾驶高科技星际战舰，探索未知的宇宙区域，发现新行星，与其他外星文明进行贸易或战争。', '2023-05-22', '/images/game_placeholder.png', 3, NOW(), NOW()) ON DUPLICATE KEY UPDATE 游戏名称='星际远征', 描述='驾驶高科技星际战舰，探索未知的宇宙区域，发现新行星，与其他外星文明进行贸易或战争。', 发行日期='2023-05-22', 图片链接='/images/game_placeholder.png', 分类id=3, 更新时间=NOW();

INSERT INTO `游戏` (`id`, `游戏名称`, `描述`, `发行日期`, `图片链接`, `分类id`, `创建时间`, `更新时间`) VALUES
(5, '迷雾岛屿的呼唤', '在一座充满迷雾的神秘岛屿上醒来，你需要解开一系列复杂的谜题，揭露岛屿的真相并找到回家的路。', '2023-07-30', '/images/game_placeholder.png', 4, NOW(), NOW()) ON DUPLICATE KEY UPDATE 游戏名称='迷雾岛屿的呼唤', 描述='在一座充满迷雾的神秘岛屿上醒来，你需要解开一系列复杂的谜题，揭露岛屿的真相并找到回家的路。', 发行日期='2023-07-30', 图片链接='/images/game_placeholder.png', 分类id=4, 更新时间=NOW();

-- 评论 (Comments) - Example
-- Assuming user ID 2 (user) and game ID 1 (烈火英雄传)
INSERT INTO `评论` (`用户id`, `游戏id`, `评论内容`, `创建时间`, `更新时间`) VALUES
(2, 1, '这款游戏太棒了，打击感十足！剧情也很吸引人。', NOW(), NOW());
INSERT INTO `评论` (`用户id`, `游戏id`, `评论内容`, `创建时间`, `更新时间`) VALUES
(3, 1, '画面很精美，武侠氛围浓厚，推荐！', NOW(), NOW());
INSERT INTO `评论` (`用户id`, `游戏id`, `评论内容`, `创建时间`, `更新时间`) VALUES
(2, 2, '世界观很宏大，任务很多，可以玩很久。', NOW(), NOW());


-- 评分 (Ratings) - Example
-- User 2 rates game 1 with 5 stars; User 3 rates game 1 with 4 stars
INSERT INTO `评分` (`用户id`, `游戏id`, `分数`, `创建时间`, `更新时间`) VALUES (2, 1, 5, NOW(), NOW()) ON DUPLICATE KEY UPDATE 分数=5, 更新时间=NOW();
INSERT INTO `评分` (`用户id`, `游戏id`, `分数`, `创建时间`, `更新时间`) VALUES (3, 1, 4, NOW(), NOW()) ON DUPLICATE KEY UPDATE 分数=4, 更新时间=NOW();
INSERT INTO `评分` (`用户id`, `游戏id`, `分数`, `创建时间`, `更新时间`) VALUES (2, 2, 4, NOW(), NOW()) ON DUPLICATE KEY UPDATE 分数=4, 更新时间=NOW();

-- 收藏 (Collections) - Example
-- User 2 collects game 1 and game 2
INSERT INTO `收藏` (`用户id`, `游戏id`, `收藏时间`) VALUES (2, 1, NOW()) ON DUPLICATE KEY UPDATE 收藏时间=NOW();
INSERT INTO `收藏` (`用户id`, `游戏id`, `收藏时间`) VALUES (2, 2, NOW()) ON DUPLICATE KEY UPDATE 收藏时间=NOW();


SET FOREIGN_KEY_CHECKS=1;
