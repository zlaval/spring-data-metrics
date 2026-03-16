-- remove old stats
SELECT pg_stat_reset();


SELECT
    *
FROM pg_stat_user_tables
WHERE relname = 'users';

SELECT
    relname AS table_name,
    pg_size_pretty(pg_table_size(C.oid)) AS table_size,
    pg_size_pretty(pg_indexes_size(C.oid)) AS index_size,
    pg_size_pretty(pg_total_relation_size(C.oid)) AS total_size
FROM pg_class C
         LEFT JOIN pg_namespace N ON (N.oid = C.relnamespace)
WHERE nspname NOT IN ('pg_catalog', 'information_schema')
  AND relkind = 'r'
  AND relname = 'users';

SELECT
    relname AS table_name,
    heap_blks_read AS heap_disk_read,
    heap_blks_hit AS heap_buffer_hit,
    (heap_blks_hit::float / NULLIF(heap_blks_hit + heap_blks_read, 0)) * 100 AS heap_hit_ratio_pct,
    idx_blks_read AS index_disk_read,
    idx_blks_hit AS index_buffer_hit,
    (idx_blks_hit::float / NULLIF(idx_blks_hit + idx_blks_read, 0)) * 100 AS index_hit_rate_pct,
    toast_blks_read AS toast_disk_read
FROM pg_statio_user_tables
WHERE relname = 'users';



SELECT
    datname AS db_name,
    xact_commit AS sikeres_tranzakciok,
    xact_rollback AS hiba_miatti_visszagorditesek,
    -- Az összesített idő (ms), amit a Postgres válaszra fordított
    round(blk_read_time::numeric, 2) AS osszes_lemezrol_olvasasi_ido_ms,
    round(blk_write_time::numeric, 2) AS osszes_lemezre_irasi_ido_ms,
    -- Kapcsolatok száma a teszt végén
    numbackends AS aktualis_kapcsolatok
FROM pg_stat_database
WHERE datname = 'postgres';

SELECT
    relname,
    heap_blks_hit AS postgres_cache_hit,
    heap_blks_read AS os_cache_or_disk_read
FROM pg_statio_user_tables
WHERE relname = 'users';

SELECT
    relname AS index_name,
    pg_size_pretty(pg_relation_size(indexrelid)) AS index_size,
    idx_blks_read AS disk_reads,
    idx_blks_hit AS buffer_hits,
    round(idx_blks_hit::numeric / (idx_blks_hit + idx_blks_read + 1) * 100, 2) AS hit_rate_pct
FROM pg_statio_user_indexes
WHERE schemaname = 'public';