# github-repos

## Motivation
1. Create an Android Client for GitHub API without using 3rd party libs.
2. Create a fully tested Android application using best practices as:
    - Clean Code.
    - Dependency Injection.
    - Inversion of Control.
    - Clean Architecture.
    - Test Driven Development.

## Features
- Load repositories by github username.

## Build
To build this project run the following command on the root of the project.

```gradle
gradlew clean assemble
```

## Test
To run unit tests execute the following command on the root of the project.
```gradle
gradlew clean test
```

## UI Test
To run UI tests execute the following command on the root of the project.
```gradle
gradlew clean connectedAndroidTest
```

## TODO
- Asynchronously load the last commit.
- Add a Data Persistency Layer.
- Configure Firebase Test Lab.
- Configure CircleCI to send code coverage and static code analysis to http://sonarcloud.io/
- Setup local server for integration tests.

## Credits
This project heavily relies on concepts from:

- [Clean Code: A Handbook of Agile Software Craftsmanship](https://www.amazon.com/Clean-Code-Handbook-Software-Craftsmanship/dp/0132350882): Robert C. Martin book about writing good and readable code.
- [Ruby Midwest 2011 - Keynote: Architecture the Lost Years by Robert Martin](https://www.youtube.com/watch?v=WpkDN78P884): Robert C. Martin talk about Clean Architecture.
- [Test Driven Development: By Example](https://www.amazon.com/Test-Driven-Development-Kent-Beck/dp/0321146530): Kent Beck book about how to develop software guided by tests.