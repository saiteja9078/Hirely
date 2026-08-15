-- ============================================================
-- HIRELY LARGE SEED DATA
-- PostgreSQL
-- ============================================================

-- IMPORTANT:
-- If a previous transaction failed, abort it first.
-- ============================================================
-- 0. MAKE ENUM-LIKE DATABASE COLUMNS STRING BASED
-- ============================================================

-- Your actual database previously had job_postings.type as SMALLINT.
-- This makes it compatible with the string seed data.


-- ============================================================
-- 1. TRUNCATE EVERYTHING
-- ============================================================

TRUNCATE TABLE
    job_rounds,
    job_applications,
    job_skills,
    resumes,
    candidate_experiences,
    candidate_skills,
    company_reviews,
    job_postings,
    hiring_managers,
    hiring_departments,
    candidates,
    companies,
    skills,
    roles,
    industries
    RESTART IDENTITY CASCADE;


-- ============================================================
-- 2. RESET SEQUENCES
-- ============================================================

ALTER SEQUENCE company_seq RESTART WITH 50;
ALTER SEQUENCE company_review_seq RESTART WITH 50;
ALTER SEQUENCE hiring_department_seq RESTART WITH 50;
ALTER SEQUENCE hr_seq RESTART WITH 50;
ALTER SEQUENCE job_posting_seq RESTART WITH 50;
ALTER SEQUENCE role_seq RESTART WITH 50;
ALTER SEQUENCE job_round_seq RESTART WITH 50;
ALTER SEQUENCE job_application_seq RESTART WITH 50;
ALTER SEQUENCE skill_seq RESTART WITH 50;
ALTER SEQUENCE candidate_seq RESTART WITH 50;
ALTER SEQUENCE resumes_seq RESTART WITH 50;
ALTER SEQUENCE candidate_exp_seq RESTART WITH 50;
ALTER SEQUENCE industry_seq RESTART WITH 50;


BEGIN;


-- ============================================================
-- 3. INDUSTRIES
-- 12
-- ============================================================

INSERT INTO industries (id, name)
VALUES
    (50, 'Software & Technology'),
    (51, 'FinTech'),
    (52, 'HealthTech'),
    (53, 'EdTech'),
    (54, 'E-Commerce'),
    (55, 'Cybersecurity'),
    (56, 'Cloud Computing'),
    (57, 'Artificial Intelligence'),
    (58, 'Consulting'),
    (59, 'Telecommunications'),
    (60, 'Automotive'),
    (61, 'Media & Entertainment');


-- ============================================================
-- 4. ROLES
-- 15
-- ============================================================

INSERT INTO roles (id, name)
VALUES
    (50, 'Software Engineer'),
    (51, 'Backend Developer'),
    (52, 'Frontend Developer'),
    (53, 'Full Stack Developer'),
    (54, 'Java Developer'),
    (55, 'Python Developer'),
    (56, 'Machine Learning Engineer'),
    (57, 'AI Engineer'),
    (58, 'DevOps Engineer'),
    (59, 'Cloud Engineer'),
    (60, 'Data Engineer'),
    (61, 'Data Scientist'),
    (62, 'Mobile Developer'),
    (63, 'QA Engineer'),
    (64, 'Product Manager');


-- ============================================================
-- 5. SKILLS
-- 40
-- ============================================================

INSERT INTO skills (id, name)
VALUES
    (50, 'Java'),
    (51, 'Spring Boot'),
    (52, 'Spring Security'),
    (53, 'Hibernate'),
    (54, 'JPA'),
    (55, 'Python'),
    (56, 'Django'),
    (57, 'FastAPI'),
    (58, 'JavaScript'),
    (59, 'TypeScript'),
    (60, 'React'),
    (61, 'Next.js'),
    (62, 'Angular'),
    (63, 'Node.js'),
    (64, 'Express.js'),
    (65, 'SQL'),
    (66, 'PostgreSQL'),
    (67, 'MongoDB'),
    (68, 'Redis'),
    (69, 'Docker'),
    (70, 'Kubernetes'),
    (71, 'AWS'),
    (72, 'Azure'),
    (73, 'GCP'),
    (74, 'Git'),
    (75, 'Linux'),
    (76, 'CI/CD'),
    (77, 'Machine Learning'),
    (78, 'Deep Learning'),
    (79, 'PyTorch'),
    (80, 'TensorFlow'),
    (81, 'Pandas'),
    (82, 'Scikit-learn'),
    (83, 'NLP'),
    (84, 'Computer Vision'),
    (85, 'LLMs'),
    (86, 'LangChain'),
    (87, 'REST API'),
    (88, 'GraphQL'),
    (89, 'System Design');


