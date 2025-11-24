import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserInscription } from 'src/app/interfaces/userInscription.interface';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.scss']
})
export class InscriptionComponent implements OnInit {

  constructor(
    private fb: FormBuilder,
    private location: Location,
    private router: Router,
    private serviceUser: UserService
  ) { }

  public erreurInscription: boolean = false;

  public messageErreur: string = "";

  public formInscription = this.fb.nonNullable.group({
    
    username: [
      '',
      [
        Validators.required,
        Validators.min(3),
        Validators.max(50)
      ]
    ],

    email: [
      '',
      [
        Validators.required,
        Validators.email
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
    
    const contenuForm: UserInscription = this.formInscription.getRawValue();
    
    if(this.formInscription.valid){
        this.serviceUser.inscription(contenuForm).subscribe({
        next: (reponse: void) =>{
          this.router.navigate(["/connection"]);
        },

        error: (err) =>{
          this.erreurInscription = true;
          if(err.status === 400){
            this.messageErreur = "Erreur de création de compte car cet email est déjà pris."
          }
          else if(err.status === 500){
            this.messageErreur = "Une erreur s'est produite, merci de ré-essayer";
          }

          else{
            this.messageErreur = "Une erreur s'est produite, merci de ré-essayer";
          }
        }
      });
    }else{
      this.erreurInscription = true;
      this.messageErreur = "Veuillez saisir tous les champs, avec une valeur correct svp";
    
    }
    
    
  }

  retourArriere(): void{
    this.location.back();
  }

}
