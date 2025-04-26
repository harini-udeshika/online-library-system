CREATE TABLE books
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    author           VARCHAR(255) NOT NULL,
    available_copies INT          NOT NULL,
    published_year   INT          NOT NULL,
    title            VARCHAR(255) NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE borrow_records
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    borrowed_at datetime NOT NULL,
    returned_at datetime NULL,
    book_id     BIGINT   NOT NULL,
    user_id     BIGINT   NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE users
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime NULL,
    email      VARCHAR(255) NULL,
    name       VARCHAR(255) NULL,
    password   VARCHAR(255) NULL,
    `role`     ENUM NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

ALTER TABLE users
    ADD CONSTRAINT UK6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);

CREATE INDEX idx_book_author ON books (author);

CREATE INDEX idx_book_year ON books (published_year);

CREATE INDEX idx_borrow_time ON borrow_records (borrowed_at);

ALTER TABLE borrow_records
    ADD CONSTRAINT FK9ep13xg9kn8vo3w0ntvd08tco FOREIGN KEY (book_id) REFERENCES books (id) ON DELETE NO ACTION;

CREATE INDEX FK9ep13xg9kn8vo3w0ntvd08tco ON borrow_records (book_id);

ALTER TABLE borrow_records
    ADD CONSTRAINT FKe5k0iaamaypstfhuluoa40yom FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE NO ACTION;

CREATE INDEX FKe5k0iaamaypstfhuluoa40yom ON borrow_records (user_id);