import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-connection',
  templateUrl: './connection.component.html',
  styleUrls: ['./connection.component.scss']
})
export class ConnectionComponent implements OnInit {

  constructor(
    private fb: FormBuilder,
    private location: Location
  ) { }

   public formInscription = this.fb.group({
      
      nomUtilisateurOuEmail: [
        '',
        [
          Validators.required,
          Validators.min(3),
          Validators.max(50)
        ]
      ],
  
      motDePasse: [
        '',
        [
          Validators.required,
          Validators.min(3),
          Validators.max(40)
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
