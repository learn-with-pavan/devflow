CREATE TABLE projects (

    id BIGSERIAL PRIMARY KEY,

    name VARCHAR(150) NOT NULL,

    slug VARCHAR(255) NOT NULL UNIQUE,

    description TEXT,

    status VARCHAR(30) NOT NULL,

    priority VARCHAR(30) NOT NULL,

    color VARCHAR(20),

    icon VARCHAR(100),

    start_date DATE,

    end_date DATE,

    archived BOOLEAN NOT NULL DEFAULT FALSE,

    workspace_id BIGINT NOT NULL,

    owner_id BIGINT NOT NULL,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_project_workspace
        FOREIGN KEY (workspace_id)
        REFERENCES workspaces(id),

    CONSTRAINT fk_project_owner
        FOREIGN KEY (owner_id)
        REFERENCES users(id)
);