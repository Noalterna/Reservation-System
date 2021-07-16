INSERT INTO TICKETS (type, price) VALUES
('adult', 25),
('child', 12.5),
('student', 18);

INSERT INTO ROOMS (room_name, seat_rows, seat_columns) values
('A', 6, 6),
('B', 7, 7),
('C', 8, 8);

INSERT INTO MOVIES (title, screening_date_time, room_id) values
('Interstellar', {ts '2021-11-22 10:30'}, 1),
('Bob', {ts '2021-11-22 10:30'}, 2),
('Alive', {ts '2021-11-22 14:20'}, 1);