-- ============================================================
-- 6. COMPANIES
-- 25
-- ============================================================

INSERT INTO companies (
    id,
    name,
    company_profile_url,
    email,
    password,
    industry_id,
    city,
    state,
    country
)
VALUES
    (50, 'NovaByte Technologies', 'https://example.com/novabyte', 'contact@novabyte.example.com', '$2a$10$seedPasswordHash', 50, 'Bengaluru', 'Karnataka', 'India'),
    (51, 'CloudNest Labs', 'https://example.com/cloudnest', 'contact@cloudnest.example.com', '$2a$10$seedPasswordHash', 56, 'Hyderabad', 'Telangana', 'India'),
    (52, 'FinEdge Systems', 'https://example.com/finedge', 'contact@finedge.example.com', '$2a$10$seedPasswordHash', 51, 'Mumbai', 'Maharashtra', 'India'),
    (53, 'DataForge Analytics', 'https://example.com/dataforge', 'contact@dataforge.example.com', '$2a$10$seedPasswordHash', 57, 'Pune', 'Maharashtra', 'India'),
    (54, 'HealthSync AI', 'https://example.com/healthsync', 'contact@healthsync.example.com', '$2a$10$seedPasswordHash', 52, 'Bengaluru', 'Karnataka', 'India'),
    (55, 'EduSphere', 'https://example.com/edusphere', 'contact@edusphere.example.com', '$2a$10$seedPasswordHash', 53, 'Noida', 'Uttar Pradesh', 'India'),
    (56, 'CyberPeak Security', 'https://example.com/cyberpeak', 'contact@cyberpeak.example.com', '$2a$10$seedPasswordHash', 55, 'Gurugram', 'Haryana', 'India'),
    (57, 'ShopKart Technologies', 'https://example.com/shopkart', 'contact@shopkart.example.com', '$2a$10$seedPasswordHash', 54, 'Bengaluru', 'Karnataka', 'India'),
    (58, 'QuantumStack', 'https://example.com/quantumstack', 'contact@quantumstack.example.com', '$2a$10$seedPasswordHash', 50, 'Chennai', 'Tamil Nadu', 'India'),
    (59, 'AutoDrive Systems', 'https://example.com/autodrive', 'contact@autodrive.example.com', '$2a$10$seedPasswordHash', 60, 'Pune', 'Maharashtra', 'India'),
    (60, 'MediaPulse', 'https://example.com/mediapulse', 'contact@mediapulse.example.com', '$2a$10$seedPasswordHash', 61, 'Mumbai', 'Maharashtra', 'India'),
    (61, 'TeleNova', 'https://example.com/telenova', 'contact@telenova.example.com', '$2a$10$seedPasswordHash', 59, 'Delhi', 'Delhi', 'India'),
    (62, 'Astra Consulting', 'https://example.com/astra', 'contact@astra.example.com', '$2a$10$seedPasswordHash', 58, 'Gurugram', 'Haryana', 'India'),
    (63, 'NeuralWorks', 'https://example.com/neuralworks', 'contact@neuralworks.example.com', '$2a$10$seedPasswordHash', 57, 'Bengaluru', 'Karnataka', 'India'),
    (64, 'CodeOrbit', 'https://example.com/codeorbit', 'contact@codeorbit.example.com', '$2a$10$seedPasswordHash', 50, 'Hyderabad', 'Telangana', 'India'),
    (65, 'SecureLayer', 'https://example.com/securelayer', 'contact@securelayer.example.com', '$2a$10$seedPasswordHash', 55, 'Noida', 'Uttar Pradesh', 'India'),
    (66, 'PayGrid', 'https://example.com/paygrid', 'contact@paygrid.example.com', '$2a$10$seedPasswordHash', 51, 'Mumbai', 'Maharashtra', 'India'),
    (67, 'VisionStack', 'https://example.com/visionstack', 'contact@visionstack.example.com', '$2a$10$seedPasswordHash', 57, 'Pune', 'Maharashtra', 'India'),
    (68, 'LearnLoop', 'https://example.com/learnloop', 'contact@learnloop.example.com', '$2a$10$seedPasswordHash', 53, 'Chennai', 'Tamil Nadu', 'India'),
    (69, 'HealthBridge', 'https://example.com/healthbridge', 'contact@healthbridge.example.com', '$2a$10$seedPasswordHash', 52, 'Delhi', 'Delhi', 'India'),
    (70, 'MarketHive', 'https://example.com/markethive', 'contact@markethive.example.com', '$2a$10$seedPasswordHash', 54, 'Bengaluru', 'Karnataka', 'India'),
    (71, 'CloudMatrix', 'https://example.com/cloudmatrix', 'contact@cloudmatrix.example.com', '$2a$10$seedPasswordHash', 56, 'Hyderabad', 'Telangana', 'India'),
    (72, 'DevSphere', 'https://example.com/devsphere', 'contact@devsphere.example.com', '$2a$10$seedPasswordHash', 50, 'Noida', 'Uttar Pradesh', 'India'),
    (73, 'AIFrontier', 'https://example.com/aifrontier', 'contact@aifrontier.example.com', '$2a$10$seedPasswordHash', 57, 'Bengaluru', 'Karnataka', 'India'),
    (74, 'NextGen Telecom', 'https://example.com/nextgentelecom', 'contact@nextgen.example.com', '$2a$10$seedPasswordHash', 59, 'Delhi', 'Delhi', 'India');


