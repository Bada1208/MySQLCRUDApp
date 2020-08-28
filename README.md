# MySQLCRUDApp
Java, MySQL, JDBC, Maven, Liquibase, JUnit, Mockito
Необходимо реализовать консольное CRUD приложение, которое взаимодействует с БД и позволяет выполнять все CRUD операции над сущностями:
Customer
Specialty
Account
AccountStatus (enum ACTIVE, BANNED, DELETED)

Customer-> Set<Specialty> specialties+ Account account
Account -> AccountStatus
Требования:

Придерживаться шаблона MVC (пакеты model, repository, service, controller, view)
Для миграции БД использовать https://www.liquibase.org/
Сервисный слой приложения должен быть покрыт юнит тестами (junit + mockito).
Для импорта библиотек использовать Maven
Результатом выполнения проекта должен быть отдельный репозиторий на github, с описанием задачи, проекта и инструкцией по локальному запуску проекта.

Технологии: Java, MySQL, JDBC, Maven, Liquibase, JUnit, Mockito
