win: docker run -d --name mongo-container -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=admin -e MONGO_INITDB_ROOT_PASSWORD=admin123 mongo:latest

linux: docker run -d \
--name mongo-container \
-p 27017:27017 \
-e MONGO_INITDB_ROOT_USERNAME=admin \
-e MONGO_INITDB_ROOT_PASSWORD=admin123 \
mongo:latest


***Visualizar datos en BDs***
docker exec -it mongo-container mongosh -u admin -p admin123
use todo_service_db
db.todo.find().pretty()