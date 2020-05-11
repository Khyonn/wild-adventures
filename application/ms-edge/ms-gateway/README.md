# ms-gateway

Ce microservice edge fourni un seul point d'entré à l'application. Ce microservice s'appuie sur `ms-config` afin de récupérer sa configuration et `ms-registry` pour s'enregistrer et pouvoir faire proxy aux autres microservices.

## Configuration

La configuration se passe en deux temps :
1) Il faut éditer le fichier [bootstrap.yml](./ms-gateway/src/main/resources/bootstrap.yml) et modifier la propriété `spring.cloud.config.uri` (l'uri du serveur `ms-config`)  
*Vous pouvez aussi renseigner la variable d'environnement `EDGE_CONFIG_HOST`.*
2) Il faudra éditer le fichier de config [gateway.yml](https://github.com/Khyonn/wild-adventures-configs/blob/develop/gateway.yml)  
Vous pouvez notamment y modifier les propriétés suivantes :
- `server.port` : le port sur lequel se lance l'application
- `eureka.client.serviceUrl.defaultZone` : l'url pour s'enregistrer auprès du `ms-registry`
- `spring.zipkin.baseUrl` : l'url du serveur `zipkin` pour tracer les requêtes  
- Ainsi que les informations concernant les accès à `keycloak`

## Lancement

*Prérequis : il faut que `ms-config` et `ms-registry` soient lancés en amont, de même pour les serveurs `zipkin` et `keycloak`.*

Vous pouvez lancer l'application via la commande suivante :  
```
$ mvn springboot:run
```

## Compilation

Vous pouvez compiler l'application via la commande suivante :  
```
$ mvn install
```
Vous trouverez une archive java dans le dossier target que vous pourrrez lancer via
```
$ java -jar target/ms-gateway.0.0.1-SNAPSHOT.jar
```
