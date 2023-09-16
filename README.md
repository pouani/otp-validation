# Activation de Compte par OTP avec Angular et Java Spring Boot

Ce projet est une démonstration d'activation de compte par OTP (One-Time Password) en utilisant Angular pour le frontend et Java Spring Boot pour le backend. Il met en avant l'utilisation de Spring Security pour sécuriser l'application.

## Prérequis

Assurez-vous d'avoir installé les éléments suivants sur votre système :

- Docker
- Node.js et npm (pour le frontend Angular)
- Java JDK (pour le backend Spring Boot)
- Maven (pour la gestion des dépendances Java)

Assurez-vous que tous les conteneurs Docker sont en cours d'exécution avec la commande
- docker-compose up -d

Accédez au dossier frontend :
- cd otp-ui
- npm install
- ng serve
- [Angular CLI](https://github.com/angular/angular-cli) version 16.0.4.

## Configuration

### Backend (Java Spring Boot)

1. Clonez ce dépôt :
   ```shell
   git clone https://github.com/pouani/otp-validation.git
