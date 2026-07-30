-- Massive Seed Data for Hirely Application

-- 1. Skills
INSERT INTO skills (id, name) VALUES
(1, 'Java'), (2, 'Spring Boot'), (3, 'React'), (4, 'SQL'), (5, 'AWS'), 
(6, 'Python'), (7, 'Kubernetes'), (8, 'Docker'), (9, 'C++'), (10, 'Node.js'), 
(11, 'Typescript'), (12, 'Ruby'), (13, 'Go'), (14, 'Terraform'), (15, 'GCP'),
(16, 'Vue.js'), (17, 'Angular'), (18, 'PostgreSQL'), (19, 'MongoDB'), (20, 'Redis');
ALTER SEQUENCE skill_seq RESTART WITH 100;

-- 2. Roles
INSERT INTO roles (id, name) VALUES
(1, 'Software Engineer'), (2, 'Data Scientist'), (3, 'Product Manager'), 
(4, 'DevOps Engineer'), (5, 'Frontend Developer'), (6, 'Backend Developer'), 
(7, 'Full Stack Engineer'), (8, 'Machine Learning Engineer'), (9, 'QA Engineer'), 
(10, 'Site Reliability Engineer');
ALTER SEQUENCE role_seq RESTART WITH 100;

-- 2.5 Industries
INSERT INTO industries (id, name) VALUES
(1, 'Technology'), (2, 'Finance'), (3, 'Healthcare'), (4, 'E-commerce'), (5, 'Automotive');

-- 3. Companies
INSERT INTO companies (id, name, company_profile_url, country, state, city, industry_id) VALUES
(1, 'Google', 'https://careers.google.com/', 'USA', 'California', 'Mountain View', 1),
(2, 'Microsoft', 'https://careers.microsoft.com/', 'USA', 'Washington', 'Redmond', 1),
(3, 'Amazon', 'https://amazon.jobs/', 'USA', 'Washington', 'Seattle', 4),
(4, 'Meta', 'https://www.metacareers.com/', 'USA', 'California', 'Menlo Park', 1),
(5, 'Apple', 'https://www.apple.com/careers/', 'USA', 'California', 'Cupertino', 1),
(6, 'Netflix', 'https://jobs.netflix.com/', 'USA', 'California', 'Los Gatos', 1),
(7, 'Uber', 'https://www.uber.com/careers/', 'USA', 'California', 'San Francisco', 1),
(8, 'Airbnb', 'https://careers.airbnb.com/', 'USA', 'California', 'San Francisco', 1),
(9, 'Stripe', 'https://stripe.com/jobs/', 'USA', 'California', 'San Francisco', 2),
(10, 'Tesla', 'https://www.tesla.com/careers/', 'USA', 'Texas', 'Austin', 5);
ALTER SEQUENCE company_seq RESTART WITH 100;

-- 4. Departments
INSERT INTO hiring_departments (id, name, company_id) VALUES
(1, 'Engineering', 1), (2, 'Product', 1), (3, 'Data Science', 1),
(4, 'Cloud Operations', 2), (5, 'Engineering', 2), (6, 'Security', 2),
(7, 'AWS', 3), (8, 'Engineering', 3), (9, 'Logistics', 3),
(10, 'AR/VR', 4), (11, 'Engineering', 4), (12, 'Product', 4),
(13, 'Hardware', 5), (14, 'Software', 5), (15, 'Services', 5),
(16, 'Engineering', 6), (17, 'Content', 6), (18, 'Product', 6),
(19, 'Engineering', 7), (20, 'Data Science', 7), (21, 'Product', 7),
(22, 'Engineering', 8), (23, 'Product', 8), (24, 'Data Science', 8),
(25, 'Engineering', 9), (26, 'Product', 9), (27, 'Data Science', 9),
(28, 'Engineering', 10), (29, 'Autopilot', 10), (30, 'Energy', 10);
ALTER SEQUENCE hiring_department_seq RESTART WITH 100;

