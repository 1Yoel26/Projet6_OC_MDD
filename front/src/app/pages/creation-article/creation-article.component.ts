import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-creation-article',
  templateUrl: './creation-article.component.html',
  styleUrls: ['./creation-article.component.scss']
})
export class CreationArticleComponent implements OnInit {

  themes : string[] = ['Java', 'Python'];

  constructor(
    private fb: FormBuilder,
    private location: Location
  ) { }

  public formCreationArticle = this.fb.group({
    
    theme: [
      '',
      [
        Validators.required,
        Validators.min(3),
        Validators.max(50)
      ]
    ],

    titre: [
      '',
      [
        Validators.required,
        Validators.min(3),
        Validators.max(50)
      ]
    ],
    
    contenu: [
      '',
      [
        Validators.required,
        Validators.min(3),
        Validators.max(1000)
      ]
    ]
  });


  ngOnInit(): void {
  }

  onSubmit(){

  }

  retourArriere(): void{
    this.location.back();
  }


}
