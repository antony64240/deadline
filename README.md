<h1 align="center">Hi 👋, I'm Adam</h1>
<h3 align="center">A passionate developer from France</h3>

<h3 align="left">Business calendar library:</h3>
<br/>
<h6>the library allows you to calculate open days based on the opening hours of an entity</h6>



## Case 1 : 


<h6>You want to specify one calendar because all the teams have the same working hour :</h6>

```java
    @Bean
    public Calendar calendar() throws DeadLineException {
        return Calendar.createCalendar()
                .createPeriod(DayOfWeek.MONDAY, "9:00", "17:00")
                .createPeriod(DayOfWeek.TUESDAY, "10:00", "12:00")
                .createPeriod(DayOfWeek.TUESDAY, "14:00", "18:00")
                .addYearlyOffDay(01, 01, 100)
                .addOffDay(29, 05, 2023)
                .build();
    }
```

<h6>You can now use it :</h6>

```java

   Date date = calendar.calcDeadLine("PT11M", new Date());

```


## Case 2 : 


<h6>Now you want to specify several calendar for different teams that do not have the same working hour :</h6>

```java
        @Bean
        public CalendarManager calendarManager() throws DeadLineException {
            Calendar calendar = Calendar.createCalendar()
                .createPeriod(DayOfWeek.MONDAY, "9:00", "17:00")
                .createPeriod(DayOfWeek.TUESDAY, "10:00", "12:00")
                .createPeriod(DayOfWeek.TUESDAY, "14:00", "18:00")
                .addYearlyOffDay(01, 01, 100)
                .addOffDay(29, 05, 2023)
                .build();
    
            Calendar calendar2 = Calendar.createCalendar()
                .createPeriod(DayOfWeek.MONDAY, "9:00", "17:00")
                .createPeriod(DayOfWeek.TUESDAY, "10:00", "12:00")
                .createPeriod(DayOfWeek.TUESDAY, "14:00", "21:00")
                .createPeriod(DayOfWeek.THURSDAY, "14:00", "18:00")
                .addYearlyOffDay(01, 01, 100)
                .addOffDay(29, 05, 2023)
                .build();
    
            return CalendarManager
                .createCalendarManager()
                .addCalendar("MONDAY_TUESDAY", calendar)
                .addCalendar("MONDAY_THURSDAY", calendar2)
                .forceSystemDeadLineOnThrow(true) // default is false
                .build();
        }
```

<h6>You can now use it :</h6>

```java

   Date date = calendarManager.calcDeadLine("MONDAY_TUESDAY", "PT11M", new Date());

```

<h6>Inside we receive a date and we convert it to UTC and then convert it to date. But you don’t need to pay attention to this.


<h6>For add in java project : </h6>
    
```java
<dependency>
  <groupId>org.hdc.product</groupId>
  <artifactId>duration-deadline</artifactId>
  <version>1.0.1</version>
</dependency>
```

<h3 align="left">Connect with me:</h3>
<p align="left">
<a href="https://linkedin.com/in/adam-mezzasalma-b7ba80206/" target="blank"><img align="center" src="https://raw.githubusercontent.com/rahuldkjain/github-profile-readme-generator/master/src/images/icons/Social/linked-in-alt.svg" alt="https://www.linkedin.com/in/adam-mezzasalma-b7ba80206/" height="30" width="40" /></a>
</p>

<h3 align="left">Languages and Tools:</h3>
<p align="left"> <a href="https://angular.io" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/angularjs/angularjs-original-wordmark.svg" alt="angularjs" width="40" height="40"/> </a> <a href="https://www.docker.com/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/docker/docker-original-wordmark.svg" alt="docker" width="40" height="40"/> </a> <a href="https://www.elastic.co" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/elastic/elastic-icon.svg" alt="elasticsearch" width="40" height="40"/> </a> <a href="https://git-scm.com/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/git-scm/git-scm-icon.svg" alt="git" width="40" height="40"/> </a> <a href="https://www.java.com" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="java" width="40" height="40"/> </a> <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/javascript/javascript-original.svg" alt="javascript" width="40" height="40"/> </a> <a href="https://www.elastic.co/kibana" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/elasticco_kibana/elasticco_kibana-icon.svg" alt="kibana" width="40" height="40"/> </a> <a href="https://kubernetes.io" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/kubernetes/kubernetes-icon.svg" alt="kubernetes" width="40" height="40"/> </a> <a href="https://www.linux.org/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/linux/linux-original.svg" alt="linux" width="40" height="40"/> </a> <a href="https://mariadb.org/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/mariadb/mariadb-icon.svg" alt="mariadb" width="40" height="40"/> </a> <a href="https://www.mongodb.com/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/mongodb/mongodb-original-wordmark.svg" alt="mongodb" width="40" height="40"/> </a> <a href="https://nodejs.org" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/nodejs/nodejs-original-wordmark.svg" alt="nodejs" width="40" height="40"/> </a> <a href="https://www.oracle.com/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/oracle/oracle-original.svg" alt="oracle" width="40" height="40"/> </a> <a href="https://www.postgresql.org" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/postgresql/postgresql-original-wordmark.svg" alt="postgresql" width="40" height="40"/> </a> <a href="https://postman.com" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/getpostman/getpostman-icon.svg" alt="postman" width="40" height="40"/> </a> <a href="https://reactjs.org/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/react/react-original-wordmark.svg" alt="react" width="40" height="40"/> </a> <a href="https://spring.io/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" alt="spring" width="40" height="40"/> </a> </p>

