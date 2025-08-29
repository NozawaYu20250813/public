CREATE TABLE book (
  id char(4) PRIMARY KEY,
  title varchar(50) NOT NULL,
  price numeric NOT NULL,
  author_ids varchar(1000) NOT NULL,
  is_published boolean NOT NULL,
  update_time timestamp NOT NULL DEFAULT current_timestamp,
  create_time timestamp NOT NULL DEFAULT current_timestamp
);