import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.scss']
})
export class ProfilComponent implements OnInit {

  constructor(private fb: FormBuilder) {

  }

  public formModifProfil = this.fb.group({
    
    username: [
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
        Validators.email,
        Validators.min(3),
        Validators.max(50)
      ]
    ],
    
    motDePasse: [
      '',
      [
        Validators.required,
        Validators.min(3),
        Validators.max(50)
      ]
    ]
  });


  ngOnInit(): void {
  }

  onSubmit(): void {
    
  }

}
