This is an enterprise-grade "city list" application which allows you to see the list of cities and update any of them if you have ROLE_ALLOW_EDIT role

To start the app on your local machine you need to do a few steps:
1. Ensure your Docker app is installed and currently running
2. Open docker directory in cmd and type "docker compose up -d"
3. Ensure you maven is installed and run "mvn spring-boot:run"

Also, you may run the app using your IDE

Initial list of cities will be populated from csv file using Liquibase.

Due to the absence of any requirements for registration process,
the first user to be registered will receive ROLE_ALLOWED_EDIT, others will get ROLE_USER.
This logic is to be improved as soon as there will be strict requirements.

Swagger API will be available on http://localhost:8081/list-app/swagger-ui/index.html