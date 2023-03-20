INSERT INTO imagedata (id, name, type, uri, path)
VALUES (1, 'default_profile_picture.png', 'image/png', '/static/images/default_profile_picture.png', 'E:\\Datos\\noOnedrive\\back-springboot\\src\\main\\resources\\static\\images\\default_profile_picture.png')
ON DUPLICATE KEY UPDATE
id = 1,
name = 'default_profile_picture.png',
type = 'image/png',
uri = '/static/images/default_profile_picture.png',
path = 'E:\\Datos\\noOnedrive\\back-springboot\\src\\main\\resources\\static\\images\\default_profile_picture.png';