-- Insert Users
INSERT INTO users (login, password)
VALUES
    ('alice', 'password123'),
    ('bob', 'securepass'),
    ('charlie', 'mypass456'),
    ('diana', 'pass789'),
    ('eve', 'evepass');

-- Insert Chatrooms
INSERT INTO chatrooms (chatroom_name, owner_id)
VALUES
    ('General Chat', 1),      -- alice is owner
    ('Java Help', 2),         -- bob is owner
    ('Random', 1),            -- alice is owner
    ('Study Group', 3),       -- charlie is owner
    ('Gaming', 4);            -- diana is owner

-- Insert Messages
INSERT INTO messages (message_text, author_id, room_id)
VALUES
    ('Hello everyone!', 1, 1),                    -- alice in General Chat
    ('Welcome to Java Help', 2, 2),               -- bob in Java Help
    ('Anyone online?', 3, 1),                     -- charlie in General Chat
    ('Let''s study together', 3, 4),              -- charlie in Study Group
    ('Good morning!', 5, 1);                      -- eve in General Chat

-- Insert User Chatroom Participations
INSERT INTO user_chatrooms (user_id, chatroom_id)
VALUES
    (1, 1),    -- alice participates in General Chat
    (2, 1),    -- bob participates in General Chat
    (2, 2),    -- bob participates in Java Help
    (3, 1),    -- charlie participates in General Chat
    (3, 4),    -- charlie participates in Study Group
    (4, 5),    -- diana participates in Gaming
    (5, 1);    -- eve participates in General Chat
