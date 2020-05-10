import { NgModule } from '@angular/core';

import { UserRoutingModule } from './user-routing.module';
import { UserReservationsComponent } from './pages/user-reservations/user-reservations.component';
import { SharedModule } from 'src/app/shared/shared.module';

@NgModule({
    declarations: [UserReservationsComponent],
    imports: [SharedModule, UserRoutingModule],
})
export class UserModule {}
