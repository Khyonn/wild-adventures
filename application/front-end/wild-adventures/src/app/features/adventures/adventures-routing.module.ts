import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdventuresListPageComponent } from './pages/adventures-list-page/adventures-list-page.component';
import { AdventureDetailPageComponent } from './pages/adventure-detail-page/adventure-detail-page.component';
import { RoleGuard, Verification } from 'src/app/shared/guards/guard';
import { NotFoundComponent } from 'src/app/shared/modules/layout/pages/not-found/not-found.component';

const routes: Routes = [
    {
        path: '',
        component: AdventuresListPageComponent,
    },
    {
        path: ':adventureId',
        component: AdventureDetailPageComponent,
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
export class AdventuresRoutingModule {}
