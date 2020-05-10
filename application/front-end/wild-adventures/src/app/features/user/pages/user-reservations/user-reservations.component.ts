import { Component, OnInit } from '@angular/core';
import { UserHttpService } from '../../services/http/user.http.service';
import { ReservationDTO, InvoiceDTO } from '../../models/user-models';

@Component({
    selector: 'adv-user-reservations',
    templateUrl: './user-reservations.component.html',
    styleUrls: ['./user-reservations.component.scss'],
})
export class UserReservationsComponent implements OnInit {
    recipes: InvoiceDTO[] = [];
    isLoading = false;

    constructor(private httpService: UserHttpService) {}

    async initReservations() {
        this.isLoading = true;
        try {
            const reservations: ReservationDTO[] = (await this.httpService.getUserReservations().toPromise()) || [];
            this.recipes = await Promise.all(
                reservations
                    .filter((reservation) => reservation.payed)
                    .map((reservation) => this.httpService.getUserRecipe(reservation.id.adventureId).toPromise())
            );
        } catch (e) {}
        this.isLoading = false;
    }

    ngOnInit() {
        this.initReservations();
    }
}
