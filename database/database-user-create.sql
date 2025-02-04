-- If users exists, drop him
DROP USER IF EXISTS 'jobportal'@'%';

SET GLOBAL validate_password.LENGTH = 4;
SET GLOBAL validate_password.policy = 0;
SET GLOBAL validate_password.mixed_case_count = 0;
SET GLOBAL validate_password.number_count = 0;
SET GLOBAL validate_password.special_char_count = 0;
SET GLOBAL validate_password.check_user_name = 0;

-- Create user with proper privilages
CREATE USER 'jobportal'@'%' IDENTIFIED BY '%jobportal$_@123321';

GRANT ALL PRIVILEGES ON * . * TO 'jobportal'@'%';