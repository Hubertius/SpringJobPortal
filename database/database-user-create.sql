-- If users exists, drop him
DROP USER IF EXISTS 'jobportal'@'%';

-- Create user with proper privilages
CREATE USER 'jobportal'@'%' IDENTIFIED BY '%jobportal$_@123321';

GRANT ALL PRIVILEGES ON * . * TO 'jobportal'@'%';