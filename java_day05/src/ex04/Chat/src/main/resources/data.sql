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
INSERT INTO chat.users (login, password)
VALUES ('ttanja1', 'pa55');
INSERT INTO chat.users (login, password)
VALUES ('skelly1', 'pssword');
INSERT INTO chat.users (login, password)
VALUES ('mmargin1', '123');
INSERT INTO chat.users (login, password)
VALUES ('bgenya1', 'pa55wor2');
INSERT INTO chat.users (login, password)
VALUES ('admin1', 'password');
INSERT INTO chat.users (login, password)
VALUES ('ttanja2', 'pa55');
INSERT INTO chat.users (login, password)
VALUES ('skelly2', 'pssword');
INSERT INTO chat.users (login, password)
VALUES ('mmargin2', '123');
INSERT INTO chat.users (login, password)
VALUES ('bgenya2', 'pa55wor2');
INSERT INTO chat.users (login, password)
VALUES ('admin2', 'password');
INSERT INTO chat.users (login, password)
VALUES ('ttanja3', 'pa55');
INSERT INTO chat.users (login, password)
VALUES ('skelly3', 'pssword');
INSERT INTO chat.users (login, password)
VALUES ('mmargin3', '123');
INSERT INTO chat.users (login, password)
VALUES ('bgenya3', 'pa55wor2');
INSERT INTO chat.users (login, password)
VALUES ('admin3', 'password');

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
VALUES (1, 1, 'Hello', '1970-01-01 00:00:01');
INSERT INTO chat.messages (author, room, message_text, date_time)
VALUES (2, 3, 'Hi', '1970-01-01 00:00:01');
INSERT INTO chat.messages (author, room, message_text, date_time)
VALUES (5, 3, 'Go drink', '1970-01-01 00:00:02');
INSERT INTO chat.messages (author, room, message_text, date_time)
VALUES (4, 4, 'i am smoking', '1970-01-01 00:00:04');
INSERT INTO chat.messages (author, room, message_text, date_time)
VALUES (5, 5, 'AFK 10 min', '1970-01-01 00:00:05');