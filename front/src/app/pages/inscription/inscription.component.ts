import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
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
    private serviceUser: UserService,
    private breakpointObserver: BreakpointObserver
  ) { }

  public ecranMobile!: boolean;
  public afficherImgMobile: boolean = false;

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
        Validators.maxLength(40),
        Validators.pattern(
          /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/
        )
      ]
    ]
  });


  ngOnInit(): void {

    // test si la taille de l'ecran est celle d'un smartphone:
         // verification si la taille est bien un smartphone
            this.breakpointObserver
              .observe([Breakpoints.Handset])
              .subscribe(result => {
                  this.ecranMobile = result.matches;
              });
    
        if(this.ecranMobile){
          this.afficherImgMobile = true;
        }
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
            this.messageErreur = "Erreur de création de compte car cet email ou cet username est déjà pris."
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

      const emailEstValider = this.formInscription.get("email");
      
      const motDePasseEstValider = this.formInscription.get("motDePasse");

      if(emailEstValider?.invalid){
        this.messageErreur = "Erreur d'inscription car l'email est invalide";
      }

      else if(motDePasseEstValider?.invalid){
        this.messageErreur = "Erreur d'inscription car le mot de passe est invalide. Le mot de passe doit contenir au minimum 8 caractères, un chiffre, 1 lettre minuscule, 1 lettre majuscule et 1 caractère spécial.";
      }

      else{
        this.messageErreur = "Erreur d'inscription, veuillez saisir tous les champs, avec une valeur correct svp";
      }
    


    }
    
    
  }

  retourArriere(): void{
    this.location.back();
  }

}
