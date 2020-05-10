import { NgModule } from '@angular/core';
import { SharedModule } from 'src/app/shared/shared.module';

// Component
import { NavigationComponent } from './components/navigation/navigation.component';

@NgModule({
    declarations: [NavigationComponent],
    imports: [SharedModule],
    exports: [NavigationComponent],
})
export class LayoutModule {}