-- 5. Hiring Managers
INSERT INTO hiring_managers (id, first_name, last_name, gender, email, department_id) VALUES
(1, 'James', 'Smith', 'MALE', 'james.smith@google.com', 1),
(2, 'Mary', 'Johnson', 'FEMALE', 'mary.johnson@google.com', 2),
(3, 'John', 'Williams', 'MALE', 'john.williams@google.com', 3),
(4, 'Patricia', 'Brown', 'FEMALE', 'patricia.brown@microsoft.com', 4),
(5, 'Robert', 'Jones', 'MALE', 'robert.jones@microsoft.com', 5),
(6, 'Jennifer', 'Garcia', 'FEMALE', 'jennifer.garcia@microsoft.com', 6),
(7, 'Michael', 'Miller', 'MALE', 'michael.miller@amazon.com', 7),
(8, 'Linda', 'Davis', 'FEMALE', 'linda.davis@amazon.com', 8),
(9, 'William', 'Rodriguez', 'MALE', 'william.rodriguez@amazon.com', 9),
(10, 'Elizabeth', 'Martinez', 'FEMALE', 'elizabeth.martinez@meta.com', 10),
(11, 'David', 'Hernandez', 'MALE', 'david.hernandez@meta.com', 11),
(12, 'Barbara', 'Lopez', 'FEMALE', 'barbara.lopez@meta.com', 12),
(13, 'Richard', 'Gonzalez', 'MALE', 'richard.gonzalez@apple.com', 13),
(14, 'Susan', 'Wilson', 'FEMALE', 'susan.wilson@apple.com', 14),
(15, 'Joseph', 'Anderson', 'MALE', 'joseph.anderson@apple.com', 15),
(16, 'Jessica', 'Thomas', 'FEMALE', 'jessica.thomas@netflix.com', 16),
(17, 'Thomas', 'Taylor', 'MALE', 'thomas.taylor@netflix.com', 17),
(18, 'Sarah', 'Moore', 'FEMALE', 'sarah.moore@netflix.com', 18),
(19, 'Charles', 'Jackson', 'MALE', 'charles.jackson@uber.com', 19),
(20, 'Karen', 'Martin', 'FEMALE', 'karen.martin@uber.com', 20),
(21, 'Christopher', 'Lee', 'MALE', 'christopher.lee@uber.com', 21),
(22, 'Nancy', 'Perez', 'FEMALE', 'nancy.perez@airbnb.com', 22),
(23, 'Daniel', 'Thompson', 'MALE', 'daniel.thompson@airbnb.com', 23),
(24, 'Lisa', 'White', 'FEMALE', 'lisa.white@airbnb.com', 24),
(25, 'Matthew', 'Harris', 'MALE', 'matthew.harris@stripe.com', 25),
(26, 'Betty', 'Sanchez', 'FEMALE', 'betty.sanchez@stripe.com', 26),
(27, 'Anthony', 'Clark', 'MALE', 'anthony.clark@stripe.com', 27),
(28, 'Margaret', 'Ramirez', 'FEMALE', 'margaret.ramirez@tesla.com', 28),
(29, 'Mark', 'Lewis', 'MALE', 'mark.lewis@tesla.com', 29),
(30, 'Sandra', 'Robinson', 'FEMALE', 'sandra.robinson@tesla.com', 30);
ALTER SEQUENCE hr_seq RESTART WITH 100;

