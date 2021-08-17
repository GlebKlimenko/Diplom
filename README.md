# Дипломный проект профессии «Тестировщик»
## Документы
* [План автоматизации](https://github.com/GlebKlimenko/Diplom/blob/master/documentation/Plan.md)
* [Отчет по итогам тестирования](https://github.com/GlebKlimenko/Diplom/blob/master/documentation/Report.md)
* [Отчет по итогам автоматизации](https://github.com/GlebKlimenko/Diplom/blob/master/documentation/Summary.md)

Дипломный проект представляет собой автоматизацию тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка.

### Настройка тестируемого приложения
Необходимо отредактировать файл `application.properties`:
 - Вписать IP адрес или URL по которому доступен Docker в параметрах: `spring.credit-gate.url`, `spring.payment-gate.url`, `spring.datasource.url`.
 - При прохождении тестов на MySQL необходимо написать:
 ```
 spring.datasource.url=jdbc:mysql://localhost:3306/app
 ```
 - При прохождении тестов на PostgerSQL необходимо написать:
 ```
 spring.datasource.url=jdbc:postgresql://localhost:5432/app
 ```

## Для запуска тестов необходимо
1. Скопировать проект;
2. Запустить контейнеры командой, при этом у вас уже должен быть установлен Docker:

 ```
 docker-compose up -d
 ```
3. Запустить приложение командой: 
(по умолчанию приложение работает с базой MySQl);

```
java -jar ./artifacts/aqa-shop.jar
```

Для работы с базой PostgreSQL запустить приложение командой:

```
java -jar ./artifacts/aqa-shop.jar --spring.datasource.url=jdbc:postgresql://localhost:5432/app
```

4. Запустить автотесты командой: 

- для Windows: 
```
gradlew clean test
```

- для *nix системы: 
```
./gradlew clean test
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
