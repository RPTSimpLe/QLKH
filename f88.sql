create database f88;
use f88;

-- Thêm vai trò Admin
INSERT INTO roles (name, code, createDate, modifierDate, createBy, modifierBy, isDeleted)
VALUES ('admin', 'ADMIN', NOW(), NOW(), 'admin', 'admin', 0);
-- Thêm tài khoản admin
INSERT INTO accounts (username, password, email, phoneNumber, name, gender, birthday, codeAccount, createDate, modifierDate, createBy, modifierBy, isDeleted)
VALUES ('admin', 'adminadmin', NULL, NULL, 'Administrator', NULL, NULL, 'ADMIN001', NOW(), NOW(), 'admin', 'admin', 0);
-- Lấy ID của vai trò admin
SET @adminRoleId = (SELECT id FROM roles WHERE code = 'ADMIN');

-- Lấy ID của tài khoản admin
SET @adminAccountId = (SELECT id FROM accounts WHERE username = 'admin');

-- Gán vai trò admin cho tài khoản admin
INSERT INTO roles_accounts (role_id, account_id, createDate, modifierDate, createBy, modifierBy, isDeleted)
VALUES (@adminRoleId, @adminAccountId, NOW(), NOW(), 'admin', 'admin', 0);

-- Thêm vai trò Student
INSERT INTO roles (name, code, createDate, modifierDate, createBy, modifierBy, isDeleted)
VALUES ('student', 'STUDENT', NOW(), NOW(), 'admin', 'admin', 0);

-- Thêm vai trò Teacher
INSERT INTO roles (name, code, createDate, modifierDate, createBy, modifierBy, isDeleted)
VALUES ('teacher', 'TEACHER', NOW(), NOW(), 'admin', 'admin', 0);

-- Thêm tài khoản student
INSERT INTO accounts (username, password, email, phoneNumber, name, gender, birthday, codeAccount, createDate, modifierDate, createBy, modifierBy, isDeleted)
VALUES 
('student1', 'password1', 'student1@example.com', '1234567890', 'Student One', 1, '2000-01-01', 'STU001', NOW(), NOW(), 'admin', 'admin', 0),
('student2', 'password2', 'student2@example.com', '1234567891', 'Student Two', 1, '2000-02-02', 'STU002', NOW(), NOW(), 'admin', 'admin', 0),
('student3', 'password3', 'student3@example.com', '1234567892', 'Student Three', 1, '2000-03-03', 'STU003', NOW(), NOW(), 'admin', 'admin', 0),
('student4', 'password4', 'student4@example.com', '1234567893', 'Student Four', 1, '2000-04-04', 'STU004', NOW(), NOW(), 'admin', 'admin', 0),
('student5', 'password5', 'student5@example.com', '1234567894', 'Student Five', 1, '2000-05-05', 'STU005', NOW(), NOW(), 'admin', 'admin', 0);

-- Thêm tài khoản teacher với số điện thoại không trùng lặp
INSERT INTO accounts (username, password, email, phoneNumber, name, gender, birthday, codeAccount, createDate, modifierDate, createBy, modifierBy, isDeleted)
VALUES 
('teacher1', 'password1', 'teacher1@example.com', '1234567845', 'Teacher One', 1, '1980-01-01', 'TEA001', NOW(), NOW(), 'admin', 'admin', 0),
('teacher2', 'password2', 'teacher2@example.com', '1234567834', 'Teacher Two', 1, '1980-02-02', 'TEA002', NOW(), NOW(), 'admin', 'admin', 0),
('teacher3', 'password3', 'teacher3@example.com', '1234567823', 'Teacher Three', 1, '1980-03-03', 'TEA003', NOW(), NOW(), 'admin', 'admin', 0),
('teacher4', 'password4', 'teacher4@example.com', '1234567832', 'Teacher Four', 1, '1980-04-04', 'TEA004', NOW(), NOW(), 'admin', 'admin', 0),
('teacher5', 'password5', 'teacher5@example.com', '1234567885', 'Teacher Five', 1, '1980-05-05', 'TEA005', NOW(), NOW(), 'admin', 'admin', 0);

-- Lấy ID của vai trò student
SET @studentRoleId = (SELECT id FROM roles WHERE code = 'STUDENT');

-- Lấy ID của vai trò teacher
SET @teacherRoleId = (SELECT id FROM roles WHERE code = 'TEACHER');

-- Lấy ID của các tài khoản student
SET @studentAccountId1 = (SELECT id FROM accounts WHERE username = 'student1');
SET @studentAccountId2 = (SELECT id FROM accounts WHERE username = 'student2');
SET @studentAccountId3 = (SELECT id FROM accounts WHERE username = 'student3');
SET @studentAccountId4 = (SELECT id FROM accounts WHERE username = 'student4');
SET @studentAccountId5 = (SELECT id FROM accounts WHERE username = 'student5');

-- Lấy ID của các tài khoản teacher
SET @teacherAccountId1 = (SELECT id FROM accounts WHERE username = 'teacher1');
SET @teacherAccountId2 = (SELECT id FROM accounts WHERE username = 'teacher2');
SET @teacherAccountId3 = (SELECT id FROM accounts WHERE username = 'teacher3');
SET @teacherAccountId4 = (SELECT id FROM accounts WHERE username = 'teacher4');
SET @teacherAccountId5 = (SELECT id FROM accounts WHERE username = 'teacher5');

-- Gán vai trò cho các tài khoản student
INSERT INTO roles_accounts (role_id, account_id, createDate, modifierDate, createBy, modifierBy, isDeleted)
VALUES 
(@studentRoleId, @studentAccountId1, NOW(), NOW(), 'admin', 'admin', 0),
(@studentRoleId, @studentAccountId2, NOW(), NOW(), 'admin', 'admin', 0),
(@studentRoleId, @studentAccountId3, NOW(), NOW(), 'admin', 'admin', 0),
(@studentRoleId, @studentAccountId4, NOW(), NOW(), 'admin', 'admin', 0),
(@studentRoleId, @studentAccountId5, NOW(), NOW(), 'admin', 'admin', 0);

-- Gán vai trò cho các tài khoản teacher
INSERT INTO roles_accounts (role_id, account_id, createDate, modifierDate, createBy, modifierBy, isDeleted)
VALUES 
(@teacherRoleId, @teacherAccountId1, NOW(), NOW(), 'admin', 'admin', 0),
(@teacherRoleId, @teacherAccountId2, NOW(), NOW(), 'admin', 'admin', 0),
(@teacherRoleId, @teacherAccountId3, NOW(), NOW(), 'admin', 'admin', 0),
(@teacherRoleId, @teacherAccountId4, NOW(), NOW(), 'admin', 'admin', 0),
(@teacherRoleId, @teacherAccountId5, NOW(), NOW(), 'admin', 'admin', 0);