-- 6. Candidates
INSERT INTO candidates (id, first_name, last_name, age, profile_picture_url, gender, password, email, description, country, state, city) VALUES
(1, 'Alexander', 'Wright', 29, 'https://example.com/profiles/alexander.wright.png', 'MALE', 'pass1', 'alexander.wright@example.com', 'Passionate developer.', 'USA', 'California', 'San Francisco'),
(2, 'Emily', 'Walker', 31, 'https://example.com/profiles/emily.walker.png', 'FEMALE', 'pass2', 'emily.walker@example.com', 'Data enthusiast.', 'USA', 'New York', 'New York'),
(3, 'Henry', 'Young', 25, 'https://example.com/profiles/henry.young.png', 'MALE', 'pass3', 'henry.young@example.com', 'Cloud engineer.', 'USA', 'Texas', 'Austin'),
(4, 'Olivia', 'Allen', 34, 'https://example.com/profiles/olivia.allen.png', 'FEMALE', 'pass4', 'olivia.allen@example.com', 'Product oriented.', 'USA', 'Washington', 'Seattle'),
(5, 'Sebastian', 'King', 28, 'https://example.com/profiles/sebastian.king.png', 'MALE', 'pass5', 'sebastian.king@example.com', 'Frontend specialist.', 'USA', 'Illinois', 'Chicago'),
(6, 'Sophia', 'Scott', 40, 'https://example.com/profiles/sophia.scott.png', 'FEMALE', 'pass6', 'sophia.scott@example.com', 'Backend architect.', 'USA', 'California', 'San Jose'),
(7, 'Jack', 'Torres', 36, 'https://example.com/profiles/jack.torres.png', 'MALE', 'pass7', 'jack.torres@example.com', 'Full stack guru.', 'USA', 'Colorado', 'Denver'),
(8, 'Isabella', 'Nguyen', 27, 'https://example.com/profiles/isabella.nguyen.png', 'FEMALE', 'pass8', 'isabella.nguyen@example.com', 'AI researcher.', 'USA', 'Massachusetts', 'Boston'),
(9, 'Julian', 'Hill', 45, 'https://example.com/profiles/julian.hill.png', 'MALE', 'pass9', 'julian.hill@example.com', 'QA leader.', 'USA', 'Georgia', 'Atlanta'),
(10, 'Mia', 'Flores', 33, 'https://example.com/profiles/mia.flores.png', 'FEMALE', 'pass10', 'mia.flores@example.com', 'SRE expert.', 'USA', 'Texas', 'Dallas'),
(11, 'Levi', 'Green', 26, 'https://example.com/profiles/levi.green.png', 'MALE', 'pass11', 'levi.green@example.com', 'Junior dev.', 'USA', 'Florida', 'Miami'),
(12, 'Charlotte', 'Adams', 30, 'https://example.com/profiles/charlotte.adams.png', 'FEMALE', 'pass12', 'charlotte.adams@example.com', 'Mid-level backend.', 'USA', 'California', 'Los Angeles'),
(13, 'Owen', 'Nelson', 38, 'https://example.com/profiles/owen.nelson.png', 'MALE', 'pass13', 'owen.nelson@example.com', 'Data architect.', 'USA', 'Nevada', 'Las Vegas'),
(14, 'Amelia', 'Baker', 24, 'https://example.com/profiles/amelia.baker.png', 'FEMALE', 'pass14', 'amelia.baker@example.com', 'Recent graduate.', 'USA', 'Oregon', 'Portland'),
(15, 'Wyatt', 'Hall', 32, 'https://example.com/profiles/wyatt.hall.png', 'MALE', 'pass15', 'wyatt.hall@example.com', 'Security engineer.', 'USA', 'Arizona', 'Phoenix'),
(16, 'Harper', 'Rivera', 29, 'https://example.com/profiles/harper.rivera.png', 'FEMALE', 'pass16', 'harper.rivera@example.com', 'UI/UX developer.', 'USA', 'Michigan', 'Detroit'),
(17, 'Carter', 'Campbell', 35, 'https://example.com/profiles/carter.campbell.png', 'MALE', 'pass17', 'carter.campbell@example.com', 'Tech lead.', 'USA', 'North Carolina', 'Charlotte'),
(18, 'Evelyn', 'Mitchell', 41, 'https://example.com/profiles/evelyn.mitchell.png', 'FEMALE', 'pass18', 'evelyn.mitchell@example.com', 'VP of Engineering.', 'USA', 'Pennsylvania', 'Philadelphia'),
(19, 'Luke', 'Carter', 27, 'https://example.com/profiles/luke.carter.png', 'MALE', 'pass19', 'luke.carter@example.com', 'System admin.', 'USA', 'Ohio', 'Columbus'),
(20, 'Abigail', 'Roberts', 31, 'https://example.com/profiles/abigail.roberts.png', 'FEMALE', 'pass20', 'abigail.roberts@example.com', 'Data analyst.', 'USA', 'Indiana', 'Indianapolis');
ALTER SEQUENCE candidate_seq RESTART WITH 100;

