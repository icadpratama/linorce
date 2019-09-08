CREATE TABLE users (
    id SERIAL8 NOT NULL,
    first_name varchar(20) NOT NULL,
    last_name varchar(20) NOT NULL,
    username char(15) NOT NULL,
    email varchar(100) NOT NULL,
    password varchar(100) NOT NULL,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT uq_users_username UNIQUE(username),
    CONSTRAINT uq_users_email UNIQUE(email)
);


CREATE TABLE roles (
    id SERIAL8 NOT NULL,
    name varchar(60) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uq_roles_name UNIQUE(name)
);

CREATE TABLE user_roles (
    user_id INT8 NOT NULL,
    role_id INT8 NOT NULL,
    PRIMARY KEY (user_id,role_id),
    CONSTRAINT fk_user_roles_role_id FOREIGN KEY (role_id) REFERENCES roles(id),
    CONSTRAINT fk_user_roles_user_id FOREIGN KEY (user_id) REFERENCES users(id)
);

-- INSERT INTO roles(id, name) values (1, 'ROLE_ADMIN');
-- INSERT INTO roles(id, name) values (2, 'ROLE_USER');

CREATE TABLE item_requests(
    id SERIAL8 NOT NULL,
    name VARCHAR(50),
    quantity INT,
    details VARCHAR(250),
    documents VARCHAR(250),
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp DEFAULT CURRENT_TIMESTAMP,
    created_by INT8 DEFAULT NULL,
    updated_by INT8 DEFAULT NULL,
    PRIMARY KEY (id)
);