CREATE TABLE author (
  id char(4) PRIMARY KEY,
  name varchar(50) NOT NULL,
  birth_date date NOT NULL,
  book_ids varchar(1000) NOT NULL,
  update_time timestamp NOT NULL DEFAULT current_timestamp,
  create_time timestamp NOT NULL DEFAULT current_timestamp
);