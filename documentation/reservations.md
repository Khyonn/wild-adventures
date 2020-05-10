# reservations

Les aventures peuvent être réservées. Les réservations sont valides pour une courte période (cette période pourrait varier, c'est pourquoi les reservations sont datées). Un utilisateur peut tout à fait réserver pour plusieurs personnes. Une fois que ce dernier valide sa réservation, on enregistre une commande qui référence la réservation.  
Le nom de l'aventure, sa date ou son prix pouvant varier, on stocke les différentes informations à plat. De plus, on enregistre aussi une référence du paiement par stripe afin de pouvoir fournir toutes les informations si nécessaire

*Schéma relationnel*

![database](/documentation/imgs/reservations/database.png)

*Diagramme de classes (entités)*

![entity](/documentation/imgs/reservations/entity.png)

*Diagramme de classes (representations / dto)*

![dto](/documentation/imgs/reservations/dto.png)
