import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistroViewComponent } from './registro-view.component';

describe('RegistroViewComponent', () => {
  let component: RegistroViewComponent;
  let fixture: ComponentFixture<RegistroViewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RegistroViewComponent]
    });
    fixture = TestBed.createComponent(RegistroViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