-- 7. Candidate Skills
INSERT INTO candidate_skills (candidate_id, skill_id, proficiency) VALUES
(1, 1, 'EXPERT'), (1, 2, 'INTERMEDIATE'), (1, 4, 'EXPERT'), (1, 8, 'INTERMEDIATE'),
(2, 6, 'EXPERT'), (2, 4, 'EXPERT'), (2, 18, 'INTERMEDIATE'), (2, 5, 'BEGINNER'),
(3, 5, 'EXPERT'), (3, 7, 'EXPERT'), (3, 8, 'EXPERT'), (3, 14, 'INTERMEDIATE'),
(4, 3, 'BEGINNER'), (4, 4, 'INTERMEDIATE'), (4, 1, 'BEGINNER'),
(5, 3, 'EXPERT'), (5, 11, 'EXPERT'), (5, 16, 'INTERMEDIATE'), (5, 17, 'INTERMEDIATE'),
(6, 1, 'EXPERT'), (6, 2, 'EXPERT'), (6, 13, 'INTERMEDIATE'), (6, 18, 'EXPERT'),
(7, 3, 'EXPERT'), (7, 10, 'EXPERT'), (7, 11, 'EXPERT'), (7, 19, 'INTERMEDIATE'),
(8, 6, 'EXPERT'), (8, 4, 'EXPERT'), (8, 9, 'INTERMEDIATE'),
(9, 1, 'INTERMEDIATE'), (9, 4, 'EXPERT'), (9, 6, 'INTERMEDIATE'),
(10, 7, 'EXPERT'), (10, 8, 'EXPERT'), (10, 14, 'EXPERT'), (10, 5, 'EXPERT'),
(11, 1, 'BEGINNER'), (11, 4, 'BEGINNER'),
(12, 10, 'INTERMEDIATE'), (12, 19, 'INTERMEDIATE'), (12, 20, 'BEGINNER'),
(13, 6, 'EXPERT'), (13, 18, 'EXPERT'), (13, 19, 'EXPERT'), (13, 4, 'EXPERT'),
(14, 3, 'BEGINNER'), (14, 11, 'BEGINNER'),
(15, 6, 'EXPERT'), (15, 9, 'EXPERT'), (15, 14, 'INTERMEDIATE'),
(16, 3, 'EXPERT'), (16, 11, 'EXPERT'), (16, 16, 'EXPERT'),
(17, 1, 'EXPERT'), (17, 5, 'EXPERT'), (17, 8, 'EXPERT'), (17, 13, 'INTERMEDIATE'),
(18, 1, 'EXPERT'), (18, 2, 'EXPERT'), (18, 5, 'EXPERT'), (18, 7, 'EXPERT'),
(19, 14, 'INTERMEDIATE'), (19, 5, 'INTERMEDIATE'), (19, 8, 'EXPERT'),
(20, 6, 'INTERMEDIATE'), (20, 4, 'EXPERT'), (20, 18, 'INTERMEDIATE');

