import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
    selector: 'adv-payment-modal',
    templateUrl: './payment-modal.component.html',
    styleUrls: ['./payment-modal.component.scss'],
})
export class PaymentModalComponent implements OnInit {
    constructor(private dialogRef: MatDialogRef<PaymentModalComponent>) {}

    ngOnInit(): void {}

    close() {
        this.dialogRef.close();
    }
}
