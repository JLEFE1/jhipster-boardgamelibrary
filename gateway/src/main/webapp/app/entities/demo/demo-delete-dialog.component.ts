import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, JhiLanguageService } from 'ng-jhipster';

import { Demo } from './demo.model';
import { DemoPopupService } from './demo-popup.service';
import { DemoService } from './demo.service';

@Component({
    selector: 'jhi-demo-delete-dialog',
    templateUrl: './demo-delete-dialog.component.html'
})
export class DemoDeleteDialogComponent {

    demo: Demo;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private demoService: DemoService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['demo']);
    }

    clear () {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete (id: number) {
        this.demoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'demoListModification',
                content: 'Deleted an demo'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-demo-delete-popup',
    template: ''
})
export class DemoDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor (
        private route: ActivatedRoute,
        private demoPopupService: DemoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe(params => {
            this.modalRef = this.demoPopupService
                .open(DemoDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
