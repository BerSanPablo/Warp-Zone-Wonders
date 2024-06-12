import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewCompletaComponent } from './review-completa.component';

describe('ReviewCompletaComponent', () => {
  let component: ReviewCompletaComponent;
  let fixture: ComponentFixture<ReviewCompletaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ReviewCompletaComponent]
    });
    fixture = TestBed.createComponent(ReviewCompletaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
