#!/bin/bash

if (($#!=5));
then
	echo "WARNING: The number of arguments is not correct... Please enter exact 5 arguments.
Format: host_info.sh host_name port_number database_name user_name password >&2
	exit 1

# collecting infomation
lscpu_out=$(lscpu)
hostName=$(hostname -f)
cpu_number=$(echo "$lscpu_out" | egrep "^CPU\(s\):" | awk '{print $2}')
cpu_architecture=$(echo "$lscpu_out" | egrep "^Architecture:" | awk '{print $2}')
cpu_model=$(echo "$lscpu_out" | egrep "^Model\sname:" | awk '{$1=$2=""; print $0}')
cpu_mhz=$(echo "$lscpu_out" | egrep "^CPU\sMHz:" | awk '{print $NF}')
L2_cache=$(echo "$lscpu_out" | egrep "^L2\scache:" | awk '{print $3}' | grep -o '[0-9]\+')
total_mem=$(cat /proc/meminfo | egrep "^MemTotal:" | awk '{print $2}')
timestamp=$(date '+%Y-%m-%d %H:%M:%S.%3N')

# insert the information collected into the table created
psql -h $1 -U $4 -w $3 -p $2 -c \
	"INSERT INTO host_info
	 (hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, l2_cache,
	  timestamp, total_mem)
	 VALUES ('$hostName',$cpu_number,'$cpu_architecture','$cpu_model',  
		$cpu_mhz, $L2_cache, '$timestamp', $total_mem);"

exit 0

