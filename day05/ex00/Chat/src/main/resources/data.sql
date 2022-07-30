INSERT INTO chat.users (login, password)
VALUES ('ttanja', 'pa55');
INSERT INTO chat.users (login, password)
VALUES ('skelly', 'pssword');
INSERT INTO chat.users (login, password)
VALUES ('mmargin', '123');
INSERT INTO chat.users (login, password)
VALUES ('bgenya', 'pa55wor2');
INSERT INTO chat.users (login, password)
VALUES ('admin', 'password');

INSERT INTO chat.rooms (name, owner)
VALUES ('Random', 1);
INSERT INTO chat.rooms (name, owner)
VALUES ('General', 2);
INSERT INTO chat.rooms (name, owner)
VALUES ('Bocal', 3);
INSERT INTO chat.rooms (name, owner)
VALUES ('Admin', 4);
INSERT INTO chat.rooms (name, owner)
VALUES ('Piscine', 5);

INSERT INTO chat.messages (author, room, message_text, date_time)
VALUES (1, 1, 'Hello', '2022-01-01 01:00:01');
INSERT INTO chat.messages (author, room, message_text, date_time)
VALUES (2, 3, 'Hi', '2022-01-01 09:09:01');
INSERT INTO chat.messages (author, room, message_text, date_time)
VALUES (5, 3, 'Go drink', '2022-01-01 09:40:02');
INSERT INTO chat.messages (author, room, message_text, date_time)
VALUES (4, 4, 'i am smoking', '2022-01-01 03:03:04');
INSERT INTO chat.messages (author, room, message_text, date_time)
VALUES (5, 5, 'AFK 10 min', '1970-01-01 21:19:05');