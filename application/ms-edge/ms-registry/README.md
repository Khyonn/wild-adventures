# ms-registry

Ce microservice edge sert de registre. Il fourni aux autres microservices une API leur permettant de s'enregistrer. Il fourni, en outre, une interface d'où on peut constater le nombre d'instances pour chacun des microservices. Ce microservice s'appui sur `ms-config` afin de récupérer sa configuration

## Configuration

La configuration se passe en deux temps :
1) Il faut éditer le fichier [bootstrap.yml](./ms-registry/src/main/resources/bootstrap.yml) et modifier la propriété `spring.cloud.config.uri` (l'uri du serveur `ms-config`)  
*Vous pouvez aussi renseigner la variable d'environnement `EDGE_CONFIG_HOST`*
2) Il faudra éditer le fichier de config [registry.yml](https://github.com/Khyonn/wild-adventures-configs/blob/develop/registry.yml)  
Vous pouvez notamment y modifier la propriété `server.port`

## Lancement

*Prérequis : il faut que ms-config soit lancé en amont*

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
$ java -jar target/ms-registry.0.0.1-SNAPSHOT.jar
```
