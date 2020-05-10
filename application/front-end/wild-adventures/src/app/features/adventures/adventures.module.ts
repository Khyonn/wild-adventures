import { NgModule } from '@angular/core';
import { SharedModule } from 'src/app/shared/shared.module';

// Routing
import { AdventuresRoutingModule } from './adventures-routing.module';
// Pages
import { AdventuresListPageComponent } from './pages/adventures-list-page/adventures-list-page.component';
import { AdventureDetailPageComponent } from './pages/adventure-detail-page/adventure-detail-page.component';
// Composants
import { AdventureCardComponent } from './components/adventure-card/adventure-card.component';
import { CommentsLayoutComponent } from './components/comments-layout/comments-layout.component';
import { CommentComponent } from './components/comment/comment.component';

import { AdventureCategoryPipe } from './pipes/adventure-categoy/adventure-category.pipe';
import { CommentsSortPipe } from './pipes/comments-sort/comments-sort.pipe';
import { ReservationInfosComponent } from './components/reservation-infos/reservation-infos.component';
import { PaymentModalComponent } from './components/payment-modal/payment-modal.component';

@NgModule({
    declarations: [
        AdventuresListPageComponent,
        AdventureDetailPageComponent,
        AdventureCardComponent,
        CommentsLayoutComponent,
        CommentComponent,
        AdventureCategoryPipe,
        CommentsSortPipe,
        ReservationInfosComponent,
        PaymentModalComponent,
    ],
    imports: [SharedModule, AdventuresRoutingModule],
})
export class AdventuresModule {}
