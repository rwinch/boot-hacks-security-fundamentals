insert into user (id,email,password,first_name,last_name) values (0,'rob@example.com','password','Rob','Winch');
insert into user (id,email,password,first_name,last_name) values (1,'luke@example.com','password','Luke','Taylor');


insert into user_roles (user_id,roles) values(0,'USER');
insert into user_roles (user_id,roles) values(0,'ADMIN');

insert into user_roles (user_id,roles) values(1,'USER');