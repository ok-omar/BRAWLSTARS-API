
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

- Before the first execution of the project, first you have to navigate to `src/model/api/official/creds.java` and update the variable `teacherApiKey` with your own key and update the function `getApiKey()` per the instructions in the comments.
- After you finish, go ahead and execute the `src/Main.java`
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


