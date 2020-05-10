import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';

import { KeycloakService } from 'src/app/shared/services/keycloak/keycloak.service';
import { CartContentService } from 'src/app/shared/services/cart-content/cart-content.service';
import { AdventuresHttpService } from '../../services/http/adventures.http.service';

import { PaymentModalComponent } from '../payment-modal/payment-modal.component';

import { AdventureDTO } from '../../models/adventure-models';
import { ReservationDTO, ReservationInfosDTO } from 'src/app/shared/models/dto/reservations';

@Component({
    selector: 'adv-reservation-infos',
    templateUrl: './reservation-infos.component.html',
    styleUrls: ['./reservation-infos.component.scss'],
})
export class ReservationInfosComponent implements OnInit {
    private existingReservation: ReservationDTO;

    // tslint:disable-next-line: variable-name
    private _adventure: AdventureDTO;
    reservationInfos: ReservationInfosDTO;
    @Input() set adventure(adventure: AdventureDTO) {
        this._adventure = adventure;
        this.initReservationInfos();
    }

    public isLogged = false;
    public isLoading = false;
    public reservationNbOptions: number[];
    public reservationForm: FormGroup;

    get otherParticipantNb(): number {
        if (this.reservationInfos) {
            const { currentUserReservationInfo: userInfos } = this.reservationInfos;

            return this.reservationInfos.actualReservationNumber - (userInfos ? userInfos.reservationsNb : 0);
        }
        return 0;
    }
    get participantPercent(): number {
        if (this._adventure && this.reservationInfos && this._adventure.maxParticipantNumber) {
            return (100 * this.otherParticipantNb) / this._adventure.maxParticipantNumber;
        }
        return 0;
    }
    get placeLeftNb(): number {
        if (this._adventure && this.reservationInfos) {
            return this._adventure.maxParticipantNumber - this.reservationInfos.actualReservationNumber;
        }
        return 0;
    }
    get participantBufferPercent(): number {
        if (this._adventure && this.reservationInfos && this._adventure) {
            return (
                (100 * (this.otherParticipantNb + this.reservationForm.get('reservationsNb').value)) /
                this._adventure.maxParticipantNumber
            );
        }
        return 0;
    }
    get isReservationInCart() {
        return !!this.existingReservation;
    }
    get isAdventureOver() {
        if (this._adventure?.startDate) {
            return new Date(this._adventure.startDate) < new Date();
        }
        return true;
    }

    constructor(
        private fb: FormBuilder,
        private dialog: MatDialog,
        private keycloakService: KeycloakService,
        private httpService: AdventuresHttpService,
        private cartService: CartContentService
    ) {
        this.reservationForm = this.fb.group({
            reservationsNb: this.fb.control(0, [Validators.required, Validators.min(1)]),
        });
    }

    async ngOnInit() {
        this.isLogged = await this.keycloakService.isLoggedIn();
    }

    async initReservationInfos() {
        this.reservationInfos = await this.httpService.getAdventureReservationsInfos(this._adventure.id).toPromise();
        const reservationNbField = this.reservationForm.get('reservationsNb');
        let userReservationNb = 0;

        if (this.reservationInfos.currentUserReservationInfo) {
            userReservationNb = this.reservationInfos.currentUserReservationInfo.reservationsNb || 0;
            reservationNbField.patchValue(userReservationNb);
        } else {
            reservationNbField.patchValue(0);
        }

        const max = this.placeLeftNb + userReservationNb;
        reservationNbField.clearValidators();
        reservationNbField.setValidators([Validators.required, Validators.min(1), Validators.max(max)]);
        this.reservationNbOptions = new Array(max).fill(0).map((_, i) => i + 1);
    }

    async onSubmitReservation() {
        this.isLoading = true;
        let reservation: ReservationDTO;

        if (this.reservationInfos?.currentUserReservationInfo) {
            reservation = await this.httpService
                .updateReservation(this._adventure.id, this.reservationForm.value)
                .toPromise()
                .finally(() => {
                    this.isLoading = false;
                });
        } else {
            reservation = await this.httpService
                .createReservation(this._adventure.id, this.reservationForm.value)
                .toPromise()
                .finally(() => {
                    this.isLoading = false;
                });
        }
        this.cartService.update();
        this.initReservationInfos();
        this.dialog.open(PaymentModalComponent, {
            data: { ...this.reservationForm.value },
        });
    }
}
