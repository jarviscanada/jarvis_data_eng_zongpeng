#!/bin/bash

# collect information for CPU, memory, disk usage
#another way: timestamp=$(vmstat -t | sed -n 3p | awk '{print $(NF-1)," ",$NF}')
timestamp=$(date '+%Y-%m-%d %H:%M:%S.%3N')
memory_free=$(vmstat --unit M | awk '{for(i=NF;i>0;i--)if($i=="free"){x=i;break}}END{print $x}')
cpu_idel=$(vmstat --unit M | awk '{for(i=NF;i>0;i--)if($i=="id"){x=i;break}}END{print $x}')
cpu_kernel=$(vmstat --unit M | awk '{for(i=NF;i>0;i--)if($i=="sy"){x=i;break}}END{print $x}')
disk_io=$(vmstat -D | egrep "inprogress\sIO" | awk '{print $1}')
disk_available=$(df -BM / | awk '{for(i=NF;i>0;i--)if($i=="Available"){x=i;break}}END{print $x}' | grep -o "[0-9]\+")
host_name=$(hostname -f)

# insert collected information into the table created
# host_id is from host_info table determined by the hostname
psql -h $1 -U $4 -w $3 -p $2 -c \
	"INSERT INTO host_usage
	 (timestamp, host_id, memory_free, cpu_idel, cpu_kernel, disk_io,
	  disk_available)
	 VALUES ('$timestamp', 
 		(SELECT id from host_info where hostname='$host_name'),
		$memory_free, $cpu_idel, $cpu_kernel, $disk_io, 
		$disk_available);"

exit 0
