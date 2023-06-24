package org.character.iras.DataAccess.MySQLImplments;

import org.character.iras.DataAccess.Interfaces.DataAccess;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class MySQLInitializer implements DataAccess {
    public boolean initialize(){
        JdbcTemplate template = getJdbcTemplate();
        String[] sql = {
                "CREATE TABLE if not exists `user` (\n" +
                        "  `username` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,\n" +
                        "  `password` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,\n" +
                        "  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,\n" +
                        "  `last_login` datetime DEFAULT NULL,\n" +
                        "  `last_token` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,\n" +
                        "  `resume_id` int DEFAULT NULL,\n" +
                        "  `privileged` int DEFAULT '0'\n" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;",
                "CREATE TABLE if not exists `token` (\n" +
                      "  `token_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,\n" +
                      "  `expired_time` datetime DEFAULT NULL\n" +
                      ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;",
                "CREATE TABLE if not exists `resume` (\n" +
                        "\t`id` INT NOT NULL,\n" +
                        "\t`url` VARCHAR ( 2048 ) CHARACTER \n" +
                        "\tSET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,\n" +
                        "\t`keywords` VARCHAR ( 2048 ) COLLATE utf8mb4_general_ci DEFAULT NULL,\n" +
                        "\t`name` VARCHAR ( 255 ) COLLATE utf8mb4_general_ci DEFAULT NULL,\n" +
                        "\t`age` INT DEFAULT NULL,\n" +
                        "\t`HighestDegree` VARCHAR ( 255 ) COLLATE utf8mb4_general_ci DEFAULT NULL,\n" +
                        "\t`GraduateSchool` VARCHAR ( 255 ) COLLATE utf8mb4_general_ci DEFAULT NULL,\n" +
                        "\t`WorkingSeniority` VARCHAR ( 255 ) COLLATE utf8mb4_general_ci DEFAULT NULL,\n" +
                        "PRIMARY KEY ( `id` ) \n" +
                        ") ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;"
        };

        for (String s : sql) {
            template.execute(s);
        }
        return template.queryForList(
                "SELECT * FROM `user` where privileged=1"
        ).size() == 0;
    }
}
