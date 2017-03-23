import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { Gamer } from './gamer.model';
import { GamerService } from './gamer.service';

@Component({
    selector: 'jhi-gamer-detail',
    templateUrl: './gamer-detail.component.html'
})
export class GamerDetailComponent implements OnInit, OnDestroy {

    gamer: Gamer;
    private subscription: any;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private gamerService: GamerService,
        private route: ActivatedRoute
    ) {
        this.jhiLanguageService.setLocations(['gamer']);
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe(params => {
            this.load(params['uuid']);
        });
    }

    load (uuid) {
        this.gamerService.find(uuid).subscribe(gamer => {
            this.gamer = gamer;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
    }

}
