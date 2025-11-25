import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Article } from 'src/app/interfaces/article.interface';
import { Theme } from 'src/app/interfaces/theme.interface';
import { ArticleService } from 'src/app/services/article.service';
import { ThemeService } from 'src/app/services/theme.service';

@Component({
  selector: 'app-creation-article',
  templateUrl: './creation-article.component.html',
  styleUrls: ['./creation-article.component.scss']
})
export class CreationArticleComponent implements OnInit {

  public listThemes!: Theme[];

  private erreurCreationArticle: boolean = false;
  private messageErreur: string = "";

  private succesCreationArticle: boolean = false;
  private messageSucces: string = "";


  constructor(
    private fb: FormBuilder,
    private location: Location,
    private serviceTheme: ThemeService,
    private serviceArticle: ArticleService,
  ) { }

  public formCreationArticle = this.fb.nonNullable.group({
    
    id_theme: [
      '',
      [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(50)
      ]
    ],

    titre: [
      '',
      [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(50)
      ]
    ],
    
    contenu: [
      '',
      [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(1000)
      ]
    ]
  });


  ngOnInit(): void {
     this.serviceTheme.lesThemes().subscribe( (lesThemes : Theme[]) => {
      this.listThemes = lesThemes;
    });
  }

  onSubmit(){

    const contenuForm : Article = this.formCreationArticle.getRawValue();

    if(this.formCreationArticle.valid){
      this.serviceArticle.creationArticle(contenuForm).subscribe({
        next: ()=>{
          
          this.erreurCreationArticle = false;
          this.messageErreur = "";
          this.succesCreationArticle = true;
          this.messageSucces = "Article créé avec succès !";
        },

        error: ()=>{
          this.erreurCreationArticle = true;
          this.messageErreur = "Erreur lors de la création de l'article";
        }
      });
    }
    
    else{
      this.erreurCreationArticle = true;
      this.messageErreur = "Erreur formulaire invalide";
    }
    
    
  }

  retourArriere(): void{
    this.location.back();
  }


}
