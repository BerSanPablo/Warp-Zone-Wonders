import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserService } from 'src/app/services/UserService/user-service.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {

  //Datos del formulario
  email: string = ''
  password: string = ''
  loading: boolean = false
  errores: boolean = false

  constructor(protected usuarioService: UserService) {  }

  async onClickLogin() {
    this.loading = true

    try{
      //Comprobamos si el login tiene errores
      this.errores = !(await this.usuarioService.login(this.email, this.password))
      if(this.errores){return}

    }finally{
      this.loading = false
    }
  }
}
