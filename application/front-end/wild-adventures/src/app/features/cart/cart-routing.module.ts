import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RoleGuard, Verification } from 'src/app/shared/guards/guard';
import { CartComponent } from './pages/cart/cart.component';

const routes: Routes = [
    {
        path: '',
        component: CartComponent,
        canActivate: [RoleGuard],
        data: { verification: new Verification(['user']) },
    },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class CartRoutingModule {}
