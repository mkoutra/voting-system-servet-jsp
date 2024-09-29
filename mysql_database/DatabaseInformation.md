### Tables

#### `candidates` Table

- **Purpose:** Stores candidate details.
- **Columns:**
    - `cid`: INT, auto-incremented primary key for candidate identification.
    - `firstname`: VARCHAR(45), the first name of the candidate.
    - `lastname`: VARCHAR(45), the last name of the candidate.
- **Indexes:**
    - `cand_lastname_idx`: Index on the `lastname` column to speed up search queries.
- **Engine:** InnoDB for reliable transaction support.

#### `users` Table

- **Purpose:** Manages user information and voting details.
- **Columns:**
    - `uid`: INT, auto-incremented primary key for user identification.
    - `username`: VARCHAR(45), unique username for login.
    - `password`: CHAR(60), password hash for user authentication.
    - `email`: VARCHAR(45), user’s email address.
    - `firstname`: VARCHAR(45), user’s first name.
    - `lastname`: VARCHAR(45), user’s last name.
    - `dob`: DATE, date of birth.
    - `hasVoted`: TINYINT, flag indicating whether the user has voted.
    - `votedCid`: INT, foreign key linking to the `cid` in the `candidates` table, representing the candidate the user voted for.
- **Indexes:**
    - `fk_users_cand_id_idx`: Index on `votedCid` for faster joins and lookups.
    - `username_idx`: Index on `username` for quick user lookups.
    - `user_lastname_idx`: Index on `lastname` for efficient searches.
    - `email_idx`: Index on `email` to optimize email-based queries.
- **Foreign Key Constraint:**
    - `fk_users_cand_id`: Ensures referential integrity between the `users` and `candidates` tables.

### Encoding
The database uses UTF-8 character encoding.

### User and Permissions
- If the database was created using **Docker** the root password was set to `password`.
  You are **strongly advised** to replace this password in the `Dockerfile`.
- Creates a MySQL user `votingUser` with full privileges on `votingDB` and password `voting`.
  As mentioned above, you are **strongly advised** to replace the password and update the `config.properties` accordingly.
