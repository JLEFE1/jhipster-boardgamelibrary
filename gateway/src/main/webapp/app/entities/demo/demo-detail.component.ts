import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { Demo } from './demo.model';
import { DemoService } from './demo.service';

@Component({
    selector: 'jhi-demo-detail',
    templateUrl: './demo-detail.component.html'
})
export class DemoDetailComponent implements OnInit, OnDestroy {

    demo: Demo;
    private subscription: any;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private demoService: DemoService,
        private route: ActivatedRoute
    ) {
        this.jhiLanguageService.setLocations(['demo']);
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe(params => {
            this.load(params['id']);
        });
    }

    load (id) {
        this.demoService.find(id).subscribe(demo => {
            this.demo = demo;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
    }

}