-- 8. Candidate Experiences
INSERT INTO candidate_experiences (id, role_id, organization_name, description, company_id, candidate_id, experience_in_months) VALUES
(1, 1, 'Tech Corp', 'Developed REST APIs.', NULL, 1, 48),
(2, 6, 'Startup Inc', 'Backend services.', NULL, 1, 24),
(3, 2, 'Data Solutions', 'Machine learning models.', NULL, 2, 60),
(4, 4, 'Cloudify', 'Managed Kubernetes clusters.', NULL, 3, 36),
(5, 3, 'Productize', 'Led product vision.', NULL, 4, 72),
(6, 5, 'Web Designs', 'Created UI components.', NULL, 5, 48),
(7, 6, 'Backend Bros', 'Microservices architecture.', NULL, 6, 120),
(8, 7, 'Full Stack LLC', 'End-to-end development.', NULL, 7, 96),
(9, 8, 'AI Research Labs', 'Deep learning research.', NULL, 8, 36),
(10, 9, 'Quality Checkers', 'Automated testing.', NULL, 9, 180),
(11, 10, 'Uptime Always', 'SRE practices.', NULL, 10, 84),
(12, 1, 'Internship Co', 'Java intern.', NULL, 11, 6),
(13, 6, 'Node Masters', 'API development.', NULL, 12, 48),
(14, 2, 'Big Data Corp', 'Data pipelines.', NULL, 13, 144),
(15, 5, 'React Agency', 'Frontend intern.', NULL, 14, 12),
(16, 4, 'Secure Systems', 'Security automation.', NULL, 15, 72),
(17, 5, 'Creative Web', 'Design implementations.', NULL, 16, 60),
(18, 1, 'Legacy Systems', 'Java tech lead.', NULL, 17, 120),
(19, 1, 'Enterprise Inc', 'VP Eng.', NULL, 18, 200),
(20, 4, 'Admin Ops', 'Linux admin.', NULL, 19, 36),
(21, 2, 'Analytica', 'Data analysis.', NULL, 20, 48);
ALTER SEQUENCE candidate_exp_seq RESTART WITH 100;

-- 9. Job Postings
INSERT INTO job_postings (
    id,
    title,
    description,
    salary_lower,
    salary_higher,
    minimum_experience_in_months,
    status,
    hiring_manager_id,
    role_id,
    country,
    state,
    city,
    company_id,
    posted_at,
    expires_at
) VALUES
      (1, 'Senior Java Developer', 'Looking for an experienced Java developer.', 140000, 180000, 60, 'OPENED', 1, 1, 'USA', 'California', 'Mountain View', 1, '2023-01-01 10:00:00', '2024-12-31 23:59:59'),
      (2, 'Data Scientist II', 'Join our AI team to build the future.', 130000, 190000, 48, 'OPENED', 3, 2, 'USA', 'Any', 'Remote', 1, '2023-02-01 10:00:00', '2024-12-31 23:59:59'),
      (3, 'DevOps Engineer', 'Help us scale our infrastructure.', 120000, 160000, 36, 'OPENED', 4, 4, 'USA', 'Washington', 'Redmond', 2, '2023-03-01 10:00:00', '2024-12-31 23:59:59'),
      (4, 'Frontend Developer (React)', 'Build beautiful UIs.', 110000, 150000, 36, 'OPENED', 7, 5, 'USA', 'Washington', 'Seattle', 3, '2023-04-01 10:00:00', '2024-12-31 23:59:59'),
      (5, 'Machine Learning Engineer', 'Work on GenAI models.', 160000, 220000, 60, 'OPENED', 10, 8, 'USA', 'California', 'Menlo Park', 4, '2023-05-01 10:00:00', '2024-12-31 23:59:59'),
      (6, 'Backend Engineer (Go)', 'High performance systems.', 135000, 175000, 48, 'OPENED', 13, 6, 'USA', 'California', 'Cupertino', 5, '2023-06-01 10:00:00', '2024-12-31 23:59:59'),
      (7, 'Full Stack Engineer', 'End-to-end streaming features.', 145000, 185000, 48, 'OPENED', 16, 7, 'USA', 'California', 'Los Gatos', 6, '2023-07-01 10:00:00', '2024-12-31 23:59:59'),
      (8, 'Site Reliability Engineer', 'Ensure 99.99% uptime.', 150000, 190000, 60, 'OPENED', 19, 10, 'USA', 'California', 'San Francisco', 7, '2023-08-01 10:00:00', '2024-12-31 23:59:59'),
      (9, 'Product Manager', 'Drive product strategy.', 130000, 180000, 48, 'OPENED', 22, 3, 'USA', 'California', 'San Francisco', 8, '2023-09-01 10:00:00', '2024-12-31 23:59:59'),
      (10, 'Staff QA Engineer', 'Lead automation testing.', 125000, 165000, 84, 'OPENED', 25, 9, 'USA', 'Any', 'Remote', 9, '2023-10-01 10:00:00', '2024-12-31 23:59:59'),
      (11, 'Backend Developer', 'Autopilot infrastructure.', 140000, 190000, 48, 'OPENED', 28, 6, 'USA', 'California', 'Palo Alto', 10, '2023-11-01 10:00:00', '2024-12-31 23:59:59'),
      (12, 'Junior Java Developer', 'Entry level engineering role.', 80000, 110000, 0, 'OPENED', 2, 1, 'USA', 'California', 'Mountain View', 1, '2023-11-15 10:00:00', '2024-12-31 23:59:59'),
      (13, 'Senior Data Scientist', 'Lead data analytics.', 160000, 210000, 84, 'OPENED', 9, 2, 'USA', 'Washington', 'Seattle', 3, '2023-12-01 10:00:00', '2024-12-31 23:59:59'),
      (14, 'React Native Developer', 'Mobile apps.', 120000, 160000, 36, 'OPENED', 11, 5, 'USA', 'Any', 'Remote', 4, '2023-12-15 10:00:00', '2024-12-31 23:59:59'),
      (15, 'Cloud Architect', 'Design AWS environments.', 170000, 230000, 96, 'OPENED', 14, 4, 'USA', 'Texas', 'Austin', 5, '2023-12-20 10:00:00', '2024-12-31 23:59:59');

