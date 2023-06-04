CREATE TABLE factory
(
    id SERIAL NOT NULL PRIMARY KEY,
    posX INTEGER,
    posY INTEGER,
    maxRelease INTEGER,
    tax INTEGER,
    model_id INTEGER
);

CREATE TABLE model
(
    id SERIAL NOT NULL PRIMARY KEY,
    carCount INTEGER,
    realiseForCar INTEGER,
    square INTEGER,
    money INTEGER,
    filterCount INTEGER,
    filterCost INTEGER,
    release INTEGER
);