-- ============================================================
-- 7. HIRING DEPARTMENTS
-- 100
-- ============================================================

INSERT INTO hiring_departments (
    id,
    name,
    company_id
)
SELECT
    50 + ((c - 50) * 4) + d,
    CASE d
        WHEN 0 THEN 'Engineering'
        WHEN 1 THEN 'Product & Technology'
        WHEN 2 THEN 'Data & AI'
        WHEN 3 THEN 'Human Resources'
        END,
    c
FROM generate_series(50, 74) AS companies(c)
         CROSS JOIN generate_series(0, 3) AS departments(d);


-- ============================================================
-- 8. HIRING MANAGERS
-- 200
-- ONLY MALE / FEMALE
-- ============================================================

INSERT INTO hiring_managers (
    id,
    first_name,
    last_name,
    gender,
    email,
    password,
    department_id
)
SELECT
    50 + ((c - 50) * 8) + m,

    CASE ((m + c) % 12)
        WHEN 0 THEN 'Aarav'
        WHEN 1 THEN 'Arjun'
        WHEN 2 THEN 'Rohan'
        WHEN 3 THEN 'Vikram'
        WHEN 4 THEN 'Aditya'
        WHEN 5 THEN 'Rahul'
        WHEN 6 THEN 'Ananya'
        WHEN 7 THEN 'Priya'
        WHEN 8 THEN 'Sneha'
        WHEN 9 THEN 'Kavya'
        WHEN 10 THEN 'Isha'
        ELSE 'Meera'
        END,

    CASE ((m + c) % 12)
        WHEN 0 THEN 'Sharma'
        WHEN 1 THEN 'Verma'
        WHEN 2 THEN 'Reddy'
        WHEN 3 THEN 'Mehta'
        WHEN 4 THEN 'Kapoor'
        WHEN 5 THEN 'Iyer'
        WHEN 6 THEN 'Patel'
        WHEN 7 THEN 'Gupta'
        WHEN 8 THEN 'Nair'
        WHEN 9 THEN 'Malhotra'
        WHEN 10 THEN 'Joshi'
        ELSE 'Chopra'
        END,

    CASE
        WHEN m % 2 = 0 THEN 'MALE'
        ELSE 'FEMALE'
        END,

    'manager' || (50 + ((c - 50) * 8) + m) || '@example.com',

    '$2a$10$seedPasswordHash',

    50 + ((c - 50) * 4) + (m % 4)

