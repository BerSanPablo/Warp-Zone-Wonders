import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateJuegoComponent } from './create-juego.component';

describe('CreateJuegoComponent', () => {
  let component: CreateJuegoComponent;
  let fixture: ComponentFixture<CreateJuegoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [CreateJuegoComponent]
    });
    fixture = TestBed.createComponent(CreateJuegoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
