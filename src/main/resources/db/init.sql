CREATE TABLE `fire_alarm` (
                              `id` bigint NOT NULL AUTO_INCREMENT,
                              `reporter` varchar(50) NOT NULL,
                              `phone` varchar(20) NOT NULL,
                              `address` varchar(255) NOT NULL,
                              `longitude` double NOT NULL,
                              `latitude` double NOT NULL,
                              `level` int NOT NULL DEFAULT '1',
                              `description` varchar(500) DEFAULT NULL,
                              `status` int NOT NULL DEFAULT '0',
                              `report_time` timestamp NOT NULL,
                              `handle_time` timestamp NULL DEFAULT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- `fire-test`.fire_alarm_plan definition

CREATE TABLE `fire_alarm_plan` (
                                   `id` bigint NOT NULL AUTO_INCREMENT,
                                   `address` varchar(255) NOT NULL,
                                   `longitude` double NOT NULL,
                                   `latitude` double NOT NULL,
                                   `fire_location` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
                                   `fire_stations` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
                                   `hospitals` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
                                   `path_hospital` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                   `path` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;