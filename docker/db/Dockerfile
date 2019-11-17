FROM mariadb/server:10.1

ENV DATABASE_URL=127.0.0.1
ENV DATABASE_PORT=10301

# Database and user
ENV MARIADB_ROOT_PASSWORD pass
ENV MARIADB_DATABASE tunturi
ENV MARIADB_USER user
ENV MARIADB_PASSWORD pass

# Spring datasource properties that will be overwritten
ENV spring.datasource.url=jdbc:mariadb://$DATABASE_URL:$DATABASE_PORT/tunturi
ENV spring.datasource.username=user
ENV spring.datasource.password=pass
ENV spring.datasource.driver-class-name=org.mariadb.jdbc.Driver

CMD ["--character-set-server=utf8mb4", "--collation-server=utf8mb4_general_ci", "--skip-character-set-client-handshake"]
