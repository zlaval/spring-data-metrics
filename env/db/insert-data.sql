INSERT INTO users (name, birth_date, description)
SELECT
    'User_' || i AS name,
    (CURRENT_DATE - INTERVAL '18 years' - (random() * INTERVAL '50 years'))::DATE AS birth_date,
    (md5(random()::text) || md5(random()::text) || md5(random()::text) || md5(random()::text) || md5(random()::text) || md5(random()::text) || md5(random()::text))::text AS description
FROM generate_series(1, 10000000) s(i);

ANALYZE users;