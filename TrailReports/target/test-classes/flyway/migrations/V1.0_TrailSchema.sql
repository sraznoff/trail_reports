drop table if exists trail_user;
drop table if exists trail_permitted_use;
drop table if exists permitted_use;
drop table if exists trail;
drop table if exists report;

create table trail_user (
	user_id INT NOT NULL AUTO_INCREMENT, 
    first_name VARCHAR(40) NOT NULL,
    last_name VARCHAR(40) NOT NULL, 
    username VARCHAR(40) NOT NULL,
    PRIMARY KEY (user_id)
    );
    
create table trail(
	trail_id INT NOT NULL AUTO_INCREMENT,
    trail_name varchar(60) not null,
    length_mi decimal(7, 2),
    difficulty int,
    location geometry,
    primary key (trail_id)
    );
    
create table report(
	report_id int not null auto_increment,
    report_type varchar(20),
    report_subtype varchar(20),
    trail_id int,
    user_id int,
    report_date datetime,
    location geometry,
	description text(250),
    primary key (report_id)
    );

create table permitted_use(
	use_id int  not null auto_increment,
    use_name varchar(40),
    transportation_mode varchar (40),
    dates_allowed varchar(60),
    primary key (use_id)
    );
    
create table trail_permitted_use(
	use_id int not null,
    trail_id int not null,
	FOREIGN KEY (use_id) REFERENCES permitted_use (use_id) ON DELETE CASCADE,
	FOREIGN KEY (trail_id) REFERENCES trail (trail_id) ON DELETE CASCADE
    );
    