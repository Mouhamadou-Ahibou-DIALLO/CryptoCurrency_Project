# **Application de Suivi des Marchés des Crypto-Monnaies**

## **Description**
Cette application se compose de deux parties principales :
1. **Application Console** : collecte périodique des données des marchés des crypto-monnaies à partir d'une API publique et les stocke dans une base de données.
2. **Application Web** : visualise les données à l'aide de graphiques interactifs, configure des alertes personnalisées et effectue des prévisions simples.

Le projet intègre :
- Méthodologie **Agile** (Scrum).
- Tests **unitaires**, de **performance**, et de **sécurité**.
- CI/CD avec **Docker** et **Kubernetes**.

---

## **Fonctionnalités**

### **Application Console**
- Collecte automatique des données (prix, volumes, etc.).
- Stockage des données dans une base relationnelle.
- Journaux d’opérations (logs).

### **Application Web**
- **Visualisation** :
    - Graphiques interactifs : courbes, chandeliers, heatmaps.
    - Filtrage par plage de temps ou crypto-monnaies.
- **Alertes** :
    - Notifications configurables (prix, variations).
    - Envoi de notifications par email.
- **Prévisions** :
    - Algorithmes : moyennes mobiles, régressions linéaires.
    - Visualisation des marges d'erreur.

---

## **Technologies Utilisées**

### **Backend**
- **Framework** : Spring Boot (support HTTPS).
- **Langage** : Java.
- **Base de données** : SQLite.

### **Frontend**
- **Framework** : Thymeleaf.

### **Tests**
- Tests unitaires/intégration : JUnit, Mockito.
- Tests de performance : k6.
- Tests de sécurité : OWASP ZAP.

### **CI/CD**
- Versionnement : GitHub.
- CI/CD : GitHub Actions.
- Conteneurisation : Docker.
- Orchestration : Kubernetes (Minikube).

---

## **Installation et Lancement**

### **Prérequis**
Avant de commencer, assurez-vous d’avoir installé :
- **Java** (17 ou supérieur).
- **Docker** et **Kubernetes** (Minikube pour local).
- **SQLite**.
- **npm** (pour le frontend).

### **Cloner le repository**
git clone https://github.com/Mouhamadou-Ahibou-DIALLO/CryptoCurrency_Project '''

### **Lancer l'application console**
Compilez et lancez :
- **mvn clean install**
- **java -jar target/crypto-collector.jar**

### **Lancez l'application web**
Démarrez le backend Spring Boot :
- **mvn spring-boot:run**
- **lancer le frontend**
- **cd frontend**
- **npm install**
- **npm start**
     
### **Dockerisation et déploiement**
Construisez les images Docker :
- **docker-compose up --build**

### **Déployer sur Kubernetes**
kubectl apply -f k8s/

# Structure du Projet

CryptoCurrency_Project/
-**├── backend/                           # Spring Boot backend**
-**│   ├── src/**
-**│   │   ├── main/**
-**│   │   │   ├── java/**
-**│   │   │   │   ├── com/cryptomarket/**
-**│   │   │   │   │   ├── controller/     # Contrôleurs pour l'application web**
-**│   │   │   │   │   ├── model/          # Modèles de données**
-**│   │   │   │   │   ├── service/        # Logique métier pour la collecte, analyse et prévisions**
-**│   │   │   │   │   ├── repository/     # Accès à la base de données**
-**│   │   │   │   │   ├── scheduler/      # Tâches périodiques de collecte des données**
-**│   │   │   │   │   └── security/       # Gestion de la sécurité**
-**│   │   │   ├── resources/**
-**│   │   │   │   ├── application.properties  # Configuration de l'application**
-**│   ├── Dockerfile                       # Dockerfile pour créer une image du backend**
-**│**
-**├── frontend/                           # Frontend avec visualisation des données**
-**│   ├── public/**
-**│   ├── src/**
-**│   │   ├── components/                  # Composants React (si utilisé)**
-**│   │   ├── services/                    # Services pour récupérer des données**
-**│   │   └── App.js                       # Composant principal**
-**│   └── package.json                     # Dépendances frontend**
-**│**
-**└── docker-compose.yml                   # Fichier pour orchestrer les services Docker**

# Auteur
- **DIALLO Mouhamadou Ahibou**
- **Étudiant à l'Université d’Avignon au CERI**
- **Email : mouhamadou-ahibou.diallo@alumni.univ-avignon.fr**

# Rapports et Badges
#                  Bagdes                STATUT
# Build CI/CD


# Couverture de Tests




# Liens Utiles
- **Documentation de CoinCap API: https://docs.coincap.io/#ee30bea9-bb6b-469d-958a-d3e35d442d7a** 
- **Spring Boot Documentation: https://docs.spring.io/spring-boot/documentation.html**

