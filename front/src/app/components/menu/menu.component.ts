import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';
import { NavigationEnd, Router } from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {

  @ViewChild('sidenav') sidenav!: MatSidenav;

  private urlActuel!: string;
  public ecranMobile!: boolean;

  public afficheLeMenu!: boolean;
  public afficheLesLiens!: boolean;

  
  constructor(
    private router: Router,
    private breakpointObserver: BreakpointObserver
  ) {}

  ngOnInit(): void {

    // verification si la taille est bien un smartphone
    this.breakpointObserver
      .observe([Breakpoints.Handset])
      .subscribe(result => {
          this.ecranMobile = result.matches;
      });


    this.router.events.subscribe({
      next: (typeEvenementNavigation)=>{

        // si la page s'est chargé completement, récupere son url:
        if(typeEvenementNavigation instanceof NavigationEnd){
          
          this.urlActuel = this.router.url;
          
          // par defaut affiche tout le menu:
          this.afficheLeMenu = true;
          this.afficheLesLiens = true;

          // si c'est la page Home:
          if(this.urlActuel == "/"){

            this.afficheLeMenu = false;
            this.afficheLesLiens = false;
            
          }

          else if(this.urlActuel.includes("/connection") || this.urlActuel.includes("/inscription")){

            this.afficheLeMenu = true;
            this.afficheLesLiens = false;

            if(this.ecranMobile){
              this.afficheLeMenu = false;
            }
            
          }

          // pour toutes les autres pages
          else{

            if(this.ecranMobile){
              this.afficheLeMenu = true;
              this.afficheLesLiens = false;
            }else{
              this.afficheLeMenu = true;
              this.afficheLesLiens = true;
            }

          }

        }
      }
    });
  }

  public deconnection(){
    localStorage.removeItem("token");
  }

}