FROM generate_series(50, 74) AS companies(c)
         CROSS JOIN generate_series(0, 7) AS managers(m);


-- ============================================================
-- 9. CANDIDATES
-- 200
-- ============================================================

INSERT INTO candidates (
    id,
    first_name,
    last_name,
    age,
    profile_picture_url,
    gender,
    password,
    email,
    description,
    city,
    state,
    country
)
SELECT
    50 + n,

    CASE n % 20
        WHEN 0 THEN 'Aarav'
        WHEN 1 THEN 'Vihaan'
        WHEN 2 THEN 'Arjun'
        WHEN 3 THEN 'Aditya'
        WHEN 4 THEN 'Rohan'
        WHEN 5 THEN 'Kabir'
        WHEN 6 THEN 'Rahul'
        WHEN 7 THEN 'Karan'
        WHEN 8 THEN 'Ananya'
        WHEN 9 THEN 'Aditi'
        WHEN 10 THEN 'Priya'
        WHEN 11 THEN 'Sneha'
        WHEN 12 THEN 'Kavya'
        WHEN 13 THEN 'Isha'
        WHEN 14 THEN 'Meera'
        WHEN 15 THEN 'Nisha'
        WHEN 16 THEN 'Riya'
        WHEN 17 THEN 'Pooja'
        WHEN 18 THEN 'Tanya'
        ELSE 'Simran'
        END,

    CASE n % 15
        WHEN 0 THEN 'Sharma'
        WHEN 1 THEN 'Verma'
        WHEN 2 THEN 'Reddy'
        WHEN 3 THEN 'Patel'
        WHEN 4 THEN 'Gupta'
        WHEN 5 THEN 'Mehta'
        WHEN 6 THEN 'Kapoor'
        WHEN 7 THEN 'Nair'
        WHEN 8 THEN 'Iyer'
        WHEN 9 THEN 'Singh'
        WHEN 10 THEN 'Malhotra'
        WHEN 11 THEN 'Joshi'
        WHEN 12 THEN 'Chopra'
        WHEN 13 THEN 'Agarwal'
        ELSE 'Bansal'
        END,

    21 + (n % 15),

    'https://i.pravatar.cc/300?img=' || (1 + (n % 70)),

    CASE
        WHEN n % 2 = 0 THEN 'MALE'
        ELSE 'FEMALE'
        END,

    '$2a$10$seedPasswordHash',

    'candidate' || (50 + n) || '@example.com',

    CASE n % 8
        WHEN 0 THEN
            'Backend-focused software engineer with experience building scalable REST APIs, distributed services, and database-backed applications.'
        WHEN 1 THEN
            'Full stack developer experienced with React, TypeScript, Node.js, Java, Spring Boot, and PostgreSQL.'
        WHEN 2 THEN
            'Machine learning engineer interested in deep learning, NLP, computer vision, model deployment, and MLOps.'
        WHEN 3 THEN
            'Cloud and DevOps engineer experienced with Docker, Kubernetes, AWS, CI/CD pipelines, and infrastructure automation.'
        WHEN 4 THEN
            'Java developer with strong experience in Spring Boot, JPA, Hibernate, PostgreSQL, REST APIs, and backend architecture.'
        WHEN 5 THEN
            'Frontend developer focused on React, TypeScript, responsive interfaces, accessibility, and web performance.'
        WHEN 6 THEN
            'Data engineer experienced in Python, SQL, PostgreSQL, data pipelines, ETL systems, and analytics infrastructure.'
        ELSE
            'AI engineer working with LLMs, Python, PyTorch, embeddings, RAG pipelines, and modern generative AI systems.'
        END,

    CASE n % 10
        WHEN 0 THEN 'Bengaluru'
        WHEN 1 THEN 'Hyderabad'
        WHEN 2 THEN 'Mumbai'
        WHEN 3 THEN 'Pune'
        WHEN 4 THEN 'Delhi'
        WHEN 5 THEN 'Noida'
        WHEN 6 THEN 'Gurugram'
        WHEN 7 THEN 'Chennai'
        WHEN 8 THEN 'Jaipur'
        ELSE 'Kolkata'
        END,

    CASE n % 10
        WHEN 0 THEN 'Karnataka'
        WHEN 1 THEN 'Telangana'
        WHEN 2 THEN 'Maharashtra'
        WHEN 3 THEN 'Maharashtra'
        WHEN 4 THEN 'Delhi'
        WHEN 5 THEN 'Uttar Pradesh'
        WHEN 6 THEN 'Haryana'
        WHEN 7 THEN 'Tamil Nadu'
        WHEN 8 THEN 'Rajasthan'
        ELSE 'West Bengal'
        END,

    'India'

