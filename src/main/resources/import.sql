-- Create roles
INSERT INTO `gifkrieg`.`role` (`id`, `role`) VALUES ('1', 'ROLE_USER');
INSERT INTO `gifkrieg`.`role` (`id`, `role`) VALUES ('2', 'ROLE_ADMIN');


-- Create some users
insert into user (active, email, password, username) values (5, "admin@gifkrieg.com", "$2a$10$hxpuZN9gamm9lUUtjDF1qO56kVZdZzvt3sbfY1FJ71kZ85CVANDS6", "admin")
insert into user_role (user_id, role_id) values (1, 1)
insert into user_role (user_id, role_id) values (1, 2)

insert into user (active, email, password, username) values (5, "robbiescheidt@gmail.com", "$2a$10$hxpuZN9gamm9lUUtjDF1qO56kVZdZzvt3sbfY1FJ71kZ85CVANDS6", "polyrob")
insert into user_role (user_id, role_id) values (2, 1)


-- Create some challenges
insert into challenge (state, card_text, start_time, end_time) values (4, "First Challenge", "2017-04-01 00:00:00", "2017-04-02 00:00:00")
insert into challenge (state, card_text, start_time, end_time) values (3, "Current Challenge", "2017-04-02 00:00:00", "2017-03-02 00:00:00")
insert into challenge (state, card_text, start_time, end_time) values (2, "Next Challenge", "2017-04-03 00:00:00", "2017-04-04 00:00:00")


-- Add Gif urls
insert into gif (url) values ("http://www.reactiongifs.com/r/chloe.gif"),("http://www.reactiongifs.com/r/lucille-portable.gif"),("http://www.reactiongifs.com/wp-content/uploads/2013/11/trre.gif"),("http://www.reactiongifs.com/r/ob1.gif"),("http://www.reactiongifs.com/wp-content/uploads/2013/05/dr-evil-right.gif"),("http://www.reactiongifs.com/wp-content/uploads/2012/12/grumpy-cat.gif"),("http://www.reactiongifs.com/r/isn.gif"),("http://www.reactiongifs.com/r/somg.gif"),("http://www.reactiongifs.com/r/triple.gif"),("http://www.reactiongifs.com/r/agape.gif"),("http://www.reactiongifs.com/wp-content/uploads/2012/11/really.gif"),("http://www.reactiongifs.com/wp-content/uploads/2014/01/zomg.gif"),("http://www.reactiongifs.com/r/sdey.gif"),("http://www.reactiongifs.com/r/elnb.gif")