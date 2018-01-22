# Курсовой проект, вариант № 7, Календарь выставок

## Задание на проект

Система **календарь выставок**. Существует список *выставочных залов*, в
каждом из которых есть перечень *экспозиций*. Посетитель покупает
*билеты*, оформив *платёж* и выбрав *тему выставки*.

## Требования

Необходимо построить веб-приложение, поддерживающую следующую функциональность:

1. На основе сущностей предметной области создать классы их описывающие.
2. Классы и методы должны иметь отражающую их функциональность названия и должны быть грамотно структурированы по пакетам
3. Информацию о предметной области хранить в БД, для доступа использовать API JDBC с использованием пула соединений, стандартного или разработанного самостоятельно. В качестве СУБД рекомендуется MySQL.
4. Приложение должно поддерживать работу с кириллицей (быть многоязычной), в том числе и при хранении информации в БД.
5. Код должен быть документирован.
6. Приложение должно быть покрыто Юнит-тестами
7. При разработке бизнес логики использовать сессии и фильтры, и события в системе обрабатывать с помощью Log4j.
8. В приложении необходимо реализовать Pagination, Transaction в зависимости от Вашего проекта
9. Используя сервлеты и JSP, реализовать функциональности, предложенные в постановке конкретной задачи.
10. В страницах JSP применять библиотеку JSTL 
11. Приложение должно корректно реагировать на ошибки и исключения разного рода (Пользователь никогда не должен видеть stack-trace на стороне front-end).
12. В приложении должна быть реализована система Авторизации и Аутентификации

## Оформление
1.	Оформление кода должно соответствовать Java Code Convention.
2.	Тестовые данные в БД должны быть корректными (не использовать в качестве данных: aaa, bbb, ccc), дабы не возникало проблем при презентации продукта.
3.	Проект должен быть залит на github/bitbucket/gitlab и иметь соответсвующий вид:
    1. Папка src.
    2.	README.md.
    3.	pom.xml / build.gradle.
4.	В проекте должен быть файл README с:
    1.	Описанием проекта (вашего варианта).
    2.	Инструкцией по установке.
    3.	Инструкцией по запуску приложения.

## Презентация

Презентация должна состоять с следующих слайдов:
1.	Титульная страница (кто Вы, кто Ваш Тренер).
2.	Ваш номер варианта и описание задачи.
3.	Общие требования к проекту.
4.	Архитектура проекта (что отвечает за M, V, C).
5.	Слайд БД (со связями), к примеру сгенерировать EER Model Вашей БД.
Всё остальные слайды которые Вы хотите от себя добавить – по Вашему желанию, они не являются обязательными.


## Архитектура
1.	Архитектура приложения должна соответствовать шаблону Model-View-Controller.
2.	webapp.
3.	Сборка приложения происходит при помощи Maven / Gradle. [Уметь собирать проект через консоль, не только нажимая зелёную кнопку Run].
4.	При реализации алгоритмов бизнес-логики использовать шаблоны:
    1. GoF: Factory Method, Command, Builder, Strategy, State, Observer etc.
    2. SOLID – понимать что это и применить в проекте 
    3. GRASP – по желанию

 
## Технологии

### Обязательные к использованию
1.	JSP + JSTL;
2.	Servlets;
3.	JDBC;
4.	Log4J;
5.	JUnit.

### Запрещённые к использованию
1.	Scriplets;
2.	Applets;
3.	Spring / EJB;
4.	Hibernate, JPA.

### Поощряемые
1.	Mockito;
2.	Apache commons;
3.	Slf4J;
4.	Собственные теги;
5.	Собственная Аннотация и Исключения.

## Установка и настройка
Приложение поставляется в виде единственного файла ".war", доступного [здесь](https://github.com/Myshj/exposition_manager/releases).

Этот файл предназначен для развёртывания в контейнере сервлетов, например [Tomcat](http://tomcat.apache.org/).

На данный момент приложение предполагает, что оно **единственное** на сервере (необходимо размещение его в папке **<сервер>/webapps/ROOT**).

Также приложению требуется для работы база данных [MySQL](https://www.mysql.com/).

Схема базы данных (формат MySQL Workbench) размещена в каталоге **docs/database/exposition_calendar.mwb**.

Чтобы соединить приложение с базой данных, необходимо отредактировать файл **<приложение>/WEB-INF/classes/application.properties**, где указать параметры соединения с базой данных.