FROM generate_series(0, 199) AS candidates(n);


-- ============================================================
-- 10. CANDIDATE SKILLS
-- 1,200
-- ============================================================

INSERT INTO candidate_skills (
    skill_id,
    candidate_id,
    proficiency
)
SELECT
    50 + ((candidate_offset * 3 + skill_offset * 7) % 40),

    50 + candidate_offset,

    CASE ((candidate_offset + skill_offset) % 4)
        WHEN 0 THEN 'BEGINNER'
        WHEN 1 THEN 'INTERMEDIATE'
        WHEN 2 THEN 'ADVANCED'
        ELSE 'EXPERT'
        END

FROM generate_series(0, 199) AS c(candidate_offset)
         CROSS JOIN generate_series(0, 5) AS s(skill_offset);


-- ============================================================
-- 11. CANDIDATE EXPERIENCES
-- 400
-- ============================================================

INSERT INTO candidate_experiences (
    id,
    organization_name,
    description,
    from_date,
    to_date,
    role_id,
    company_id,
    candidate_id
)
SELECT
    50 + ((candidate_offset * 2) + exp_offset),

    CASE ((candidate_offset + exp_offset) % 10)
        WHEN 0 THEN 'TechNova Solutions'
        WHEN 1 THEN 'DigitalEdge Labs'
        WHEN 2 THEN 'CloudWorks India'
        WHEN 3 THEN 'DataCraft Systems'
        WHEN 4 THEN 'InnovateX'
        WHEN 5 THEN 'ByteLabs'
        WHEN 6 THEN 'FutureStack'
        WHEN 7 THEN 'NextWave Technologies'
        WHEN 8 THEN 'CodeLabs India'
        ELSE 'TechBridge Systems'
        END,

    CASE ((candidate_offset + exp_offset) % 7)
        WHEN 0 THEN 'Developed REST APIs and backend services using Java and Spring Boot.'
        WHEN 1 THEN 'Built responsive frontend applications using React and TypeScript.'
        WHEN 2 THEN 'Designed machine learning pipelines and trained predictive models.'
        WHEN 3 THEN 'Automated deployment workflows and managed containerized applications.'
        WHEN 4 THEN 'Worked on data processing pipelines using Python and SQL.'
        WHEN 5 THEN 'Implemented scalable microservices and participated in system design.'
        ELSE 'Built production applications and collaborated with cross-functional engineering teams.'
        END,

    TIMESTAMP '2021-01-01'
        + ((candidate_offset % 36) * INTERVAL '30 days'),

    TIMESTAMP '2022-06-01'
        + ((candidate_offset % 36) * INTERVAL '30 days'),

    50 + ((candidate_offset + exp_offset * 3) % 15),

    50 + ((candidate_offset + exp_offset) % 25),

    50 + candidate_offset

FROM generate_series(0, 199) AS c(candidate_offset)
         CROSS JOIN generate_series(0, 1) AS e(exp_offset);


-- ============================================================
-- 12. RESUMES
-- 200
-- ============================================================

INSERT INTO resumes (
    id,
    actual_name,
    stored_path,
    content,
    uploaded_at,
    candidate_id
)
SELECT
    50 + n,

    'candidate_' || (50 + n) || '_resume.pdf',

    '/resumes/candidate_' || (50 + n) || '.pdf',

    '## Professional Summary

Software professional with experience in modern application development.

## Skills

Java, Python, SQL, Spring Boot, React, Docker, Git.

## Experience

