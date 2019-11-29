# script usage 
# ./scripts/psql_docker.sh start|stop [password for database]

dockerrun () {
	docker run --name jrvs-psql -e POSTGRES_PASSWORD=$2 -d -v \
	pgdata:/var/lib/postgresql/data -p 5432:5432 postgres
	exit 0
}


if (($#>2));
then
	echo "WARNING: You have entered too many arguments...
Format: psql_docker.sh start [password] OR psql_docker.sh stop" >&2
	exit 1
elif [[ "$1" == "start" && "$#" == 1 ]];
then
	echo "WARNING: You did not enter password..." >&2
	exit 1
fi


if [ "$1" == "start" ];
then
	systemctl status docker || systemctl start docker
	if [[ "$(docker ps -f name=jrvs-psql | wc -l)" == 2 ]]
	then
		exit 0;
	fi

	if [[ "$(docker volume ls | egrep "pgdata1$" | wc -l)" == 0 ]];
	then
		if [[ "$(docker ps -a -f name=jrvs-psql | wc -l)" == 2 ]];
		then
			docker stop jrvs-psql
			docker container rm jrvs-psql
		fi
		docker volume create pgdata
		dockerrun
	elif [[ "$(docker container ls -a | egrep "jrvs-psql$" | wc -l)" == 0 ]];
	then	
		dockerrun
	else
		docker start jrvs-psql
		exit 0
	fi
elif [[ "$1" == "stop" && "$#" == 1 ]];
then
	systemctl status docker && docker stop jrvs-psql
	systemctl status docker && systemctl stop docker
	exit 0
else
	echo "WARNING: The arguments you entered is wrong... 
Format: psql_docker.sh start [password] OR psql_docker.sh stop" >&2
	exit 1
fi

exit 0
