import { ReservationDTO } from 'src/app/shared/models/dto/reservations';

export class AdventureReservation extends ReservationDTO {
    adventureName: string;
    adventureDescription: string;
    price: number;

    constructor(reservation: ReservationDTO = null) {
        super();
        if (reservation) {
            Object.assign(this, reservation);
        }
    }
}
