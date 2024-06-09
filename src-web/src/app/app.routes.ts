import { Routes } from '@angular/router';
import { VideoGameViewComponent } from './views/video-game-view/video-game-view.component';
import { PerfilViewComponent } from './views/perfil-view/perfil-view.component';
import { RegistroViewComponent } from './views/registro-view/registro-view.component';

export const routes: Routes = [
  { path: '', component:  VideoGameViewComponent},
  { path: 'perfil', component:  PerfilViewComponent},
  { path: 'registro', component:  RegistroViewComponent},
];
