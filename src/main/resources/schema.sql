CREATE EXTENSION IF NOT EXISTS postgis;

CREATE TABLE IF NOT EXISTS mushroom_location (
                                   id BIGSERIAL PRIMARY KEY,
                                   description TEXT,
                                   location GEOMETRY(Point, 4326) -- WGS84
                                   
);
