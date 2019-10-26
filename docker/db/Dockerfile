FROM mariadb/server:10.1

ENV MARIADB_ROOT_PASSWORD pass
ENV MARIADB_DATABASE tunturi
ENV MARIADB_USER user
ENV MARIADB_PASSWORD pass

CMD ["--character-set-server=utf8mb4", "--collation-server=utf8mb4_general_ci", "--skip-character-set-client-handshake"]
