use playground

CREATE TABLE department (
  dep_id VARCHAR(36) PRIMARY KEY,
  dep_name VARCHAR(50)
);

CREATE TABLE employee (
  emp_id VARCHAR(36) PRIMARY KEY,
  emp_name VARCHAR(50),
  dep_id VARCHAR(36),
  registration_datetime TIMESTAMP,
  FOREIGN KEY (dep_id) REFERENCES department(dep_id)
);

ALTER TABLE department MODIFY COLUMN dep_id VARCHAR(36);

INSERT INTO department VALUES ('1b88ed56-de1d-422b-8a5c-caae26b50baa', '営業部');
INSERT INTO department VALUES ('0a7ab531-ec30-4c25-a5ec-f60b8dc27093', '開発部');

INSERT INTO employee VALUES ('c55560fd-547c-46ba-a57b-d9036c57f506', 'Naoko', '0a7ab531-ec30-4c25-a5ec-f60b8dc27093', CURRENT_TIMESTAMP);
INSERT INTO employee VALUES ('984c0f18-3699-4a33-86c7-12f6d38a4be2', 'Hiro', '1b88ed56-de1d-422b-8a5c-caae26b50baa', CURRENT_TIMESTAMP);


SELECT * FROM employee;
SELECT * FROM department;

ALTER TABLE employee DROP FOREIGN KEY employee_ibfk_1;
ALTER TABLE department MODIFY COLUMN dep_id VARCHAR(36);

ALTER TABLE employee
ADD CONSTRAINT employee_ibfk_1
FOREIGN KEY (dep_id)employeeemployee
REFERENCES department(dep_id);
dep_id

SET SQL_SAFE_UPDATES = 0;
UPDATE employee SET registration_datetime = CURRENT_TIMESTAMP;
SET SQL_SAFE_UPDATES = 1;

ALTER TABLE employee MODIFY COLUMN empId VARCHAR(36);