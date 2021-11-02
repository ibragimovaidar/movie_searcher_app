DROP TRIGGER avg_rating ON review;

DROP FUNCTION update_avg_rating;

CREATE FUNCTION update_avg_rating() RETURNS TRIGGER AS $avg_rating$
BEGIN
    IF (TG_OP = 'INSERT') THEN
        UPDATE movie SET average_rating = (SELECT (sum(rating)+ NEW.rating)/(count(rating)+1) FROM review WHERE movie_id = NEW.movie_id)
            WHERE id = NEW.movie_id;
        RETURN NEW;
    END IF;
    RETURN NULL;
END;
$avg_rating$ LANGUAGE plpgsql;

CREATE TRIGGER avg_rating
    AFTER INSERT ON review
    FOR EACH ROW EXECUTE PROCEDURE update_avg_rating();