import './vendor.ts';

import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { Ng2Webstorage } from 'ng2-webstorage';

import { GatewaySharedModule, UserRouteAccessService } from './shared';
import { GatewayHomeModule } from './home/home.module';
import { GatewayAdminModule } from './admin/admin.module';
import { GatewayAccountModule } from './account/account.module';
import { GatewayEntityModule } from './entities/entity.module';

import { LayoutRoutingModule } from './layouts';
import { customHttpProvider } from './blocks/interceptor/http.provider';
import { PaginationConfig } from './blocks/config/uib-pagination.config';

import {GatewayGamerModule} from './pages/gamer/gamer.module';

import {
    JhiMainComponent,
    NavbarComponent,
    FooterComponent,
    ProfileService,
    PageRibbonComponent,
    ActiveMenuDirective,
    ErrorComponent
} from './layouts';
import {GamerDatabaseService} from './shared/database/gamer-database.service';



@NgModule({
    imports: [
        BrowserModule,
        LayoutRoutingModule,
        Ng2Webstorage.forRoot({ prefix: 'jhi', separator: '-'}),
        GatewaySharedModule,
        GatewayHomeModule,
        GatewayAdminModule,
        GatewayAccountModule,
        GatewayEntityModule,
        GatewayGamerModule
    ],
    declarations: [
        JhiMainComponent,
        NavbarComponent,
        ErrorComponent,
        PageRibbonComponent,
        ActiveMenuDirective,
        FooterComponent
    ],
    providers: [
        ProfileService,
        { provide: Window, useValue: window },
        { provide: Document, useValue: document },
        customHttpProvider(),
        PaginationConfig,
        UserRouteAccessService,
        GamerDatabaseService
    ],
    bootstrap: [ JhiMainComponent ]
})
export class GatewayAppModule {}
