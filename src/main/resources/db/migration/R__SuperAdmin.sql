insert into users (id,login,password,first_name,last_name,email,role)
values (gen_random_uuid(),'FuturamaAdmin','$2a$12$x7rEjbm0UEv/yjHQsnF9tOnXAqMa5E6iBmrINUvCJ/J0I0aN/ijtm','Gleb','Gukov','Gleb_gukov@inbox.ru','ROLE_ADMIN');
insert into users (id,login,password,first_name,last_name,email,role)
values (gen_random_uuid(),'owner','$2a$12$actJ.73bVN8f3q1kPCHeyumOqflgZbFhjO/NKrgY5wSI4sBqDsOPK','Owner','Ownerovich','Owner_owner@owner.com','ROLE_OWNER');