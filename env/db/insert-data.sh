docker exec -i postgres_perf_test psql -U admin -d perf_db < create-table.sql
docker exec -i postgres_perf_test psql -U admin -d perf_db < insert-data.sql
docker exec -it postgres_perf_test psql -U admin -d perf_db -c "SELECT pg_size_pretty(pg_total_relation_size('users'));"