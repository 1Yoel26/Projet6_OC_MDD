import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserConnection } from 'src/app/interfaces/userConnection.interface';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-connection',
  templateUrl: './connection.component.html',
  styleUrls: ['./connection.component.scss']
})
export class ConnectionComponent implements OnInit {

  public erreurConnection : boolean = false;
  public messageErreur: String = "";

  constructor(
    private fb: FormBuilder,
    private location: Location,
    private router: Router,
    private userService: UserService
  ) { }

   public formConnection = this.fb.nonNullable.group({
      
      usernameOuEmail: [
        '',
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(50)
        ]
      ],
  
      motDePasse: [
        '',
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(40)
        ]
      ]
    });

  ngOnInit(): void {
  }

  onSubmit(){
    const contenuForm : UserConnection  = this.formConnection.getRawValue();

    if(this.formConnection.valid){

      this.userService.connection(contenuForm).subscribe({
      
        next : (reponse)=>{
          this.erreurConnection = false;
          this.messageErreur = "";

          // stockage du jwt pour la connection:
          localStorage.setItem("token", reponse.token);


          this.router.navigate(["/index"]);
        },

        error : ()=>{
          this.erreurConnection = true;
          this.messageErreur = "Identifiant ou mot de passe incorrect."
        }
    });

    }else{
      this.erreurConnection = true;
      this.messageErreur = "Erreur, les infos ne sont pas valides.";
    }


  }

  retourArriere(): void{
    this.location.back();
  }

}
