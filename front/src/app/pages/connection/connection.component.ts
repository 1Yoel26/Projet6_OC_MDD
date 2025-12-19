import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UserConnection } from 'src/app/interfaces/userConnection.interface';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-connection',
  templateUrl: './connection.component.html',
  styleUrls: ['./connection.component.scss']
})
export class ConnectionComponent implements OnInit {

  public erreurConnection : boolean = false;
  public ecranMobile!: boolean;
  public messageErreur: String = "";
  public titre : string = "Se connecter";
  public afficherImgMobile: boolean = false;

  constructor(
    private fb: FormBuilder,
    private location: Location,
    private router: Router,
    private routerActivate: ActivatedRoute,
    private userService: UserService,
    private breakpointObserver: BreakpointObserver
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

    // si l'utilisateur vient de modifier son profil, 
    // changement du nom du titre de la page afin de lui
    // expliquer pourquoi il est redirigé vers la page de connection après avoir modifié son profil :
    
    let parametreUrlModifProfil: string | null = this.routerActivate.snapshot.paramMap.get("profilModifie");
    
    if(parametreUrlModifProfil != null){
      this.titre = "Votre profil à bien été mis à jour, veuillez vous reconnectez svp"
    }

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
