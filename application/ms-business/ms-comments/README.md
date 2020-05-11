# ms-comments

Ce microservice permet de récupérer des commentaires sur une aventure, en rédiger et en supprimer. Ce microservice s'appuie sur `ms-config` afin de récupérer sa configuration, `ms-registry` pour s'enregistrer, sur `keycloak` afin de s'authentifier ainsi que sur une base de donnée.

## Configuration

La configuration se passe en deux temps :
1) Il faut éditer le fichier [bootstrap.yml](./ms-comments/src/main/resources/bootstrap.yml) et modifier la propriété `spring.cloud.config.uri` (l'uri du serveur `ms-config`)  
*Vous pouvez aussi renseigner la variable d'environnement `CONFIG_HOST`.*
2) Il faudra éditer le fichier de config [comments.yml](https://github.com/Khyonn/wild-adventures-configs/blob/develop/comments.yml)  

Vous pouvez notamment y modifier les propriétés suivantes :
- `server.port` : le port sur lequel se lance l'application
- `eureka.client.serviceUrl.defaultZone` : l'url pour s'enregistrer auprès du `ms-registry`
- `spring.zipkin.baseUrl` : l'url du serveur `zipkin` pour tracer les requêtes  

Les informations de connexion à la BDD
- `spring.datasource.url` : l'url pour accéder à la base de donnée. Elle se présente sous la forme 'jdbc:mysql://`hôte`:`port`/`database`'
- `spring.datasource.username` : le username
- `spring.datasource.password`: le mot de passe

Ainsi que les informations concernant les accès à `keycloak`

## Lancement

*Prérequis : il faut que `ms-config`, `ms-registry` et `ms-adventures` soient lancés en amont, de même pour les serveurs `zipkin` et `keycloak` et la base de données.  
Note : vous trouverez un script d'initialisation ici : [init.sql](/docker/comments-db/init.sql)*

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
$ java -jar target/ms-comments.0.0.1-SNAPSHOT.jar
```

## Conception

A une aventure peuvent être rattachés des commentaires. Ces commentaires sont écrits par des utilisateurs. Ces derniers peuvent en écrire plusieurs pour une même aventure. Les commentaires sont datés.

*Schéma relationnel*

![database](/documentation/imgs/comments/database.png)

*Diagramme de classes (entités)*

![entity](/documentation/imgs/comments/entity.png)

*Diagramme de classes (representations / dto)*

![dto](/documentation/imgs/comments/dto.png)
