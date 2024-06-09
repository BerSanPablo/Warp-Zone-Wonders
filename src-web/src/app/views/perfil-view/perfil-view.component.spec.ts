import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PerfilViewComponent } from './perfil-view.component';

describe('PerfilViewComponent', () => {
  let component: PerfilViewComponent;
  let fixture: ComponentFixture<PerfilViewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [PerfilViewComponent]
    });
    fixture = TestBed.createComponent(PerfilViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
