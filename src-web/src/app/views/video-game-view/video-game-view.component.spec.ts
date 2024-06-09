import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VideoGameViewComponent } from './video-game-view.component';

describe('VideoGameViewComponent', () => {
  let component: VideoGameViewComponent;
  let fixture: ComponentFixture<VideoGameViewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [VideoGameViewComponent]
    });
    fixture = TestBed.createComponent(VideoGameViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
