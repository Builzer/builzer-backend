INSERT INTO project_plan (plan_type, plan_price, plan_explanation)
VALUES
    ('LITE', 3300, 'LITE 이용권'),
    ('PRO', 9900, 'PRO 이용권');

INSERT INTO supported_language (language_type, language_version, language_provider, is_used)
VALUES
    ('JAVA', 11, 'OPEN_JDK', true),
    ('JAVA', 17, 'OPEN_JDK', true),
    ('JAVA', 21, 'OPEN_JDK', false);

INSERT INTO supported_database (database_type, database_version, is_used)
VALUES
    ('MYSQL', 5.0, true),
    ('MYSQL', 8.0, true);

INSERT INTO supported_server
    (server_name, server_v_cpu, server_memory, server_type, cloud_provider, dollar_price, credit_price, is_used)
VALUES
    ('T3_NANO', 1, '1GiB', 'SPOT', 'AWS', 1, 3, true),
    ('T3_MICRO', 2, '1GiB', 'ON_DEMAND', 'AWS', 3, 9, true);

INSERT INTO project (project_plan_id, project_name, project_status, project_domain_name, git_repository_name, is_private_git_repository)
VALUES
    (1, 'test project', 'CREATING', 'example.builzer.site', 'test-repo', false);