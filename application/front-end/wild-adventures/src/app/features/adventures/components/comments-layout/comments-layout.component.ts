import { Component, Input, ViewChild, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup, NgForm } from '@angular/forms';
import { AdventureDTO, CommentDTO, CommentIdDTO, WriterDTO } from '../../models/adventure-models';

import { AdventuresHttpService } from '../../services/http/adventures.http.service';
import { PageEvent } from '@angular/material/paginator';
import { KeycloakService } from 'src/app/shared/services/keycloak/keycloak.service';

@Component({
    selector: 'adv-comments-layout',
    templateUrl: './comments-layout.component.html',
    styleUrls: ['./comments-layout.component.scss'],
})
export class CommentsLayoutComponent implements OnInit {
    @ViewChild('htmlForm') htmlForm: NgForm;
    @Input() set adventure(adventure: AdventureDTO) {
        this._adventure = adventure;
        this.onChangeAdventure();
    }

    // tslint:disable-next-line: variable-name
    private _adventure: AdventureDTO = null;

    isLoadingCommentForm = false;
    areCommentsLoading = false;
    isLogged = false;

    commentForm: FormGroup;

    userId: string;
    comments: CommentDTO[];
    commentPager: PageEvent;
    get commentsListSliceStart(): number {
        return this.commentPager.pageIndex * this.commentPager.pageSize;
    }
    get commentsListSliceEnd(): number {
        return (this.commentPager.pageIndex + 1) * this.commentPager.pageSize;
    }

    constructor(
        private httpService: AdventuresHttpService,
        private fb: FormBuilder,
        private keycloakService: KeycloakService
    ) {
        this.commentForm = this.fb.group({
            content: this.fb.control('', Validators.required),
        });
        this.comments = [];
        this.commentPager = Object.assign(new PageEvent(), { pageIndex: 0, pageSize: 10 });
    }

    onChangeCommentPager(pageEvent: PageEvent) {
        this.commentPager = pageEvent;
    }

    async onClickDeleteComment(comment: CommentDTO) {
        await this.httpService.deleteComment(comment.id.adventureId, comment.id.commentId).toPromise();
        await this.loadComments();
    }

    onSubmitCommentForm() {
        this.isLoadingCommentForm = true;
        this.httpService
            .writeComment(this._adventure.id, this.commentForm.value)
            .toPromise()
            .then(() => this.loadComments())
            .then(() => this.htmlForm.resetForm())
            .finally(() => {
                this.isLoadingCommentForm = false;
            });
    }

    onChangeAdventure() {
        if (this._adventure && this._adventure.id) {
            this.loadComments();
        }
    }

    async ngOnInit() {
        this.isLogged = await this.keycloakService.isLoggedIn();
        this.userId = await this.keycloakService.getUserId();
    }

    private async loadComments() {
        this.areCommentsLoading = true;
        try {
            this.comments = (await this.httpService.getCommentsByAdventureId(this._adventure.id).toPromise()) || [];
        } catch (e) {
            this.comments = [];
        }
        this.areCommentsLoading = false;
        this.commentPager.pageIndex = 0;
    }
}
