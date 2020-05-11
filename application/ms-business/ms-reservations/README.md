# ms-reservations

Ce microservice permet de récupérer, créer, modifier et supprimer une réservation et payer cette denière. Ce microservice s'appuie sur `ms-config` afin de récupérer sa configuration, `ms-registry` pour s'enregistrer, sur `keycloak` afin de s'authentifier ainsi que sur une base de donnée.

## Configuration

La configuration se passe en deux temps :
1) Il faut éditer le fichier [bootstrap.yml](./ms-reservations/src/main/resources/bootstrap.yml) et modifier la propriété `spring.cloud.config.uri` (l'uri du serveur `ms-config`)  
*Vous pouvez aussi renseigner la variable d'environnement `CONFIG_HOST`.*
2) Il faudra éditer le fichier de config [reservations.yml](https://github.com/Khyonn/wild-adventures-configs/blob/develop/reservations.yml)  

Vous pouvez notamment y modifier les propriétés suivantes :
- `server.port` : le port sur lequel se lance l'application
- `eureka.client.serviceUrl.defaultZone` : l'url pour s'enregistrer auprès du `ms-registry`
- `spring.zipkin.baseUrl` : l'url du serveur `zipkin` pour tracer les requêtes  

Les informations de connexion à la BDD
- `spring.datasource.url` : l'url pour accéder à la base de donnée. Elle se présente sous la forme 'jdbc:mysql://`hôte`:`port`/`database`'
- `spring.datasource.username` : le username
- `spring.datasource.password`: le mot de passe

Les informations pour l'api Stripe :
`wildadventures.reservations.stripe.apiKey` : la clé privée pour réaliser les paiements

Des règles métiers :
`wildadventures.reservations.business.reservationLimitDays` : le nombre de jour de validité d'une réservation

Ainsi que les informations concernant les accès à `keycloak`

## Lancement

*Prérequis : il faut que `ms-config`, `ms-registry` et `ms-adventures` soient lancés en amont, de même pour les serveurs `zipkin` et `keycloak` et la base de données.  
Note : vous trouverez un script d'initialisation ici : [init.sql](/docker/reservations-db/init.sql)*

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
$ java -jar target/ms-reservations.0.0.1-SNAPSHOT.jar
```

## Conception

Les aventures peuvent être réservées. Les réservations sont valides pour une courte période (cette période pourrait varier, c'est pourquoi les reservations sont datées). Un utilisateur peut tout à fait réserver pour plusieurs personnes. Une fois que ce dernier valide sa réservation, on enregistre une commande qui référence la réservation.  
Le nom de l'aventure, sa date ou son prix pouvant varier, on stocke les différentes informations à plat. De plus, on enregistre aussi une référence du paiement par stripe afin de pouvoir fournir toutes les informations si nécessaire

*Schéma relationnel*

![database](/documentation/imgs/reservations/database.png)

*Diagramme de classes (entités)*

![entity](/documentation/imgs/reservations/entity.png)

*Diagramme de classes (representations / dto)*

![dto](/documentation/imgs/reservations/dto.png)
