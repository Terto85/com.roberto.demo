docker build -t demoimage .
docker stop democontainer
docker rm democontainer
docker run -p 127.0.0.1:8080:8080 --name democontainer demoimage