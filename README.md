# NBA Reports API

## Стек разработки  
- Java 17
- Spring Boot (ORM)
- PostgreSQL
- Liquibase
- Swagger

## Описание задачи
Разработать API для получения отчетов о игроках НБА.  

1. Импортировать данные из файла Excel в базу данных PostgreSQL.
2. **Отчеты:**
    1. Отчет о самых продолжительных карьерах в НБА. Вывести 20 самых продолжительных карьер, отсортированных по убыванию.
    2. Отчет о количестве игроков в каждой команде.

## Результат  
Документация API доступна на локальном хосте по адресу: Swagger UI

![image](https://github.com/abserro/reports-rest-api/assets/107203406/82dfcc8d-3a75-42ec-bb8d-2dd80a480044)

1. **Get-запрос на получение отчета о самых продолжительных карьерах (первые 20 игроков), сортировка по убыванию**

    1. Формат **json**
     Путь: __http://localhost:8080/api/v1/nba-players/report-longest-careers?format=json__  
     Результат:
```
[
  {
    "displayFirstLast": "Kevin Willis",
    "career": 22,
    "personId": 788,
    "toYear": 2006,
    "fromYear": 1984
  },
  {
    "displayFirstLast": "Mark Jones",
    "career": 21,
    "personId": 2891,
    "toYear": 2004,
    "fromYear": 1983
  },
  ...
]
```
_Аналогичный ответ для второго отчета_  

2.  **Get-запрос на получение отчета о количестве игроков в каждой команде**
   
    1. Формат **csv**
Путь: __http://localhost:8080/api/v1/nba-players/report-count-players-by-team?format=csv__  
Результат:

![image](https://github.com/abserro/reports-rest-api/assets/107203406/eaa0f330-7933-4d37-b73b-7e79af8c9f6f)

```csv
TEAM_ID,"TEAM_CODE","TEAM_CITY","TEAM_ABBREVIATION","TEAM_NAME","COUNT"
1610612740,"pelicans","New Orleans","NOP","Pelicans","17"
1610612741,"bulls","Chicago","CHI","Bulls","16"
1610612763,"grizzlies","Memphis","MEM","Grizzlies","16"
1610612765,"pistons","Detroit","DET","Pistons","16"
1610612760,"thunder","Oklahoma City","OKC","Thunder","15"
1610612747,"lakers","Los Angeles","LAL","Lakers","15"
1610612766,"hornets","Charlotte","CHA","Hornets","15"
1610612757,"blazers","Portland","POR","Trail Blazers","15"
1610612758,"kings","Sacramento","SAC","Kings","15"
1610612751,"nets","Brooklyn","BKN","Nets","15"
и так далее...
```

_Аналогичный ответ для второго отчета_  

## Решение
1. Создание Spring Boot приложения и подключение зависимостей ([Spring Initializr](https://start.spring.io/))
   
   <img src="https://github.com/abserro/reports-rest-api/assets/107203406/8dc0e6a9-e62f-4e2e-90a1-3a3bd38c1488" height="50%"> 

2. Создание базы данных (**nba_player_db**) в PostgreSQL (pgAdmin)

3. Создание миграций:
    - Создание таблицы:
        [2023-11-14-create-table.yml](https://github.com/abserro/reports-rest-api/blob/master/src/main/resources/db/changelog/v.1.0.0/2023-11-14-create-table.yml)
    - Загрузка данных из CSV файла
        [2023-11-14-load-data-table.yml](https://github.com/abserro/reports-rest-api/blob/master/src/main/resources/db/changelog/v.1.0.0/2023-11-14-load-data-table.yml)
      
      _**ПРИМЕЧАНИЕ**: исходный файл "[NBA-playerlist.csv](https://github.com/abserro/reports-rest-api/blob/master/src/main/resources/csv/NBA-playerlist.csv)" был изменен: удалены пронумерованные значения в первом столбце и заменены на автоинкремент_
      
4. Работа с запросами на отчет (JPQL)  
    1)
   ```jpql
   SELECT p.personId as personId, p.displayFirstLast as displayFirstLast, p.fromYear as fromYear, p.toYear as toYear, (p.toYear - p.fromYear) as career
   FROM NBAPlayerEntity p
   ORDER BY (p.toYear - p.fromYear) DESC, p.personId
   ```
   
    2) 
    ```jpql
    SELECT p.teamId as teamId, p.teamCode as teamCode, p.teamCity as teamCity, p.teamAbbreviation as teamAbbreviation, p.teamName as teamName, COUNT(p) as count
    FROM NBAPlayerEntity p
    WHERE p.teamName != '' AND p.teamName IS NOT NULL
    GROUP BY p.teamName, p.teamId, p.teamCode, p.teamCity, p.teamAbbreviation
    ORDER BY COUNT(p) DESC
   ```
    
5. Создание GET-запросов для получения отчетов в форматах JSON и CSV
6. Связывание репозитория и контроллера через сервис
7. Добавление дополнительной логики в сервис
