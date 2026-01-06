-- Users table
CREATE TABLE users (
    user_id BIGSERIAL PRIMARY KEY,
    login VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- ChatRooms table
CREATE TABLE chatrooms (
    chatroom_id BIGSERIAL PRIMARY KEY,
    chatroom_name VARCHAR(255) NOT NULL UNIQUE,
    owner_id BIGINT NOT NULL,

    FOREIGN KEY (owner_id) REFERENCES users(user_id)
);

-- Messages table
CREATE TABLE messages (
    message_id BIGSERIAL PRIMARY KEY,
    message_text TEXT NOT NULL,
    message_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    author_id BIGINT NOT NULL,
    room_id BIGINT NOT NULL,

    FOREIGN KEY (author_id) REFERENCES users(user_id),
    FOREIGN KEY (room_id) REFERENCES chatrooms(chatroom_id)
);

-- Junction table for user participation in chatrooms Many-to-Many
CREATE TABLE user_chatrooms (
    user_id BIGINT NOT NULL,
    chatroom_id BIGINT NOT NULL,

    PRIMARY KEY (user_id, chatroom_id),  -- Composite primary key
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (chatroom_id) REFERENCES chatrooms(chatroom_id)
);
