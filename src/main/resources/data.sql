-- Disable foreign key checks to allow inserting data more freely, especially if script is re-runnable
SET FOREIGN_KEY_CHECKS=0;

-- Clear existing data (optional, use with caution, good for development resets)
-- DELETE FROM `user_roles`;
-- DELETE FROM `roles`;
-- DELETE FROM `users`;
-- DELETE FROM `categories`;
-- DELETE FROM `games`;
-- DELETE FROM `comments`;
-- DELETE FROM `ratings`;
-- DELETE FROM `collections`;

-- Reset auto-increment counters (optional, use with caution)
-- ALTER TABLE `roles` AUTO_INCREMENT = 1;
-- ALTER TABLE `users` AUTO_INCREMENT = 1;
-- ALTER TABLE `categories` AUTO_INCREMENT = 1;
-- ALTER TABLE `games` AUTO_INCREMENT = 1;
-- ALTER TABLE `comments` AUTO_INCREMENT = 1;
-- ALTER TABLE `ratings` AUTO_INCREMENT = 1;
-- ALTER TABLE `collections` AUTO_INCREMENT = 1;


-- Roles
INSERT INTO `roles` (`id`, `name`) VALUES (1, '普通用户') ON DUPLICATE KEY UPDATE name='普通用户';
INSERT INTO `roles` (`id`, `name`) VALUES (2, '管理员') ON DUPLICATE KEY UPDATE name='管理员';

-- Users
-- Assuming ID 1 for admin, ID 2 for user.
-- Passwords are 'admin' and 'user' respectively (plain text)
INSERT INTO `users` (`id`, `username`, `password`, `email`, `created_at`, `updated_at`) VALUES
(1, 'admin', 'admin', 'admin@example.com', NOW(), NOW()) ON DUPLICATE KEY UPDATE username='admin', password='admin', email='admin@example.com', updated_at=NOW();
INSERT INTO `users` (`id`, `username`, `password`, `email`, `created_at`, `updated_at`) VALUES
(2, 'user', 'user', 'user@example.com', NOW(), NOW()) ON DUPLICATE KEY UPDATE username='user', password='user', email='user@example.com', updated_at=NOW();
INSERT INTO `users` (`id`, `username`, `password`, `email`, `created_at`, `updated_at`) VALUES
(3, 'testuser', 'password', 'test@example.com', NOW(), NOW()) ON DUPLICATE KEY UPDATE username='testuser', password='password', email='test@example.com', updated_at=NOW();


-- UserRoles
-- Assign '管理员' (ID 2) to 'admin' user (ID 1)
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (1, 2) ON DUPLICATE KEY UPDATE `user_id`=`user_id`; -- ON DUPLICATE KEY UPDATE to prevent error if re-run
-- Assign '普通用户' (ID 1) to 'user' user (ID 2)
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (2, 1) ON DUPLICATE KEY UPDATE `user_id`=`user_id`;
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (3, 1) ON DUPLICATE KEY UPDATE `user_id`=`user_id`;


-- Categories
INSERT INTO `categories` (`id`, `name`, `description`, `created_at`, `updated_at`) VALUES
(1, '动作游戏', '充满紧张刺激的动作场面，考验玩家的反应和技巧。', NOW(), NOW()) ON DUPLICATE KEY UPDATE name='动作游戏', description='充满紧张刺激的动作场面，考验玩家的反应和技巧。', updated_at=NOW();
INSERT INTO `categories` (`id`, `name`, `description`, `created_at`, `updated_at`) VALUES
(2, '角色扮演', '玩家扮演虚拟世界中的角色，体验史诗般的故事情节和角色成长。', NOW(), NOW()) ON DUPLICATE KEY UPDATE name='角色扮演', description='玩家扮演虚拟世界中的角色，体验史诗般的故事情节和角色成长。', updated_at=NOW();
INSERT INTO `categories` (`id`, `name`, `description`, `created_at`, `updated_at`) VALUES
(3, '策略游戏', '运筹帷幄，决胜千里。考验玩家战略思维和决策能力。', NOW(), NOW()) ON DUPLICATE KEY UPDATE name='策略游戏', description='运筹帷幄，决胜千里。考验玩家战略思维和决策能力。', updated_at=NOW();
INSERT INTO `categories` (`id`, `name`, `description`, `created_at`, `updated_at`) VALUES
(4, '冒险游戏', '探索广阔的世界，解开谜题，经历一段难忘的旅程。', NOW(), NOW()) ON DUPLICATE KEY UPDATE name='冒险游戏', description='探索广阔的世界，解开谜题，经历一段难忘的旅程。', updated_at=NOW();


-- Games
-- Using placeholder image '/images/game_placeholder.png' as discussed
INSERT INTO `games` (`id`, `name`, `description`, `release_date`, `image_url`, `category_id`, `created_at`, `updated_at`) VALUES
(1, '烈火英雄传', '一款国产武侠动作大作，体验快意恩仇的江湖。江湖风雨，英雄辈出，剑指天下，谁与争锋？', '2023-01-15', '/images/game_placeholder.png', 1, NOW(), NOW()) ON DUPLICATE KEY UPDATE name='烈火英雄传', description='一款国产武侠动作大作，体验快意恩仇的江湖。江湖风雨，英雄辈出，剑指天下，谁与争锋？', release_date='2023-01-15', image_url='/images/game_placeholder.png', category_id=1, updated_at=NOW();

