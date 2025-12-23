import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {

  constructor(
    private breakpointObserver: BreakpointObserver
  ) {}

  public ecranMobile!: boolean;

  ngOnInit(): void {

    // verification si la taille est bien un smartphone
    this.breakpointObserver
      .observe([Breakpoints.Handset])
      .subscribe(result => {
          this.ecranMobile = result.matches;
      });
    
    
    
  }

  
}
