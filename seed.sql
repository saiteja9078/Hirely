-- Clean existing data (respecting foreign key order)
TRUNCATE TABLE candidate_experiences CASCADE;
TRUNCATE TABLE candidate_skills CASCADE;
TRUNCATE TABLE job_rounds CASCADE;
TRUNCATE TABLE job_skills CASCADE;
TRUNCATE TABLE job_applications CASCADE;
TRUNCATE TABLE job_postings CASCADE;
TRUNCATE TABLE resumes CASCADE;
TRUNCATE TABLE candidates CASCADE;
TRUNCATE TABLE hiring_managers CASCADE;
TRUNCATE TABLE hiring_departments CASCADE;
TRUNCATE TABLE companies CASCADE;
TRUNCATE TABLE industries CASCADE;
TRUNCATE TABLE roles CASCADE;
TRUNCATE TABLE skills CASCADE;

-- Restart Sequences
ALTER SEQUENCE IF EXISTS company_seq RESTART WITH 100;
ALTER SEQUENCE IF EXISTS hiring_department_seq RESTART WITH 100;
ALTER SEQUENCE IF EXISTS hr_seq RESTART WITH 100;
ALTER SEQUENCE IF EXISTS role_seq RESTART WITH 100;
ALTER SEQUENCE IF EXISTS skill_seq RESTART WITH 100;
ALTER SEQUENCE IF EXISTS candidate_seq RESTART WITH 100;
ALTER SEQUENCE IF EXISTS resumes_SEQ RESTART WITH 100;
ALTER SEQUENCE IF EXISTS job_posting_seq RESTART WITH 100;
ALTER SEQUENCE IF EXISTS job_application_seq RESTART WITH 100;
ALTER SEQUENCE IF EXISTS job_round_seq RESTART WITH 100;
ALTER SEQUENCE IF EXISTS candidate_exp_seq RESTART WITH 100;

-- 1. Industries
INSERT INTO industries (id, name) VALUES
(1, 'Technology & Software'),
(2, 'Healthcare & Biotech'),
(3, 'Finance & Banking'),
(4, 'Automotive & Aerospace'),
(5, 'Education & E-Learning');

-- 2. Companies
INSERT INTO companies (id, name, company_profile_url, email, password, industry_id, country, state, city) VALUES
(1, 'TechCorp Solutions', 'https://techcorp.example.com', 'contact@techcorp.example.com', 'securepass123', 1, 'United States', 'California', 'San Francisco'),
(2, 'BioHealth Labs', 'https://biohealth.example.com', 'careers@biohealth.example.com', 'biosecure456', 2, 'United States', 'Massachusetts', 'Boston'),
(3, 'FinEdge Group', 'https://finedge.example.com', 'info@finedge.example.com', 'finance789', 3, 'United States', 'New York', 'New York'),
(4, 'AutoDrive Systems', 'https://autodrive.example.com', 'jobs@autodrive.example.com', 'drive2026', 4, 'Germany', 'Bavaria', 'Munich');

-- 3. Hiring Departments
INSERT INTO hiring_departments (id, name, company_id) VALUES
(1, 'Engineering', 1),
(2, 'Product Management', 1),
(3, 'Research & Development', 2),
(4, 'Quantitative Trading', 3),
(5, 'Autonomous Controls', 4);

-- 4. Hiring Managers (Gender must be 'MALE' or 'FEMALE')
INSERT INTO hiring_managers (id, first_name, last_name, gender, email, password, department_id) VALUES
(1, 'Sarah', 'Jenkins', 'FEMALE', 's.jenkins@techcorp.example.com', 'SarahPass2026', 1),
(2, 'David', 'Miller', 'MALE', 'd.miller@techcorp.example.com', 'DavidPass2026', 2),
(3, 'Elena', 'Rostova', 'FEMALE', 'e.rostova@biohealth.example.com', 'ElenaPass2026', 3),
(4, 'Marcus', 'Aurelius', 'MALE', 'm.aurelius@finedge.example.com', 'MarcusPass2026', 4),
(5, 'Dieter', 'Schwarz', 'MALE', 'd.schwarz@autodrive.example.com', 'DieterPass2026', 5);

-- 5. Roles
INSERT INTO roles (id, name) VALUES
(1, 'Software Engineer'),
(2, 'Product Manager'),
(3, 'Bioinformatics Scientist'),
(4, 'Quantitative Analyst'),
(5, 'Control Systems Engineer');

-- 6. Skills
INSERT INTO skills (id, name) VALUES
(1, 'Java'),
(2, 'Spring Boot'),
(3, 'Python'),
(4, 'React'),
(5, 'Kubernetes'),
(6, 'SQL'),
(7, 'Machine Learning'),
(8, 'C++'),
(9, 'Data Analysis');