INSERT INTO `games` (`id`, `name`, `description`, `release_date`, `image_url`, `category_id`, `created_at`, `updated_at`) VALUES
(2, '幻想史诗录', '一部宏大的西方奇幻角色扮演游戏，探索神秘的魔法大陆，与巨龙搏斗，揭开古老的秘密。', '2022-11-20', '/images/game_placeholder.png', 2, NOW(), NOW()) ON DUPLICATE KEY UPDATE name='幻想史诗录', description='一部宏大的西方奇幻角色扮演游戏，探索神秘的魔法大陆，与巨龙搏斗，揭开古老的秘密。', release_date='2022-11-20', image_url='/images/game_placeholder.png', category_id=2, updated_at=NOW();

INSERT INTO `games` (`id`, `name`, `description`, `release_date`, `image_url`, `category_id`, `created_at`, `updated_at`) VALUES
(3, '三国智谋', '回到三国时代，扮演一方诸侯，招募名将，发展内政，与其他玩家斗智斗勇，统一天下。', '2023-03-10', '/images/game_placeholder.png', 3, NOW(), NOW()) ON DUPLICATE KEY UPDATE name='三国智谋', description='回到三国时代，扮演一方诸侯，招募名将，发展内政，与其他玩家斗智斗勇，统一天下。', release_date='2023-03-10', image_url='/images/game_placeholder.png', category_id=3, updated_at=NOW();

INSERT INTO `games` (`id`, `name`, `description`, `release_date`, `image_url`, `category_id`, `created_at`, `updated_at`) VALUES
(4, '星际远征', '驾驶高科技星际战舰，探索未知的宇宙区域，发现新行星，与其他外星文明进行贸易或战争。', '2023-05-22', '/images/game_placeholder.png', 3, NOW(), NOW()) ON DUPLICATE KEY UPDATE name='星际远征', description='驾驶高科技星际战舰，探索未知的宇宙区域，发现新行星，与其他外星文明进行贸易或战争。', release_date='2023-05-22', image_url='/images/game_placeholder.png', category_id=3, updated_at=NOW();

INSERT INTO `games` (`id`, `name`, `description`, `release_date`, `image_url`, `category_id`, `created_at`, `updated_at`) VALUES
(5, '迷雾岛屿的呼唤', '在一座充满迷雾的神秘岛屿上醒来，你需要解开一系列复杂的谜题，揭露岛屿的真相并找到回家的路。', '2023-07-30', '/images/game_placeholder.png', 4, NOW(), NOW()) ON DUPLICATE KEY UPDATE name='迷雾岛屿的呼唤', description='在一座充满迷雾的神秘岛屿上醒来，你需要解开一系列复杂的谜题，揭露岛屿的真相并找到回家的路。', release_date='2023-07-30', image_url='/images/game_placeholder.png', category_id=4, updated_at=NOW();

-- Comments - Example
-- Assuming user ID 2 (user) and game ID 1 (烈火英雄传)
INSERT INTO `comments` (`user_id`, `game_id`, `content`, `created_at`, `updated_at`) VALUES
(2, 1, '这款游戏太棒了，打击感十足！剧情也很吸引人。', NOW(), NOW());
INSERT INTO `comments` (`user_id`, `game_id`, `content`, `created_at`, `updated_at`) VALUES
(3, 1, '画面很精美，武侠氛围浓厚，推荐！', NOW(), NOW());
INSERT INTO `comments` (`user_id`, `game_id`, `content`, `created_at`, `updated_at`) VALUES
(2, 2, '世界观很宏大，任务很多，可以玩很久。', NOW(), NOW());


-- Ratings - Example
-- User 2 rates game 1 with 5 stars; User 3 rates game 1 with 4 stars
INSERT INTO `ratings` (`user_id`, `game_id`, `score`, `created_at`, `updated_at`) VALUES (2, 1, 5, NOW(), NOW()) ON DUPLICATE KEY UPDATE score=5, updated_at=NOW();
INSERT INTO `ratings` (`user_id`, `game_id`, `score`, `created_at`, `updated_at`) VALUES (3, 1, 4, NOW(), NOW()) ON DUPLICATE KEY UPDATE score=4, updated_at=NOW();
INSERT INTO `ratings` (`user_id`, `game_id`, `score`, `created_at`, `updated_at`) VALUES (2, 2, 4, NOW(), NOW()) ON DUPLICATE KEY UPDATE score=4, updated_at=NOW();

-- Collections - Example
-- User 2 collects game 1 and game 2
INSERT INTO `collections` (`user_id`, `game_id`, `collected_at`) VALUES (2, 1, NOW()) ON DUPLICATE KEY UPDATE collected_at=NOW();
INSERT INTO `collections` (`user_id`, `game_id`, `collected_at`) VALUES (2, 2, NOW()) ON DUPLICATE KEY UPDATE collected_at=NOW();


SET FOREIGN_KEY_CHECKS=1;
