--Q1--
SELECT 
  cpu_number, id AS host_id, total_mem
FROM host_info
ORDER BY
  cpu_number ASC,
  total_mem DESC;


--Q2--

CREATE TEMP TABLE temp 
AS
select info.id as host_id, info.hostname as host_name, 
(info.total_mem * 1.0 - usage.memory_free * 1024.0) / info.total_mem * 100 AS memory_usage, 
info.total_mem, usage.timestamp AS timestamp
FROM
host_info AS info 
RIGHT OUTER JOIN 
host_usage AS usage 
ON info.id=usage.host_id;

ALTER TABLE temp
ADD COLUMN time_interval timestamp;

UPDATE temp
SET time_interval = date_trunc('hour', timestamp) + INTERVAL '5 min' * 
ROUND( date_part('minute', timestamp) / 5.0);

CREATE TEMP TABLE temp_5min 
AS
SELECT host_id, host_name, 
AVG(memory_usage) over (partition by time_interval) as average_usage,
time_interval, total_mem
FROM temp;

SELECT host_id, host_name, total_mem, AVG(average_usage)
FROM temp_5min 
GROUP BY host_id, host_name, total_mem;





