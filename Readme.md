# Application de Suivi des Marchés des Crypto Monnaies

# Description
Cette application se divise en deux parties :
Une application console qui collecte périodiquement les données des marchés des crypto-monnaies à partir d'une API publique et les stocke dans une base de données.
Une application web permettant de visualiser ces données via des graphiques interactifs, de configurer des alertes personnalisées et d'effectuer des prévisions basées sur des algorithmes simples.
Le projet met l’accent sur les bonnes pratiques de développement logiciel, notamment :
. Méthodologie agile (Scrum).
. Tests unitaires, de performance et de sécurité.
. CI/CD et déploiement avec Docker et Kubernetes.

# Clé API
05a43613-1499-48ad-a603-4715c9754dcf

# Fonctionnalités
# Application Console
Collecte périodique des données (prix, volumes d’échange, etc.).
Stockage des données dans une base de données relationnelle.
Logs des opérations.

# Application Web
. Visualisation :
. Graphiques interactifs : courbes de prix, chandeliers, heatmaps.
. Navigation par plage de temps et sélection de crypto-monnaies.
. Alertes :
  . Configuration de seuils (prix, variations).
  . Notifications par email.
. Prévisions :
  . Algorithmes simples : moyennes mobiles, régressions linéaires.
  . Affichage des marges d’erreur.

# Technologies Utilisées
# Backend
. Framework : Spring Boot (avec prise en charge de HTTPS)
. Langage : Java
. Base de données : SQLite

# Frontend
. Framework : Thymeleaf

# Tests
. Tests unitaires et d’intégration : JUnit, Mockito
. Tests de performance : k6
. Tests de sécurité : OWASP ZAP

# CI/CD et Déploiement
. Versionnement : GitHub
. CI/CD : GitHub Actions (ou GitLab CI)
. Conteneurisation : Docker
. Orchestration : Kubernetes (Minikube en local)

# Monitoring
Prometheus et Grafana

# Prérequis
Avant de démarrer, assurez-vous d’avoir installé :
. Java 17 ou une version supérieure.
. Docker et Kubernetes (Minikube pour local).
. Une base de données (SQLite).
. Un compte GitHub pour le versionnement.

# Installation et Lancement
1. Cloner le repository
git clone https://github.com/Mouhamadou-Ahibou-DIALLO/CryptoCurrency_Project

2. Lancez l'application console
Compilez et lancez :
mvn clean install
java -jar target/crypto-collector.jar

3. Lancez l'application web
Démarrez le backend Spring Boot :
mvn spring-boot:run
lancer le frontend
	cd frontend
npm install
npm start
     
4. Dockerisation et déploiement
Construisez les images Docker :
docker-compose up --build

# Déployer sur Kubernetes
kubectl apply -f k8s/

# Structure du Projet

CryptoCurrency_Project/
├── backend/                           # Spring Boot backend
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   ├── com/cryptomarket/
│   │   │   │   │   ├── controller/     # Contrôleurs pour l'application web
│   │   │   │   │   ├── model/          # Modèles de données
│   │   │   │   │   ├── service/        # Logique métier pour la collecte, analyse et prévisions
│   │   │   │   │   ├── repository/     # Accès à la base de données
│   │   │   │   │   ├── scheduler/      # Tâches périodiques de collecte des données
│   │   │   │   │   └── security/       # Gestion de la sécurité
│   │   │   ├── resources/
│   │   │   │   ├── application.properties  # Configuration de l'application
│   ├── Dockerfile                       # Dockerfile pour créer une image du backend
│
├── frontend/                           # Frontend avec visualisation des données
│   ├── public/
│   ├── src/
│   │   ├── components/                  # Composants React (si utilisé)
│   │   ├── services/                    # Services pour récupérer des données
│   │   └── App.js                       # Composant principal
│   └── package.json                     # Dépendances frontend
│
└── docker-compose.yml                   # Fichier pour orchestrer les services Docker

# Auteur
. DIALLO Mouhamadou Ahibou
. Étudiant à l'Université d’Avignon au CERI
. Email : mouhamadou-ahibou.diallo@alumni.univ-avignon.fr

# Rapports et Badges
#                  Bagdes                STATUT
# Build CI/CD


# Couverture de Tests




# Liens Utiles
Documentation de CoinCap API: https://docs.coincap.io/#ee30bea9-bb6b-469d-958a-d3e35d442d7a 
Spring Boot Documentation: https://docs.spring.io/spring-boot/documentation.html