Experience developing scalable software applications and collaborating with engineering teams.

## Education

Bachelor of Technology in Computer Science and Engineering.',

    TIMESTAMP '2026-01-01'
        + (n * INTERVAL '1 day'),

    50 + n

FROM generate_series(0, 199) AS candidates(n);


-- ============================================================
-- 13. JOB POSTINGS
-- 500
-- STRING ENUM VALUES
-- ============================================================

INSERT INTO job_postings (
    id,
    title,
    description,
    salary_lower,
    salary_higher,
    status,
    hiring_manager_id,
    type,
    working_hours_per_day,
    role_id,
    city,
    state,
    country,
    company_id,
    work_mode,
    minimum_experience_in_months,
    posted_at,
    expires_at
)
SELECT
    50 + j,

    CASE (j % 15)
        WHEN 0 THEN 'Software Engineer'
        WHEN 1 THEN 'Backend Developer'
        WHEN 2 THEN 'Frontend Developer'
        WHEN 3 THEN 'Full Stack Developer'
        WHEN 4 THEN 'Java Developer'
        WHEN 5 THEN 'Python Developer'
        WHEN 6 THEN 'Machine Learning Engineer'
        WHEN 7 THEN 'AI Engineer'
        WHEN 8 THEN 'DevOps Engineer'
        WHEN 9 THEN 'Cloud Engineer'
        WHEN 10 THEN 'Data Engineer'
        WHEN 11 THEN 'Data Scientist'
        WHEN 12 THEN 'Mobile Developer'
        WHEN 13 THEN 'QA Engineer'
        ELSE 'Product Manager'
        END,

    $markdown$
## About the Role

We are looking for a talented **software professional** to join our engineering organization.

The ideal candidate will enjoy solving challenging technical problems and building reliable products used by real customers.

## Responsibilities

- Design, develop, test, and maintain production-quality software.
- Collaborate with engineers, product managers, and designers.
- Build scalable and maintainable systems.
- Participate in code reviews.
- Investigate production issues.
- Improve application performance and reliability.
- Contribute to architectural and technical discussions.

## Requirements

- Strong programming fundamentals.
- Good understanding of data structures and algorithms.
- Experience with software development practices.
- Familiarity with Git and collaborative development.
- Strong problem-solving ability.
- Good communication skills.

## Nice to Have

- Experience with cloud platforms.
- Experience with Docker or Kubernetes.
- Knowledge of distributed systems.
- Experience designing REST APIs.
- Familiarity with CI/CD.

## What You Will Work On

You will work on customer-facing applications, internal platforms, APIs, data systems, and distributed services.

## Benefits

- Flexible working environment.
- Learning and development opportunities.
- Technical mentorship.
- Performance-based growth.
- Collaborative engineering culture.
$markdown$,

    -- salary_lower
    400000 + ((j * 35000) % 1000000),

    -- salary_higher
    (
        400000
            + ((j * 35000) % 1000000)
            + 100000
            + ((j * 50000) % 1000000)
        ),

    CASE j % 10
        WHEN 0 THEN 'DRAFT'
        WHEN 1 THEN 'CLOSED'
        WHEN 2 THEN 'CLOSED'
        ELSE 'OPEN'
        END,

    50 + ((j / 25) * 8) + (j % 8),

    CASE j % 8
        WHEN 0 THEN 'INTERN'
        WHEN 1 THEN 'INTERN'
        WHEN 2 THEN 'PART_TIME'
        ELSE 'FULL_TIME'
        END,

    CASE j % 3
        WHEN 0 THEN 8
        WHEN 1 THEN 9
        ELSE 10
        END,

    50 + (j % 15),

    CASE j % 10
        WHEN 0 THEN 'Bengaluru'
        WHEN 1 THEN 'Hyderabad'
        WHEN 2 THEN 'Mumbai'
        WHEN 3 THEN 'Pune'
        WHEN 4 THEN 'Delhi'
        WHEN 5 THEN 'Noida'
        WHEN 6 THEN 'Gurugram'
        WHEN 7 THEN 'Chennai'
        WHEN 8 THEN 'Jaipur'
        ELSE 'Kolkata'
        END,

    CASE j % 10
        WHEN 0 THEN 'Karnataka'
        WHEN 1 THEN 'Telangana'
        WHEN 2 THEN 'Maharashtra'
        WHEN 3 THEN 'Maharashtra'
        WHEN 4 THEN 'Delhi'
        WHEN 5 THEN 'Uttar Pradesh'
        WHEN 6 THEN 'Haryana'
        WHEN 7 THEN 'Tamil Nadu'
        WHEN 8 THEN 'Rajasthan'
        ELSE 'West Bengal'
        END,

    'India',

    50 + (j % 25),

    CASE j % 3
        WHEN 0 THEN 'REMOTE'
        WHEN 1 THEN 'ONSITE'
        ELSE 'HYBRID'
        END,

    (j % 8) * 6,

    TIMESTAMP '2026-01-01'
        + ((j % 300) * INTERVAL '1 day'),

    TIMESTAMP '2026-01-01'
        + ((j % 300) * INTERVAL '1 day')
        + ((30 + (j % 120)) * INTERVAL '1 day')

