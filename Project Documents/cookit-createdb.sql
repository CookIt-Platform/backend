CREATE DATABASE cs157a_cookit_DB;
USE cs157a_cookit_DB;

CREATE TABLE user(
    username VARCHAR(256),
    bio VARCHAR(256),
    join_date DATE NOT NULL,
    password VARCHAR(256) NOT NULL,
    profile_picture VARCHAR(256),
    PRIMARY KEY(username)
);

CREATE TABLE follows(
    followee VARCHAR(256) NOT NULL,
    follower VARCHAR(256) NOT NULL,
    PRIMARY KEY(follower,followee),
    FOREIGN KEY(followee) REFERENCES user(username) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(follower) REFERENCES user(username) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE post(
    id INTEGER AUTO_INCREMENT,
    difficulty ENUM('easy', 'medium', 'hard') NOT NULL,
    name VARCHAR(256),
    publish_date DATE NOT NULL,
    short_description VARCHAR(256),
    steps VARCHAR(1000),
    time INTEGER NOT NULL,
    author VARCHAR(256) NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(author) REFERENCES user(username) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE photo(
    photo_url VARCHAR(256),
    post_id INTEGER,
    PRIMARY KEY(photo_url, post_id),
    FOREIGN KEY(post_id) REFERENCES post(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE rate(
    post_id INTEGER,
    user_id VARCHAR(256),
    value INTEGER NOT NULL,
    PRIMARY KEY(post_id, user_id),
    FOREIGN KEY(user_id) REFERENCES user(username) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(post_id) REFERENCES post(id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT rating_range CHECK (value>=1 and value<=10)
);

CREATE TABLE user_likes(
    post_id INTEGER,
    user_id VARCHAR(256),
    PRIMARY KEY(post_id, user_id),
    FOREIGN KEY(user_id) REFERENCES user(username) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(post_id) REFERENCES post(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE bookmark(
    post_id INTEGER,
    user_id VARCHAR(256),
    PRIMARY KEY(post_id, user_id),
    FOREIGN KEY(user_id) REFERENCES user(username) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(post_id) REFERENCES post(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE comment(
    date DATETIME,
    user_id VARCHAR(256),
    textual_content VARCHAR(256),
    post_id INTEGER,
    PRIMARY KEY(user_id, textual_content, post_id),
    FOREIGN KEY(user_id) REFERENCES user(username) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(post_id) REFERENCES post(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE tag(
    tag_name VARCHAR(256),
    PRIMARY KEY(tag_name)
);

CREATE TABLE has_tag(
    post_id INTEGER,
    tag_name VARCHAR(256),
    PRIMARY KEY(post_id, tag_name),
    FOREIGN KEY(post_id) REFERENCES post(id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(tag_name) REFERENCES tag(tag_name) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE ingredient(
    ingredient_name VARCHAR(256),
    PRIMARY KEY(ingredient_name)
);

CREATE TABLE contains_ingredient(
    ingredient_name VARCHAR(256),
    post_id INTEGER,
    quantity FLOAT,
    unit ENUM('g','kg','L','mL','qty'),
    PRIMARY KEY(ingredient_name, post_id),
    FOREIGN KEY(post_id) REFERENCES post(id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(ingredient_name) REFERENCES ingredient(ingredient_name) ON UPDATE CASCADE ON DELETE CASCADE
);