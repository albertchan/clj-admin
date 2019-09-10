-- CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS users (
    id uuid DEFAULT uuid_generate_v4() NOT NULL,
    email character varying(255) UNIQUE NOT NULL,
    password character varying(255),
    name character varying(255),
    avatar_url character varying(255),
    bio text,
    locale character varying(255),
    created_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL 
);

CREATE UNIQUE INDEX users_email_index ON users USING btree (email);