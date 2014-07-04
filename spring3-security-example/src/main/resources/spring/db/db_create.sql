--SET DATABASE DEFAULT INITIAL SCHEMA PUBLIC

CREATE TABLE users (
    id character varying(36) PRIMARY KEY,
    name character varying(50) NOT NULL,
    role character varying(36) NOT NULL,
    password character varying(100) NOT NULL,
    active boolean default true  NOT NULL
);


CREATE TABLE roles (
    id character varying(36) PRIMARY KEY,
    name character varying(50) NOT NULL
);


GRANT ALL ON TABLE users TO SA;
GRANT ALL ON TABLE roles TO SA;


ALTER TABLE users
    ADD CONSTRAINT role_fk FOREIGN KEY (role)
    REFERENCES roles (id);

ALTER TABLE users
  ADD CONSTRAINT user_name_unique UNIQUE (name);

ALTER TABLE roles
ADD CONSTRAINT role_name_unique UNIQUE (name);
