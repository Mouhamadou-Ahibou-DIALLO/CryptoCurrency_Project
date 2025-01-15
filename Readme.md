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
- **Portefeuille virtuel** :
    - Ajout, mise à jour et suppression de transactions.
    - Calcul de gains et perte.
    - graphe interactif des transactions.

---

## **Technologies Utilisées**

### **Backend**
- **Framework** : Spring Boot
- **Langage** : Java.
- **Base de données** : SQLite3.

### **Frontend**
- **Framework** : React js.

### **Tests**
- Tests unitaires/intégration : JUnit, Mockito, gitHub actions.
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
- **SQLite3**.
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
Dans une autre terminal :
- **cd frontend**
- **npm install**
- **npm start**

Pour accéder à l'application par navigateur web:
- **http://localhost:3000/**

### **Pour lancer les tests**
- **mvn test**

### **Test de performance**
- **1) Installer k6
Commencez par installer k6 :
-**sudo apt update**
- **sudo apt install -y gnupg software-properties-common ca-certificates**
- **curl -s https://dl.k6.io/key.gpg | sudo gpg --dearmor --output /usr/share/keyrings/k6-archive-keyring.gpg**
- **echo "deb [signed-by=/usr/share/keyrings/k6-archive-keyring.gpg] https://dl.k6.io/deb stable main" | sudo tee /etc/apt/sources.list.d/k6.list**
- **sudo apt update**
- **sudo apt install k6**

- **2) Ecrire les scripts de tests**
  Les scripts de test de k6 sont écrits en JavaScript. Par exemple, pour tester un endpoint HTTP, créez un fichier nommé test.js :

- **3) Exécuter les tests**
- **k6 run test.js**

- **4) Générer un rapport détaillé**
- **k6 run --out json=result.json test.js**
Et les résultats seront rapportés dans le fichier result.json
     
### **Dockerisation et déploiement**
Construisez les images Docker :
- Pour le backend: **docker build -t my-backend**
- Pour le frontend: d'abord **cd frontend**,et après faire: **docker build -t my-frontend**
- Lancer les services avec Docker Compose: **docker-compose up --build**

### **Déployer sur Kubernetes**
kubectl apply -f k8s/

# Structure du Projet

CryptoCurrency_Project/
- **├── backend/                           # Spring Boot backend**
- **│   ├── src/**
- **│   │   ├── main/**
- **│   │   │   ├── java/**
- **│   │   │   │   ├── com/cryptomarket/**
- **│   │   │   │   │   ├── controller/     # Contrôleurs pour l'application web**
- **│   │   │   │   │   ├── model/          # Modèles de données**
- **│   │   │   │   │   ├── service/        # Logique métier pour la collecte, analyse et prévisions**
- **│   │   │   │   │   ├── repository/     # Accès à la base de données**
- **│   │   │   │   │   ├── scheduler/      # Tâches périodiques de collecte des données**
- **│   │   │   │   │   └── security/       # Gestion de la sécurité**
- **│   │   │   ├── resources/**
- **│   │   │   │   ├── application.properties  # Configuration de l'application**
- **│   ├── Dockerfile                       # Dockerfile pour créer une image du backend**
- **│**
- **├── frontend/                           # Frontend avec visualisation des données**
- **│   ├── public/**
- **│   ├── src/**
- **│   │   ├── components/                  # Composants React (si utilisé)**
- **│   │   ├── services/                    # Services pour récupérer des données**
- **│   │   └── App.js                       # Composant principal**
- **│   └── package.json                     # Dépendances frontend**
- **│**
- **└── docker-compose.yml                   # Fichier pour orchestrer les services Docker**

# Auteur
- **DIALLO Mouhamadou Ahibou**
- **Étudiant à l'Université d’Avignon au CERI**
- **Email : mouhamadou-ahibou.diallo@alumni.univ-avignon.fr**


- **Qualité code
[![Quality Gate Status](http://192.168.57.101:9000/api/project_badges/measure?project=MyProject-Crypto&metric=alert_status&token=sqb_f3eeb70cb07402e822e3c9eb3036d143f6a71371)](http://192.168.57.101:9000/dashboard?id=MyProject-Crypto)

- **Taux de Couvertures
[![Coverage](http://192.168.57.101:9000/api/project_badges/measure?project=MyProject-Crypto&metric=coverage&token=sqb_f3eeb70cb07402e822e3c9eb3036d143f6a71371)](http://192.168.57.101:9000/dashboard?id=MyProject-Crypto)

- **Nombres de lignes dupliqués
[![Duplicated Lines (%)](http://192.168.57.101:9000/api/project_badges/measure?project=MyProject-Crypto&metric=duplicated_lines_density&token=sqb_f3eeb70cb07402e822e3c9eb3036d143f6a71371)](http://192.168.57.101:9000/dashboard?id=MyProject-Crypto)

- **Sécurité Hotspots
[![Security Hotspots](http://192.168.57.101:9000/api/project_badges/measure?project=MyProject-Crypto&metric=security_hotspots&token=sqb_f3eeb70cb07402e822e3c9eb3036d143f6a71371)](http://192.168.57.101:9000/dashboard?id=MyProject-Crypto)

- **Reliability Rating
[![Reliability Rating](http://192.168.57.101:9000/api/project_badges/measure?project=MyProject-Crypto&metric=software_quality_reliability_rating&token=sqb_f3eeb70cb07402e822e3c9eb3036d143f6a71371)](http://192.168.57.101:9000/dashboard?id=MyProject-Crypto)

- **Security Issues
[![Security Issues](http://192.168.57.101:9000/api/project_badges/measure?project=MyProject-Crypto&metric=software_quality_security_issues&token=sqb_f3eeb70cb07402e822e3c9eb3036d143f6a71371)](http://192.168.57.101:9000/dashboard?id=MyProject-Crypto)

- **Maintainability Issues
[![Maintainability Issues](http://192.168.57.101:9000/api/project_badges/measure?project=MyProject-Crypto&metric=software_quality_maintainability_issues&token=sqb_f3eeb70cb07402e822e3c9eb3036d143f6a71371)](http://192.168.57.101:9000/dashboard?id=MyProject-Crypto)


# Liens Utiles
- **Documentation de CoinCap API: https://docs.coincap.io/#ee30bea9-bb6b-469d-958a-d3e35d442d7a** 
- **Spring Boot Documentation: https://docs.spring.io/spring-boot/documentation.html**
- **Docker Documentation: https://docs.docker.com/**
- **Kubernetes Documentation: https://kubernetes.io/docs/**
- **Swagger Documentation: https://swagger.io/**

