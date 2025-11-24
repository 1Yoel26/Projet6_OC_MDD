import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Theme } from 'src/app/interfaces/theme.interface';
import { ThemeService } from 'src/app/services/theme.service';

@Component({
  selector: 'app-creation-article',
  templateUrl: './creation-article.component.html',
  styleUrls: ['./creation-article.component.scss']
})
export class CreationArticleComponent implements OnInit {

  public listThemes!: Theme[];


  constructor(
    private fb: FormBuilder,
    private location: Location,
    private serviceTheme: ThemeService
  ) { }

  public formCreationArticle = this.fb.group({
    
    theme: [
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
     this.serviceTheme.lesThemes().subscribe( (themes : Theme[]) => {
      this.listThemes = themes;
    });
  }

  onSubmit(){
    
  }

  retourArriere(): void{
    this.location.back();
  }


}
