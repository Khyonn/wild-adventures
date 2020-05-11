# ms-adventures

Ce microservice permet de récupérer des aventures. Ce microservice s'appuie sur `ms-config` afin de récupérer sa configuration, `ms-registry` pour s'enregistrer ainsi que sur une base de donnée.

## Configuration

La configuration se passe en deux temps :
1) Il faut éditer le fichier [bootstrap.yml](./ms-adventures/src/main/resources/bootstrap.yml) et modifier la propriété `spring.cloud.config.uri` (l'uri du serveur `ms-config`)  
*Vous pouvez aussi renseigner la variable d'environnement `CONFIG_HOST`.*
2) Il faudra éditer le fichier de config [adventures.yml](https://github.com/Khyonn/wild-adventures-configs/blob/develop/adventures.yml)  

Vous pouvez notamment y modifier les propriétés suivantes :
- `server.port` : le port sur lequel se lance l'application
- `eureka.client.serviceUrl.defaultZone` : l'url pour s'enregistrer auprès du `ms-registry`
- `spring.zipkin.baseUrl` : l'url du serveur `zipkin` pour tracer les requêtes  

Ainsi que les informations de connexion à la BDD
- `spring.datasource.url` : l'url pour accéder à la base de donnée. Elle se présente sous la forme 'jdbc:mysql://`hôte`:`port`/`database`'
- `spring.datasource.username` : le username
- `spring.datasource.password`: le mot de passe

## Lancement

*Prérequis : il faut que `ms-config` et `ms-registry` soient lancés en amont, de même pour le serveur `zipkin` et la base de données.  
Note : vous trouverez un script d'initialisation ici : [init.sql](/docker/adventures-db/init.sql)*

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
$ java -jar target/ms-adventures.0.0.1-SNAPSHOT.jar
```

## Conception

Pour une aventure, il y a plusieurs images.  
Une aventure est définie par son nom, une description, un prix et une date. Cette dernière est affecté à une catégorie  
Une image est définie par son url, un libellé et ses dimensions

*Schéma relationnel*

![database](/documentation/imgs/adventures/database.png)

*Diagramme de classes (entités)*

![entity](/documentation/imgs/adventures/entity.png)

*Diagramme de classes (representations / dto)*

![dto](/documentation/imgs/adventures/dto.png)
