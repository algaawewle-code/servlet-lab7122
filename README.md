# Servlet технологиясының негіздері: зертханалық жұмыстар

**Студент:** Аты-жөні  
**Топ:** ТОП-000  
**Оқытушы:** Аты-жөні

Технологиялар: JDK 17, Apache Tomcat 9 (`javax.servlet`), Maven.

## Іске қосу
1. `mvn clean package` командасы `target/lab.war` жасайды.
2. `lab.war` файлын Tomcat-тың `webapps/` папкасына салыңыз (немесе IDE арқылы іске қосыңыз).
3. Браузерде `http://localhost:8080/lab/` ашыңыз.

> Tomcat 10+ қолдансаңыз, барлық `javax.` дегенді `jakarta.` деп өзгертіңіз, `pom.xml`-де `jakarta.servlet-api` (6.0.0) қолданыңыз, `web.xml`-де Jakarta EE 10 схемасын қойыңыз.

## Жұмыстар тізімі

| № | Тақырып | Файл | Мекенжай |
|---|---------|------|----------|
| 1 | Алғашқы Servlet | HelloServlet, AboutServlet | /hello, /about |
| 2 | Өмірлік цикл | LifeServlet | /life |
| 3 | doGet параметрлері | ProfileServlet | /profile?name=..&age=..&city=.. |
| 4 | Форма және doPost | form.html, RegisterServlet | /form.html |
| 5 | Калькулятор | CalcServlet | /calc |
| 6 | Көп мәнді параметрлер | survey.html, SurveyServlet | /survey.html |
| 7 | Сұраныс ақпараты | InfoServlet | /info |
| 8 | Статус кодтары | StatusServlet, error404.html | /status?code=404 |
| 9 | Redirect / forward | GoServlet, TargetServlet | /go?type=redirect |
| 10 | Валидация | SignupServlet | /signup |
| 11 | Cookie | WelcomeServlet | /welcome?name=Аты |
| 12 | HttpSession | LoginServlet, CabinetServlet | /login, /cabinet |
| 13 | ServletContext | SiteServlet | /site |
| 14 | Filter | EncodingFilter, LogFilter, AuthFilter | барлық сұраныс |
| 15 | Пікір кітабы | GuestbookServlet | /guestbook |

Тест пайдаланушылары (12-жұмыс): `admin/1234`, `student/abcd`, `aigerim/qwerty`.

---

## Есеп

Әр жұмысқа скрин қосып (`screenshots/` папкасына салыңыз), 3–5 сөйлемдік қорытынды жазыңыз.

### 1-жұмыс
![lab01](screenshots/lab01.png)

**Қорытынды:** ...

### 2-жұмыс
![lab02](screenshots/lab02.png)

**Қорытынды:** ...

### 3-жұмыс
![lab03](screenshots/lab03.png)

**Қорытынды:** ...

### 4-жұмыс
![lab04](screenshots/lab04.png)

**Қорытынды:** ...

### 5-жұмыс
![lab05](screenshots/lab05.png)

**Қорытынды:** ...

### 6-жұмыс
![lab06](screenshots/lab06.png)

**Қорытынды:** ...

### 7-жұмыс
![lab07](screenshots/lab07.png)

**Қорытынды:** ...

### 8-жұмыс
![lab08](screenshots/lab08.png)

**Қорытынды:** ...

### 9-жұмыс
![lab09](screenshots/lab09.png)

**Қорытынды (redirect пен forward айырмашылығы, 3–4 сөйлем):** ...

### 10-жұмыс
![lab10](screenshots/lab10.png)

**Қорытынды:** ...

### 11-жұмыс
![lab11](screenshots/lab11.png)

**Қорытынды (HttpOnly мағынасы):** ...

### 12-жұмыс
![lab12](screenshots/lab12.png)

**Қорытынды (кірмей /cabinet ашсақ не болады):** ...

### 13-жұмыс
![lab13](screenshots/lab13.png)

**Қорытынды (Tomcat қайта қосылғанда санауыш не болады):** ...

### 14-жұмыс
![lab14](screenshots/lab14.png)

**Қорытынды:** ...

### 15-жұмыс
![lab15](screenshots/lab15.png)

**Қорытынды (escape() не үшін керек):** ...
