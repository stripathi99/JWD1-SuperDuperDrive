-----------------------------------------------------------------
-- Users (admin, 1234)
-----------------------------------------------------------------
INSERT INTO USERS (userid, username, salt, password, firstname, lastname)
VALUES (1, 'admin', 'xXI0EZWEwrXXX7klTnBRew==', 'vlEeJINRDe6Us99/x4yIIpIhFAPPu4BU7UuhRIq1xjPbAtibzqSXnQvFmVwvY1ejo9X74uOi4osXXXrjkgh/Y/XSxs/qId3NQrKljByqsN3i3X/dvKL1yOfS537ToK0KwusjwCef1r5dXYd1X6QZ/9h4iTnPOGYwTM+Q6CnRbrejbJv1mf+UxteJcZmblNEYu6NjchUfePCktVEqc2XVW4YsmanTQL9sgKgGanMKN/rXlsFrLWNFhyjita4eT/j4OogOD515Bbcs6sncUExxQ0pvBZxG/wyW59YHN1V4VzCIWfrcCHQHa2tdBwQBMqnpLlanPUzw8r4m/yj9rq8PZA==', 'admin', 'admin');
-----------------------------------------------------------------
-- NOTES
-----------------------------------------------------------------
INSERT INTO NOTES (notetitle, notedescription, userid)
VALUES
  ('test-note-title-1', 'test-note-description-1', 1),
  ('test-note-title-2', 'test-note-description-2', 1),
  ('test-note-title-3', 'test-note-description-3', 1);
-----------------------------------------------------------------
-- CREDENTIALS
-----------------------------------------------------------------
INSERT INTO CREDENTIALS (url, username, key, password, userid)
VALUES
  ('test.com', 'test', 'Gy+qTlv/d5BlkweVy5ohhA==', 'YYfU2RSoT8FZ1fh0T3Jb7w==', 1),
  ('google.com', 'test-username', 'VxmmGbeTZ+lxNUYvhrvJoQ==', 'vC7hyExnwF7kpJOqz5ctcA==', 1);