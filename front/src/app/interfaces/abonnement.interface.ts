import { Theme } from "./theme.interface"
import { UserInscription } from "./userInscription.interface"

export interface AbonnementId{
    id_user: number,
    id_theme: number
}

export interface Abonnement{
    id: AbonnementId,
    user: UserInscription,
    theme: Theme
}