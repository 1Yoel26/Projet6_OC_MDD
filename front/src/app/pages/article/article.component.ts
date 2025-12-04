import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { ArticleComplet } from 'src/app/interfaces/articleComplet.interface';
import { Commentaire } from 'src/app/interfaces/commentaire.interface';
import { CommentaireCreation } from 'src/app/interfaces/commentaireCreation.interface';
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

  public erreurCreationCommentaire: boolean = false;
  public messageErreurCreationCommentaire: string = "";

  private idArticleNumber!: number;
  private contenuCommentaireCreation!: CommentaireCreation;

  constructor(
    private location: Location,
    private fb: FormBuilder,
    private routerActived: ActivatedRoute,
    private serviceArticle: ArticleService,
    private serviceCommentaire: CommentaireService
  ) { }

  public formGroupCreationCommentaire = this.fb.nonNullable.group({
    contenu : this.fb.nonNullable.control<string>("", 
      Validators.required
    )
  });

  ngOnInit(): void {
    const idArticle : string | null = this.routerActived.snapshot.paramMap.get("id");

    this.idArticleNumber = Number(idArticle);
    
    this.serviceArticle.article(this.idArticleNumber).subscribe({
      next:(reponseObs : ArticleComplet) =>{
        this.erreurArticle = false;
        this.unArticle = reponseObs;
      },

      error:(err: any)=>{
        this.erreurArticle = true;
        this.messageErreur = "Erreur, l'identifiant de l'article est invalide.";
      }
    });


    this.serviceCommentaire.listeDesCommentaires(this.idArticleNumber).subscribe({
      next: (reponseObs: Commentaire[]) =>{
        this.listeDesCommentaires = reponseObs;
      },

      error: ()=>{
        this.erreurArticle = true;
      }
    });
  }

  onSubmit(){

    const contenuForm = this.formGroupCreationCommentaire.getRawValue();
    
    if(this.formGroupCreationCommentaire.valid){

      this.erreurCreationCommentaire = false;
      this.messageErreurCreationCommentaire = "";

      this.contenuCommentaireCreation = {
        idArticle: this.idArticleNumber,
        contenu: contenuForm.contenu
      };

      this.serviceCommentaire.creationCommentaire(this.contenuCommentaireCreation).subscribe({
        next:()=>{
          this.erreurCreationCommentaire = false;
          this.messageErreurCreationCommentaire = "";

          // rappel du service listeDesCommentaires pour raffraichir la liste avec le nouveau commentaire créé:
          this.serviceCommentaire.listeDesCommentaires(this.idArticleNumber).subscribe({
            next: (responseObs: Commentaire[])=>{
              this.listeDesCommentaires = responseObs;
              this.formGroupCreationCommentaire.reset();
            }
          });
          
        },

        error: (erreur: Error)=>{
          this.erreurCreationCommentaire = true;
          this.messageErreurCreationCommentaire = "Une erreur s'est produite lors de l'enregistrement de votre commentaire." + erreur.message;
        }
      });

    }
    else{
      this.erreurCreationCommentaire = true;
      this.messageErreurCreationCommentaire = "Formulaire invalide car le commentaire est vide.";
  }

}

retourArriere(): void{
    this.location.back();
}

}
