# ms-config

Ce microservice edge permet de fournir une api pour récupérer les configurations depuis un repository git.  
Par défaut, et utilisé via docker-compose, ce microservice se lance sur le port 80 de son conteneur et se base sur le repository suivant : https://github.com/Khyonn/wild-adventures-configs.

## Configuration

Il est cependant possible de modifier le repository utilisé ou le port :
Il faut pour celà éditer le fichier [application.yml](./ms-config/src/main/resources/application.yml) et modifier les propriétés suivantes :  
- `server.port` : Le port de lancement du microservice 
- `spring.cloud.config.server.git.uri` : l'uri du repository git
- `spring.cloud.config.server.git.default-label` : la branche git à parser

*Si vous souhaitez modifier uniquement la configuration du git, vous pouvez ajouter les variables d'environnement suivantes : `GIT_CONFIG_URI` et `GIT_CONFIG_BRANCH`.*

## Lancement

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
$ java -jar target/ms-config.0.0.1-SNAPSHOT.jar
```
