import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.scss']
})
export class InscriptionComponent implements OnInit {

  constructor(
    private fb: FormBuilder,
    private location: Location
  ) { }

  public formInscription = this.fb.group({
    
    nomUtilisateur: [
      '',
      [
        Validators.required,
        Validators.min(3),
        Validators.max(50)
      ]
    ],

    email: [
      '',
      [
        Validators.required,
        Validators.email
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
