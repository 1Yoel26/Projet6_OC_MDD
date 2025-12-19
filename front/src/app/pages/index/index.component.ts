import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Article } from 'src/app/interfaces/article.interface';
import { ArticleComplet } from 'src/app/interfaces/articleComplet.interface';
import { ArticleService } from 'src/app/services/article.service';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.scss']
})
export class IndexComponent implements OnInit {

  public listeDesArticles!: ArticleComplet[];

  constructor(
    private serviceArticle: ArticleService,
    private router: Router
  ) { }

  ngOnInit(): void {

    // par defaut on affiche les articles dont l'utilisateur est abonné,
    // trié par ordre du plus récent au moins récent :
    this.serviceArticle.listeDesArticlesAbonneRecent().subscribe({
      next : (reponseObs: ArticleComplet[]) => {
        this.listeDesArticles = reponseObs;

         // si aucun articles abonné n'est trouvé our cette utilisateur afficher tout les articles par ordre récent :
        if(this.listeDesArticles.length == 0){

          this.serviceArticle.listeDesArticles().subscribe({
          next : (reponseObs: ArticleComplet[]) => {
            this.listeDesArticles = reponseObs;
          }
        });

    }


      }
    });


  }


  filtreAncien(): void{

    const btnRecent = document.querySelector(".boutonFiltreRecent") as HTMLElement;
    btnRecent.style.display = "inline-block";

    const btnAncien = document.querySelector(".boutonFiltreAncien") as HTMLElement;
    btnAncien.style.display = "none";

    // Appel du service pour trier par ancien
    this.serviceArticle.listeDesArticlesAbonneAncien().subscribe({
      next: (lesArticles: ArticleComplet[]) => {
        this.listeDesArticles = lesArticles;

        if(this.listeDesArticles.length == 0){

          this.serviceArticle.listeDesArticlesAncien().subscribe({
          next : (reponseObs: ArticleComplet[]) => {
            this.listeDesArticles = reponseObs;
          }
        });
      }
        
      }
    });
  }

  filtreRecent(): void{

    const btnAncien2 = document.querySelector(".boutonFiltreAncien") as HTMLElement;
    btnAncien2.style.display = "inline-block";

    const btnRecent2 = document.querySelector(".boutonFiltreRecent") as HTMLElement;
    btnRecent2.style.display = "none";

    // Appel du service pour trier par ancien
    this.serviceArticle.listeDesArticlesAbonneRecent().subscribe({
      next: (lesArticles: ArticleComplet[]) => {
        this.listeDesArticles = lesArticles;

        if(this.listeDesArticles.length == 0){

          this.serviceArticle.listeDesArticles().subscribe({
          next : (reponseObs: ArticleComplet[]) => {
            this.listeDesArticles = reponseObs;
          }
        });
      }
        
      }
    });
  }


  ouvrirUnArticle(id: number){
    this.router.navigate(["/article/" + id]);
  }





}
