CREATE TABLE if not exists opty_clients (
                                            id VARCHAR(255) PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    middle_name VARCHAR(255),
    birth_date VARCHAR(255),
    gender VARCHAR(255),
    marital_status VARCHAR(255),
    omt VARCHAR(255),
    passport_seria VARCHAR(255),
    passport_number VARCHAR(255),
    passport_type VARCHAR(255),
    nationality VARCHAR(255)
    );

CREATE TABLE if not exists opty(
      Id VARCHAR(255) PRIMARY KEY,
      created VARCHAR(255),
      status VARCHAR(255),
      client_id VARCHAR(255),
      comment VARCHAR(255),
      FOREIGN KEY (client_id) REFERENCES opty_clients(id)
);