-- Sample admin user
INSERT INTO users (username, password, email, phone_number, address, role)
VALUES ('admin', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MqrqL3LmbrE.YP7yplUQ7/3WlQ5fDaq', 
        'admin@gym.com', '1234567890', '123 Admin St', 'ADMIN');

-- Sample trainer
INSERT INTO users (username, password, email, phone_number, address, role)
VALUES ('trainer1', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MqrqL3LmbrE.YP7yplUQ7/3WlQ5fDaq',
        'trainer1@gym.com', '2345678901', '456 Trainer Ave', 'TRAINER');

-- Sample member
INSERT INTO users (username, password, email, phone_number, address, role)
VALUES ('member1', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MqrqL3LmbrE.YP7yplUQ7/3WlQ5fDaq',
        'member1@gym.com', '3456789012', '789 Member Blvd', 'MEMBER');