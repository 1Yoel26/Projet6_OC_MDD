# P6-Full-Stack-reseau-dev

## Front

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 14.1.3.

Don't forget to install your node_modules before starting (`npm install`).

### Créer toute la base de donnée déja remplis
### Pour cela copier coller tout le code mysql et executer le dans MySql :

```sql

-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: bdd_mdd
-- ------------------------------------------------------
-- Server version	8.0.42

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `abonnements`
--

DROP TABLE IF EXISTS `abonnements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `abonnements` (
  `id_user` int NOT NULL,
  `id_theme` int NOT NULL,
  PRIMARY KEY (`id_user`,`id_theme`),
  KEY `fk_abonnements_themes` (`id_theme`),
  CONSTRAINT `fk_abonnements_themes` FOREIGN KEY (`id_theme`) REFERENCES `themes` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_abonnements_users` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `abonnements`
--

LOCK TABLES `abonnements` WRITE;
/*!40000 ALTER TABLE `abonnements` DISABLE KEYS */;
INSERT INTO `abonnements` VALUES (5,1),(5,2),(5,4);
/*!40000 ALTER TABLE `abonnements` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `articles`
--

DROP TABLE IF EXISTS `articles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `articles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_theme` int NOT NULL,
  `id_user` int NOT NULL,
  `titre` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `contenu` text COLLATE utf8mb4_general_ci NOT NULL,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_articles_themes` (`id_theme`),
  KEY `fk_articles_users` (`id_user`),
  CONSTRAINT `fk_articles_themes` FOREIGN KEY (`id_theme`) REFERENCES `themes` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_articles_users` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articles`
--

LOCK TABLES `articles` WRITE;
/*!40000 ALTER TABLE `articles` DISABLE KEYS */;
INSERT INTO `articles` VALUES (10,4,5,'Top des avantages d\'Angular !','Angular est l’un des frameworks front-end les plus complets et fiables dans l’univers JavaScript. Développé et maintenu par Google, il offre une architecture solide qui permet de construire des applications web modernes, performantes et parfaitement organisées. 🚀\n\nL’un des grands atouts d’Angular est l’intégration native de TypeScript, qui apporte une meilleure lisibilité du code, une réduction importante des erreurs et une approche plus professionnelle du développement. Grâce à son écosystème complet, incluant le routing, les services, l’injection de dépendances ou encore les formulaires réactifs, Angular fournit tout ce dont un développeur a besoin pour créer une application complexe sans avoir à multiplier les librairies.\n\nPourquoi Angular est-il autant utilisé ? ⭐\n\nFiabilité et support continu de Google\n\nTypeScript intégré par défaut\n\nFramework complet prêt à l’emploi\n\nExcellente scalabilité pour les grandes applications\n\nUne communauté très active dans le monde entier\n\nAngular reste aujourd’hui un choix privilégié pour les entreprises et les projets ambitieux nécessitant robustesse, structure et longévité. 📈\n\nSources :\nhttps://angular.io/\n\nhttps://survey.stackoverflow.co/\n\nhttps://github.com/angular/angular\n\nhttps://developers.google.com/web/platform/angular','2025-11-25 18:38:46'),(13,6,5,'Info de classement sur PHP','PHP est l’un des langages de programmation web les plus populaires et utilisés depuis plus de deux décennies. Sa simplicité d’apprentissage et sa flexibilité ont permis à de nombreux développeurs débutants comme expérimentés de créer rapidement des sites web dynamiques et interactifs.\n\nGrâce à un vaste écosystème de frameworks (comme Laravel, Symfony ou CodeIgniter) et de CMS (tels que WordPress, Drupal ou Joomla), PHP continue d’alimenter une grande partie d’Internet. Sa communauté active, ses milliers de bibliothèques et sa compatibilité avec presque tous les serveurs web en font un choix privilégié pour le développement web.\n\nMalgré l’émergence de nouveaux langages côté serveur, PHP reste apprécié pour sa robustesse, sa rapidité de déploiement et sa capacité à gérer des projets de toutes tailles, allant de simples blogs à des plateformes e-commerce complexes. Son évolution continue avec PHP 8 et les versions ultérieures garantit une performance optimisée et des fonctionnalités modernes pour les développeurs.','2025-11-27 16:32:28'),(17,2,5,'Domaines ou Python excelle ! ','Python s’est imposé comme l’un des langages les plus polyvalents et populaires du monde. Grâce à sa syntaxe simple, sa grande lisibilité et son immense écosystème de librairies, il brille dans de nombreux domaines :\n\n1. Data Science et Analyse de données\nPython est devenu la référence pour traiter, analyser et visualiser des données. Des librairies comme Pandas, NumPy, Matplotlib ou Seaborn permettent de manipuler de grandes quantités d’informations avec facilité.\n\n2. Intelligence Artificielle et Machine Learning\nC’est le langage numéro un pour l’IA et le machine learning. Des frameworks comme TensorFlow, PyTorch ou Scikit-learn offrent des outils puissants pour créer des modèles, entraîner des réseaux neuronaux ou développer des systèmes prédictifs.\n\n3. Développement Web\nAvec des frameworks tels que Django et Flask, Python permet de créer des sites web rapides, sécurisés et évolutifs. Il est très utilisé pour les backends modernes.\n\n4. Automatisation et Scripting\nPython excelle pour automatiser des tâches répétitives : extraction de données, gestion de fichiers, tests automatisés, interactions API… C’est un véritable couteau suisse du scripting.\n\n5. Cybersécurité\nGrâce à ses bibliothèques spécialisées (comme Scapy ou Paramiko) et à sa facilité d’utilisation, Python est largement utilisé pour l’analyse de vulnérabilités, la création d’outils de sécurité ou l’automatisation de tests.\n\n6. Développement d’Applications\nIl permet aussi de créer des applications bureautiques ou multiplateformes grâce à Tkinter, Kivy ou PyQt. Python est idéal pour prototyper rapidement des solutions robustes.\n\n7. Analyse scientifique et calcul avancé\nDans la recherche et l’ingénierie, Python est un standard. Ses bibliothèques comme SciPy ou SymPy en font un outil puissant pour le calcul scientifique, la modélisation et la simulation.','2025-12-02 15:20:03'),(19,1,5,'Quelle est la popularité de Java et pourquoi ?','Java est l’un des langages de programmation les plus populaires et les plus utilisés depuis plus de deux décennies. Sa longévité et sa présence dans presque tous les secteurs technologiques s’expliquent par plusieurs forces clés.\n\n1. Un langage robuste et polyvalent\nJava est conçu pour être stable, fiable et performant. Il est utilisé aussi bien pour des applications d’entreprise que pour des systèmes embarqués, des jeux ou des outils financiers. Cette polyvalence contribue fortement à sa popularité.\n\n2. Une compatibilité exceptionnelle (“Write Once, Run Anywhere”)\nSon principe fondateur — écrire le code une fois et l’exécuter partout — a fait de Java un choix incontournable pour les entreprises cherchant des solutions multiplateformes. La JVM (Java Virtual Machine) permet d’exécuter Java sur Windows, macOS, Linux et bien d’autres environnements.\n\n3. Un écosystème mature et riche\nJava possède un écosystème de frameworks et de librairies extrêmement complet : Spring, Hibernate, Jakarta EE, et bien d’autres. Cela permet de développer rapidement et efficacement des applications robustes.\n\n4. Très utilisé dans les grandes entreprises\nLes systèmes bancaires, les assurances, les télécoms et les applications de gestion à large échelle reposent massivement sur Java. Sa fiabilité et sa capacité à gérer de fortes charges expliquent cette adoption durable.\n\n5. Une communauté mondiale solide\nJava bénéficie d’une immense communauté de développeurs, de ressources d’apprentissage, de forums et de mises à jour régulières. Cela garantit une évolution constante et un support permanent.\n\n6. Performance et sécurité\nJava offre une gestion mémoire automatisée, une sécurité intégrée et de bonnes performances, ce qui en fait un choix stable pour les applications critiques.','2025-12-02 15:27:45');
/*!40000 ALTER TABLE `articles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commentaires`
--

DROP TABLE IF EXISTS `commentaires`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `commentaires` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_user` int NOT NULL,
  `id_article` int NOT NULL,
  `contenu` text COLLATE utf8mb4_general_ci NOT NULL,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_commentaires_users` (`id_user`),
  KEY `fk_commentaires_articles` (`id_article`),
  CONSTRAINT `fk_commentaires_articles` FOREIGN KEY (`id_article`) REFERENCES `articles` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_commentaires_users` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commentaires`
--

LOCK TABLES `commentaires` WRITE;
/*!40000 ALTER TABLE `commentaires` DISABLE KEYS */;
INSERT INTO `commentaires` VALUES (20,5,19,'Moi j\'apprécie beaucoup plus coder en Java qu\'en Python car en Java les variables sont typé de manière très clair et cela facilite beaucoup la compréhension du code écrit par d\'autres! Et de plus cela facilite beaucoup aussi pour corriger les erreurs!','2025-12-04 15:25:58'),(21,5,10,'Moi aussi j\'aime bcp Angular! ','2025-12-04 21:10:59'),(22,6,17,'Moi personnelement,je suis data scientist, et j\'aime bcp plus python car il permet d\'écrire des petits scripts très rapidement et facilement! Et avec l\'indentation obligatoire, le code est bien lisible, peu importe qui l\'a écrit!','2025-12-15 12:05:49'),(23,5,17,'Bonjour Profil2! Moi je suis developpeur logiciel en Java, et moi j\'aime bien la structure carré et l\'architecture en couches avec le framework spring boot! Mais c\'est vrai que j\'aime bien aussi Python pour des scripts rapides et simples!','2025-12-15 12:10:03');
/*!40000 ALTER TABLE `commentaires` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `themes`
--

DROP TABLE IF EXISTS `themes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `themes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `description` varchar(1000) COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'Aucune description',
  PRIMARY KEY (`id`),
  UNIQUE KEY `nom` (`nom`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `themes`
--

LOCK TABLES `themes` WRITE;
/*!40000 ALTER TABLE `themes` DISABLE KEYS */;
INSERT INTO `themes` VALUES (1,'Java','Java est un langage robuste et portable. Il excelle pour les applications d\'entreprise et Android. Sa communauté et ses bibliothèques sont très riches.'),(2,'Python','Python est simple et lisible. Idéal pour le scripting, la data science et l\'IA. Sa flexibilité et ses frameworks le rendent très populaire.'),(3,'ReactJs','ReactJs est une bibliothèque JavaScript moderne. Elle permet de créer des interfaces utilisateurs dynamiques. Elle est rapide, modulable et maintenue par Facebook.'),(4,'Angular','Angular est un framework complet pour le web. Il facilite la création d\'applications complexes et modulaires. Il fournit des outils intégrés pour routing et tests.'),(5,'IA','L\'Intelligence Artificielle permet d\'automatiser et d\'optimiser les tâches. Elle s\'applique au machine learning, NLP et vision par ordinateur. Elle ouvre la voie à l’innovation dans tous les secteurs.'),(6,'Php','PHP est un langage web très répandu. Il est facile à déployer et idéal pour les sites dynamiques. Il possède un large écosystème et de nombreux frameworks.');
/*!40000 ALTER TABLE `themes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `mot_de_passe` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (5,'Yoel','yoel.illouz@free.fr','$2a$10$.tvxtRaDfCW9B0sZhZ4mb.639INquynVZU9MDydSsJUN2aTR0UNLO'),(6,'Profil2','yoel.illouz@gmail.com','$2a$10$xMam0d3e/.9B31zxBnePj.vgzKpM8RT/RcHAhObRXrkEk6upgs6KK');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-12-23 19:37:56


```

### Générer la JavaDoc du projet

- Dans votre terminal, aller dans : LeCheminDeVotreProjet\Developpez-une-application-full-stack-complete\back>
- Puis une fois à cet endroit, taper: mvn javadoc:javadoc
- Cela va générer la JavaDoc du projet
- Puis pour voir la JavaDoc dans votre navigateur, aller dans le dossier :
  LeCheminDeVotreProjet\Developpez-une-application-full-stack-complete\back\target\site\apidocs\index.html

### Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The application will automatically reload if you change any of the source files.

### Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory.

### Where to start

As you may have seen if you already started the app, a simple home page containing a logo, a title and a button is available. If you take a look at its code (in the `home.component.html`) you will see that an external UI library is already configured in the project.

This library is `@angular/material`, it's one of the most famous in the angular ecosystem. As you can see on their docs (https://material.angular.io/), it contains a lot of highly customizable components that will help you design your interfaces quickly.

Note: I recommend to use material however it's not mandatory, if you prefer you can get rid of it.

Good luck!
