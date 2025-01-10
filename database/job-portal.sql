DROP DATABASE IF EXISTS jobportal;

CREATE DATABASE jobportal;

USE jobportal;

CREATE TABLE users_type (
	user_type_id INT NOT NULL AUTO_INCREMENT,
    user_type_name VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (user_type_id)
);

INSERT INTO jobportal.users_type (user_type_id, user_type_name) VALUES (1, 'Recruiter'), (2, 'Job Seeker');

CREATE TABLE users (
	user_id INT NOT NULL AUTO_INCREMENT,
    email VARCHAR(255) NULL,
    is_active BOOL NULL,
    password VARCHAR(255) DEFAULT NULL,
    registration_date DATETIME(6) DEFAULT NULL,
    user_type_id INT NULL,
    PRIMARY KEY (user_id),
    UNIQUE (email),
    CONSTRAINT Fk_UserTypeId Foreign Key (user_type_id) REFERENCES users_type (user_type_id)
);

CREATE TABLE job_company (
	id INT NOT NULL AUTO_INCREMENT,
	logo VARCHAR(255) NULL,
    name VARCHAR(255) NULL,
    PRIMARY KEY (id)
);

CREATE TABLE job_location (
	id INT NOT NULL AUTO_INCREMENT,
    city VARCHAR(255) NULL,
    country VARCHAR(255) NULL,
    state VARCHAR(255) NULL,
    PRIMARY KEY (id)
);

CREATE TABLE job_seeker_profile (
	user_account_id INT NOT NULL,
    city VARCHAR(255) NULL,
    country VARCHAR(255) NULL,
    employment_type VARCHAR(255) NULL,
    first_name VARCHAR(255) NULL,
    last_name VARCHAR(255) NULL,
    profile_photo VARCHAR(255) NULL,
    seeker_resume VARCHAR(255)  NULL,
    state VARCHAR(255) NULL,
    work_authorization VARCHAR(255) NULL,
    PRIMARY KEY (user_account_id),
	CONSTRAINT Fk_UserAccountId_1 Foreign Key (user_account_id) REFERENCES users (user_id)
);

CREATE TABLE recruiter_profile (
	user_account_id INT NOT NULL,
    city VARCHAR(255) NULL,
    company VARCHAR(255) NULL,
    country VARCHAR(255) NULL,
    first_name  VARCHAR(255) NULL,
    last_name VARCHAR(255) NULL,
    profile_photo VARCHAR(255) NULL,
    state VARCHAR(255) NULL,
    PRIMARY KEY (user_account_id),
	CONSTRAINT Fk_UserAccountId_2 Foreign Key (user_account_id) REFERENCES users (user_id)
);

CREATE TABLE job_post_activity (
	job_post_id INT NOT NULL AUTO_INCREMENT,
    description_of_job VARCHAR(255) NULL,
    job_title VARCHAR(255) NULL,
    job_type VARCHAR(255) NULL,
    posted_date datetime(6) NULL,
    remote VARCHAR(255) NULL,
    salary VARCHAR(255) NULL,
    job_company_id INT NULL,
    job_location_id INT NULL,
    posted_by_id INT NULL,
    PRIMARY KEY (job_post_id),
    CONSTRAINT Fk_JobCompanyId Foreign Key (job_company_id) REFERENCES job_company (id),
	CONSTRAINT Fk_JobLocationId Foreign Key (job_location_id) REFERENCES job_location (id),
	CONSTRAINT Fk_JobPostedById Foreign Key (posted_by_id) REFERENCES users (user_id)
);

CREATE TABLE job_seeker_save (
	id INT NOT NULL AUTO_INCREMENT,
    job INT NULL,
    user_id INT NULL,
    PRIMARY KEY (id),
	UNIQUE (user_id, job),
	CONSTRAINT Fk_Job_1 Foreign Key (job) REFERENCES job_post_activity (job_post_id),
	CONSTRAINT Fk_UserId_1 Foreign Key (user_id) REFERENCES job_seeker_profile (user_account_id)
);

CREATE TABLE job_seeker_apply (
	id INT NOT NULL AUTO_INCREMENT,
    date_of_apply DATETIME(6) NULL,
    cover_letter VARCHAR(255) NULL,
    job INT NULL, 
    user_id INT NULL,
    PRIMARY KEY (id),
	CONSTRAINT Fk_Job_2 Foreign Key (job) REFERENCES job_post_activity (job_post_id),
	CONSTRAINT Fk_UserId_2 Foreign Key (user_id) REFERENCES job_seeker_profile (user_account_id)
);

CREATE TABLE skills (
	id INT NOT NULL AUTO_INCREMENT,
    experience_level VARCHAR(255) NULL,
    name_of_seeker VARCHAR(255) NULL,
    years_of_experience VARCHAR(255) NULL,
    job_seeker_profile INT NULL,
    PRIMARY KEY (id),
	CONSTRAINT Fk_JobSeekerProfile Foreign Key (job_seeker_profile) REFERENCES job_seeker_profile (user_account_id)
);



