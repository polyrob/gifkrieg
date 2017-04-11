-- Create role
INSERT INTO `gifkrieg`.`role` (`id`, `role`) VALUES ('1', 'ROLE_USER');
INSERT INTO `gifkrieg`.`role` (`id`, `role`) VALUES ('2', 'ROLE_ADMIN');


-- Create some users
insert into user (active, email, password, username) values (6, "admin@gifkrieg.com", "$2a$10$hxpuZN9gamm9lUUtjDF1qO56kVZdZzvt3sbfY1FJ71kZ85CVANDS6", "admin")
insert into user_role (user_id, role_id) values (1, 1)
insert into user_role (user_id, role_id) values (1, 2)

insert into user (active, email, password, username) values (6, "robbiescheidt@gmail.com", "$2a$10$hxpuZN9gamm9lUUtjDF1qO56kVZdZzvt3sbfY1FJ71kZ85CVANDS6", "polyrob")
insert into user_role (user_id, role_id) values (2, 1)

insert into user (active, email, password, username) values (6, "user1@gmail.com", "$2a$10$dUeWB1H9Zj78c.fQiQi1mOAmbJyvZc7NmMLW6lRNCpcJzU41puam2", "user1")
insert into user_role (user_id, role_id) values (3, 1)

insert into user (active, email, password, username) values (6, "user2@gmail.com", "$2a$10$dUeWB1H9Zj78c.fQiQi1mOAmbJyvZc7NmMLW6lRNCpcJzU41puam2", "user2")
insert into user_role (user_id, role_id) values (4, 1)

insert into user (active, email, password, username) values (6, "user3@gmail.com", "$2a$10$dUeWB1H9Zj78c.fQiQi1mOAmbJyvZc7NmMLW6lRNCpcJzU41puam2", "user3")
insert into user_role (user_id, role_id) values (5, 1)

insert into user (active, email, password, username) values (6, "user4@gmail.com", "$2a$10$dUeWB1H9Zj78c.fQiQi1mOAmbJyvZc7NmMLW6lRNCpcJzU41puam2", "user4")
insert into user_role (user_id, role_id) values (6, 1)

insert into user (active, email, password, username) values (6, "user5@gmail.com", "$2a$10$dUeWB1H9Zj78c.fQiQi1mOAmbJyvZc7NmMLW6lRNCpcJzU41puam2", "user5")
insert into user_role (user_id, role_id) values (7, 1)

insert into user (active, email, password, username) values (6, "user6@gmail.com", "$2a$10$dUeWB1H9Zj78c.fQiQi1mOAmbJyvZc7NmMLW6lRNCpcJzU41puam2", "user6")
insert into user_role (user_id, role_id) values (8, 1)

insert into user (active, email, password, username) values (6, "user7@gmail.com", "$2a$10$dUeWB1H9Zj78c.fQiQi1mOAmbJyvZc7NmMLW6lRNCpcJzU41puam2", "user7")
insert into user_role (user_id, role_id) values (9, 1)

insert into user (active, email, password, username) values (6, "user8@gmail.com", "$2a$10$dUeWB1H9Zj78c.fQiQi1mOAmbJyvZc7NmMLW6lRNCpcJzU41puam2", "user8")
insert into user_role (user_id, role_id) values (10, 1)


-- Create some challenges
insert into challenge (state, card_text, start_time, end_time) values (6, "First Challenge Ever", "2017-04-01 00:00:00", "2017-04-02 00:00:00")
insert into challenge (state, card_text, start_time, end_time) values (6, "Second Challenge Ever", "2017-04-02 00:00:00", "2017-04-03 00:00:00")
insert into challenge (state, card_text, start_time, end_time) values (6, "Third Challenge Ever", "2017-04-03 00:00:00", "2017-04-04 00:00:00")
insert into challenge (state, card_text, start_time, end_time) values (6, "Oh great. It's Monday.", "2017-04-04 00:00:00", "2017-04-05 00:00:00")
insert into challenge (state, card_text, start_time, end_time) values (5, "You keep whiping your butt and there's still poop", "2017-04-05 00:00:00", "2017-04-06 00:00:00")
insert into challenge (state, card_text, start_time, end_time) values (4, "I saw your mom making out with the last challenge", "2017-04-06 00:00:00", "2017-04-07 00:00:00")
insert into challenge (state, card_text, start_time, end_time) values (3, "The current challenge makes you feel like...", "2017-04-07 00:00:00", "2017-04-08 00:00:00")
insert into challenge (state, card_text, start_time, end_time) values (2, "Tomorrow's Challenge", "2017-04-08 00:00:00", "2017-04-09 00:00:00")
insert into challenge (state, card_text, start_time, end_time) values (2, "Some future challenge", "2017-04-09 00:00:00", "2017-04-10 00:00:00")


-- Add Gif urls
insert into gif (url) values ("http://www.reactiongifs.com/r/chloe.gif"),("http://www.reactiongifs.com/r/lucille-portable.gif"),("http://www.reactiongifs.com/wp-content/uploads/2013/11/trre.gif"),("http://www.reactiongifs.com/r/ob1.gif"),("http://www.reactiongifs.com/wp-content/uploads/2013/05/dr-evil-right.gif"),("http://www.reactiongifs.com/wp-content/uploads/2012/12/grumpy-cat.gif"),("http://www.reactiongifs.com/r/isn.gif"),("http://www.reactiongifs.com/r/somg.gif"),("http://www.reactiongifs.com/r/triple.gif"),("http://www.reactiongifs.com/r/agape.gif"),("http://www.reactiongifs.com/wp-content/uploads/2012/11/really.gif"),("http://www.reactiongifs.com/wp-content/uploads/2014/01/zomg.gif"),("http://www.reactiongifs.com/r/sdey.gif"),("http://www.reactiongifs.com/r/elnb.gif")

-- Add User Gifs user users
insert into user_gif (user_id, gif_id) values (3, 1), (3,2), (3,3), (4,5), (4,6), (4,7), (5,8), (5,9), (6,10), (6,11), (7,12), (7,13), (8,14), (8,15), (9,16), (9,17), (10,18), (10,19)

-- Add some submissions
insert into submission (challenge_id, gif_id, user_id) values (7, 3, 1),(7, 4, 5),(7, 5, 8),(7, 6, 11),(7, 7, 13),(7, 10, 18)

-- Add scores
insert into stats (username, score, rounds) values ("polyrob", 25, 2),("user1", 20, 2),("user2", 25, 2),("user3", 2, 1),("user4", 0, 0)



-- Set up indicies
CREATE INDEX `idx_submission_challenge_id`  ON `gifkrieg`.`submission` (challenge_id) ALGORITHM DEFAULT LOCK DEFAULT
