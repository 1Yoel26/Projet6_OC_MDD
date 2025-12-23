import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Abonnement } from 'src/app/interfaces/abonnement.interface';
import { Theme } from 'src/app/interfaces/theme.interface';
import { UserInfo } from 'src/app/interfaces/userInfo.interface';
import { UserInscription } from 'src/app/interfaces/userInscription.interface';
import { ThemeService } from 'src/app/services/theme.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.scss']
})
export class ProfilComponent implements OnInit {

  public ecranMobile!: boolean; 

  constructor(
    private fb: FormBuilder,
    private serviceTheme: ThemeService,
    private serviceUser: UserService,
    private router: Router,
    private breakpointObserver: BreakpointObserver
  ) {

  }

  public messageAucunAbonnement! : string;

  public messageReponseModifProfil! : string;

  public infoUserConnecte! : UserInfo;

  public listeDesThemesAbonne! : Abonnement[];

  public formModifProfil = this.fb.group({
    
    username: [
      '',
      [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(50)
      ]
    ],

    email: [
      '',
      [
        Validators.required,
        Validators.email,
        Validators.minLength(3),
        Validators.maxLength(50)
      ]
    ],
    
    motDePasse: [
      '',
      [
       
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

    this.serviceTheme.lesThemesAbonne().subscribe({
      next : (reponseObs: Abonnement[])=>{
        this.listeDesThemesAbonne = reponseObs;

        if(this.listeDesThemesAbonne.length == 0){
          this.messageAucunAbonnement = ", vous n'avez aucun abonnement pour le moment.";
        }else{
          this.messageAucunAbonnement = "";
        }
      }
    });


   this.serviceUser.infoUser().subscribe({
          next: (reponseObs: UserInfo)=>{
            this.infoUserConnecte = reponseObs;

            this.formModifProfil.patchValue({
              username: this.infoUserConnecte.username,
              email: this.infoUserConnecte.email,
              motDePasse: ''
            });
          }
        });
    
  }

  onSubmit(){
    const contenuForm = this.formModifProfil.getRawValue() as UserInscription;

    this.serviceUser.modificationInfoUser(contenuForm).subscribe({
      
      // si la modif à marcher réaffiche les infos du profil à jour dans le form:
      next: (reponseObs : void)=>{
        
        // redirection vers la page de connection pour se reconnecter avec le nouveau profil:
        localStorage.removeItem("token");
        this.router.navigate(["/connection/profilModifie"]);
       
      },

      error:(reponseObs: void)=>{
      
        this.messageReponseModifProfil = "Erreur de modification du profil car cet email ou cet username est déjà pris par un autre utilisateur.";
        
      }
    });

  }

  desabonner(idTheme: number): void {
    this.serviceTheme.desabonnement(idTheme).subscribe({
      next:()=>{

        // si le desabonnement a fonctionner, on recharge les tème abonner:
        this.serviceTheme.lesThemesAbonne().subscribe({
          next: (reponseObs: Abonnement[])=>{
            this.listeDesThemesAbonne = reponseObs;

            if(this.listeDesThemesAbonne.length == 0){
              this.messageAucunAbonnement = ", vous n'avez aucun abonnement pour le moment.";
            }
            else{
              this.messageAucunAbonnement = "";
            }
          }
          
        });
       
      }

    });
  }

}
