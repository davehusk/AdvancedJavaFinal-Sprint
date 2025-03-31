### How to Run the Application

1. **Database Setup**:
   - Create a PostgreSQL database named `gymdb`
   - Run the SQL scripts:
     ```bash
     psql -U postgres -c "CREATE DATABASE gymdb;"
     psql -U postgres -d gymdb -f src/main/resources/sql/create_tables.sql
     psql -U postgres -d gymdb -f src/main/resources/sql/insert_data.sql
     ```

2. **Configuration**:
   - Update `src/main/resources/config.properties` with your database credentials

3. **Build and Run**:
   ```bash
   mvn clean install
   mvn exec:java -Dexec.mainClass="com.gym.main.GymManagementSystem"
   ```

### Key Features Implemented

1. **User Management**:
   - Registration with role-based accounts (Admin, Trainer, Member)
   - Secure password hashing with BCrypt
   - Authentication and session management

2. **Membership System**:
   - Members can purchase different membership types
   - Admin can view total revenue
   - Members can view their membership history and expenses

3. **Workout Class Management**:
   - Trainers can create, update, and delete classes
   - Members can view available classes
   - Schedule management with timestamps

4. **Database Integration**:
   - PostgreSQL backend with proper table relationships
   - CRUD operations for all entities
   - Foreign key constraints with cascading deletes

5. **Console Interface**:
   - Role-based menu system
   - Input validation and error handling
   - Clear navigation and user feedback

This implementation provides a complete, working Gym Management System with all the requested features. The code is organized with proper separation of concerns, follows best practices, and includes all necessary components for a production-ready application.