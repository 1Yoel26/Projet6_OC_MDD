import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ArticleComplet } from 'src/app/interfaces/articleComplet.interface';
import { Commentaire } from 'src/app/interfaces/commentaire.interface';
import { ArticleService } from 'src/app/services/article.service';
import { CommentaireService } from 'src/app/services/commentaire.service';

@Component({
  selector: 'app-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.scss']
})
export class ArticleComponent implements OnInit {

  public unArticle! : ArticleComplet;
  public erreurArticle: boolean = false;
  public messageErreur: string = "";
  public listeDesCommentaires!: Commentaire[];

  constructor(
    private location: Location,
    private routerActived: ActivatedRoute,
    private serviceArticle: ArticleService,
    private serviceCommentaire: CommentaireService
  ) { }

  ngOnInit(): void {
    const idArticle : string | null = this.routerActived.snapshot.paramMap.get("id");

    const idArticleNumber = Number(idArticle);
    
    this.serviceArticle.article(idArticleNumber).subscribe({
      next:(reponseObs : ArticleComplet) =>{
        this.erreurArticle = false;
        this.unArticle = reponseObs;
      },

      error:(err: any)=>{
        this.erreurArticle = true;
        this.messageErreur = "Erreur, l'identifiant de l'article est invalide.";
      }
    });


    this.serviceCommentaire.listeDesCommentaires(idArticleNumber).subscribe({
      next: (reponseObs: Commentaire[]) =>{
        this.listeDesCommentaires = reponseObs;
      },

      error: ()=>{
        this.erreurArticle = true;
      }
    });
  }

  

   retourArriere(): void{
    this.location.back();
  }
}
