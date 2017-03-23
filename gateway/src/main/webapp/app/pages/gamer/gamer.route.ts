import { Route } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { GamerComponent } from './';

export const GAMER_ROUTE: Route = {
  path: 'gamer',
  component: GamerComponent,
  data: {
    authorities: [],
    pageTitle: 'gamer.home.title'
  }
};
