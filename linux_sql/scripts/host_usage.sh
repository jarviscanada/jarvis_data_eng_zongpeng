#!/bin/bash

host_name=$1
port_number=$2
database_name=$3
user_name=$4
password=$5

if (($#!=5));
then
	echo "WARNING: The number of arguments is not correct... Please enter exact 5 arguments.
Format: host_usage.sh host_name port_number database_name user_name password >&2
	exit 1
fi

# collect information for CPU, memory, disk usage
#another way: timestamp=$(vmstat -t | sed -n 3p | awk '{print $(NF-1)," ",$NF}')
timestamp=$(date '+%Y-%m-%d %H:%M:%S.%3N')
memory_free=$(vmstat --unit M | awk '{for(i=NF;i>0;i--)if($i=="free"){x=i;break}}END{print $x}')
cpu_idel=$(vmstat --unit M | awk '{for(i=NF;i>0;i--)if($i=="id"){x=i;break}}END{print $x}')
cpu_kernel=$(vmstat --unit M | awk '{for(i=NF;i>0;i--)if($i=="sy"){x=i;break}}END{print $x}')
disk_io=$(vmstat -D | egrep "inprogress\sIO" | awk '{print $1}')
disk_available=$(df -BM / | awk '{for(i=NF;i>0;i--)if($i=="Available"){x=i;break}}END{print $x}' | grep -o "[0-9]\+")
hostName=$(hostname -f)

# insert collected information into the table created
# host_id is from host_info table determined by the hostname
psql -h $host_name -U $user_name -w $database_name -p $port_number -c \
	"INSERT INTO host_usage
	 (timestamp, host_id, memory_free, cpu_idel, cpu_kernel, disk_io,
	  disk_available)
	 VALUES ('$timestamp', 
 		(SELECT id from host_info where hostname='$hostName'),
		$memory_free, $cpu_idel, $cpu_kernel, $disk_io, 
		$disk_available);"

exit 0
