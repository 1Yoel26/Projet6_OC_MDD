import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { InscriptionComponent } from './pages/inscription/inscription.component';
import { ConnectionComponent } from './pages/connection/connection.component';
import { IndexComponent } from './pages/index/index.component';
import { ThemesComponent } from './pages/themes/themes.component';
import { ArticleComponent } from './pages/article/article.component';

// consider a guard combined with canLoad / canActivate route option
// to manage unauthenticated user to access private routes
const routes: Routes = [
  { 
    path: '', 
    component: HomeComponent 
  },

  { 
    path: 'inscription', 
    component: InscriptionComponent 
  },

  { 
    path: 'connection', 
    component: ConnectionComponent 
  },

  { 
    path: 'index', 
    component: IndexComponent 
  },

  { 
    path: 'themes', 
    component: ThemesComponent 
  },

  { 
    path: 'article', 
    component: ArticleComponent 
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}

