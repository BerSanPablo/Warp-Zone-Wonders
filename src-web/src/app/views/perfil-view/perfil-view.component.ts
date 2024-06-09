import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserService } from 'src/app/services/UserService/user-service.service';
import { UserData } from 'src/app/entities/userData';
import { Router } from '@angular/router';

@Component({
  selector: 'app-perfil-view',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './perfil-view.component.html',
  styleUrls: ['./perfil-view.component.scss']
})
export class PerfilViewComponent {

  userData: UserData | null = null;

  constructor(protected usuarioService: UserService, private router: Router) {
    this.userData = usuarioService.getUser()

    if(this.userData == null){
      this.router.navigate(['/']);
    }
  }

}
