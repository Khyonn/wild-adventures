import { Component, Input, HostBinding } from '@angular/core';

@Component({
    selector: 'adv-loading',
    templateUrl: './loading.component.html',
    styleUrls: ['./loading.component.scss'],
})
export class LoadingComponent {
    @HostBinding('class.is-loading') @Input() isLoading = false;
}