ALTER SEQUENCE job_posting_seq RESTART WITH 100;

-- 10. Job Skills
INSERT INTO job_skills (job_posting_id, skill_id, proficiency, required) VALUES
(1, 1, 'EXPERT', true), (1, 2, 'EXPERT', true), (1, 4, 'INTERMEDIATE', false),
(2, 6, 'EXPERT', true), (2, 4, 'EXPERT', true), (2, 5, 'INTERMEDIATE', false),
(3, 7, 'EXPERT', true), (3, 8, 'EXPERT', true), (3, 5, 'EXPERT', true), (3, 14, 'INTERMEDIATE', false),
(4, 3, 'EXPERT', true), (4, 11, 'EXPERT', true), (4, 10, 'BEGINNER', false),
(5, 6, 'EXPERT', true), (5, 9, 'INTERMEDIATE', false),
(6, 13, 'EXPERT', true), (6, 18, 'INTERMEDIATE', false),
(7, 3, 'EXPERT', true), (7, 10, 'EXPERT', true), (7, 11, 'EXPERT', true), (7, 18, 'INTERMEDIATE', false),
(8, 7, 'EXPERT', true), (8, 5, 'EXPERT', true), (8, 6, 'INTERMEDIATE', false),
(9, 4, 'BEGINNER', true),
(10, 1, 'INTERMEDIATE', true), (10, 6, 'INTERMEDIATE', true),
(11, 9, 'EXPERT', true), (11, 6, 'INTERMEDIATE', false),
(12, 1, 'BEGINNER', true), (12, 2, 'BEGINNER', true),
(13, 6, 'EXPERT', true), (13, 4, 'EXPERT', true), (13, 18, 'EXPERT', true),
(14, 3, 'EXPERT', true), (14, 11, 'EXPERT', true),
(15, 5, 'EXPERT', true), (15, 14, 'EXPERT', true), (15, 7, 'EXPERT', true);


