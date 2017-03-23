import { Routes } from '@angular/router';

import { GamerComponent } from './gamer.component';
import { GamerDetailComponent } from './gamer-detail.component';
import { GamerPopupComponent } from './gamer-dialog.component';
import { GamerDeletePopupComponent } from './gamer-delete-dialog.component';



export const gamerRoute: Routes = [
  {
    path: 'gamer',
    component: GamerComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'gatewayApp.gamer.home.title'
    }
  }, {
    path: 'gamer/:uuid',
    component: GamerDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'gatewayApp.gamer.home.title'
    }
  }
];

export const gamerPopupRoute: Routes = [
  {
    path: 'gamer-new',
    component: GamerPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'gatewayApp.gamer.home.title'
    },
    outlet: 'popup'
  },
  {
    path: 'gamer/:uuid/edit',
    component: GamerPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'gatewayApp.gamer.home.title'
    },
    outlet: 'popup'
  },
  {
    path: 'gamer/:uuid/delete',
    component: GamerDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'gatewayApp.gamer.home.title'
    },
    outlet: 'popup'
  }
];
