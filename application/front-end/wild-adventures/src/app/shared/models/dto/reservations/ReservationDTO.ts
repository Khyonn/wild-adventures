import { ReservationIdDTO } from './ReservationIdDTO';

export class ReservationDTO {
    id: ReservationIdDTO;
    reservationsNb: number;
    reservationDate: Date;
    payed: boolean;
}
