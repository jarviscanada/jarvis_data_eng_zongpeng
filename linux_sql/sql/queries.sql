--Q1--
SELECT 
  cpu_number, id AS host_id, total_mem
FROM host_info
ORDER BY
  cpu_number ASC,
  total_mem DESC;


--Q2--
--create temporary table to sort calculated values
select 
	host_usage.host_id as host_id, 
	host_info.host_name as host_name, 
	host_info.total_mem as total_memory, 
	avg(
		((host_info.total_mem - host.usage.memory_free) / host_info.total_mem) * 100) 
		as used_memory_percentage 
from 	
	host_usage 
	inner join host_info on host_usage.host_id=host_info.id 
group by
	host_id, 
	host_name, 
	total_memory, 
	DATE_TRUNC('hour', host_usage.timestamp) + DATE_PART('minute', host_usage.timestamp)::int / 5 * interval '5 min' as time 
order by 
	host_usage.host_id;




