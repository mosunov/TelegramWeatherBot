# Telegram Weather Bot

Телеграм бот, который взаимодействует с пользователем, получая его координаты, 
время оповещения (задается пользователем) и конфигурирует по желанию пользователя 
выдаваемую информацию о текущей погоде в его местоположении.

Работа приложения основана на обмене HTTP запросами между различными API

### Список доступных команд:
start - выводит в чат приветствие 
help - выводит список доступных команд
weather - выводит погоду в текущем местоположении 
(только для мобильных устройств)
settings - настройка основных условий 
(Время вывода текущей погоды,Ручное задание местоположения) 
добавится в будущем

### В ходе разработки были использованы:
* Java 8
* Maven - средство сборки
* postgresql - в качестве базы данных
* Spring Data JPA - для взаимодействия с БД
* Spring Boot
* Telegram API
* Open Weather API  
* lombok - для уменьшения количества кода
* Flyway - для миграции БД

