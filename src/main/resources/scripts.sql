CREATE TABLE users(
                      id integer PRIMARY KEY,
                      full_name character varying(255) NOT NULL,
                      login character varying(255),
                      email character varying(255),
                      age integer,
                      isAuthor boolean,
                      isModerator boolean
);

CREATE TABLE user_subscriptions (
                                    follower_id INTEGER NOT NULL references users,
                                    account_id INTEGER NOT NULL references users,
                                    primary key(follower_id,account_id)
);

CREATE TABLE post(
                     id           integer not null
                         constraint post_pkey
                             primary key,
                     title        varchar(100),
                     content      varchar(1000),
                     author_id    integer not null
                         constraint post_author_id_fkey
                             references users,
                     moderator_id integer not null
                         constraint post_moderator_id_fkey
                             references users,
                     rating       integer,
                     post_status  varchar(30)

);