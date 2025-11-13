import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.scss']
})
export class ArticleComponent implements OnInit {

  constructor(private location: Location) { }

  ngOnInit(): void {
  }

   retourArriere(): void{
    this.location.back();
  }
}
