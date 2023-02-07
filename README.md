# MT MANAGER API


***

## Criação do Container do PostgreSQL

* Primeiramente, criar uma network dentro do Docker

```docker network create --driver bridge mtmanager-network```

* Em seguida, criar o container dentro da rede

```docker run --name mtmanager-db -p 5432:5432 --network mtmanager-network -d -e POSTGRES_DB=mt_manager_db -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=123456 postgres:13```

## Build da imagem e container da API

* Antes de fazer o build da imagem, é preciso fazer o build da aplicação, para gerar o .jar atualizado.

```./mvnw clean package```

* Em seguida, fazer o build da imagem

```docker image build mtmanager-api .```

* Para gerar o container, passar as variáveis de ambiente como parâmetro.

```docker run --name mtmanager-app -p 8080:8080 --network mtmanager-network -e DB_HOSTNAME=mt_manager_db -e DB_PORT=5432 mtmanager-api```
