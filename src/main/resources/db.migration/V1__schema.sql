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

CREATE TABLE images(
    id SERIAL8 NOT NULL,
    object_name VARCHAR(50),
    bucket_name VARCHAR(50),
    size INT8,
    content_type VARCHAR(50),
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp DEFAULT CURRENT_TIMESTAMP,
    created_by INT8 DEFAULT NULL,
    updated_by INT8 DEFAULT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE images
ADD CONSTRAINT image_uq UNIQUE (object_name, bucket_name);

CREATE TABLE item_requests(
    id SERIAL8 NOT NULL,
    name VARCHAR(50),
    quantity INT,
    details VARCHAR(250),
    image_id INT8,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp DEFAULT CURRENT_TIMESTAMP,
    created_by INT8 DEFAULT NULL,
    updated_by INT8 DEFAULT NULL,
    approver INT8,
    status VARCHAR(10),
    PRIMARY KEY (id)
);

ALTER TABLE item_requests
ADD CONSTRAINT ir_image_fk
    FOREIGN KEY (image_id)
    REFERENCES images(id);

ALTER TABLE item_requests
ADD CONSTRAINT ir_user_fk
    FOREIGN KEY (approver)
    REFERENCES users(id);

CREATE TABLE support_homes(
    id SERIAL8 NOT NULL,
    start timestamp,
    ends timestamp,
    reason VARCHAR(250),
    image_id INT8,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp DEFAULT CURRENT_TIMESTAMP,
    created_by INT8 DEFAULT NULL,
    updated_by INT8 DEFAULT NULL,
    approver INT8,
    status VARCHAR(10),
    PRIMARY KEY (id)
);

ALTER TABLE support_homes
    ADD CONSTRAINT sh_image_fk
    FOREIGN KEY (image_id)
    REFERENCES images(id);

ALTER TABLE support_homes
    ADD CONSTRAINT sh_user_fk
    FOREIGN KEY (approver)
    REFERENCES users(id);

CREATE TABLE business_trips(
    id SERIAL8 NOT NULL,
    start timestamp,
    ends timestamp,
    reason VARCHAR(250),
    image_id INT8,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp DEFAULT CURRENT_TIMESTAMP,
    created_by INT8 DEFAULT NULL,
    updated_by INT8 DEFAULT NULL,
    approver INT8,
    status VARCHAR(10),
    PRIMARY KEY (id)
);

ALTER TABLE business_trips
    ADD CONSTRAINT bt_image_fk
    FOREIGN KEY (image_id)
    REFERENCES images(id);

ALTER TABLE business_trips
    ADD CONSTRAINT bt_user_fk
    FOREIGN KEY (approver)
    REFERENCES users(id);

CREATE TABLE general_approvals(
    id SERIAL8 NOT NULL,
    subject VARCHAR(50),
    details VARCHAR(50),
    image_id INT8,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp DEFAULT CURRENT_TIMESTAMP,
    created_by INT8 DEFAULT NULL,
    updated_by INT8 DEFAULT NULL,
    approver INT8,
    status VARCHAR(10),
    PRIMARY KEY (id)
);

ALTER TABLE general_approvals
    ADD CONSTRAINT ga_image_fk
    FOREIGN KEY (image_id)
    REFERENCES images(id);

ALTER TABLE general_approvals
    ADD CONSTRAINT ga_user_fk
    FOREIGN KEY (approver)
    REFERENCES users(id);

CREATE TABLE leave_applications(
    id SERIAL8 NOT NULL,
    leave_type VARCHAR(250),
    start timestamp,
    ends timestamp,
    start_day_type VARCHAR(14),
    end_day_type VARCHAR(14),
    reason VARCHAR(250),
    image_id INT8,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp DEFAULT CURRENT_TIMESTAMP,
    created_by INT8 DEFAULT NULL,
    updated_by INT8 DEFAULT NULL,
    approver INT8,
    status VARCHAR(10),
    PRIMARY KEY (id)
);

ALTER TABLE leave_applications
    ADD CONSTRAINT la_image_fk
    FOREIGN KEY (image_id)
    REFERENCES images(id);

ALTER TABLE leave_applications
    ADD CONSTRAINT la_user_fk
    FOREIGN KEY (approver)
    REFERENCES users(id);

CREATE TABLE financial_claims(
    id SERIAL8 NOT NULL,
    amount INT8,
    reason VARCHAR(250),
    image_id INT8,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp DEFAULT CURRENT_TIMESTAMP,
    created_by INT8 DEFAULT NULL,
    updated_by INT8 DEFAULT NULL,
    approver INT8,
    status VARCHAR(10),
    PRIMARY KEY (id)
);

ALTER TABLE financial_claims
    ADD CONSTRAINT fc_image_fk
    FOREIGN KEY (image_id)
    REFERENCES images(id);

ALTER TABLE financial_claims
    ADD CONSTRAINT fc_user_fk
    FOREIGN KEY (approver)
    REFERENCES users(id);

CREATE TABLE over_times(
    id SERIAL8 NOT NULL,
    description  VARCHAR(250),
    start_time time,
    end_time time,
    image_id INT8,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp DEFAULT CURRENT_TIMESTAMP,
    created_by INT8 DEFAULT NULL,
    updated_by INT8 DEFAULT NULL,
    approver INT8,
    status VARCHAR(10),
    PRIMARY KEY (id)
);

ALTER TABLE over_times
    ADD CONSTRAINT ot_image_fk
    FOREIGN KEY (image_id)
    REFERENCES images(id);

ALTER TABLE over_times
    ADD CONSTRAINT ot_user_fk
    FOREIGN KEY (approver)
    REFERENCES users(id);

-- INSERT INTO roles(id, name) values (1, 'ROLE_ADMIN');
-- INSERT INTO roles(id, name) values (2, 'ROLE_USER');
-- INSERT INTO roles(id, name) values (3, 'ROLE_HR');

CREATE TABLE absences(
    id SERIAL8 NOT NULL,
    location VARCHAR(255),
    project_name VARCHAR(150),
    start TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ends TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    activity VARCHAR(255),
    month INT,
    year INT,
    absence_type VARCHAR(60),
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp DEFAULT CURRENT_TIMESTAMP,
    created_by INT8 DEFAULT NULL,
    updated_by INT8 DEFAULT NULL,
    PRIMARY KEY (id)
);

-- User transaction logs
CREATE TABLE transaction_logs(
    transaction_type VARCHAR(250),
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp DEFAULT CURRENT_TIMESTAMP,
    created_by INT8 DEFAULT NULL,
    updated_by INT8 DEFAULT NULL
);

-- User Activity Logs, login, logout, profile manipulation,