# request-counter

This repository is a project of SpringBoot microservice created for 
recruitment process to empik.

## Description

The microservice retrieves data from https://api.github.com/users/[login} (e.g. https://api.github.com/users/octocat) and transmits it in the API in the form (Example of returned json by calling GET /users/{login}):

```json
{
    "id": 583231,
    "login": "octocat",
    "name": "The Octocat",
    "type": "User",
    "avatarUrl": "https://avatars.githubusercontent.com/u/583231?v=4",
    "createdAt": "2011-01-25T18:44:36",
    "calculations": 0.01726121979286536
}
```

The calculations field returns the result of the operation:
 6 / number_followers * (2 + number_public_repos).
The service saves the number of API calls for each login in the database. (Even if GIT api return Error every call GET /users/{login} is saved to db ) The service contains an internal h2 database with a User table which has the following columns: LOGIN and REQUEST_COUNT. The REQUEST_COUNT value is incremented by one for each service invocation.

