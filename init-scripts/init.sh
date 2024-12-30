#!/bin/bash
set -e
export PGPASSWORD=$POSTGRES_PASSWORD;

# Create databases
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
CREATE DATABASE inventory_db;
CREATE DATABASE notification_db;
CREATE DATABASE order_db;
CREATE DATABASE payment_db;
CREATE DATABASE product_db;
CREATE DATABASE user_db;
EOSQL

# Create users
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
CREATE USER inventory_user WITH PASSWORD 'inventory_password';
CREATE USER notification_user WITH PASSWORD 'notification_password';
CREATE USER order_user WITH PASSWORD 'order_password';
CREATE USER payment_user WITH PASSWORD 'payment_password';
CREATE USER product_user WITH PASSWORD 'product_password';
CREATE USER user_user WITH PASSWORD 'user_password';

GRANT ALL PRIVILEGES ON DATABASE inventory_db TO inventory_user;
GRANT ALL PRIVILEGES ON DATABASE notification_db TO notification_user;
GRANT ALL PRIVILEGES ON DATABASE order_db TO order_user;
GRANT ALL PRIVILEGES ON DATABASE payment_db TO payment_user;
GRANT ALL PRIVILEGES ON DATABASE product_db TO product_user;
GRANT ALL PRIVILEGES ON DATABASE user_db TO user_user;
EOSQL

# Grant schema privileges for each database separately
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "inventory_db" <<-EOSQL
GRANT ALL PRIVILEGES ON SCHEMA public TO inventory_user;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO inventory_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL PRIVILEGES ON TABLES TO inventory_user;
EOSQL

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "notification_db" <<-EOSQL
GRANT ALL PRIVILEGES ON SCHEMA public TO notification_user;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO notification_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL PRIVILEGES ON TABLES TO notification_user;
EOSQL

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "order_db" <<-EOSQL
GRANT ALL PRIVILEGES ON SCHEMA public TO order_user;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO order_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL PRIVILEGES ON TABLES TO order_user;
EOSQL

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "payment_db" <<-EOSQL
GRANT ALL PRIVILEGES ON SCHEMA public TO payment_user;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO payment_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL PRIVILEGES ON TABLES TO payment_user;
EOSQL

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "product_db" <<-EOSQL
GRANT ALL PRIVILEGES ON SCHEMA public TO product_user;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO product_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL PRIVILEGES ON TABLES TO product_user;
EOSQL

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "user_db" <<-EOSQL
GRANT ALL PRIVILEGES ON SCHEMA public TO user_user;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO user_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL PRIVILEGES ON TABLES TO user_user;
EOSQL
