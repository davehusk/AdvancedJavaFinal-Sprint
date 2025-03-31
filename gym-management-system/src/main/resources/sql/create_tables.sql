-- Users table
CREATE TABLE IF NOT EXISTS users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    phone_number VARCHAR(20),
    address TEXT,
    role VARCHAR(20) NOT NULL
);

-- Memberships table
CREATE TABLE IF NOT EXISTS memberships (
    membership_id SERIAL PRIMARY KEY,
    membership_type VARCHAR(50) NOT NULL,
    description TEXT,
    cost DECIMAL(10,2) NOT NULL,
    user_id INTEGER REFERENCES users(user_id) ON DELETE CASCADE,
    purchase_date DATE DEFAULT CURRENT_DATE
);

-- Workout classes table
CREATE TABLE IF NOT EXISTS workout_classes (
    class_id SERIAL PRIMARY KEY,
    class_name VARCHAR(100) NOT NULL,
    description TEXT,
    schedule TIMESTAMP NOT NULL,
    trainer_id INTEGER REFERENCES users(user_id) ON DELETE CASCADE
);