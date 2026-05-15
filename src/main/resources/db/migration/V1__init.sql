CREATE TABLE optimization_request (
                                      id UUID PRIMARY KEY,
                                      max_volume INTEGER NOT NULL,
                                      total_volume INTEGER NOT NULL,
                                      total_revenue INTEGER NOT NULL,
                                      created_at TIMESTAMP NOT NULL
);

CREATE TABLE selected_shipment (
                                   id BIGSERIAL PRIMARY KEY,
                                   request_id UUID NOT NULL REFERENCES optimization_request(id),
                                   name VARCHAR(255) NOT NULL,
                                   volume INTEGER NOT NULL,
                                   revenue INTEGER NOT NULL
                                       selected   BOOLEAN      NOT NULL,

                                   CONSTRAINT fk_shipment_request
                                       FOREIGN KEY (request_id)
                                           REFERENCES optimization_request (id)
                                           ON DELETE CASCADE
);

CREATE INDEX idx_shipment_request_id
    ON shipment (request_id);