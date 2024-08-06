DELETE FROM PropertyOwner WHERE id = 1;

INSERT INTO PropertyOwner (vat_number, name, surname, address, phone_number, email, username, password)
VALUES 
('23534543', 'Panagiotis', 'Kostopoulos', 'Paris', '69349234', 'panagiotis@gmail.com', 'Pankos', '2222'),
('235345', 'Spiros', 'Katiforis', 'London', '69234324324', 'spiros@gmail.com', 'Spikat', '3333'),
('324123', 'Stefanos', 'Ountrakis', 'New York', '69324234234', 'stefanos@gmail.com', 'Stevoun', '1234');

-- Admin 
INSERT INTO Admin (username, email, password)
VALUES 
('Katif', 'katiforis@gmail.com', '1212'),
('Pankost', 'kostop@gmail.com', '1111'),
('Stephen', 'stef6754@gmail.com', '1234');

-- Property 
INSERT INTO Property (address, property_type, owner_id)
VALUES 
('Chania', 'Hotel', (SELECT id FROM PropertyOwner WHERE username = 'Stevoun')),
('Chania', 'Apartment', (SELECT id FROM PropertyOwner WHERE username = 'Stevoun')),
('Athens', 'Hotel', (SELECT id FROM PropertyOwner WHERE username = 'Spikat')),
('Athens', 'Apartment', (SELECT id FROM PropertyOwner WHERE username = 'Spikat')),
('Patra', 'Hotel', (SELECT id FROM PropertyOwner WHERE username = 'Pankos')),
('Patra', 'Apartment', (SELECT id FROM PropertyOwner WHERE username = 'Pankos'));

-- PropertyRepair 
INSERT INTO PropertyRepair (repair_type, description, repair_date, status, property_id, owner_id)
VALUES 
('Plumbing', 'Losing water from toilet', '2024-08-06', 'PENDING', 
    (SELECT id FROM Property WHERE address = 'Chania' AND property_type = 'Hotel'), 
    (SELECT id FROM PropertyOwner WHERE username = 'Stevoun')),
('Electricity', 'no lights in reception', '2024-08-06', 'PENDING', 
    (SELECT id FROM Property WHERE address = 'Athens' AND property_type = 'Hotel'), 
    (SELECT id FROM PropertyOwner WHERE username = 'Spikat')),
('Repair-Building', 'Someone broke a whole wall', '2024-08-06', 'PENDING', 
    (SELECT id FROM Property WHERE address = 'Patra' AND property_type = 'Hotel'), 
    (SELECT id FROM PropertyOwner WHERE username = 'Pankos'));