FROM generate_series(0, 499) AS jobs(j);


-- ============================================================
-- 14. JOB SKILLS
-- 2,500
-- ============================================================

INSERT INTO job_skills (
    job_posting_id,
    skill_id,
    proficiency,
    required
)
SELECT
    50 + j,

    50 + ((j * 5 + s * 9) % 40),

    CASE ((j + s) % 4)
        WHEN 0 THEN 'BEGINNER'
        WHEN 1 THEN 'INTERMEDIATE'
        WHEN 2 THEN 'ADVANCED'
        ELSE 'EXPERT'
        END,

    CASE
        WHEN s IN (0, 1, 2) THEN TRUE
        ELSE FALSE
        END

FROM generate_series(0, 499) AS jobs(j)
         CROSS JOIN generate_series(0, 4) AS skills(s);


-- ============================================================
-- 15. JOB APPLICATIONS
-- 800
-- ============================================================

INSERT INTO job_applications (
    id,
    status,
    cover_letter,
    applied_at,
    candidate_id,
    job_posting_id
)
SELECT
    50 + ((candidate_offset * 4) + application_offset),

    CASE ((candidate_offset + application_offset) % 6)
        WHEN 0 THEN 'APPLIED'
        WHEN 1 THEN 'SCREENING'
        WHEN 2 THEN 'INTERVIEW'
        WHEN 3 THEN 'OFFER'
        WHEN 4 THEN 'REJECTED'
        ELSE 'APPROVED'
        END,

    CASE ((candidate_offset + application_offset) % 6)
        WHEN 0 THEN
            'I am excited to apply for this opportunity and believe my engineering experience aligns well with the role.'
        WHEN 1 THEN
            'My experience building scalable backend systems makes me a strong candidate for this position.'
        WHEN 2 THEN
            'I am particularly interested in this role because of the opportunity to solve challenging technical problems.'
        WHEN 3 THEN
            'I would be excited to contribute my experience in software development and system design.'
        WHEN 4 THEN
            'My background in Java, databases, and distributed systems would allow me to contribute effectively.'
        ELSE
            'I believe my technical background and problem-solving skills are a strong match for this opportunity.'
        END,

    TIMESTAMP '2026-02-01'
        + ((candidate_offset * 3 + application_offset * 11) % 180)
        * INTERVAL '1 day',

    50 + candidate_offset,

    50 + ((candidate_offset * 17 + application_offset * 31) % 500)

FROM generate_series(0, 199) AS candidates(candidate_offset)
         CROSS JOIN generate_series(0, 3) AS applications(application_offset);


-- ============================================================
-- 16. JOB ROUNDS
-- 1,600
-- ============================================================

