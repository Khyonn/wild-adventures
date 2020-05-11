# WildAdventures

Ce projet a été initialisé avec [Angular CLI](https://github.com/angular/angular-cli) version 9.0.7. Il fourni une interface graphique pour l'application et se base sur `ms-gateway` (pour accéder aux différents microservices) et `keycloak` pour s'authentifier.

## Lancement

Vous pouvez lancer le serveur de développement avec cette commande
```
$ npm install && npm start
```

## Build

En amont, il faut éditer le fichier `environments/environment.prod.ts` afin d'y renseigner les informations pour s'interfacer à `keycloak` et Stripe

Vous pouvez lancer la compilation
```
$ npm install && npm run build
```

Pour déployer la solution, il suffit de prendre les éléments du dossier `dist`. Il faudra de plus rediriger toutes requêtes depuis `/api` vers l'url du gateway
