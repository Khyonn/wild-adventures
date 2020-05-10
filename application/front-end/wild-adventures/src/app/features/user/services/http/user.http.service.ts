import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ReservationDTO, InvoiceDTO } from '../../models/user-models';
import { environment } from 'src/environments/environment';

@Injectable({
    providedIn: 'root',
})
export class UserHttpService {
    private readonly MS_RESERVATIONS = `api/${environment.microservices.reservations}`;
    constructor(private http: HttpClient) {}

    getUserReservations() {
        return this.http.get<ReservationDTO[]>(`${this.MS_RESERVATIONS}/user-reservations`);
    }

    getUserRecipe(adventureId) {
        return this.http.get<InvoiceDTO>(`${this.MS_RESERVATIONS}/user-reservations/${adventureId}/invoice`);
    }
}
