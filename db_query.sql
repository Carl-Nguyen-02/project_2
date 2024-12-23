CREATE TABLE RoleID (
    role_id INTEGER PRIMARY KEY NOT NULL,
    title TEXT NOT NULL
);

-- Account status definitions table
CREATE TABLE AccountStatus (
    ID INTEGER PRIMARY KEY NOT NULL,
    description TEXT NOT NULL
);

-- Status definitions table
CREATE TABLE Status (
    ID INTEGER PRIMARY KEY NOT NULL,
    description TEXT NOT NULL
);

-- Employee table with foreign key constraints
CREATE TABLE Employee (
    ID INTEGER PRIMARY KEY NOT NULL,
    Password TEXT,
    Name TEXT,
    role_id INTEGER NOT NULL,
    status INTEGER NOT NULL,
    FOREIGN KEY (role_id) REFERENCES RoleID(role_id),
    FOREIGN KEY (status) REFERENCES AccountStatus(ID)
);

-- Customer table
CREATE TABLE Customer (
    ID INTEGER PRIMARY KEY NOT NULL,
    PhoneNumber TEXT,
    MailAddress TEXT,
    Name TEXT
);

-- Products table with stock constraint
CREATE TABLE Products (
    ProductID INTEGER PRIMARY KEY NOT NULL,
    name TEXT,
    price REAL,
    stock INTEGER NOT NULL CHECK (stock >= 0)
);

-- Orders table with foreign key constraints
CREATE TABLE Orders (
    OrderID INTEGER PRIMARY KEY NOT NULL,
    Date TEXT,
    TotalCost REAL,
    CustomerID INTEGER NOT NULL,
    EmployeeID INTEGER NOT NULL,
    PaymentMethod TEXT,
    FOREIGN KEY (CustomerID) REFERENCES Customer(ID),
    FOREIGN KEY (EmployeeID) REFERENCES Employee(ID)
);

-- OrderContent table with quantity constraint and foreign keys
CREATE TABLE OrderContent (
    ProdID INTEGER NOT NULL,
    OrderID INTEGER NOT NULL,
    Quantity INTEGER NOT NULL CHECK (Quantity >= 1),
    Price REAL,
    PRIMARY KEY (ProdID, OrderID),
    FOREIGN KEY (ProdID) REFERENCES Products(ProductID),
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID)
);
