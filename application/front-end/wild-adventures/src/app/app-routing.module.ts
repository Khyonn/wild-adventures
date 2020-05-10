import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NotFoundComponent } from './shared/modules/layout/pages/not-found/not-found.component';

const routes: Routes = [
    {
        path: '',
        pathMatch: 'full',
        redirectTo: '/adventures',
    },
    {
        path: 'adventures',
        loadChildren: () => import('./features/adventures/adventures.module').then((i) => i.AdventuresModule),
    },
    {
        path: 'user',
        loadChildren: () => import('./features/user/user.module').then((i) => i.UserModule),
    },
    {
        path: 'cart',
        loadChildren: () => import('./features/cart/cart.module').then((i) => i.CartModule),
    },
    {
        path: '**',
        component: NotFoundComponent,
    },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
})
export class AppRoutingModule {}
