import { Pipe, PipeTransform } from '@angular/core';
import { CommentDTO } from '../../models/adventure-models';

@Pipe({
    name: 'commentsSort',
})
export class CommentsSortPipe implements PipeTransform {
    transform(comments: CommentDTO[]): CommentDTO[] {
        if (comments) {
            return comments.sort((a, b) => b.date.getTime() - a.date.getTime());
        }
        return comments;
    }
}
