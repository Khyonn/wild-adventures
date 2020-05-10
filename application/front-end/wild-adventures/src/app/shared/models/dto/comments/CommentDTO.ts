import { CommentIdDTO } from './CommentIdDTO';
import { WriterDTO } from './WriterDTO';

export class CommentDTO {
    id: CommentIdDTO;
    writer: WriterDTO;
    date: Date;
    content: string;
}