INSERT INTO job_rounds (
    id,
    feedback,
    round_number,
    round_name,
    rating,
    "at",
    hr_id,
    job_id
)
SELECT
    50 + ((application_offset * 2) + round_offset),

    CASE round_offset
        WHEN 0 THEN
            'Candidate demonstrated a good understanding of fundamentals. Communication was clear and the approach was structured.'
        ELSE
            'Candidate handled the technical discussion well and demonstrated reasonable problem-solving ability.'
        END,

    round_offset + 1,

    CASE round_offset
        WHEN 0 THEN 'Technical Screening'
        ELSE 'Technical Interview'
        END,

    5 + ((application_offset + round_offset) % 6),

    TIMESTAMP '2026-03-01'
        + ((application_offset + round_offset * 7) % 200)
        * INTERVAL '1 day',

    50 + (application_offset % 200),

    50 + application_offset

FROM generate_series(0, 799) AS applications(application_offset)
         CROSS JOIN generate_series(0, 1) AS rounds(round_offset);


-- ============================================================
-- 17. COMPANY REVIEWS
-- 200
-- ============================================================

INSERT INTO company_reviews (
    id,
    text,
    stars,
    candidate_id,
    company_id,
    created_at
)
SELECT
    50 + n,

    CASE n % 10
        WHEN 0 THEN
            'Great engineering culture and helpful senior developers.'
        WHEN 1 THEN
            'Good learning opportunities and interesting technical challenges.'
        WHEN 2 THEN
            'The interview process was structured and professional.'
        WHEN 3 THEN
            'Strong team collaboration and good exposure to modern technologies.'
        WHEN 4 THEN
            'The company provides opportunities to work on meaningful projects.'
        WHEN 5 THEN
            'Supportive management and a healthy learning environment.'
        WHEN 6 THEN
            'The technical team is strong and the projects are challenging.'
        WHEN 7 THEN
            'Good opportunity for engineers who want to work on scalable systems.'
        WHEN 8 THEN
            'The hiring process was transparent and communication was good.'
        ELSE
            'Overall a positive experience with good growth opportunities.'
        END,

    1 + (n % 5),

    50 + (n % 200),

    50 + (n % 25),

    TIMESTAMP '2026-01-01'
        + (n * INTERVAL '1 day')

FROM generate_series(0, 199) AS reviews(n);


-- ============================================================
-- 18. COMMIT
-- ============================================================

COMMIT;


-- ============================================================
-- 19. FIX SEQUENCES
-- ============================================================

SELECT setval('company_seq', 100, true);
SELECT setval('company_review_seq', 250, true);
SELECT setval('hiring_department_seq', 150, true);
SELECT setval('hr_seq', 250, true);
SELECT setval('job_posting_seq', 550, true);
SELECT setval('role_seq', 100, true);
SELECT setval('job_round_seq', 1650, true);
SELECT setval('job_application_seq', 850, true);
SELECT setval('skill_seq', 100, true);
SELECT setval('candidate_seq', 250, true);
SELECT setval('resumes_seq', 250, true);
SELECT setval('candidate_exp_seq', 450, true);
SELECT setval('industry_seq', 100, true);


-- ============================================================
-- 20. VERIFY COUNTS
-- ============================================================

SELECT 'industries' AS table_name, COUNT(*) AS row_count
FROM industries

UNION ALL

SELECT 'roles', COUNT(*)
FROM roles

UNION ALL

SELECT 'skills', COUNT(*)
FROM skills

UNION ALL

SELECT 'companies', COUNT(*)
FROM companies

UNION ALL

SELECT 'hiring_departments', COUNT(*)
FROM hiring_departments

UNION ALL

SELECT 'hiring_managers', COUNT(*)
FROM hiring_managers

UNION ALL

SELECT 'candidates', COUNT(*)
FROM candidates

UNION ALL

SELECT 'candidate_skills', COUNT(*)
FROM candidate_skills

UNION ALL

SELECT 'candidate_experiences', COUNT(*)
FROM candidate_experiences

UNION ALL

SELECT 'resumes', COUNT(*)
FROM resumes

UNION ALL

SELECT 'job_postings', COUNT(*)
FROM job_postings

UNION ALL

SELECT 'job_skills', COUNT(*)
FROM job_skills

UNION ALL

SELECT 'job_applications', COUNT(*)
FROM job_applications

UNION ALL

SELECT 'job_rounds', COUNT(*)
FROM job_rounds

UNION ALL

SELECT 'company_reviews', COUNT(*)
FROM company_reviews

ORDER BY table_name;