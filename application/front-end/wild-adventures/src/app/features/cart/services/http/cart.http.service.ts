import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ReservationDTO, AdventureDTO } from '../../models/cart-models';
import { environment } from 'src/environments/environment';

@Injectable({
    providedIn: 'root',
})
export class CartHttpService {
    private readonly MS_ADVENTURES = `api/${environment.microservices.adventures}`;
    private readonly MS_RESERVATIONS = `api/${environment.microservices.reservations}`;
    constructor(private http: HttpClient) {}

    getUserReservations() {
        return this.http.get<ReservationDTO[]>(`${this.MS_RESERVATIONS}/user-reservations`);
    }

    getAdventures() {
        return this.http.get<AdventureDTO[]>(`${this.MS_ADVENTURES}/aventures`);
    }

    deleteReservation(adventureId: number) {
        return this.http.delete(`${this.MS_RESERVATIONS}/adventures/${adventureId}/reservations`);
    }

    payment(adventureId: number, token: string) {
        return this.http.post(`${this.MS_RESERVATIONS}/adventures/${adventureId}/reservations/payment`, token);
    }
}
