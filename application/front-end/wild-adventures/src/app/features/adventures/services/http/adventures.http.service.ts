import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import {
    AdventureDTO,
    AdventureLightDTO,
    CommentDTO,
    ReservationInfosDTO,
    ReservationDTO,
} from '../../models/adventure-models';

@Injectable({
    providedIn: 'root',
})
export class AdventuresHttpService {
    private readonly MS_ADVENTURES = `api/${environment.microservices.adventures}`;
    private readonly MS_COMMENTS = `api/${environment.microservices.comments}`;
    private readonly MS_RESERVATIONS = `api/${environment.microservices.reservations}`;
    constructor(private http: HttpClient) {}

    // #region ===== AVENTURES

    getAdventureList(): Observable<AdventureLightDTO[]> {
        const today = new Date();
        return this.http
            .get<AdventureLightDTO[]>(`${this.MS_ADVENTURES}/aventures`)
            .pipe(
                map((adventures) =>
                    adventures.filter((adventure) => new Date(adventure.startDate).getTime() > today.getTime())
                )
            );
    }

    getAdventureById(id: number): Observable<AdventureDTO> {
        return this.http.get<AdventureDTO>(`${this.MS_ADVENTURES}/aventures/${id}`);
    }

    // #endregion

    // #region ===== COMMENTS

    getCommentsByAdventureId(adventureId: number): Observable<CommentDTO[]> {
        return this.http.get<CommentDTO[]>(`${this.MS_COMMENTS}/adventures/${adventureId}/comments`).pipe(
            map((comments) => {
                comments.forEach((comment) => (comment.date = new Date(comment.date)));
                return comments;
            })
        );
    }

    writeComment(adventureId: number, comment: CommentDTO) {
        return this.http.post<CommentDTO>(`${this.MS_COMMENTS}/adventures/${adventureId}/comments`, comment);
    }

    deleteComment(adventureId: number, commentId: number) {
        return this.http.delete(`${this.MS_COMMENTS}/adventures/${adventureId}/comments/${commentId}`);
    }

    // #endregion

    getAdventureReservationsInfos(adventureId: number) {
        return this.http.get<ReservationInfosDTO>(
            `${this.MS_RESERVATIONS}/adventures/${adventureId}/reservations/info`
        );
    }

    createReservation(adventureId: number, reservation: ReservationDTO) {
        return this.http.post<ReservationDTO>(
            `${this.MS_RESERVATIONS}/adventures/${adventureId}/reservations`,
            reservation
        );
    }

    updateReservation(adventureId: number, reservation: ReservationDTO) {
        return this.http.put<ReservationDTO>(
            `${this.MS_RESERVATIONS}/adventures/${adventureId}/reservations`,
            reservation
        );
    }
}
