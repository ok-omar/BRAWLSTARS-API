
## About the Project
> This is a java program that consumes both Brawlstars and Brawlify's Brawlers Endpoints, combining both data in a single database, using Java, Gson and SQLITE for the database.

---

## 📦 Features

- List brawlers from the database, Official Brawlstars or Brawlify API.
- Search for a brawler by its ID in Both APIs, and update the database with the info if desired.
- Update the whole database from either from either Official Brawlstars or Brawlify API.
- Add the missing Brawlers to the database from either Official Brawlstars or Brawlify API.

---

## 🚀 Getting Started

- Before the first execution of the project, first you have to navigate to `src/model/api/official/creds.java` and update the variable `apiKey` with your own key.
- Next, go ahead and execute the `src/Main.java`
- The Gson and sqlite-jdbc libraries are in the `lib` folder, import them if needed.
---
## 📁 Project Structure
### Project tree:
```
├── src/
│   ├── controller/
│   │   └── BrawlersController.java
│   ├── model/
│   │   ├── api/
│   │   │   ├── BrawlersWrapper.java
│   │   │   ├── brawlify/
│   │   │   │   └── BrawlifyEndpoint.java
│   │   │   └── official/
│   │   │       ├── OfficialEndpoint.java
│   │   │       └── creds.java
│   │   ├── DAO/
│   │   │   ├── DBConnection.java
│   │   │   └── mysql/
│   │   │       ├── MySQLBrawlerDAO.java
│   │   │       ├── MySQLGadgetsDAO.java
│   │   │       └── MySQLStarpowersDAO.java
│   │   └── classes/
│   │       ├── Brawler.java
│   │       ├── BrawlerClass.java
│   │       ├── Gadget.java
│   │       ├── Rarity.java
│   │       └── StarPower.java
│   ├── view/
│   │   └── View.java
│   └── Main.java
└── brawlers.db
```
### Functionalities
##### User Interactions
- `Main.java`: Entry point and user interactions
- `view/View.java`: Console output / visualization for the Menus, Objects ..etc
##### API Intercations
- `model/api/brawlify/BrawlifyEndpoint.java`: Handles the calls/requests to the Brawlify API.
- `model/api/official/OfficialEndpoint.java`: Handles the request to the official Brawlstars API and saves it as a file in the same directory.
- `model/api/BrawlersWrapper.java`: Handles parsing the requests from both classes above.
##### Data Handling
- `model/classes`: Where all the java objects are located.
- `model/DAO`: Handles the CRUD Operations on the database.
##### Other:
- `controller/BrawlersController`: Handles the interactions / communication between the parts mentioned above.
---
## 🛠️ Tests that I have done
- List Brawlers from the database.
- List Brawlers from the Official Brawlstars API.
- List Brawlers from the Official Brawlstars API with incorrect API key / IP Address.
- List Brawlers from the Brawlify API.
- List Brawlers form the Brawlify API with the server down (ERROR 404).
- Insert all Brawlers from the Official Brawlstars API to the database.
- Insert all Brawlers from the Brawlify API to the database.
- Insert missing Brawlers from the Official Brawlstars API and Brawlify API to the database (after deleting brawlers manualy, this test has been done twice, one for the official API, one for the brawlify API).
- Update a Brawler from Brawlify API (The one that was already in the db was from the official API with missing rarity and class).
- Update a Brawler from Official Brawlstars API (The one that was already in the db was from the brawlify API with rarity and class).

\*These tests were done just before submitting the assigment, way more tests were done in course of the development, but these are the ones that I have done before submitting the assignment to make it easy to correct, to make it easier for you to "Try to break the program" by doing different tests than the mentioned above.


