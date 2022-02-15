# Автоматичское тестирование Api и UI сайта http://test-app.d6.dev.devcaz.com/

##Установка
Для запуска Ui тесто необходимо скачать chromedriver.exe в путь
```bash
C:/WebDrivers/chromedriver.exe
```
Либо указать его местоположение в conf.properties в driverPath

## Запуск Api тестов
```bash
.\gradlew test
```
## Запуск UI тестов
```bash
.\gradlew cucumber
```