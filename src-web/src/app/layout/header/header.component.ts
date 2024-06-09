import { Component, ElementRef, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserService } from 'src/app/services/UserService/user-service.service';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import * as bootstrap from 'bootstrap';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {

  modal: bootstrap.Modal | null = null

  //Datos del formulario
  email: string = ''
  password: string = ''
  loading: boolean = false
  errores: boolean = false

  constructor(protected usuarioService: UserService, private router: Router) { }

  async onClickLogin() {
    this.loading = true

    try{
      //Comprobamos si el login tiene errores
      this.errores = !(await this.usuarioService.login(this.email, this.password))
      if(this.errores){
        return
      }
      else{
        document.getElementById("cierreModal")!.click()
        this.router.navigate(['/']);
      }

    }finally{
      this.loading = false
    }
  }

  cierreSesion() {
    this.usuarioService.clearUser()
    this.router.navigate(['/']);
  }
}
