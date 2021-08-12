## Для запуска тестов необходимо
1. Скопировать проект;
2. Запустить контейнеры командой 'docker-compose up -d', при этом у вас уже должен быть установлен Docker;
3. Запустить приложение командой: 
* `java -jar ./artifacts/aqa-shop.jar` (по умолчанию приложение работает с базой MySQl); 

Для работы с базой PostgreSQL запустить приложение командой:
* `java -jar ./artifacts/aqa-shop.jar --spring.datasource.url=jdbc:postgresql://localhost:5432/app_`).

4. Запустить автотесты командой: 

- для Windows: `gradlew clean test`;
- для *nix системы: './gradlew clean test'.

 Для работы с базой PostgreSQL запустить автотесты командой: 

* для Windows: _'gradlew clean test -Ddb.url=jdbc:postgresql://localhost:5432/app'_;
* для *nix системы: './gradlew clean test -Ddb.url=jdbc:postgresql://localhost:5432/app'.

5. Для создания отчета Allure запустить команду:
* для Windows: _'gradlew allureReport'_ и _'gradlew allureServe'_;
* для *nix системы: './gradlew allureReport' и './gradlew allureServe'.
