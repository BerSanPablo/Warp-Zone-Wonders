import { Routes } from '@angular/router';
import { VideoGameViewComponent } from './views/video-game-view/video-game-view.component';
import { PerfilViewComponent } from './views/perfil-view/perfil-view.component';
import { RegistroViewComponent } from './views/registro-view/registro-view.component';
import { CreateJuegoComponent } from './views/create-juego/create-juego.component';
import { DetalleJuegoComponent } from './views/detalle-juego/detalle-juego.component';
import { ReviewViewComponent } from './views/review-view/review-view.component';

export const routes: Routes = [
  { path: '', component:  VideoGameViewComponent},
  { path: 'perfil', component:  PerfilViewComponent},
  { path: 'registro', component:  RegistroViewComponent},
  { path: 'admin', component:  CreateJuegoComponent},
  { path: 'videojuego/:nombre', component:  DetalleJuegoComponent},
  { path: 'reviews', component:  ReviewViewComponent},
];
