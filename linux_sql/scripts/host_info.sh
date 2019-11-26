#!/bin/bash

lscpu_out="$lscpu"
id=1
hostname=$(hostname -f)
cpu_number=$(echo "$lscpu_out" | egrep "^CPU\(s\):" | awk '{print $2}')
cpu_architecture=$(echo "$lscpu_out" | egrep "^Architecture:" | awk '{print $2}')
cpu_model=$(echo "$lscpu_out" | egrep "^Model\sname:" | awk '{$1=$2=""; print $0}')
cpu_mhz=$(echo "$lscpu_out" | egrep "^CPU\sMhz:" | awk '{print $2}')
L2_cache=$(echo "$lscpu_out" | egrep "^L2\scache:" | awk '{print $3}' | grep -o '[0-9]\+')
total_mem=$(cat /proc/meminfo | egrep "^MemTotal:" | awk '{print $2}')
timestamp=$(date '+%Y-%m-%d %H:%M:%S')

exit 0

