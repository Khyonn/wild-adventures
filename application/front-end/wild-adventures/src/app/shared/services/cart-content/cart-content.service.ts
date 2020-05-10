import { Injectable, EventEmitter } from '@angular/core';
import { ReservationDTO } from '../../models/dto/reservations';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
    providedIn: 'root',
})
export class CartContentService {
    private readonly MS_RESERVATIONS = `api/${environment.microservices.reservations}`;
    // tslint:disable-next-line: variable-name
    private _reservations: ReservationDTO[] = [];
    public reservationsUpdated = new EventEmitter<ReservationDTO[]>();

    constructor(private http: HttpClient) {}

    get reservations() {
        return JSON.parse(JSON.stringify(this._reservations)) as ReservationDTO[];
    }

    async update() {
        this._reservations = (
            (await this.http.get<ReservationDTO[]>(`${this.MS_RESERVATIONS}/user-reservations`).toPromise()) || []
        ).filter((reservation) => !reservation.payed);
        this.reservationsUpdated.emit(this.reservations);
    }
}
