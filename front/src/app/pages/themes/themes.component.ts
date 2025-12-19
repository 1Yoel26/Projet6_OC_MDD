import { Component, OnInit } from '@angular/core';
import { Theme } from 'src/app/interfaces/theme.interface';
import { ThemeService } from 'src/app/services/theme.service';

@Component({
  selector: 'app-themes',
  templateUrl: './themes.component.html',
  styleUrls: ['./themes.component.scss']
})
export class ThemesComponent implements OnInit {

  public listeDesThemes!: Theme[];

  public listeDesIdDesThemesAbonne!: number[];

  constructor(private serviceTheme: ThemeService) { }

  ngOnInit(): void {

    this.serviceTheme.lesThemes().subscribe({
      next:(reponseObs: Theme[])=>{
        this.listeDesThemes = reponseObs;
      }
    });

    this.serviceTheme.lesIdThemesAbonne().subscribe({
      next:(reponseObs: number[])=>{
        this.listeDesIdDesThemesAbonne = reponseObs;
      }
    });
  }

  abonnement(idTheme: number){
    this.serviceTheme.abonnement(idTheme).subscribe({
      next: ()=>{

        // s'il s'est bien abonné on recharge les id des themes abonné:
        this.serviceTheme.lesIdThemesAbonne().subscribe({
          next:(reponseObs: number[])=>{
            this.listeDesIdDesThemesAbonne = reponseObs;
          }
        });

      }
    });
  }

}
