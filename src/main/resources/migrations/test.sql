-- liquibase formatted sql
-- changeset brand:1

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation` (
    `reservationID`   INT         NOT NULL,
    `reservationName` VARCHAR(45) NULL,
    `date_created`    DATE        NULL,
    PRIMARY KEY (`reservationID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

-- rollback SET FOREIGN_KEY_CHECKS = 0;
-- rollback DROP TABLE IF EXISTS overtime_shift_method;
-- rollback SET FOREIGN_KEY_CHECKS = 1;
