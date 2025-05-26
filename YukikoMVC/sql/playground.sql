use playground;

-- Table 'department'
CREATE TABLE department (
    dep_id VARCHAR(36) PRIMARY KEY, -- Define dep_id como chave primária
    dep_name VARCHAR(50)
);

-- Table 'employee'
CREATE TABLE employee (
    emp_id VARCHAR(36) PRIMARY KEY, -- Define emp_id como chave primária
    emp_name VARCHAR(50),
    dep_id VARCHAR(36),
    registration_datetime TIMESTAMP,
    FOREIGN KEY (dep_id) REFERENCES department(dep_id) -- Define dep_id como chave estrangeira, referenciando a tabela 'department'
);

-- Create a index into colunm dep_id of table 'employee'
CREATE INDEX idx_employee_dep_id ON employee (dep_id);

-- Insert data into 'department' and 'employee'
INSERT INTO department VALUES ('1b88ed56-de1d-422b-8a5c-caae26b50baa', '営業部');
INSERT INTO department VALUES ('0a7ab531-ec30-4c25-a5ec-f60b8dc27093', '開発部');

INSERT INTO employee VALUES ('c55560fd-547c-46ba-a57b-d9036c57f506', 'Naoko', '0a7ab531-ec30-4c25-a5ec-f60b8dc27093', CURRENT_TIMESTAMP);
INSERT INTO employee VALUES ('984c0f18-3699-4a33-86c7-12f6d38a4be2', 'Hiro', '1b88ed56-de1d-422b-8a5c-caae26b50baa', CURRENT_TIMESTAMP);

SELECT * FROM employee;
SELECT * FROM department;

-- SQL_SAFE_UPDATES
-- SET SQL_SAFE_UPDATES = 0;
-- UPDATE employee SET registration_datetime = CURRENT_TIMESTAMP;
-- SET SQL_SAFE_UPDATES = 1;