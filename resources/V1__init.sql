CREATE TABLE categories
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_categories PRIMARY KEY (id)
);

CREATE TABLE jt_instructors
(
    user_id        BIGINT NOT NULL,
    specialization VARCHAR(255) NULL,
    avg_rating DOUBLE NULL,
    CONSTRAINT pk_jt_instructors PRIMARY KEY (user_id)
);

CREATE TABLE jt_mentors
(
    user_id      BIGINT NOT NULL,
    company_name VARCHAR(255) NULL,
    avg_rating DOUBLE NULL,
    CONSTRAINT pk_jt_mentors PRIMARY KEY (user_id)
);

CREATE TABLE jt_tas
(
    user_id BIGINT NOT NULL,
    no_ofhr VARCHAR(255) NULL,
    CONSTRAINT pk_jt_tas PRIMARY KEY (user_id)
);

CREATE TABLE jt_users
(
    id       BIGINT NOT NULL,
    name     VARCHAR(255) NULL,
    email    VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    CONSTRAINT pk_jt_users PRIMARY KEY (id)
);

CREATE TABLE msc_instructors
(
    id             BIGINT NOT NULL,
    name           VARCHAR(255) NULL,
    email          VARCHAR(255) NULL,
    password       VARCHAR(255) NULL,
    specialization VARCHAR(255) NULL,
    avg_rating DOUBLE NULL,
    CONSTRAINT pk_msc_instructors PRIMARY KEY (id)
);

CREATE TABLE msc_mentors
(
    id           BIGINT NOT NULL,
    name         VARCHAR(255) NULL,
    email        VARCHAR(255) NULL,
    password     VARCHAR(255) NULL,
    company_name VARCHAR(255) NULL,
    avg_rating DOUBLE NULL,
    CONSTRAINT pk_msc_mentors PRIMARY KEY (id)
);

CREATE TABLE msc_tas
(
    id       BIGINT NOT NULL,
    name     VARCHAR(255) NULL,
    email    VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    no_ofhr  VARCHAR(255) NULL,
    CONSTRAINT pk_msc_tas PRIMARY KEY (id)
);

CREATE TABLE products
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    title         VARCHAR(255) NULL,
    `description` VARCHAR(255) NULL,
    image_url     VARCHAR(255) NULL,
    price DOUBLE NOT NULL,
    category_id   BIGINT NULL,
    CONSTRAINT pk_products PRIMARY KEY (id)
);

CREATE TABLE st_users
(
    id             BIGINT NOT NULL,
    user_type      INT NULL,
    name           VARCHAR(255) NULL,
    email          VARCHAR(255) NULL,
    password       VARCHAR(255) NULL,
    specialization VARCHAR(255) NULL,
    avg_rating DOUBLE NULL,
    no_ofhr        VARCHAR(255) NULL,
    company_name   VARCHAR(255) NULL,
    CONSTRAINT pk_st_users PRIMARY KEY (id)
);

CREATE TABLE tpc_instructors
(
    id             BIGINT NOT NULL,
    name           VARCHAR(255) NULL,
    email          VARCHAR(255) NULL,
    password       VARCHAR(255) NULL,
    specialization VARCHAR(255) NULL,
    avg_rating DOUBLE NULL,
    CONSTRAINT pk_tpc_instructors PRIMARY KEY (id)
);

CREATE TABLE tpc_mentor
(
    id           BIGINT NOT NULL,
    name         VARCHAR(255) NULL,
    email        VARCHAR(255) NULL,
    password     VARCHAR(255) NULL,
    company_name VARCHAR(255) NULL,
    avg_rating DOUBLE NULL,
    CONSTRAINT pk_tpc_mentor PRIMARY KEY (id)
);

CREATE TABLE tpc_tas
(
    id       BIGINT NOT NULL,
    name     VARCHAR(255) NULL,
    email    VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    no_ofhr  VARCHAR(255) NULL,
    CONSTRAINT pk_tpc_tas PRIMARY KEY (id)
);

CREATE TABLE tpc_users
(
    id       BIGINT NOT NULL,
    name     VARCHAR(255) NULL,
    email    VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    CONSTRAINT pk_tpc_users PRIMARY KEY (id)
);

ALTER TABLE categories
    ADD CONSTRAINT uc_categories_name UNIQUE (name);

ALTER TABLE jt_instructors
    ADD CONSTRAINT FK_JT_INSTRUCTORS_ON_USER FOREIGN KEY (user_id) REFERENCES jt_users (id);

ALTER TABLE jt_mentors
    ADD CONSTRAINT FK_JT_MENTORS_ON_USER FOREIGN KEY (user_id) REFERENCES jt_users (id);

ALTER TABLE jt_tas
    ADD CONSTRAINT FK_JT_TAS_ON_USER FOREIGN KEY (user_id) REFERENCES jt_users (id);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES categories (id);