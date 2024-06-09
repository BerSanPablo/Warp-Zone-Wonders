import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VideoGameCardComponent } from './video-game-card.component';

describe('VideoGameCardComponent', () => {
  let component: VideoGameCardComponent;
  let fixture: ComponentFixture<VideoGameCardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [VideoGameCardComponent]
    });
    fixture = TestBed.createComponent(VideoGameCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
