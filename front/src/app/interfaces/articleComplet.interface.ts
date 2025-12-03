export interface Theme {
  id: number;
  nom: string;
}

export interface User {
  id: number;
  username: string;
  email: string;
  mot_de_passe: string;
}


export interface ArticleComplet {

    id: number;
    theme: Theme;
    user: User;
    titre: string;
    contenu: string;
    date: string;
    
}