-- 12. Job Applications
INSERT INTO job_applications (id, status, applied_at, candidate_id, job_posting_id) VALUES
(1, 'APPLIED', '2023-05-01 12:00:00', 1, 1),
(2, 'APPROVED', '2023-05-02 14:30:00', 6, 1),
(3, 'REJECTED', '2023-05-03 09:15:00', 11, 1),
(4, 'APPLIED', '2023-06-01 10:00:00', 2, 2),
(5, 'APPROVED', '2023-06-05 11:00:00', 13, 2),
(6, 'REJECTED', '2023-06-10 16:00:00', 20, 2),
(7, 'APPROVED', '2023-07-01 10:00:00', 3, 3),
(8, 'APPLIED', '2023-07-02 09:00:00', 10, 3),
(9, 'APPROVED', '2023-08-01 10:00:00', 5, 4),
(10, 'REJECTED', '2023-08-02 11:00:00', 14, 4),
(11, 'APPROVED', '2023-09-01 12:00:00', 8, 5),
(12, 'APPLIED', '2023-10-01 10:00:00', 7, 7),
(13, 'APPROVED', '2023-11-01 10:00:00', 10, 8),
(14, 'APPROVED', '2023-11-05 10:00:00', 4, 9),
(15, 'APPLIED', '2023-12-01 10:00:00', 9, 10),
(16, 'APPROVED', '2023-12-10 10:00:00', 11, 12),
(17, 'APPLIED', '2024-01-01 10:00:00', 14, 12),
(18, 'APPLIED', '2024-01-10 10:00:00', 16, 14),
(19, 'APPROVED', '2024-01-15 10:00:00', 15, 15);
ALTER SEQUENCE job_application_seq RESTART WITH 100;

-- 13. Job Rounds
INSERT INTO job_rounds (id, feedback, round_number, round_name, rating, at, hr_id, job_id) VALUES
(1, 'Candidate did well in technical questions.', 1, 'Technical Screen', 8, '2023-05-10 10:00:00', 1, 1),
(2, 'Excellent cultural fit.', 2, 'Onsite Interview', 9, '2023-05-15 13:00:00', 2, 2),
(3, 'Lacked experience in necessary tools.', 1, 'Technical Screen', 4, '2023-05-12 11:00:00', 1, 3),
(4, 'Great modeling skills.', 1, 'Take-home Assignment', 8, '2023-06-15 10:00:00', 3, 5),
(5, 'Not a culture fit.', 1, 'Behavioral Interview', 3, '2023-06-12 14:00:00', 3, 6),
(6, 'Outstanding K8s knowledge.', 1, 'Technical Screen', 9, '2023-07-10 10:00:00', 4, 7),
(7, 'Needs more system design experience.', 1, 'Technical Screen', 5, '2023-07-12 10:00:00', 4, 8),
(8, 'Great React fundamentals.', 1, 'Technical Screen', 8, '2023-08-10 10:00:00', 7, 9),
(9, 'Failed basic coding test.', 1, 'Technical Screen', 2, '2023-08-10 14:00:00', 7, 10),
(10, 'Exceptional deep learning expertise.', 2, 'Onsite Interview', 10, '2023-09-15 10:00:00', 10, 11),
(11, 'Good system design.', 2, 'Onsite Interview', 8, '2023-11-15 10:00:00', 19, 13),
(12, 'Clear product vision.', 1, 'Behavioral Interview', 9, '2023-11-10 10:00:00', 22, 14),
(13, 'Shows promise for entry level.', 1, 'Technical Screen', 7, '2023-12-15 10:00:00', 2, 16),
(14, 'A bit slow on algorithms.', 1, 'Technical Screen', 6, '2024-01-05 10:00:00', 2, 17),
(15, 'Great security mindset.', 2, 'Onsite Interview', 9, '2024-01-20 10:00:00', 14, 19);
ALTER SEQUENCE job_round_seq RESTART WITH 100;
