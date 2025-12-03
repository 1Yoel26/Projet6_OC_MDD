import { ArticleComplet, User } from "./articleComplet.interface";



export interface Commentaire {

    id: number;
    user: User;
    article: ArticleComplet;
    contenu: string;
    
    
}
