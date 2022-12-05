INSERT INTO tickets (name , dayOfWeek , price , email , nOfPeople ) VALUES 
('Kunj', 'Sunday', 20.99, 'mitp@gmail.com', 5),
('Andrew', 'Saturday', 45.99, 'jsp@gmail.com', 2),
('Jon','Monday', 40.99, 'jdhd@gmail.com', 4),
('Kunj', 'Sunday', 45.99, 'ertygmail.com', 6),
('Kunj', 'Sunday', 30.99, 'rty@gmail.com', 4),
('Troy', 'Saturday', 80.99, 'ghj@gmail.com', 5),
('Andrew', 'Saturday', 45.99, 'rtyu@gmail.com', 3),
('Andrew', 'Monday', 48.99, 'ertyu@gmail.com', 2),
('Andrew', 'Monday', 89.99, 'wsd@gmail.com', 3),
('Andrew', 'Saturday', 99.99, 'rtyu@gmail.com', 7),
('Ricky', 'Monday', 45.99, 'jkjh@gmail.com', 5),
('Ricky', 'Sunday', 20.99, 'ikm@gmail.com', 6),
('Ricky', 'Saturday', 45.99, 'ews85@gmail.com', 2),
('Andrew', 'Monday', 60.99, 'jed789@gmail.com', 4),
('Adam', 'Sunday', 45.99, 'tyuh456@gmail.com', 5),
('Adam', 'Saturday', 40.99, '785eds@gmail.com', 2),
('Adam', 'Monday', 55.99, 'vgujnn452@gmail.com', 4),
('Jason', 'Saturday', 48.99, 'plmn@gmail.com', 6),
('Jason', 'Sunday', 47.99, 'qwe@gmail.com', 2),
('Jason', 'Sunday', 109.99, 'qewrty@gmail.com', 9),
('Jason', 'Saturday', 85.99, 'sdfgh@gmail.com', 6);

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Jon', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
 
insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Andrew', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
 
insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Kunj', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
 
insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Ricky', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Adam', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Jason', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);

insert into sec_role (roleName)
values ('ROLE_VENDOR');
 
insert into sec_role (roleName)
values ('ROLE_GUEST');

insert into user_role (userId, roleId)
values (1, 1);

insert into user_role (userId, roleId)
values (2, 2);

insert into user_role (userId, roleId)
values (3, 2);

insert into user_role (userId, roleId)
values (4, 2);

insert into user_role (userId, roleId)
values (5, 2);

insert into user_role (userId, roleId)
values (6, 2);
