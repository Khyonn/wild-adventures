import { Component, OnInit } from '@angular/core';
import { CartHttpService } from '../../services/http/cart.http.service';
import { ReservationDTO, AdventureDTO, AdventureReservation } from '../../models/cart-models';
import { CartContentService } from 'src/app/shared/services/cart-content/cart-content.service';
import { PaymentModalComponent } from '../../components/payment-modal/payment-modal.component';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';

@Component({
    selector: 'adv-cart',
    templateUrl: './cart.component.html',
    styleUrls: ['./cart.component.scss'],
})
export class CartComponent implements OnInit {
    reservations: AdventureReservation[] = [];
    isLoading: boolean;

    constructor(
        private httpService: CartHttpService,
        private cartService: CartContentService,
        private dialog: MatDialog,
        private router: Router
    ) {}

    async initReservations() {
        this.isLoading = true;
        const reservations: ReservationDTO[] = (await this.httpService.getUserReservations().toPromise()) || [];
        const adventures: AdventureDTO[] = (await this.httpService.getAdventures().toPromise()) || [];

        this.reservations = reservations
            .filter((reservation) => !reservation.payed)
            .map((reservation) => {
                const adventureRes = new AdventureReservation(reservation);
                const adventure = adventures.find((a) => a.id === reservation.id.adventureId);

                adventureRes.adventureName = adventure.name;
                adventureRes.adventureDescription = adventure.description;
                adventureRes.price = adventure.price * reservation.reservationsNb;
                return adventureRes;
            });
        this.isLoading = false;
    }

    ngOnInit() {
        this.initReservations();
    }

    onClickPayment(reservation: AdventureReservation) {
        this.dialog
            .open(PaymentModalComponent, {
                data: reservation,
            })
            .afterClosed()
            .subscribe((isPayed) => {
                if (isPayed) {
                    this.dialog
                        .open(PaymentOkComponent)
                        .afterClosed()
                        .subscribe(() => {
                            this.router.navigate(['/user/reservations']);
                        });
                        this.cartService.update();
                        this.initReservations();
                }
            });
    }

    async onDeleteReservation(reservation: AdventureReservation) {
        await this.httpService.deleteReservation(reservation.id.adventureId).toPromise();
        this.cartService.update();
        this.initReservations();
    }
}

@Component({
    selector: 'adv-cart',
    template: 'Paiement effectué avec succès',
})
class PaymentOkComponent {}
