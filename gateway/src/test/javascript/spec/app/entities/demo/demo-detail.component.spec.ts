import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { MockBackend } from '@angular/http/testing';
import { Http, BaseRequestOptions } from '@angular/http';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils } from 'ng-jhipster';
import { JhiLanguageService } from 'ng-jhipster';
import { MockLanguageService } from '../../../helpers/mock-language.service';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { DemoDetailComponent } from '../../../../../../main/webapp/app/entities/demo/demo-detail.component';
import { DemoService } from '../../../../../../main/webapp/app/entities/demo/demo.service';
import { Demo } from '../../../../../../main/webapp/app/entities/demo/demo.model';

describe('Component Tests', () => {

    describe('Demo Management Detail Component', () => {
        let comp: DemoDetailComponent;
        let fixture: ComponentFixture<DemoDetailComponent>;
        let service: DemoService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                declarations: [DemoDetailComponent],
                providers: [
                    MockBackend,
                    BaseRequestOptions,
                    DateUtils,
                    DataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    {
                        provide: Http,
                        useFactory: (backendInstance: MockBackend, defaultOptions: BaseRequestOptions) => {
                            return new Http(backendInstance, defaultOptions);
                        },
                        deps: [MockBackend, BaseRequestOptions]
                    },
                    {
                        provide: JhiLanguageService,
                        useClass: MockLanguageService
                    },
                    DemoService
                ]
            }).overrideComponent(DemoDetailComponent, {
                set: {
                    template: ''
                }
            }).compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(DemoDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DemoService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Demo(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.demo).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
