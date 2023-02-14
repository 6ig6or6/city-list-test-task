This is an enterprise-grade "city list" application which allows you. to see the list of cities and update any of them if you have ROLE_ALLOW_EDIT role
To start the app on your local machine you need to do a few steps:
1. Ensure your Docker app is installed and currently running
2. Open docker directory in cmd and type "docker compose up -d"
3. Ensure you maven is installed and run "mvn spring-boot:run"

Also, you may run the app using your IDE

The first user to be registered will receive ROLE_ALLOWED_EDIT.

Swagger API will be available on http://localhost:8081/list-app/swagger-ui/index.html