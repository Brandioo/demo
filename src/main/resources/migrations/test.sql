--liquibase formatted sql
--changeset brand:1

CREATE TABLE IF NOT EXISTS `overtime_shift_method`(
    `reqdid` int(12) NOT NULL ,
    `overtime_method` varchar(15) NOT NULL,
    `date_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`reqdid`)
    );


INSERT INTO overtime_shift_method (SELECT reqdid, "Regular",now() from iCare.payrollOvertime group by reqdid );

-- rollback SET FOREIGN_KEY_CHECKS = 0;
-- rollback DROP TABLE IF EXISTS overtime_shift_method;
-- rollback SET FOREIGN_KEY_CHECKS = 1;
