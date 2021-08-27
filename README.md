# Дипломный проект профессии «Тестировщик»
## Документы
* [План автоматизации](https://github.com/GlebKlimenko/Diplom/blob/master/documentation/Plan.md)
* [Отчет по итогам тестирования](https://github.com/GlebKlimenko/Diplom/blob/master/documentation/Report.md)
* [Отчет по итогам автоматизации](https://github.com/GlebKlimenko/Diplom/blob/master/documentation/Summary.md)

Дипломный проект представляет собой автоматизацию тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка.


## Для запуска тестов необходимо
1. Скопировать проект;
2. Запустить контейнеры командой, при этом у вас уже должен быть установлен Docker:

 ```
 docker-compose up -d
 ```
3. Для работы с базой MySQL запустить приложение командой: 

```
java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar artifacts/aqa-shop.jar
```

Для работы с базой PostgreSQL запустить приложение командой:

```
java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -jar artifacts/aqa-shop.jar
```

4. Для работы с базой MySQL запустить автотесты командой: 

- для Windows: 
```
gradlew clean test -Ddb.url=jdbc:mysql://localhost:3306/app
```

- для *nix системы: 
```
./gradlew clean test -Ddb.url=jdbc:mysql://localhost:3306/app
```

 Для работы с базой PostgreSQL запустить автотесты командой: 

* для Windows: 
```
gradlew clean test -Ddb.url=jdbc:postgresql://localhost:5432/app
```
* для *nix системы: 
```
./gradlew clean test -Ddb.url=jdbc:postgresql://localhost:5432/app
```

5. Для создания отчета Allure запустить команду:
* для Windows: 
```
gradlew allureReport
```
и
```
gradlew allureServe
```

* для *nix системы: 
```
./gradlew allureReport
```
 и 
 ```
 ./gradlew allureServe
```
