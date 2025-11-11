-- ED-175-AWS

INSERT INTO genre (genre_name, genre_active) VALUES
('Rock', TRUE),
('Pop', TRUE),
('Hip-Hop', TRUE),
('Jazz', TRUE),
('Classical', TRUE),
('Electronic', TRUE),
('Indie', TRUE),
('Metal', TRUE),
('Country', TRUE),
('Soul', TRUE),
('News', TRUE),
('Tech', TRUE),
('Comedy', TRUE),
('True Crime', TRUE),
('Society', TRUE),
('History', TRUE),
('Science', TRUE),
('Sports', TRUE),
('Business', TRUE),
('Education', TRUE),
('Vlog', TRUE),
('Gaming', TRUE),
('How-To', TRUE),
('Documentary', TRUE),
('Travel', TRUE),
('Music Video', TRUE),
('Animation', TRUE),
('Review', TRUE),
('Shorts', TRUE),
('Live', TRUE);

-- ED-52-AWS
INSERT INTO genre_mapping (media_type, media_id, genre_id) VALUES
('SONG', 1, 1),   -- Rock
('SONG', 2, 2),   -- Pop
('SONG', 3, 3),   -- Hip-Hop
('SONG', 4, 4),   -- Jazz
('SONG', 5, 5),   -- Classical
('SONG', 6, 6),   -- Electronic
('SONG', 7, 7),   -- Indie
('SONG', 8, 8),   -- Metal
('SONG', 9, 9),   -- Country
('SONG', 10, 10); -- Soul

INSERT INTO genre_mapping (media_type, media_id, genre_id) VALUES
('PODCAST_EPISODE', 1, 11),  -- News
('PODCAST_EPISODE', 2, 12),  -- Tech
('PODCAST_EPISODE', 3, 13),  -- Comedy
('PODCAST_EPISODE', 4, 14),  -- True Crime
('PODCAST_EPISODE', 5, 15),  -- Society
('PODCAST_EPISODE', 6, 16),  -- History
('PODCAST_EPISODE', 7, 17),  -- Science
('PODCAST_EPISODE', 8, 18),  -- Sports
('PODCAST_EPISODE', 9, 19),  -- Business
('PODCAST_EPISODE', 10, 20); -- Education

INSERT INTO genre_mapping (media_type, media_id, genre_id) VALUES
('VIDEO_CLIP', 1, 21),  -- Vlog
('VIDEO_CLIP', 2, 22),  -- Gaming
('VIDEO_CLIP', 3, 23),  -- How-To
('VIDEO_CLIP', 4, 24),  -- Documentary
('VIDEO_CLIP', 5, 25),  -- Travel
('VIDEO_CLIP', 6, 26),  -- Music Video
('VIDEO_CLIP', 7, 27),  -- Animation
('VIDEO_CLIP', 8, 28),  -- Review
('VIDEO_CLIP', 9, 29),  -- Shorts
('VIDEO_CLIP', 10, 30); -- Live