-- 7. Candidates (Gender must be 'MALE' or 'FEMALE')
INSERT INTO candidates (id, first_name, last_name, age, profile_picture_url, gender, password, email, description, country, state, city) VALUES
(1, 'Alice', 'Smith', 28, 'https://images.example.com/alice.jpg', 'FEMALE', 'password123', 'alice.smith@example.com', 'Passionate Java developer looking for cloud-native challenges.', 'United States', 'California', 'Los Angeles'),
(2, 'Bob', 'Jones', 34, 'https://images.example.com/bob.jpg', 'MALE', 'password456', 'bob.jones@example.com', 'Product leader with a track record of scaling enterprise SaaS products.', 'United States', 'Washington', 'Seattle'),
(3, 'Charlie', 'Brown', 25, 'https://images.example.com/charlie.jpg', 'MALE', 'password789', 'charlie.brown@example.com', 'Recent PhD graduate in Computational Biology.', 'United Kingdom', 'England', 'Cambridge'),
(4, 'Diana', 'Prince', 30, 'https://images.example.com/diana.jpg', 'FEMALE', 'password101', 'diana.prince@example.com', 'Quantitative modeler with strong statistics background.', 'United States', 'New York', 'New York');

-- 8. Job Postings (Status: 'DRAFT', 'OPEN', 'CLOSED'; WorkMode: 'REMOTE', 'ONSITE', 'HYBRID')
INSERT INTO job_postings (id, title, description, salary_lower, salary_higher, status, work_mode, minimum_experience_in_months, posted_at, expires_at, country, state, city, hiring_manager_id, role_id, company_id) VALUES
(1, 'Senior Java / Spring Boot Engineer', 'We are looking for a Senior Java Developer to scale our core microservices architecture.', 120000, 160000, 'OPEN', 'HYBRID', 60, '2026-01-05 08:00:00', '2026-03-05 23:59:59', 'United States', 'California', 'San Francisco', 1, 1, 1),
(2, 'Technical Product Manager', 'Lead the roadmap definition and feature delivery for our developer platforms.', 130000, 170000, 'OPEN', 'REMOTE', 48, '2026-01-10 09:00:00', '2026-03-10 23:59:59', 'United States', 'California', 'San Francisco', 2, 2, 1),
(3, 'Lead Research Scientist', 'Apply deep learning tools to genome mapping and protein folding models.', 140000, 190000, 'OPEN', 'ONSITE', 36, '2026-01-15 10:00:00', '2026-04-15 23:59:59', 'United States', 'Massachusetts', 'Boston', 3, 3, 2),
(4, 'Quant Risk Developer', 'Design and implement high-frequency trading models.', 180000, 250000, 'OPEN', 'HYBRID', 24, '2026-01-20 08:00:00', '2026-02-20 23:59:59', 'United States', 'New York', 'New York', 4, 4, 3);

-- 9. Job Skills (Proficiency: 'BEGINNER', 'INTERMEDIATE', 'EXPERT')
INSERT INTO job_skills (job_posting_id, skill_id, proficiency, required) VALUES
(1, 1, 'EXPERT', TRUE),      -- Java for Job 1
(1, 2, 'EXPERT', TRUE),      -- Spring Boot for Job 1
(1, 5, 'INTERMEDIATE', FALSE),-- Kubernetes for Job 1
(2, 6, 'INTERMEDIATE', TRUE), -- SQL for Job 2
(3, 3, 'EXPERT', TRUE),      -- Python for Job 3
(3, 7, 'EXPERT', TRUE),      -- Machine Learning for Job 3
(4, 8, 'EXPERT', TRUE);      -- C++ for Job 4

-- 10. Job Applications (Status: 'APPLIED', 'REJECTED', 'APPROVED')
INSERT INTO job_applications (id, status, applied_at, candidate_id, job_posting_id) VALUES
(1, 'APPLIED', '2026-01-06 14:30:00', 1, 1),
(2, 'APPROVED', '2026-01-11 10:15:00', 2, 2),
(3, 'APPLIED', '2026-01-16 11:00:00', 3, 3),
(4, 'REJECTED', '2026-01-21 16:45:00', 4, 4);

-- 11. Candidate Skills
INSERT INTO candidate_skills (candidate_id, skill_id, proficiency) VALUES
(1, 1, 'EXPERT'),
(1, 2, 'EXPERT'),
(1, 6, 'INTERMEDIATE'),
(2, 6, 'EXPERT'),
(2, 9, 'EXPERT'),
(3, 3, 'EXPERT'),
(3, 7, 'EXPERT'),
(4, 8, 'EXPERT'),
(4, 3, 'INTERMEDIATE');

-- 12. Candidate Experiences
INSERT INTO candidate_experiences (id, organization_name, description, experience_in_months, role_id, company_id, candidate_id) VALUES
(1, 'GlobalTech Inc', 'Built distributed streaming systems using Kafka and Java.', 36, 1, 1, 1),
(2, 'Startup Launchpad', 'Led product integration for customer onboarding software.', 48, 2, 1, 2),
(3, 'Cambridge Bio Lab', 'Conducted clinical data profiling using Python tools.', 12, 3, 2, 3),
(4, 'HedgeFund Analytics', 'Maintained low latency performance scripts.', 24, 4, 3, 4);