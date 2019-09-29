CREATE TABLE employees
(
    id    INT(11) PRIMARY KEY AUTO_INCREMENT,
    fname VARCHAR(50),
    mname VARCHAR(50),
    lname VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL
)

SELECT id, fname, lname, email
FROM employees
WHERE fname IS NOT NULL;