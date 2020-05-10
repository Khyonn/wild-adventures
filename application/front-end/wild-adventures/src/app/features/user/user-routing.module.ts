import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { UserReservationsComponent } from './pages/user-reservations/user-reservations.component';
import { NotFoundComponent } from 'src/app/shared/modules/layout/pages/not-found/not-found.component';
import { RoleGuard, Verification } from 'src/app/shared/guards/guard';

const routes: Routes = [
    {
        path: 'reservations',
        component: UserReservationsComponent,
        canActivate: [RoleGuard],
        data: { verification: new Verification(['user']) },
    },
    {
        path: '**',
        component: NotFoundComponent,
    },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class UserRoutingModule {}
