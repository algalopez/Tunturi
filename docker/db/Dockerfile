FROM mariadb/server:10.3

ENV DATABASE_URL=127.0.0.1 \
    DATABASE_PORT=10301

# Database and user
ENV MARIADB_ROOT_PASSWORD=pass \
    MARIADB_DATABASE=tunturi \
    MARIADB_USER=user \
    MARIADB_PASSWORD=pass \
    MARIADB_INITDB_SKIP_TZINFO=1

# Spring datasource properties that will be overwritten
ENV spring.datasource.url=jdbc:mariadb://$DATABASE_URL:$DATABASE_PORT/tunturi \
    spring.datasource.username=user \
    spring.datasource.password=pass \
    spring.datasource.driver-class-name=org.mariadb.jdbc.Driver

CMD ["--character-set-server=utf8mb4", "--collation-server=utf8mb4_general_ci", "--skip-character-set-client-handshake"]
