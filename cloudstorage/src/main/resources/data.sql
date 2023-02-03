-----------------------------------------------------------------
-- Users (admin, 1234)
-----------------------------------------------------------------
INSERT INTO USERS (userid, username, salt, password, firstname, lastname)
VALUES
  (1, 'admin', 'xXI0EZWEwrXXX7klTnBRew==', 'vlEeJINRDe6Us99/x4yIIpIhFAPPu4BU7UuhRIq1xjPbAtibzqSXnQvFmVwvY1ejo9X74uOi4osXXXrjkgh/Y/XSxs/qId3NQrKljByqsN3i3X/dvKL1yOfS537ToK0KwusjwCef1r5dXYd1X6QZ/9h4iTnPOGYwTM+Q6CnRbrejbJv1mf+UxteJcZmblNEYu6NjchUfePCktVEqc2XVW4YsmanTQL9sgKgGanMKN/rXlsFrLWNFhyjita4eT/j4OogOD515Bbcs6sncUExxQ0pvBZxG/wyW59YHN1V4VzCIWfrcCHQHa2tdBwQBMqnpLlanPUzw8r4m/yj9rq8PZA==', 'admin', 'admin'),
  (2, 'user', 'nfSuvZD86M1XPacZXpRyPQ==', 'unQb1xJNhO29sQLr5HNMcdKnDg9NXFQtuXkVWR24aUzIS1MGhzvjySUfQ8obmBAJXPmSyscm+9AHl/2dlUEx+NhkOb2v5K7KqAEDUeB8tjpKpi3l+QAtN+0q4lzHFU3ifsJnAkN26QcrnmjcmxCkb6uvX7Rm4wPFEPH8ePIrWs8FNJub3BWCoL31uN7Huw8Z9Nx9t/+SGisvEmxefV76IXWC/+mywOMFTqn0Zvgz0o7HqDFIxm/sDIBkXQlj+b0hYM6eOIMf1BqTGFhJX6A4rXVdoRkI1XGwiF53nDDRwLk/AHGp3ppIOnX0zWd5l1Kd8tIy3mWSq/opKT5yORa3DA==', 'user', 'user');
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