import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { StripeToken, StripeSource } from 'stripe-angular';
import { AdventureReservation } from '../../models/cart-models';
import { FormBuilder } from '@angular/forms';
import { CartHttpService } from '../../services/http/cart.http.service';

@Component({
    selector: 'adv-payment-modal',
    templateUrl: './payment-modal.component.html',
    styleUrls: ['./payment-modal.component.scss'],
})
export class PaymentModalComponent implements OnInit {
    constructor(
        private httpService: CartHttpService,
        private fb: FormBuilder,
        private dialogRef: MatDialogRef<PaymentModalComponent>,
        @Inject(MAT_DIALOG_DATA) public reservation: AdventureReservation
    ) {}

    form = this.fb.group({});
    invalidError = null;
    currentlyPaying: boolean;

    options = {
        hidePostalCode: true,
    };

    ngOnInit(): void {}

    onStripeInvalid(error: Error) {
        console.log('Validation Error', error);
    }

    setStripeToken(token: StripeToken) {
        console.log('Stripe token', token);
    }

    setStripeSource(source: StripeSource) {
        console.log('Stripe source', source);
    }

    onStripeError(error: Error) {
        console.error('Stripe error', error);
    }

    async onSubmit(token: StripeToken) {
        this.currentlyPaying = true;
        try {
            await this.httpService.payment(this.reservation.id.adventureId, token.id).toPromise();
            this.dialogRef.close(true);
        } catch (e) {}
        this.currentlyPaying = false;
    }
}
