import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { DemoComponent } from './demo.component';
import { DemoDetailComponent } from './demo-detail.component';
import { DemoPopupComponent } from './demo-dialog.component';
import { DemoDeletePopupComponent } from './demo-delete-dialog.component';

import { Principal } from '../../shared';


export const demoRoute: Routes = [
  {
    path: 'demo',
    component: DemoComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'gatewayApp.demo.home.title'
    }
  }, {
    path: 'demo/:id',
    component: DemoDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'gatewayApp.demo.home.title'
    }
  }
];

export const demoPopupRoute: Routes = [
  {
    path: 'demo-new',
    component: DemoPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'gatewayApp.demo.home.title'
    },
    outlet: 'popup'
  },
  {
    path: 'demo/:id/edit',
    component: DemoPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'gatewayApp.demo.home.title'
    },
    outlet: 'popup'
  },
  {
    path: 'demo/:id/delete',
    component: DemoDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'gatewayApp.demo.home.title'
    },
    outlet: 'popup'
  }
];
