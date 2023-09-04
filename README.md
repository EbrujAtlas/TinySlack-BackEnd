# TinySlack

Bienvenus dans l'API de notre projet de certification de la POEC Java proposée par M2iFormation.

Celle-ci a été créée dans le but de fournir une petite application de messagerie semblable à Slack, permettant aux utilisateurs de créer de nouveaux canaux de communication et de s'envoyer des messages sur chacun d'entre eux.

Vous retrouverez la partie front du projet ici => [Frontend Tinyslack](https://github.com/EbrujAtlas/FrontEndCertif)

![Illustration Slack](https://images.unsplash.com/photo-1563986768609-322da13575f3?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1470&q=80)



## Spécifications du projet demandé

### Le projet à réaliser
* Une application de tchat en ligne, en prenant exemple sur Slack.
* Pas d’authentification.
* Pas de sécurisation de l’API.
* Un seul espace de travail constitue la page d’accueil de l’application.
* Un canal général par défaut est actif. Il n’est pas possible de renommer ou supprimer
le canal général.
* L’utilisateur doit pouvoir ajouter, renommer ou supprimer un canal.
* Dans chaque canal, l’utilisateur doit pouvoir ajouter des messages au format texte
uniquement à l’aide d’un formulaire.
* Dans chaque canal, les messages s’affichent au format texte avec le nom d’un utilisateur statique, la date et l’heure à laquelle le message a été ajouté.
* Tous les canaux et leurs messages doivent être enregistrés en base de données.
* L’application doit être responsive.
* Le design de l’application est libre.


### Consignes et bonus
* L’API et l’application tournent en local sur les machines des stagiaires le jour de la présentation, il n’est pas demandé de les mettre en ligne.
* L'option multi-utilisateurs impliquant l’utilisation d’un Websocket pour le temps réel n’est pas demandée.
* L’utilisateur est créé côté Backend ou côté Frontend de manière statique dans le code.
* Toute fonctionnalité supplémentaire donnera lieu à des points bonus à l’ensemble du groupe.

### Aspects techniques
#### Backend
* Base de données : MySQL
* API : Spring

#### Frontend
* Angular & Bootstrap

## Base de données
Pour réaliser ce projet, nous avons réalisée une base de données en MySQL, composée de 3 tables : Channels, Messages, Users.

Il s'agit d'une base de données relationnelle permettant de lier nos tables entre elles.

## Création du back
Notre API Spring Boot REST a été créée à l'aide de Maven et de différents starters de dépendances:
* **spring-boot-starter-data-jpa** : pour utiliser Spring et JPA, afin de communiquer avec une base de données
* **spring-boot-starter-web** : pour développer des applications web en utilisant Spring Boot
* **mysql-connector-j** : pilote JDBC spécifique à MySql pour se connecter à une BDD MySQL
* **spring-boot-starter-test** : ensemble de bibliothèques pour écrire et exécuter des tests unitaires et d'intégration

## Utilisation de l'API
Voici comment utiliser notre API :

1. Clonez le projet

```bash
  git clone https://github.com/EbrujAtlas/Projet_Jury.git
```

2. Placez vous dans le projet

```bash
  cd Projet_Jury
```

3. Lancez le serveur
---
### Channels

#### Get channels

```http
  GET /tinyslack/channels
```
#### Get channel

```http
  GET /tinyslack/channels/${name}
```
#### Get messages from channel

```http
  GET /tinyslack/channels/${name}/messages
```
#### Post channel

```http
  POST /tinyslack/channels
```
#### Delete channel

```http
  DELETE /tinyslack/channels/${name}
```
#### Put channel

```http
  PUT /tinyslack/channels/${name}
```
#### Patch channel

```http
  PATCH /tinyslack/channels/${name}
```
---
### Messages
#### Get messages

```http
  GET /tinyslack/messages
```
#### Get message

```http
  GET /tinyslack/messages/${id}
```
#### Post message

```http
  POST /tinyslack/messages
```
#### Delete message

```http
  DELETE /tinyslack/messages/${id}
```
#### Put message

```http
  PUT /tinyslack/messages/${id}
```
#### Patch message

```http
  PATCH /tinyslack/messages/${id}
```
---
### Users
#### Get users

```http
  GET /tinyslack/users
```
#### Get user

```http
  GET /tinyslack/users/${name}
```

#### Post user

```http
  POST /tinyslack/users
```
#### Delete user

```http
  DELETE /tinyslack/users/${name}
```
#### Put user

```http
  PUT /tinyslack/users/${name}
```
#### Patch user

```http
  PATCH /tinyslack/users/${name}
```
---
## Axes d'amélioration
* Réaliser des tests
* Chiffrer le mot de passe utilisateur avant son intégration en base
## Présentation de l'équipe

Pour réaliser ce projet, nous étions 4 :
* [Coralie Dubreuil](https://github.com/Ciyasan)
* [Ebru Jolivet](https://github.com/EbrujAtlas)
* [Florian Marchive](https://github.com/MarchiveFlorian)
* [Noellie Peuch](https://github.com/pandaka87)

Nous avons ainsi pu nous apporter nos compétences complémentaires et se diviser pour le travail en pair programming.