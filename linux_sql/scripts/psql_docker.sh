# script usage 
# ./scripts/psql_docker.sh start|stop [password for database]

if (($#>2));
then
	echo "You have entered too many arguments!"
	echo "Format: psql_docker.sh start [password] OR psql_docker.sh stop"
	exit 1
fi

if [ "$1" == "start" ];
then
	systemctl status docker || systemctl start docker
	docker run --rm --name jrvs-psql -e POSTGRES_PASSWORD=$2 -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres
	psql -h localhost -U postgres -W
elif [ "$1" == "stop" ];
then
	systemctl stop docker
else
	echo "The arguments you entered is wrong"
	echo "Format: psql_docker.sh start [password] OR psql_docker.sh stop"
	exit 1
fi

exit 0
