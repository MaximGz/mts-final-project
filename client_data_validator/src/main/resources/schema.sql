CREATE TABLE if not exists clients (
        id VARCHAR(255) PRIMARY KEY,
        passport_seria VARCHAR(255),
        passport_number VARCHAR(255),
        first_name VARCHAR(255),
        last_name VARCHAR(255),
        middle_name VARCHAR(255),
        birth_date VARCHAR(255),
        gender VARCHAR(255),
        marital_status VARCHAR(255),
        omt VARCHAR(255),
        passport_type VARCHAR(255),
        nationality VARCHAR(255)
);

CREATE TABLE if not exists accounts (
      id VARCHAR(255) PRIMARY KEY,
      client_id VARCHAR(255),
      created VARCHAR(255),
      FOREIGN KEY (client_id) REFERENCES clients(id)
);

CREATE TABLE if not exists clients_checks(
      opty_id VARCHAR(255) PRIMARY KEY,
      created VARCHAR(255),
      passport_seria boolean,
      passport_number boolean,
      first_name VARCHAR(255),
      last_name VARCHAR(255),
      middle_name VARCHAR(255),
      birth_date VARCHAR(255),
      gender VARCHAR(255),
      marital_status VARCHAR(255),
      omt VARCHAR(255),
      passport_type VARCHAR(255),
      nationality VARCHAR(255)
);

truncate table clients CASCADE;

INSERT INTO clients (id, passport_seria, passport_number, first_name, last_name, middle_name, birth_date, gender, marital_status, omt, passport_type, nationality)
VALUES
    ('id1', 'AB1234', '123456789', 'Иван', 'Иванов', 'Иванович', '1980-01-01', 'М', 'Женат', 'OMT1', 'Тип1', 'Национальность1'),
    ('id2', 'CD5678', '234567890', 'Петр', 'Петров', 'Петрович', '1985-02-02', 'М', 'Не женат', 'OMT2', 'Тип2', 'Национальность2'),
    ('id3', 'EF9101', '345678901', 'Сергей', 'Сергеев', '1',  '1990-03-03', 'М', 'Женат', 'OMT3', 'Тип3', 'Национальность3'),
    ('id4', 'GH2345', '456789012', 'Алексей', 'Алексеев', '2', '1995-04-04', 'М', 'Не женат', 'OMT4', 'Тип4', 'Национальность4'),
    ('id5', 'IJ6789', '567890123', 'Олег', 'Олегов', '1', '2000-05-05', 'М', 'Женат', 'OMT5', 'Тип5', 'Национальность5'),
    ('id6', 'KL1234', '678901234', 'Елена', 'Еленова', '1', '1980-06-06', 'Ж', 'Замужем', 'OMT6', 'Тип6', 'Национальность6'),
    ('id7', 'MN5678', '789012345', 'Мария', 'Мариева', '1', '1985-07-07', 'Ж', 'Не замужем', 'OMT7', 'Тип7', 'Национальность7'),
    ('id8', 'OP9101', '890123456', 'Наталья', 'Натальева', '1', '1990-08-08', 'Ж', 'Замужем', 'OMT8', 'Тип8', 'Национальность8'),
    ('id9', 'QR2345', '901234567', 'Ирина', 'Иринова', '1', '1995-09-09', 'Ж', 'Не замужем', 'OMT9', 'Тип9', 'Национальность9'),
    ('id10', 'ST6789', '012345678', 'Ксения', 'Ксениева', '1', '2000-10-10', 'Ж', 'Замужем', 'OMT10', 'Тип10', 'Национальность10'),
    ('id11', 'UV1234', '123456780', 'Владимир', 'Владимиров', '1', '1981-11-11', 'М', 'Женат', 'OMT11', 'Тип11', 'Национальность11'),
    ('id12', 'WX5678', '234567891', 'Дмитрий', 'Дмитриев', '1', '1986-12-12', 'М', 'Не женат', 'OMT12', 'Тип12', 'Национальность12'),
    ('id13', 'YZ9101', '345678902', 'Юрий', 'Юрьев', '1', '1991-01-01', 'М', 'Женат', 'OMT13', 'Тип13', 'Национальность13'),
    ('id14', 'AB2345', '456789013', 'Станислав', 'Станиславов', '1', '1996-02-02', 'М', 'Не женат', 'OMT14', 'Тип14', 'Национальность14'),
    ('id15', 'CD6789', '567890124', 'Роман', 'Романов', '1', '2001-03-03', 'М', 'Женат', 'OMT15', 'Тип15', 'Национальность15');