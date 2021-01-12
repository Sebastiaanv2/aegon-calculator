DROP TABLE IF EXISTS calculation;

CREATE TABLE calculation
(
    id       int AUTO_INCREMENT PRIMARY KEY,
    operator VARCHAR(250),
    value1   DOUBLE,
    value2   DOUBLE,
    result   DOUBLE
);
