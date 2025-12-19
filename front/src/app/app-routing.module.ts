import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { InscriptionComponent } from './pages/inscription/inscription.component';
import { ConnectionComponent } from './pages/connection/connection.component';
import { IndexComponent } from './pages/index/index.component';
import { ThemesComponent } from './pages/themes/themes.component';
import { ArticleComponent } from './pages/article/article.component';
import { CreationArticleComponent } from './pages/creation-article/creation-article.component';
import { ProfilComponent } from './pages/profil/profil.component';
import { UnauthGuard } from './guards/unauth.guard';
import { AuthGuard } from './guards/auth.guard';

// consider a guard combined with canLoad / canActivate route option
// to manage unauthenticated user to access private routes
const routes: Routes = [
  { 
    path: '', 
    component: HomeComponent, 
    canActivate: [UnauthGuard]
  },

  { 
    path: 'inscription', 
    component: InscriptionComponent, 
    canActivate: [UnauthGuard]
  },

  { 
    path: 'connection', 
    component: ConnectionComponent, 
    canActivate: [UnauthGuard]
  },

  {
    path: 'connection/:profilModifie',
    component: ConnectionComponent,
    canActivate: [UnauthGuard]
  },

  { 
    path: 'index', 
    component: IndexComponent, 
    canActivate: [AuthGuard]
  },

  { 
    path: 'themes', 
    component: ThemesComponent, 
    canActivate: [AuthGuard]
  },

  { 
    path: 'article/:id', 
    component: ArticleComponent, 
    canActivate: [AuthGuard]
  },

  { 
    path: 'creationArticle', 
    component: CreationArticleComponent,
    canActivate: [AuthGuard] 
  },

  { 
    path: 'profil', 
    component: ProfilComponent, 
    canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}

