create table if not exists playlist(id bigint auto_increment primary key, name VARCHAR(250) NOT NULL, created_on DATE default CURRENT_TIMESTAMP );
create table if not exists song(id bigint auto_increment PRIMARY KEY, playlist_id bigint NOT NULL, name VARCHAR(250) NOT NULL, cover_url VARCHAR(250) NOT NULL, created_on DATE default CURRENT_TIMESTAMP, FOREIGN KEY(playlist_id) REFERENCES playlist(id) ON UPDATE CASCADE);

insert into playlist(id, name, created_on) values(1, 'guitar', CURRENT_TIMESTAMP);
insert into song(id, name, playlist_id, cover_url, created_on) values(2, 'guitar song', 1, 'https://www.dummies.com/wp-content/uploads/open-guitar-chords-chart.jpg', CURRENT_TIMESTAMP);