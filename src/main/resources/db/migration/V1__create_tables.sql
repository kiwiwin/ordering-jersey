CREATE TABLE Products (
  id SERIAL PRIMARY KEY,
  name VARCHAR(32) NOT NULL,
  description VARCHAR(255),
  price INT NOT NULL
);

CREATE TABLE Users (
  id SERIAL PRIMARY KEY,
  name VARCHAR(32) NOT NULL
);

CREATE TABLE Orders (
  id SERIAL PRIMARY KEY,
  user_id INT NOT NULL,
  product_id INT NOT NULL,
  Foreign Key (user_id) REFERENCES Users(id),
  Foreign Key (product_id) REFERENCES Products(id)
);

CREATE TABLE Payment (
  id SERIAL PRIMARY KEY,
  order_id INT NOT NULL,
  Foreign Key (order_id) REFERENCES Orders(id)
);