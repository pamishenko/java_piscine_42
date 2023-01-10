DROP SCHEMA IF EXISTS TEST CASCADE;

CREATE SCHEMA IF NOT EXISTS TEST;

DROP TABLE IF EXISTS TEST.PRODUCTS CASCADE;

CREATE TABLE IF NOT EXISTS TEST.PRODUCTS (
                                        ID INT IDENTITY PRIMARY KEY,
                                        NAME VARCHAR(100) NOT NULL,
                                        PRICE INTEGER
);

DROP TABLE IF EXISTS TEST.USERS CASCADE;

CREATE TABLE IF NOT EXISTS TEST.USERS (
                                             ID INT IDENTITY PRIMARY KEY,
                                             NAME VARCHAR(100) NOT NULL,
                                             PASSWORD VARCHAR(100) NOT NULL
);