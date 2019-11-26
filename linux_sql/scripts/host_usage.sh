#!/bin/bash

#another way: timestamp=$(vmstat -t | sed -n 3p | awk '{print $(NF-1)," ",$NF}')
timestamp=$(date '+%Y-%m-%d %H:%M:%S')
host_id=1
memory_free=$(vmstat --unit M | awk '{for(i=NF;i>0;i--)if($i=="free"){x=i;break}}END{print $x}')
cpu_idel=$(vmstat --unit M | awk '{for(i=NF;i>0;i--)if($i=="id"){x=i;break}}END{print $x}')
cpu_kernel==$(vmstat --unit M | awk '{for(i=NF;i>0;i--)if($i=="sy"){x=i;break}}END{print $x}')
disk_io=$(vmstat -D | egrep "inprogress\sIO" | awk '{print $1}')
disk_available=$(df -BM / | awk '{for(i=NF;i>0;i--)if($i=="Available"){x=i;break}}END{print $x}' | grep -o "[0-9]\+")

exit 0
