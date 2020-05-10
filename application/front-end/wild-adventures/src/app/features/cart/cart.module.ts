import { NgModule } from '@angular/core';

import { CartRoutingModule } from './cart-routing.module';
import { CartComponent } from './pages/cart/cart.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { StripeModule } from 'stripe-angular';
import { PaymentModalComponent } from './components/payment-modal/payment-modal.component';

@NgModule({
    declarations: [CartComponent, PaymentModalComponent],
    imports: [SharedModule, CartRoutingModule, StripeModule],
})
export class CartModule {}
