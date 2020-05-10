import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { LayoutModule } from '@angular/cdk/layout';
import { FlexLayoutModule } from '@angular/flex-layout';

import { MaterialModule } from './modules/material.module';

import { LoadingComponent } from './components/loading/loading.component';
import { NotFoundComponent } from './modules/layout/pages/not-found/not-found.component';

@NgModule({
    declarations: [LoadingComponent, NotFoundComponent],
    imports: [
        CommonModule,
        HttpClientModule,
        ReactiveFormsModule,
        RouterModule,
        LayoutModule,
        FlexLayoutModule,
        MaterialModule,
    ],
    exports: [
        CommonModule,
        HttpClientModule,
        ReactiveFormsModule,
        RouterModule,
        LayoutModule,
        FlexLayoutModule,
        MaterialModule,
        LoadingComponent,
    ],
    providers: [],
})
export class SharedModule {}
