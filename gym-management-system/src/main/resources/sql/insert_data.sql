-- Sample admin user (password: admin123)
INSERT INTO users (username, password, email, phone_number, address, role)
VALUES ('admin', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MqrqL3LmbrE.YP7yplUQ7/3WlQ5fDaq', 
        'admin@gym.com', '1234567890', '123 Admin St', 'ADMIN');

-- Sample trainer (password: trainer123)
INSERT INTO users (username, password, email, phone_number, address, role)
VALUES ('trainer1', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MqrqL3LmbrE.YP7yplUQ7/3WlQ5fDaq',
        'trainer1@gym.com', '2345678901', '456 Trainer Ave', 'TRAINER');

-- Sample member (password: member123)
INSERT INTO users (username, password, email, phone_number, address, role)
VALUES ('member1', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MqrqL3LmbrE.YP7yplUQ7/3WlQ5fDaq',
        'member1@gym.com', '3456789012', '789 Member Blvd', 'MEMBER');

-- Sample memberships
INSERT INTO memberships (membership_type, description, cost, user_id)
VALUES ('Premium', 'Gym access + classes', 50.00, 3);

-- Sample classes
INSERT INTO workout_classes (class_name, description, schedule, trainer_id)
VALUES ('Yoga', 'Beginner yoga class', '2023-06-15 09:00:00', 2);