-- SQLite
-- CREATE TABLE Users (
--     ID INTEGER PRIMARY KEY NOT NULL,
--     Password TEXT NOT NULL,
--     Name TEXT NOT NULL,
--     role_id INTEGER NOT NULL,
--     Username TEXT NOT NULL
-- );

-- ALTER TABLE Users ADD COLUMN Status INTEGER;

-- DROP TABLE IF EXISTS Employee;

-- INSERT INTO Users  
-- VALUES (1, 'password123', 'John Doe', 1, 'johndoe', 1);

-- INSERT INTO AccountStatus
-- VALUES (1, 'Active');

-- INSERT INTO AccountStatus
-- VALUES (0, 'Inactive');

-- DROP TABLE Status;

-- INSERT INTO RoleID
-- VALUES (1, 'Manager');

-- INSERT INTO RoleID
-- VALUES (2, 'Cashier');

-- INSERT INTO RoleID
-- VALUES (3, 'Customer');

-- UPDATE Users
-- SET Password = 'admin'
-- WHERE role_id = 1;

-- INSERT INTO Users  
-- VALUES (2, 'cashier', 'Jane Doe', 2, 'cashier', 1);

INSERT INTO Users  
VALUES (3, 'customer', 'Jack Smith', 3, 'customer', 1);