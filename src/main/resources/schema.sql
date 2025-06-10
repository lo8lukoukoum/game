-- Drop tables in an order that respects foreign key constraints
DROP TABLE IF EXISTS `user_roles`;
DROP TABLE IF EXISTS `collections`;
DROP TABLE IF EXISTS `ratings`;
DROP TABLE IF EXISTS `comments`;
DROP TABLE IF EXISTS `games`;
DROP TABLE IF EXISTS `categories`;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `roles`;

-- Role Table
CREATE TABLE `roles` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL UNIQUE COMMENT 'Role name (e.g., ROLE_USER, ROLE_ADMIN)'
) DEFAULT CHARSET=utf8mb4 COMMENT='Stores user roles';

-- User Table
CREATE TABLE `users` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL UNIQUE,
  `password` VARCHAR(255) NOT NULL,
  `email` VARCHAR(100) UNIQUE,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) DEFAULT CHARSET=utf8mb4 COMMENT='Stores user information';

-- UserRoles Table (Join Table)
CREATE TABLE `user_roles` (
  `user_id` INT,
  `role_id` INT,
  PRIMARY KEY (`user_id`, `role_id`),
  FOREIGN KEY (`user_id`) REFERENCES `users`(`id`) ON DELETE CASCADE,
  FOREIGN KEY (`role_id`) REFERENCES `roles`(`id`) ON DELETE CASCADE
) DEFAULT CHARSET=utf8mb4 COMMENT='Maps users to roles';

-- Category Table
CREATE TABLE `categories` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL UNIQUE,
  `description` TEXT,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) DEFAULT CHARSET=utf8mb4 COMMENT='Stores game categories';

-- Game Table
CREATE TABLE `games` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `description` TEXT,
  `release_date` DATE,
  `image_url` VARCHAR(255),
  `category_id` INT,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (`category_id`) REFERENCES `categories`(`id`) ON DELETE SET NULL -- Or ON DELETE RESTRICT
) DEFAULT CHARSET=utf8mb4 COMMENT='Stores game information';

-- Comment Table
CREATE TABLE `comments` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `content` TEXT NOT NULL,
  `user_id` INT,
  `game_id` INT,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (`user_id`) REFERENCES `users`(`id`) ON DELETE CASCADE, -- Or SET NULL if comments should remain
  FOREIGN KEY (`game_id`) REFERENCES `games`(`id`) ON DELETE CASCADE
) DEFAULT CHARSET=utf8mb4 COMMENT='Stores user comments on games';

-- Rating Table
CREATE TABLE `ratings` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `score` INT NOT NULL COMMENT 'e.g., 1-5',
  `user_id` INT,
  `game_id` INT,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY `uk_user_game_rating` (`user_id`, `game_id`) COMMENT 'One rating per user per game',
  FOREIGN KEY (`user_id`) REFERENCES `users`(`id`) ON DELETE CASCADE,
  FOREIGN KEY (`game_id`) REFERENCES `games`(`id`) ON DELETE CASCADE
) DEFAULT CHARSET=utf8mb4 COMMENT='Stores user ratings for games';

-- Collection Table
CREATE TABLE `collections` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `user_id` INT,
  `game_id` INT,
  `collected_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Time the game was added to collection',
  UNIQUE KEY `uk_user_game_collection` (`user_id`, `game_id`) COMMENT 'Game collected only once by a user',
  FOREIGN KEY (`user_id`) REFERENCES `users`(`id`) ON DELETE CASCADE,
  FOREIGN KEY (`game_id`) REFERENCES `games`(`id`) ON DELETE CASCADE
) DEFAULT CHARSET=utf8mb4 COMMENT='Stores user game collections';
