import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommentDTO } from '../../models/adventure-models';

@Component({
    selector: 'adv-comment',
    templateUrl: './comment.component.html',
    styleUrls: ['./comment.component.scss'],
})
export class CommentComponent {
    @Input() userId: string;
    @Input() comment: CommentDTO;
    @Output() clickDeleteComment = new EventEmitter();

    constructor() {}

    get isUserComment(): boolean {
        return this.userId === this.comment.id.userId;
    }

    onClickDelete() {
        this.clickDeleteComment.emit();
    }
}
